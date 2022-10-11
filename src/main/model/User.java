package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Represents a user on this platform with a username and a list of products they sell
public class User {
    private String name;                    // Username of profile
    private ArrayList<Product> products;    // User list of products

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

    // USER STORY #1: add product reselling to profile
    // REQUIRES:
    // MODIFIES: this
    // EFFECTS:
    public void addProduct() {
        // stub
    }


}
