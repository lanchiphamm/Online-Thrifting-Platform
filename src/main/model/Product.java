package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a product and its owner, type, price, and any additional information
public class Product implements Writable {
    private User user;              // seller of this product
    private ProductType type;       // type of clothing - ENUM
    private int price;              // price of product
    private String info;            // extra information inputted by user

    // Constructor
    // REQUIRES: price >= 0
    // MODIFIES: this
    // EFFECTS: construct a product with type, price and any additional information
    public Product(ProductType type, int price, String info) {
        this.type = type;
        this.price = price;
        this.info = info;
    }

    // REQUIRES: u != null
    // MODIFIES: this
    // EFFECTS: set the user of this product to u
    public void setUser(User u) {
        this.user = u;
    }

    public User getUser() {
        return user;
    }

    public ProductType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("price", price);
        json.put("info", info);
        return json;
    }
}
