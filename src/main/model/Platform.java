package model;

import java.util.ArrayList;

// Represents a platform where users can browse products
public class Platform {
    private ArrayList<Product> platformProducts;

    // Constructor
    // REQUIRES
    // MODIFIES
    // EFFECTS
    public Platform() {
        platformProducts = new ArrayList<Product>();
    }

    // Add products to platform
    //
    // MODIFIES: this
    //
    public void addProduct(Product p) {
        platformProducts.add(p);
    }

    // USER STORY #2: view available products on platform
    public void availableProducts() {
        // stub
        // Question: how should I implement this
    }
}
