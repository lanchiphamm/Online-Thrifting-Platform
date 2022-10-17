package ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import model.Platform;
import model.Product;
import model.ProductType;
import model.User;

public class ShoppingUI {

    private static final String REGISTER_COMMAND = "sign up";
    private static final String BROWSE_COMMAND = "browse";
    private static final String SELL_COMMAND = "sell";
    private static final String ADD_TO_CART_COMMAND = "buy";
    private static final String CART_COMMAND = "cart";
    private static final String ADD_TO_PROFILE_COMMAND = "add";
    private static final String PROFILE_COMMAND = "profile";
    private static final String REMOVE_COMMAND = "remove";
    private static final String BACK_COMMAND = "back";
    private static final String QUIT_COMMAND = "quit";


    private Scanner input;
    private boolean runProgram;
    private int caseUI = 1;  // 1 for main, 2 for buy, 3 for sell
    private User user;
    private Platform platform;

    public ShoppingUI(Platform platform) {
        input = new Scanner(System.in);
        runProgram = true;
        this.platform = platform;
    }

    // EFFECTS: parses user input until they quit
    public void runUI() {
        printMainMenu();
        String str;

        while (runProgram) {
            if (input.hasNext()) {
                str = input.nextLine();
                str = appropriateInput(str);
                if (caseUI == 1) {
                    handleMainMenuInput(str);
                } else if (caseUI == 2) {
                    handleBuyInput(str);
                } else {
                    handleSellInput(str);
                }
            }
        }
    }

    // EFFECTS: handle navigation commands: back, quit
    private void handleNavigateInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case BACK_COMMAND:
                    caseUI = 1;
                    printMainMenu();
                    break;
                case QUIT_COMMAND:
                    runProgram = false;
                    break;
                default:
                    System.out.println("Sorry, I didn't understand that command :( Please try again.");
                    break;
            }
        }
    }

    // EFFECTS: handle main menu commands
    private void handleMainMenuInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case REGISTER_COMMAND:
                    handleSignUp();
                    break;
                case BROWSE_COMMAND:
                    printBuyMenu();
                    break;
                case SELL_COMMAND:
                    printSellMenu();
                    break;
                default:
                    handleNavigateInput(str);
                    break;
            }
        }
    }

    // EFFECTS: print main menu
    private void printMainMenu() {
        if (user == null) {
            System.out.println("You must sign up first to shop.");
            System.out.println("Enter '" + REGISTER_COMMAND + "' to sign up as an user.\n");
        } else {
            System.out.println("Welcome " + user.getName() + "! \nLet's explore OnlineThrift:");
            System.out.println("Enter '" + BROWSE_COMMAND + "' to start thrifting.");
            System.out.println("Enter '" + SELL_COMMAND + "' to start selling.\n");
        }
        quit();
    }

    // MODIFIES: this
    // EFFECTS: sign up user to the platform
    public void handleSignUp() {
        System.out.println("To create an OnlineThrift account, "
                + "please fill in the following information.\n"
                + "Enter your username:");

        String name = input.nextLine();
        user = new User(name);
        platform.signUpUser(user);
        System.out.println("Thank you for signing up with us!\n");
        printMainMenu();
    }

    // EFFECTS: print menu when using is browsing
    public void printBuyMenu() {
        caseUI = 2;
        System.out.println("Here is a list of current Users and their Products on our platform:");
        printUsersOnPlatform();
        System.out.println("\nEnter '" + ADD_TO_CART_COMMAND + "' to add products you want to your shopping cart.\n"
                + "Enter '" + CART_COMMAND + "' to view products you have chosen.");
        returnToMainMenu();
        quit();
    }


    // EFFECTS: print list of users and their products
    private void printUsersOnPlatform() {
        ArrayList<User> listUser = platform.getUsersOnPlatform();
        for (int i = 1; i <= listUser.size(); i++) {
            User user = listUser.get(i - 1);
            ArrayList<Product> listProducts = user.getProducts();
            if (listProducts.size() > 0) {
                System.out.println("User: " + user.getName());
                for (int j = 1; j <= listProducts.size(); j++) {
                    Product p = listProducts.get(j - 1);
                    System.out.println(i + "." + j + " " + printProduct(p));
                }
            }
        }
    }

    // EFFECTS: handle user's inputs when they want to buy
    private void handleBuyInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case ADD_TO_CART_COMMAND:
                    handleAddProducts();
                    break;
                case CART_COMMAND:
                    handleViewCart();
                    break;
                default:
                    handleNavigateInput(str);
                    break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: add product of user's choice to their own cart
    private void handleAddProducts() {
        System.out.println("Please enter the index (e.g. '1.1') of the product you would like to buy: ");
        String index = input.nextLine();
        int u;
        int p;
        while (true) {
            ArrayList<Integer> indexes = validInputForProduct(index);
            u = indexes.get(0);
            p = indexes.get(1);
            if (1 <= u && u <= platform.numOfUsersOnPlatform() - 1) {
                User tmp = platform.getUsersOnPlatform().get(u - 1);
                if (1 <= p && p <= tmp.getProducts().size()) {
                    break;
                }
            }
            System.out.println("Item not found. Please re-enter index: ");
            index = input.nextLine();
        }
        User seller = platform.getUsersOnPlatform().get(u - 1);
        Product productAdded = seller.getProducts().get(p - 1);
        user.addToCart(productAdded);
        System.out.println(printProduct(productAdded) + " is added to your cart");
        printBuyMenu();
    }

    // EFFECTS: check for valid input when choosing products to add to cart;
    //          return list of integer if input is valid
    private ArrayList<Integer> validInputForProduct(String s) {
        ArrayList<Integer> indexes = new ArrayList<>(2);
        boolean valid = Pattern.matches("[0-9]+\\.[0-9]+", s);
        while (!valid) {
            System.out.println("Item not found. Please re-enter index: ");
            s = input.nextLine();
            valid = Pattern.matches("[0-9]+\\.[0-9]+", s);
        }

        String[] tmp = s.split("[.]", 0);
        for (String str : tmp) {
            indexes.add(Integer.parseInt(str));
        }

        return indexes;
    }

    // EFFECTS: print items in user's cart
    private void handleViewCart() {
        System.out.println("Your shopping cart (" + user.getCart().size() + " items):");
        for (int i = 1; i <= user.getCart().size(); i++) {
            Product p = user.getCart().get(i);
            System.out.println(i + ". " + printProduct(p));
        }
    }


    // EFFECTS: print menu when user choose to start selling
    public void printSellMenu() {
        caseUI = 3;
        System.out.println("Let's begin your business career!");
        System.out.println("Enter '" + ADD_TO_PROFILE_COMMAND + "' to add products you want to sell\n"
                + "Enter '" + PROFILE_COMMAND + "' to view your profile as a seller\n"
                + "Enter '" + REMOVE_COMMAND + "' to remove products you no longer sell");
    }

    // EFFECTS: handle user's inputs when selling
    private void handleSellInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case ADD_TO_PROFILE_COMMAND:
                    handleAddProductstoProfile();
                    break;
                case PROFILE_COMMAND:
                    handleViewProfile();
                    break;
                case REMOVE_COMMAND:
                    handleRemoveProductfromProfile();
                    break;
                default:
                    handleNavigateInput(str);
                    break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: add product with specifications from user to user's profile
    private void handleAddProductstoProfile() {
        System.out.println("Please specify the following details for your product:\n"
                + "1. Enter one of the following Product Types:\n"
                + "shirt\n"
                + "outerwear\n"
                + "pants\n"
                + "shoes\n"
                + "accessories");
        String s = input.nextLine();
        ProductType pt = ProductType.valueOf(s.toUpperCase());
        System.out.println("2. Specify the price for this product (CAD): ");
        int price = input.nextInt();
        input.nextLine();
        System.out.println("3. Specify any extra information for this product: ");
        String info = input.nextLine();

        Product p = new Product(pt, price, info);
        user.addProduct(p);
        System.out.println(printProduct(p) + " has been added to your profile.");
        printSellMenu();
    }

    // EFFECTS: print user's profile - username and products they sell
    private void handleViewProfile() {
        System.out.println("User: " + user.getName());
        ArrayList<Product> listProducts = user.getProducts();
        if (listProducts.isEmpty()) {
            System.out.println("Your profile is empty!");
        } else {
            for (int i = 1; i <= listProducts.size(); i++) {
                Product p = listProducts.get(i - 1);
                System.out.println(i + ". " + printProduct(p));
            }
        }
    }

    private void handleRemoveProductfromProfile() {
        if (user.getProducts().isEmpty()) {
            System.out.println("You currently have no products.");
        } else {
            System.out.println("Specify the index for the product you are no longer selling: ");
            int index = input.nextInt();
            while (true) {
                if (index > 0 && index <= user.getProducts().size()) {
                    Product p = user.getProducts().get(index - 1);
                    user.removeItem(p);
                    System.out.println("Item removed.");
                    break;
                }
                System.out.println("Item not found. Please re-enter index: ");
                index = input.nextInt();
            }
        }
    }

    // EFFECTS: stops receiving user input
    public void quitProgram() {
        input.close();
    }

    // Extra functions
    // EFFECTS: print description of Product p
    private String printProduct(Product p) {
        return p.getType() + ": $" + p.getPrice() + " - " + p.getInfo();
    }

    // EFFECTS: remove whitespace around s and make inputs uniformed
    private String appropriateInput(String s) {
        s = s.toLowerCase();
        s = s.trim();
        return s;
    }

    // EFFECTS: print instructions to return to main Menu
    private void returnToMainMenu() {
        System.out.println("To go back to the main Menu, enter '" + BACK_COMMAND + "'.");
    }

    // EFFECTS: print instructions to quit program
    private void quit() {
        System.out.println("To quit at any time, enter '" + QUIT_COMMAND + "'.");
    }
}
