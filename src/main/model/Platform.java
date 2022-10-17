package model;

import java.util.ArrayList;

// Represents a platform where users can browse products
// platform <-> users
public class Platform {
    private ArrayList<User> platformUsers;

    // Constructor
    // MODIFIES: this
    // EFFECTS: initialise an empty list of Users
    public Platform() {
        platformUsers = new ArrayList<>();
    }

    // Add products to platform
    // REQUIRES: u != null
    // MODIFIES: this
    // EFFECTS: add u to list of platformUsers
    public void signUpUser(User u) {
        platformUsers.add(u);
    }

    // USER STORY #2: view users on platform
    // EFFECTS: return the list platformUsers
    public ArrayList<User> usersOnPlatform() {
        return platformUsers;
    }

    // EFFECTS: return the number of Users on this platform
    public int numOfUsersOnPlatform() {
        return platformUsers.size();
    }

}
