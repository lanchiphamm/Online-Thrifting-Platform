package model;

import java.util.ArrayList;

// Represents a user on this platform with a username and a list of products they sell
public class User {
    private final String name;                    // Username of profile
    private ArrayList<Product> products;    // User's list of products
    private ArrayList<Product> cart;              // keep track of purchases

    // REQUIRES: accountName is not an empty string
    // EFFECTS: name on account is set to accountName; an empty list of Products
    //          is initialised; an empty list of Purchased Products is initialised
    public User(String accountName) {
        name = accountName;
        products = new ArrayList<>();
        cart = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    // USER STORY #1: add products the user is reselling to profile
    // REQUIRES: p != null
    // MODIFIES: this and Product
    // EFFECTS: p is added to products and p.user is set to this.user
    public void addProduct(Product p) {
        products.add(p);
        p.setUser(this);
    }

    // USER STORY #3: set status of product
    //
    //
    //
    public void setStatus(Product p) {
        //
    }

    // USER STORY #4: remove product from profile
    // REQUIRES: p != null && p in products
    // MODIFIES: this
    // EFFECTS: remove p from products
    public void removeItem(Product p) {
        products.remove(p);
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void addToCart(Product p) {
        cart.add(p);
    }

    public void removeFromCart(Product p) {
        cart.remove(p);
    }
}
