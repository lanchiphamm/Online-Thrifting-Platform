package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import model.Product;
import model.User;

public class JsonTest {
    protected void checkUser(User user, String name, List<Product> product, List<Product> cart) {
        assertEquals(name, user.getName());
        assertEquals(product, user.getProducts());
        assertEquals(cart, user.getCart());
    }
}
