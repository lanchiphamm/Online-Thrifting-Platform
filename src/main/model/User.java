package model;

import java.util.ArrayList;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/***
 * Represents a user on this platform with a username, password,
 * a list of products they sell, and their shopping cart
 */
public class User implements Writable {
    private Integer key;                        // Unique identifier for each profile
    private final String name;                  // Username of profile
    private final String password;              // Password of profile
    private ArrayList<Product> products;        // User's list of products
    private ArrayList<Product> cart;            // keep track of purchases

    // REQUIRES: accountName is not an empty string
    // EFFECTS: name on account is set to accountName; an empty list of Products
    //          is initialised; an empty list of Purchased Products is initialised
    public User(String accountName, String password) {
        name = accountName;
        this.password = password;
        products = new ArrayList<>();
        cart = new ArrayList<>();
    }

    // getter
    public int getUserKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setUserKey(int k) {
        this.key = k;
    }

    // USER STORY #1: add products the user is reselling to profile
    // REQUIRES: p != null
    // MODIFIES: this and Product
    // EFFECTS: p is added to products and p.user is set to this.user
    public void addProduct(Product p) {
        products.add(p);
        p.setUser(this);
        String k =  "" + this.key + ".";
        k += products.indexOf(p) + 1;
        p.setProductKey(k);
        EventLog.getInstance().logEvent(new Event("New " + p.getType() + " added to profile"));
    }

    // USER STORY #3: remove product from profile
    // REQUIRES: p != null
    // MODIFIES: this
    // EFFECTS: if p is in products, then remove p from products and return true;
    //          otherwise return false
    public boolean removeItem(Product p) {
        if (products.contains(p)) {
            products.remove(p);
            EventLog.getInstance().logEvent(new Event("Product ("
                    + p.getType() + ") removed from profile"));
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
//        EventLog.getInstance().logEvent(new Event("New product added to cart"));
    }

    // REQUIRES: p != null
    // MODIFIES: this
    // EFFECTS:  if p is in cart, then remove p and return true; otherwise return false
    public boolean removeFromCart(Product p) {
        if (cart.contains(p)) {
            cart.remove(p);
//            EventLog.getInstance().logEvent(new Event("Product removed from cart"));
            return true;
        }
        return false;
    }

    // REQUIRES: cart is not empty
    // MODIFIES: this
    // EFFECTS: empty out user's cart
    public void emptyCart() {
        cart.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return name.equals(user.name) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("password", password);
        json.put("products", productsToJson());
        json.put("cart", cartToJson());
        return json;
    }

    // EFFECTS: returns products of this user as a JSON array
    private JSONArray productsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Product p : products) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns products of this user as a JSON array
    private JSONArray cartToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Product c : cart) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
