package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Repository {
    private static Repository instance = null;
    private ArrayList<User> users;
    private HashMap<String, User> userVerify; // Use email as the key to connect to the User object


    private Repository() {
        users = new ArrayList<>();
        userVerify = new HashMap<>();

    }

    public static Repository getInstance() {
        if (instance == null) {
            synchronized (Repository.class) {
                if (instance == null) {
                    instance = new Repository();
                }
            }
        }
        return instance;
    }


    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUserByEmail(String email) {
        return userVerify.get(email);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userVerify.values());
    }

    // Add other methods to retrieve users or perform other operations as needed
}

