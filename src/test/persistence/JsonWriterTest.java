package persistence;

import static model.ProductType.ACCESSORIES;
import static model.ProductType.PANTS;
import static model.ProductType.SHIRT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;
import model.Platform;
import model.Product;
import model.User;
import org.junit.jupiter.api.Test;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            Platform wr = new Platform();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Platform pf = new Platform();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlatform.json");
            writer.open();
            writer.write(pf);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlatform.json");
            pf = reader.read();
            assertEquals(0, pf.numOfUsersOnPlatform());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Platform pf = new Platform();
            User user1 = new User("andrew_01");
            user1.addProduct(new Product(SHIRT, 90, "Nike"));
            User user2 = new User("vintage_thrifter");
            user2.addProduct(new Product(SHIRT, 10, "Black"));
            user2.addProduct(new Product(PANTS, 90, "Carhartt"));
            user2.addToCart(new Product(ACCESSORIES, 150, "Vivienne"));
            pf.signUpUser(user1);
            pf.signUpUser(user2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(pf);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            pf = reader.read();
            List<User> userList = pf.getUsersOnPlatform();
            assertEquals(2, userList.size());
            User u1 = userList.get(0);
            User u2 = userList.get(1);
            checkUser(u1, "andrew_01", u1.getProducts(), u1.getCart());
            checkUser(u2, "vintage_thrifter", u2.getProducts(), u2.getCart());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
