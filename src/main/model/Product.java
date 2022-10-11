package model;

// Represents a product and its owner, type, price, status and any additional information
public class Product {
    private User user;      // Seller of this product
    private String type;    // type of clothing - 1 of 5: shirt/jacket/pant/shoes/accessories - ENUM
    private int price;      // price of product
    private boolean status; // status of product - sold (false) or available (true)
    private String info;    // extra information inputted by user

    //
    public Product(User user, String type, int price, String info) {
        this.user = user;
        this.type = type;
        this.price = price;
        this.status = true;
        this.info = info;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public String getType() {
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
