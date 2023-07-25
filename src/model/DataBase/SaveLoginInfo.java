package model.DataBase;

import model.user.User;

import java.io.*;
import java.util.ArrayList;

public class SaveLoginInfo {


    public void saveToFile(ArrayList<User> user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Users.csv"))) {
            for (User checkUser : Repository.users) {
                writer.write(checkUser.getEmail() + "," + checkUser.getPassWord() + ";");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(User user) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by the comma delimiter
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    System.out.println("Username: " + username + ", Password: " + password);
                    // In a real application, compare the username and password with the user input for authentication
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Repository {
        static ArrayList<User> users = new ArrayList<>();

        public static ArrayList<User> getUsers() {
            return users;
        }
    }
}
