package model;

import static model.ProductType.OUTERWEAR;
import static model.ProductType.SHIRT;
import static model.ProductType.SHOES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
    private User testUser;
    private Product p1;
    private Product p2;
    private Product p3;

    @BeforeEach
    void setUp() {
        testUser = new User("abcdxyz", "cpsc210");
        p1 = new Product(SHIRT, 50, "Nike black");
        p2 = new Product(SHOES, 90, "Nike AF1");
        p3 = new Product(OUTERWEAR, 150, "Carhartt Work");
    }

    @Test
    void testConstructor() {
        assertEquals("abcdxyz", testUser.getName());
        assertEquals("cpsc210", testUser.getPassword());
        assertTrue(testUser.getProducts().isEmpty());
        assertTrue(testUser.getCart().isEmpty());
    }

    @Test
    void testAddProduct() {
        testUser.addProduct(p1);
        assertEquals(1, testUser.getProducts().size());
        assertTrue(testUser.getProducts().contains(p1));
    }

    @Test
    void testAddManyProducts() {
        testUser.addProduct(p1);
        testUser.addProduct(p2);
        testUser.addProduct(p3);
        assertEquals(3, testUser.getProducts().size());
        assertTrue(testUser.getProducts().contains(p1));
        assertTrue(testUser.getProducts().contains(p2));
        assertTrue(testUser.getProducts().contains(p3));
    }

    @Test
    void testRemoveItemFail() {
        assertFalse(testUser.removeItem(p2));
    }

    @Test
    void testRemoveItemSuccess() {
        testUser.addProduct(p1);
        testUser.addProduct(p2);
        assertTrue(testUser.removeItem(p1));
        assertEquals(1, testUser.getProducts().size());
        assertFalse(testUser.getProducts().contains(p1));
        assertTrue(testUser.getProducts().contains(p2));
    }

    @Test
    void testRemoveManyItem() {
        testUser.addProduct(p1);
        testUser.addProduct(p2);
        assertTrue(testUser.removeItem(p1));
        assertTrue(testUser.removeItem(p2));
        assertEquals(0, testUser.getProducts().size());
        assertFalse(testUser.getProducts().contains(p1));
        assertFalse(testUser.getProducts().contains(p2));
        assertFalse(testUser.removeItem(p1));
    }

    @Test
    void testAddToCart() {
        testUser.addToCart(p1);
        assertEquals(1, testUser.getCart().size());
        assertTrue(testUser.getCart().contains(p1));
    }

    @Test
    void testAddManyToCart() {
        testUser.addToCart(p1);
        testUser.addToCart(p2);
        testUser.addToCart(p3);
        assertEquals(3, testUser.getCart().size());
        assertTrue(testUser.getCart().contains(p1));
        assertTrue(testUser.getCart().contains(p2));
        assertTrue(testUser.getCart().contains(p3));
    }

    @Test
    void testRemoveFromEmptyCart() {
        assertFalse(testUser.removeFromCart(p1));
    }

    @Test
    void testRemoveFromCart() {
        testUser.addToCart(p1);
        testUser.addToCart(p2);
        assertTrue(testUser.removeFromCart(p1));
        assertEquals(1, testUser.getCart().size());
        assertFalse(testUser.getCart().contains(p1));
        assertTrue(testUser.getCart().contains(p2));
    }

    @Test
    void testRemoveManyFromCart() {
        testUser.addToCart(p1);
        testUser.addToCart(p2);
        assertTrue(testUser.removeFromCart(p1));
        assertTrue(testUser.removeFromCart(p2));
        assertFalse(testUser.removeFromCart(p1));
        assertEquals(0, testUser.getCart().size());
        assertFalse(testUser.getCart().contains(p1));
        assertFalse(testUser.getCart().contains(p2));
    }

    @Test
    void testEmptyCart() {
        testUser.addToCart(p1);
        testUser.addToCart(p2);
        testUser.emptyCart();
        assertEquals(0, testUser.getCart().size());
    }

    @Test
    void testGetUserKey() {
        Platform p =  new Platform();
        p.signUpUser(testUser);
        assertEquals(1, testUser.getUserKey());
    }

    @Test
    void testHashCode() {
        assertEquals(Objects.hash("abcdxyz", "cpsc210"), testUser.hashCode());
    }

    @Test
    void testEquals() {
        assertFalse(testUser.equals(new Platform()));
        assertTrue(testUser.equals(testUser));
        assertTrue(testUser.equals(new User("abcdxyz", "cpsc210")));
        assertFalse(testUser.equals(new User("abcdxyz", "c")));
        assertFalse(testUser.equals(new User("a", "c")));
        assertFalse(testUser.equals(new User("a", "cpsc210")));
    }
}
