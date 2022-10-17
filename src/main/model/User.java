package model;

import java.util.ArrayList;

// Represents a user on this platform with a username and a list of products they sell
public class User {
    private final String name;                  // Username of profile
    private ArrayList<Product> products;        // User's list of products
    private ArrayList<Product> cart;            // keep track of purchases

    // REQUIRES: accountName is not an empty string
    // EFFECTS: name on account is set to accountName; an empty list of Products
    //          is initialised; an empty list of Purchased Products is initialised
    public User(String accountName) {
        name = accountName;
        products = new ArrayList<>();
        cart = new ArrayList<>();
    }

    // getter
    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    // USER STORY #1: add products the user is reselling to profile
    // REQUIRES: p != null
    // MODIFIES: this and Product
    // EFFECTS: p is added to products and p.user is set to this.user
    public void addProduct(Product p) {
        products.add(p);
        p.setUser(this);
    }

    // USER STORY #3: remove product from profile
    // REQUIRES: p != null
    // MODIFIES: this
    // EFFECTS: if p is in products, then remove p from products and return true;
    //          otherwise return false
    public boolean removeItem(Product p) {
        if (products.contains(p)) {
            products.remove(p);
            return true;
        }
        return false;
    }


    // USER STORY #4: add products the user wants to purchase to their cart
    // REQUIRES: p != null
    // MODIFIES: this
    // EFFECTS: p is added to cart
    public void addToCart(Product p) {
        cart.add(p);
    }

    // REQUIRES: p != null
    // MODIFIES: this
    // EFFECTS:  if p is in cart, then remove p and return true; otherwise return false
    public boolean removeFromCart(Product p) {
        if (cart.contains(p)) {
            cart.remove(p);
            return true;
        }
        return false;
    }
}
