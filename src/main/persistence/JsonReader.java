package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import model.Platform;
import model.Product;
import model.ProductType;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

// Represents a reader that reads platfrom from JSON data stored in the file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads platform from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Platform read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlatform(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses platform from JSON object and returns it
    private Platform parsePlatform(JSONObject jsonObject) {
        Platform platform = new Platform();
        addUsers(platform, jsonObject);
        return platform;
    }

    // MODIFIES: platform
    // EFFECTS: parses users from JSON object and adds them to platform
    private void addUsers(Platform platform, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("users");
        for (Object user : jsonArray) {
            JSONObject nextUser = (JSONObject) user;
            addUser(platform, nextUser);
        }
    }

    // MODIFIES: platform
    // EFFECTS: parses user from JSON object and adds it to platform
    private void addUser(Platform platform, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        User user = new User(name, password);
        addProducts(user, jsonObject);
        addCarts(user, jsonObject);
        platform.signUpUser(user);
    }

    // MODIFIES: user
    // EFFECTS: parses products from JSON object and adds it to user
    private void addProducts(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("products");
        for (Object product : jsonArray) {
            JSONObject nextProduct = (JSONObject) product;
            addProduct(user, nextProduct);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses product from JSON object and adds it to user's list of products
    private void addProduct(User user, JSONObject jsonObject) {
        ProductType type = ProductType.valueOf(jsonObject.getString("type"));
        int price = jsonObject.getInt("price");
        String info = jsonObject.getString("info");
        Product product = new Product(type, price, info);
        user.addProduct(product);
    }

    // MODIFIES: user
    // EFFECTS: parses carts from JSON object and adds it to user
    private void addCarts(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cart");
        for (Object product : jsonArray) {
            JSONObject nextProduct = (JSONObject) product;
            addCart(user, nextProduct);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses cart from JSON object and adds it to user's cart
    private void addCart(User user, JSONObject jsonObject) {
        ProductType type = ProductType.valueOf(jsonObject.getString("type"));
        int price = jsonObject.getInt("price");
        String info = jsonObject.getString("info");
        Product product = new Product(type, price, info);
        user.addToCart(product);
    }
}
