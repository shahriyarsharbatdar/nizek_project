package model.DataBase;

import java.io.*;
import java.util.HashMap;

public class SaveLoginInfo {


    public void saveToFile(HashMap<String,String> user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Users.csv"))) {
            for (String email : Repository.getUsers().keySet()) {
                String password = Repository.users.get(email);
                writer.write(email + "," + password);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(HashMap<String, String> users) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Users.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String email = parts[0];
                    String password = parts[1];
                    users.put(email, password);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Repository {
        static HashMap<String,String> users = new HashMap<>();

        public static HashMap<String,String> getUsers() {
            return users;
        }
    }
}
