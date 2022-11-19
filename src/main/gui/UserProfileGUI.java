package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.Platform;
import model.Product;
import model.ProductType;
import model.User;

public class UserProfileGUI implements ActionListener {
    HomePageGUI hp;
    User user;
    Platform platform;
    JSplitPane userProfileTab;
    JPanel addProduct;
    JLabel pictureType;
    JLabel priceLabel;
    JTextField price;
    JLabel descriptionLabel;
    JTextField description;
    JButton add;
    JList userProductsList;
    DefaultListModel userProductsModel;
    String typeChosen;
    JRadioButton shirtButton;
    JRadioButton outerButton;
    JRadioButton pantsButton;
    JRadioButton shoesButton;
    JRadioButton accessButton;

    JPanel userInfo;
    JPanel userProfileProducts;
    JSplitPane mainUserProfile;
    JButton remove;

    // EFFECTS: Constructs the User Profile tab, displaying user profile's information, products
    // they have added, and a panel for user to specify the product they want to add
    public UserProfileGUI(LoginGUI loginGUI, HomePageGUI hp) {
        this.hp = hp;
        this.user = loginGUI.getUser();
        this.platform = loginGUI.getPlatform();

        loadUserInfoPanel();

        userProfileProducts = new JPanel();
        loadUserProductsPanel();

        mainUserProfile = new JSplitPane(JSplitPane.VERTICAL_SPLIT, userInfo, userProfileProducts);

        addProduct = new JPanel();
        loadAddProductPanel();

        userProfileTab = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainUserProfile, addProduct);
    }

    // MODIFIES: this
    // EFFECTS: display all user information - name, number of products in their cart/profile, the
    // products they have added
    public void loadUserInfoPanel() {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image profilePic = t.getImage("./data/profile.png");

        userInfo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(profilePic, 30, 30, 150, 150, null);
                g.drawString("User: " + user.getName(), 200, 100);
                g.drawString("Shopping Cart: " + user.getCart().size() + " item(s)", 200, 130);
                g.drawString("Products: " + user.getProducts().size() + " item(s)", 200, 160);
            }
        };

        JLabel profile = new JLabel("Your Profile", SwingConstants.CENTER);
        userInfo.add(profile);
    }

    // MODIFIES: this
    // EFFECTS: display the products they have added to their profile
    public void loadUserProductsPanel() {
        userProductsModel = new DefaultListModel();
        for (Product p : user.getProducts()) {
            userProductsModel.addElement(p.printProduct());
        }
        userProductsList = new JList(userProductsModel);
        JScrollPane userScrollProduct = new JScrollPane(userProductsList);
        userScrollProduct.setPreferredSize(new Dimension(200, 100));

        userProfileProducts.add(userScrollProduct);

        loadRemoveButton();
    }

    // MODIFIES: this
    // EFFECTS: display and handle Remove button for user to remove products they are no longer selling
    public void loadRemoveButton() {
        remove = new JButton("Remove");
        remove.setSize(50, 50);
        userProfileProducts.add(remove);
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedItem = (String) userProductsList.getSelectedValue();
                if (e.getClickCount() == 1 && !user.getProducts().isEmpty() && selectedItem != null) {
                    Product p = getProductFromString(selectedItem);
                    System.out.println("hi" + user.getUserKey() + user.getProducts().indexOf(p));
                    hp.getUsersModel().removeElement(user.getUserKey() + "."
                            +  (user.getProducts().indexOf(p) + 1) + " User: " + user.getName()
                            + " - Yourself" + " || " + p.printProduct());
                    user.removeItem(p);
                    userProductsModel.removeElement(userProductsList.getSelectedValue());
                }
            }
        };
        remove.addMouseListener(mouseListener);
    }

    // MODIFIES: this
    // EFFECTS: load components for the Panel to specify the information of the product
    // user want to sell
    public void loadAddProductPanel() {
        addProduct.setLayout(new BoxLayout(addProduct, BoxLayout.PAGE_AXIS));
        addProduct.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel addYourProduct = new JLabel("Add your Products");
        addProduct.add(addYourProduct);

        loadTypeButtons();
        displayButtons();
        loadTextFields();
        addProductButton();
    }

    // MODIFIES: this
    // EFFECTS: load buttons for user to choose their product's TYPE
    public void loadTypeButtons() {
        shirtButton = new JRadioButton("SHIRT");
        shirtButton.setActionCommand("shirt");
        shirtButton.setSelected(true);

        outerButton = new JRadioButton("OUTERWEAR");
        outerButton.setActionCommand("outerwear");

        pantsButton = new JRadioButton("PANTS");
        pantsButton.setActionCommand("pants");

        accessButton = new JRadioButton("ACCESSORIES");
        accessButton.setActionCommand("accessories");

        shoesButton = new JRadioButton("SHOES");
        shoesButton.setActionCommand("shoes");

        ButtonGroup group = new ButtonGroup();
        group.add(shirtButton);
        group.add(pantsButton);
        group.add(outerButton);
        group.add(shoesButton);
        group.add(accessButton);
    }

    // MODIFIES: this
    // EFFECTS: display TYPE buttons loaded above
    public void displayButtons() {
        shirtButton.addActionListener(this);
        outerButton.addActionListener(this);
        pantsButton.addActionListener(this);
        shoesButton.addActionListener(this);
        accessButton.addActionListener(this);

        pictureType = new JLabel(new ImageIcon(getScaledImage("shirt")));
        pictureType.setPreferredSize(new Dimension(100, 100));
        addProduct.add(shirtButton);
        addProduct.add(pantsButton);
        addProduct.add(outerButton);
        addProduct.add(shoesButton);
        addProduct.add(accessButton);
        addProduct.add(pictureType);
    }

    // MODIFIES: this
    // EFFECTS: load text fields for user to input their specification for the product they sell
    public void loadTextFields() {
        priceLabel = new JLabel("Price");
        priceLabel.setBounds(10, 20, 80, 25);
        addProduct.add(priceLabel);

        price = new JTextField();
        price.setBounds(100, 20, 165, 25);
        addProduct.add(price);

        descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(10, 50, 80, 25);
        addProduct.add(descriptionLabel);

        description = new JTextField();
        description.setBounds(100, 50, 165, 25);
        addProduct.add(description);
    }

    // MODIFIES: this
    // EFFECTS: display and handle when user want to add a product to their profile
    public void addProductButton() {
        add = new JButton(new AbstractAction("Add Product") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductType type = ProductType.valueOf(typeChosen.toUpperCase());
                int priceValue = Integer.parseInt(price.getText());
                String des = description.getText();
                Product p = new Product(type, priceValue, des);
                user.addProduct(p);
                userProductsModel.addElement(p.printProduct());
                hp.getUsersModel().addElement(p.getProductKey() + " User: " + user.getName()
                        + " - Yourself" + " || " + p.printProduct());
            }
        });
        add.setBounds(10, 80, 80, 25);
        add.addActionListener(this);
        addProduct.add(add);
    }

    // EFFECTS: return this entire tab
    public JSplitPane loadUserProfileTab() {
        return userProfileTab;
    }

    // EFFECTS: helper function to generate a product from valid String
    public Product getProductFromString(String s) {
        ProductType t = ProductType.valueOf(s.substring(0, s.indexOf(":")));
        int p = Integer.parseInt(s.substring(s.indexOf("$") + 1, s.indexOf("-") - 1));
        String d = s.substring(s.indexOf("-") + 2);
        return new Product(t, p, d);
    }

    // EFFECTS: helper function to scale image for TYPE button
    public Image getScaledImage(String s) {
        ImageIcon imageIcon = new ImageIcon("./data/" + s + ".png");
        Image image = imageIcon.getImage();
        return image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    }

    // MODIFIES: this
    // EFFECTS: display different images according to button chose
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == shirtButton || e.getSource() == outerButton
                || e.getSource() == pantsButton || e.getSource() == shoesButton
                || e.getSource() == accessButton) {
            pictureType.setBackground(new Color(100, 20, 70));
            ImageIcon imageIcon = new ImageIcon("./data/" + e.getActionCommand() + ".png");
            Image image = imageIcon.getImage();
            Image newImg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            pictureType.setIcon(new ImageIcon(newImg));
            System.out.println("./data/" + e.getActionCommand() + ".png");
            typeChosen = e.getActionCommand();
        }
    }
}
