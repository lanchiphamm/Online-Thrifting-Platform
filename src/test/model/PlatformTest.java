package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlatformTest {
    private Platform testPlatform;

    @BeforeEach
    void setUp() {
        testPlatform = new Platform();
    }

    @Test
    void testConstructor() {
        assertTrue(testPlatform.getUsersOnPlatform().isEmpty());
    }

    @Test
    void testSignUpUser() {
        User u1 = new User("user1");
        testPlatform.signUpUser(u1);
        assertTrue(testPlatform.getUsersOnPlatform().contains(u1));
        assertEquals(1, testPlatform.numOfUsersOnPlatform());
    }

    @Test
    void testSignUpMultipleUser() {
        User u1 = new User("user1");
        User u2 = new User("user2");
        User u3 = new User("user3");
        testPlatform.signUpUser(u1);
        testPlatform.signUpUser(u2);
        testPlatform.signUpUser(u3);
        assertEquals(3, testPlatform.numOfUsersOnPlatform());
        assertTrue(testPlatform.getUsersOnPlatform().contains(u1));
        assertTrue(testPlatform.getUsersOnPlatform().contains(u2));
        assertTrue(testPlatform.getUsersOnPlatform().contains(u3));
    }
}