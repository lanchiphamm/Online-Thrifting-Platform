package model;

import static model.ProductType.SHIRT;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
