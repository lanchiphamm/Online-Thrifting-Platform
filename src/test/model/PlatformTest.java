package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlatformTest {
    private Platform testPlatform;
    private User u1;
    private User u2;
    private User u3;

    @BeforeEach
    void setUp() {
        testPlatform = new Platform();
        u1 = new User("user1", "123");
        u2 = new User("user2", "210");
        u3 = new User("user3", "cpsc");
    }

    @Test
    void testConstructor() {
        assertTrue(testPlatform.getUsersOnPlatform().isEmpty());
    }

    @Test
    void testSignUpUser() {
        testPlatform.signUpUser(u1);
        assertTrue(testPlatform.getUsersOnPlatform().contains(u1));
        assertEquals(1, testPlatform.numOfUsersOnPlatform());
    }

    @Test
    void testSignUpMultipleUser() {
        testPlatform.signUpUser(u1);
        testPlatform.signUpUser(u2);
        testPlatform.signUpUser(u3);
        assertEquals(3, testPlatform.numOfUsersOnPlatform());
        assertTrue(testPlatform.getUsersOnPlatform().contains(u1));
        assertTrue(testPlatform.getUsersOnPlatform().contains(u2));
        assertTrue(testPlatform.getUsersOnPlatform().contains(u3));
    }

    @Test
    void testRemoveUser() {
        testPlatform.signUpUser(u1);
        testPlatform.removeUser(u1);
        assertTrue(testPlatform.getUsersOnPlatform().isEmpty());
        testPlatform.signUpUser(u1);
        testPlatform.signUpUser(u2);
        testPlatform.removeUser(u1);
        assertEquals(1, testPlatform.numOfUsersOnPlatform());
        assertTrue(testPlatform.getUsersOnPlatform().contains(u2));
    }
}