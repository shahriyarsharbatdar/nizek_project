package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    private HashMap<Integer, User> userMap; // user by id
    private HashMap<String,User> userByEmail; // user by email
    private HashMap<Integer,Project> projectMap = new HashMap<>(); //project by id

    public Repository() {
        userMap = new HashMap<>();
        userByEmail = new HashMap<>();


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

