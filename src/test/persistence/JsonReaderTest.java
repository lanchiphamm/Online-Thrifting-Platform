package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;
import model.Platform;
import model.User;
import org.junit.jupiter.api.Test;

public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Platform platform = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlatform.json");
        try {
            Platform pf = reader.read();
            assertEquals(0, pf.numOfUsersOnPlatform());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlatform.json");
        try {
            Platform pf = reader.read();
            List<User> userList = pf.getUsersOnPlatform();
            assertEquals(2, userList.size());
            User user1 = userList.get(0);
            User user2 = userList.get(1);
            checkUser(user1, "andrew_01", user1.getProducts(), user1.getCart());
            checkUser(user2, "vintage_thrifter", user2.getProducts(), user2.getCart());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
