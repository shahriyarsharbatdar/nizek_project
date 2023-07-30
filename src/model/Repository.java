package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private static Repository instance = null;
    private HashMap<Integer, User> userMap; // Repository using HashMap to store users
    private HashMap<String,User> userByEmail;

    private Repository() {
        userMap = new HashMap<>();
        userByEmail = new HashMap<>();

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

    public Map<Integer, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(HashMap<Integer, User> userMap) {
        this.userMap = userMap;
    }

    public HashMap<String, User> getUserByEmail() {
        return userByEmail;
    }

    public void setUserByEmail(HashMap<String, User> userByEmail) {
        this.userByEmail = userByEmail;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "userMap=" + userMap +
                '}';
    }
}

