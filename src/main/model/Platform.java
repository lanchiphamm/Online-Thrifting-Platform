package model;

import java.util.ArrayList;

// Represents a platform having users
public class Platform {
    private ArrayList<User> platformUsers;

    // Constructor
    // Effects: empty list of Users is initialised
    public Platform() {
        platformUsers = new ArrayList<>();
    }

    // Add Users to Platform
    // REQUIRES: u != null
    // MODIFIES: this
    // EFFECTS: adds this user to the platform
    public void signUpUser(User u) {
        platformUsers.add(u);
    }

    // USER STORY #2: View users on platform
    // EFFECTS: return the list of users on the platform
    public ArrayList<User> usersOnPlatform() {
        return platformUsers;
    }

    // EFFECTS: return the number of users on the platform
    public int numOfUsersOnPlatform() {
        return platformUsers.size();
    }
}
