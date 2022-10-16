package model;

// Represents a product and its owner, type, price, status and any additional information
public class Product {
    private User user;          // Seller of this product
    private ProductType type;   // type of clothing - 1 of 5: shirt/jacket/pant/shoes/accessories
    private int price;          // price of product
    private boolean status = true; // status of product - sold (false) or available (true)
    private String info;        // extra information inputted by user

    // Constructor
    // REQUIRES: price >= 0
    public Product(ProductType type, int price, String info) {
        this.type = type;
        this.price = price;
        this.info = info;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User u) {
        this.user = u;
    }

    public ProductType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean s) {
        status = s;
    }

    public String getInfo() {
        return info;
    }
}
