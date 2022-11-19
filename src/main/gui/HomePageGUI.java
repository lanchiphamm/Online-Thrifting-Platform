package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import model.Platform;
import model.Product;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

public class HomePageGUI implements ActionListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;
    private static final String JSON_STORE = "./data/platform.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private Platform platform;
    private User user;

    private JFrame frame;
    private JList<String> usersList;
    private JLabel success;
    private ArrayList<Integer> index = new ArrayList<>(2);
    private DefaultListModel cartListModel;
    private JButton choose;

    private DefaultListModel usersModel;

    private JSplitPane browserTab;
    private JPanel cart;
    private JPanel products;

    // EFFECT: Constructs the Main frame composing of 3 tabs: Browser, User Profile, Settings
    // Browser tab is this class
    public HomePageGUI(LoginGUI loginGUI) {
        this.user = loginGUI.getUser();
        this.platform = loginGUI.getPlatform();
        frame = new JFrame("OnlineThrift");
        frame.setBounds(300, 200, WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tab = new JTabbedPane();
        loadBrowserTab();
        tab.addTab("Browser", browserTab);

        // CREATING USER TAB
        UserProfileGUI userProfileGUI = new UserProfileGUI(loginGUI, this);
        tab.addTab("User Profile", userProfileGUI.loadUserProfileTab());

        SettingsGUI settingsGUI = new SettingsGUI(loginGUI, this);
        tab.addTab("Settings", settingsGUI.loadSettingsTab());

        frame.getContentPane().add(tab, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public Platform getPlatform() {
        return this.platform;
    }

    public User getUser() {
        return this.user;
    }

    public void hideFrame() {
        frame.setVisible(false);
    }

    // EFFECTS: display components of the Browser Tab
    public void loadBrowserTab() {
        loadProductsPanel();
        loadCartPanel();
        browserTab = new JSplitPane(JSplitPane.VERTICAL_SPLIT, products, cart);
        browserTab.setDividerLocation(HEIGHT / 5 * 2);
    }

    // MODIFIES: this
    // EFFECTS: load components for the Browser Tab where all products on the platform is displayed
    public void loadProductsPanel() {
        products = new JPanel(new BorderLayout());
        JLabel productsLabel = new JLabel("Products");
        products.add(productsLabel, BorderLayout.PAGE_START);

        success = new JLabel("");
        success.setBounds(100, 100, 200, 200);
        products.add(success, BorderLayout.PAGE_END);

        choose = new JButton("Add to Cart");
        choose.addActionListener(this);
        choose.setPreferredSize(new Dimension(100, 30));
        products.add(choose, BorderLayout.LINE_END);

        usersList = new JList<>();
        reload();
        usersList.setModel(usersModel);
        JScrollPane usersScroll = new JScrollPane(usersList);
        usersList.setFixedCellHeight(20);
        usersList.setPreferredSize(new Dimension(500, 300));
        handleProductSelection();
        products.add(usersScroll, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: load all products on the platform
    public void reload() {
        usersModel = new DefaultListModel<>();
        usersModel.removeAllElements();
        List<User> listUser = platform.getUsersOnPlatform();
        for (int i = 1; i <= listUser.size(); i++) {
            User user = listUser.get(i - 1);
            ArrayList<Product> listProducts = user.getProducts();
            if (listProducts.size() > 0) {
                System.out.println(user.getName());
                for (int j = 1; j <= listProducts.size(); j++) {
                    Product p = listProducts.get(j - 1);
                    String item = i + "." + j + " ";
                    if (user == this.user) {
                        System.out.println("how did it get here");
                        item += "User: " + user.getName() + " - Yourself";
                    } else {
                        item += "User: " + user.getName();
                    }
                    item += " || " + p.printProduct();
                    System.out.println(item);
                    usersModel.addElement(item);
                }
            }
        }
    }

    // EFFECTS: handle mouse event when a product is chosen in Products panel
    public void handleProductSelection() {
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                index.clear();
                if (e.getClickCount() == 1) {
                    String selectedItem = usersList.getSelectedValue();
                    index.add(Integer.parseInt(selectedItem.substring(0,1)));
                    index.add(Integer.parseInt(selectedItem.substring(2,3)));
                }
            }
        };
        usersList.addMouseListener(mouseListener);
    }

    // MODIFIES: this
    // EFFECTS: load components for the Browser Tab where all products the user have added to cart
    // is displayed
    public void loadCartPanel() {
        cart = new JPanel();
        JLabel cartLabel = new JLabel("Your cart");
        cart.add(cartLabel);

        cartListModel = new DefaultListModel();
        for (Product p : user.getCart()) {
            cartListModel.addElement(p.printProduct());
        }
        JList<String> cartList = new JList<>(cartListModel);
        JScrollPane cartScroll = new JScrollPane(cartList);
        cart.add(cartScroll);
    }

    public DefaultListModel getUsersModel() {
        return this.usersModel;
    }

    // MODIFIES: this
    // EFFECTS: handle when a product from platform is added to user's cart
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg;

        if (e.getSource() == choose && !index.isEmpty()) {
            Product productToAdd;
            User buyer = platform.getUsersOnPlatform().get(index.get(0) - 1);
            productToAdd = buyer.getProducts().get(index.get(1) - 1);

            if (this.user == buyer) {
                msg = "You can't add your own items to your cart";
                success.setText(msg);
            } else {
                if (!user.getCart().contains(productToAdd)) {
                    msg = "Item " + index.get(0) + "." + index.get(1) + " added to your cart!";
                    success.setText(msg);
                    user.addToCart(productToAdd);
                    cartListModel.addElement(productToAdd.printProduct());
                } else {
                    msg = "This item is already added to your cart";
                    success.setText(msg);
                }
            }
        }
    }
}
