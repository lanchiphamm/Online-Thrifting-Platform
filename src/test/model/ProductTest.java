package model;

import static model.ProductType.SHIRT;
import static model.ProductType.SHOES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product(SHIRT, 50, "Nike black");
    }

    @Test
    void testConstructor() {
        assertEquals(SHIRT, testProduct.getType());
        assertEquals(50, testProduct.getPrice());
        assertEquals("Nike black", testProduct.getInfo());
    }

    @Test
    void testSetUser() {
        User u = new User("kevin01", "cpsc210");
        testProduct.setUser(u);
        assertEquals(u, testProduct.getUser());
    }

    @Test
    void testPrintProduct() {
        assertEquals("SHIRT: $50 - Nike black", testProduct.printProduct());
    }

    @Test
    void testGetProductKey() {
        User u = new User("kevin01", "cpsc210");
        Platform p = new Platform();
        p.signUpUser(u);
        u.addProduct(testProduct);
        testProduct.setUser(u);
        assertEquals("1.1", testProduct.getProductKey());
    }

    @Test
    void testHashCode() {
        assertEquals(Objects.hash(SHIRT, 50, "Nike black"), testProduct.hashCode());
    }

    @Test
    void testEquals() {
        assertFalse(testProduct.equals(new User("he", "hi")));
        assertTrue(testProduct.equals(testProduct));
        assertFalse(testProduct.equals(new Product(SHOES, 100, "Nike")));
        assertFalse(testProduct.equals(new Product(SHOES, 50, "Nike black")));
        assertFalse(testProduct.equals(new Product(SHIRT, 60, "Nike black")));
        assertFalse(testProduct.equals(new Product(SHIRT, 50, "black")));
    }
}
