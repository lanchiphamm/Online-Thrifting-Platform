package model;

import java.util.ArrayList;

// Represents a platform where users can browse products
// platform <-> users
public class Platform {
    private ArrayList<User> platformUsers;

    // Constructor
    // REQUIRES
    // MODIFIES
    // EFFECTS
    public Platform() {
        platformUsers = new ArrayList<User>();
    }

    // Add users to platform
    //
    // MODIFIES: this
    //
    public void signUpUser(User u) {
        platformUsers.add(u);
    }

    // USER STORY #2: view users on platform
    public ArrayList<User> usersOnPlatform() {
        // stub
        // Question: how should I implement this
        return platformUsers;
    }
}
