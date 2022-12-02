package gui;

import static model.ProductType.ACCESSORIES;
import static model.ProductType.OUTERWEAR;
import static model.ProductType.PANTS;
import static model.ProductType.SHIRT;
import static model.ProductType.SHOES;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.EventLog;
import model.Platform;
import model.Product;
import model.User;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

/***
 * Represents a Login page for user to login/register their
 * account to the platform
 */
public class LoginGUI implements ActionListener {
    private static final String JSON_STORE = "./data/platform.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private Platform platform;
    private User user;

    public static final int WIDTH = 400;
    public static final int HEIGHT = 250;
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();
    private JTextField userText;
    private JPasswordField passwordText;
    private JLabel success;
    private JButton login;
    private JButton register;

    // EFFECTS: Constructs a Login Page when first running application
    public LoginGUI() {
        frame.setBounds(500, 250, WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        File sys = new File(JSON_STORE);
        if (!sys.exists()) {
            loadFixedPlatform();
        } else {
            loadPlatform();
        }

        EventLog.getInstance().clear();
        loadInputFields();
        buttonDisplay();

        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Display fields for users to input their login information
    public void loadInputFields() {
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);
    }

    // MODIFIES: this
    // EFFECTS: Display login/register button and generate field for displaying message
    public void buttonDisplay() {
        login = new JButton("Login");
        login.setBounds(10, 80, 80, 25);
        login.addActionListener(this);
        panel.add(login);

        register = new JButton("Register");
        register.setBounds(100, 80, 80, 25);
        register.addActionListener(this);
        panel.add(register);

        success = new JLabel("");
        success.setBounds(10, 100, 300, 25);
        panel.add(success);
    }

    // MODIFIES: this
    // EFFECTS: Handle login/register by User.
    // If choose register then check if account has already existed. If existed then require User
    // login, else register User as a new user and add user to platform.
    // If choose login then check if account has already existed. If existed then load
    // previous state of account, else require User to register.
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userText.getText();
        String password = passwordText.getText();

        if (e.getSource() == register) {
            if (platform.getUsersOnPlatform().contains(new User(username, password))) {
                success.setText("This account already exists. Please login");
            } else {
                success.setText("success");
                user = new User(username, password);
                platform.signUpUser(user);
                changeToMain();
            }
        } else if (e.getSource() == login) {
            if (platform.getUsersOnPlatform().contains(new User(username, password))) {
                for (User u : platform.getUsersOnPlatform()) {
                    if (u.getName().equals(username) && u.getPassword().equals(password)) {
                        user = u;
                    }
                }
                changeToMain();
            } else {
                success.setText("Account does not exist. Please register or login again.");
            }
        }
    }

    // EFFECTS: change to Main frame when login/register is successful.
    private void changeToMain() {
        frame.setVisible(false);
        new HomePageGUI(this);
    }

    // MODIFIES: this
    // EFFECTS: loads platform from file
    private void loadPlatform() {
        try {
            platform = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: save the platform to file
    public void savePlatform() {
        try {
            jsonWriter.open();
            jsonWriter.write(platform);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public User getUser() {
        return this.user;
    }

    public Platform getPlatform() {
        return this.platform;
    }

    public void setUser(User u) {
        this.user = u;
    }

    // MODIFIES: this
    // EFFECTS: load a fixed platform for the 1st time running application
    public void loadFixedPlatform() {
        User u1 = new User("andrew_01", "12345678");
        User u2 = new User("vintage_thrifter", "hahahaha");
        this.platform.signUpUser(u1);
        this.platform.signUpUser(u2);

        Product p1 = new Product(SHOES, 100, "Nike AF1");
        Product p2 = new Product(SHIRT, 50, "Nike Sportswear");
        Product p3 = new Product(ACCESSORIES, 30, "Carhartt Beanie");
        Product p4 = new Product(OUTERWEAR, 60, "Black hoodie");
        Product p5 = new Product(PANTS, 70, "ZARA Work Jeans");
        u1.addProduct(p1);
        u1.addProduct(p3);
        u2.addProduct(p4);
        u2.addProduct(p2);
        u2.addProduct(p5);
        savePlatform();
    }

    public static void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }
}

