package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    private static Repository instance = null;
    private HashMap<Integer, User> userMap;
    private HashMap<String, User> userByEmail;
    private HashMap<Integer, Project> projectMap;

    private Repository() {
        userMap = new HashMap<>();
        userByEmail = new HashMap<>();
        projectMap = new HashMap<>();
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

    public HashMap<Integer, User> getUserMap() {
        return userMap;
    }

    public HashMap<String, User> getUserByEmail() {
        return userByEmail;
    }

    public HashMap<Integer, Project> getProjectMap() {
        return projectMap;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "userMap=" + userMap +
                ", userByEmail=" + userByEmail +
                ", projects=" + projectMap +
                '}';
    }
}

