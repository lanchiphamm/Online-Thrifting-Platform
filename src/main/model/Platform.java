package model;

import java.util.ArrayList;

// Represents a platform with Users on it
public class Platform {
    private ArrayList<User> platformUsers;

    // CONSTRUCTOR
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

    // getter
    // USER STORY #2: view users on platform
    // EFFECTS: return the list platformUsers
    public ArrayList<User> getUsersOnPlatform() {
        return platformUsers;
    }

    // getter
    // EFFECTS: return the number of Users on this platform
    public int numOfUsersOnPlatform() {
        return platformUsers.size();
    }

}
