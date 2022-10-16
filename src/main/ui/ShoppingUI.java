package ui;

import java.util.Scanner;
import model.Platform;
import model.User;

public class ShoppingUI {

    private static final String REGISTER_COMMAND = "sign up";
    private static final String USERS_COMMAND = "users";
    private static final String PRODUCTS_COMMAND = "products";
    private static final String BUY_COMMAND = "buy";
    private static final String SELL_COMMAND = "sell";
    private static final String CART_COMMAND = "cart";
    private static final String PROFILE_COMMAND = "profile";
    private static final String QUIT_COMMAND = "quit";

    private Scanner input;
    private boolean runProgram;
    private User user;
    private Platform platform;

    public ShoppingUI(Platform platform) {
        input = new Scanner(System.in);
        runProgram = true;
        this.platform = platform;
    }

    public void runUI() {
        printMenu();
        String str;

        while (runProgram) {
            if (input.hasNext()) {
                str = input.nextLine();
                str = approriateInput(str);
                handleMainInput(str);
            }
        }
    }

    private void handleMainInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case REGISTER_COMMAND:
                    handleSignUp();
                    break;
                case BUY_COMMAND:
                    handleBuy();
                    break;
                case SELL_COMMAND:
                    handleSell();
                    break;
                case QUIT_COMMAND:
                    runProgram = false;
                    break;
                default:
                    System.out.println("Sorry, I didn't understand that command :( PLease try again.");
                    break;
            }
        }
    }

    private void printMenu() {
        if (user == null) {
            System.out.println("You must sign up first to shop.");
            System.out.println("Enter '" + REGISTER_COMMAND + "' to sign up as an user");
        } else {
            System.out.println("Welcome " + user.getName() + "! \nLet's explore OnlineThrift:");
            System.out.println("Enter '" + BUY_COMMAND + "' to start thrifting.");
            System.out.println("Enter '" + SELL_COMMAND + "' to start selling.");
        }
        System.out.println("To quit at any time, enter '" + QUIT_COMMAND + "'");
    }



    public void handleSignUp() {

    }

    // Start Buying and can view cart
    public void handleBuy() {

    }

    // start selling and can view their own profile
    public void handleSell() {

    }

    private String approriateInput(String s) {
        s = s.toLowerCase();
        s = s.trim();
        return s;
    }
}
