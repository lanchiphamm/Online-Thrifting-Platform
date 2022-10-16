package ui;

import static model.ProductType.ACCESSORIES;
import static model.ProductType.OUTERWEAR;
import static model.ProductType.PANTS;
import static model.ProductType.SHIRT;
import static model.ProductType.SHOES;

import model.Platform;
import model.Product;
import model.User;

public class ShoppingApp {
    private Platform platform;
    private User u1;
    private User u2;

    public ShoppingApp() {
        platform = new Platform();
        loadUsers();
        loadUsersProducts();
    }

    public static void main(String[] args) {
        ShoppingApp shoppingApp = new ShoppingApp();
        ShoppingUI shoppingUI = new ShoppingUI(shoppingApp.getPlatform());
        System.out.println("Welcome to OnlineThrift!\n");

        shoppingUI.runUI();

        System.out.println("Thank you for shopping with OnlineThrift!");
    }

    public Platform getPlatform() {
        return platform;
    }

    private void loadUsers() {
        u1 = new User("andrew_01");
        u2 = new User("vintage_thrifter");

        platform.signUpUser(u1);
        platform.signUpUser(u2);
    }

    private void loadUsersProducts() {
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
    }

}
