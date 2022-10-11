package model;

import java.util.ArrayList;

// Represents a user on this platform with a username and a list of products they sell
public class User {
    private String name;                    // Username of profile
    private ArrayList<Product> products;    // User's list of products
    private ShoppingCart cart;              // keep track of purchases

    // REQUIRES: accountName is not an empty string
    // EFFECTS: name on account is set to accountName; an empty list of Products
    //          is initialised
    public User(String accountName) {
        this.name = accountName;
        this.products = new ArrayList<Product>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    // USER STORY #1: add products the user is reselling to profile
    // REQUIRES:
    // MODIFIES: this
    // EFFECTS:
    public void addProduct(Product p) {
        products.add(p);
    }

    // USER STORY #3: set status of product
    //
    //
    //
    public void setStatus(Product p) {
        //
    }

    // USER STORY #4: remove product from profile

    public void removeItem(Product p) {
        products.remove(p);
    }
}
