package manager;


import model.User;
import model.UserRole;
import model.dataBase.DataBase;

import javax.management.relation.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManagerSQL {
    private static UserManagerSQL instance;

    private DataBase dataBase = DataBase.getInstance();

    // Private constructor to prevent direct instantiation from other classes
    private UserManagerSQL() {
    }

    // Public static method to get the single instance of UserManagerSQL
    public static UserManagerSQL getInstance() {
        if (instance == null) {
            instance = new UserManagerSQL();
        }
        return instance;
    }

    public void addUser(String name, String lastname, String email, String password, UserRole role) {
        try {
            String query = "INSERT INTO user" + "(name,lastname,email,password,role)" + "VALUES (?,?,?,?,?)";
            PreparedStatement statement = dataBase.getConnection().prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, lastname);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, role.toString());
            // Execute the query to insert the user into the database
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle any potential database-related exceptions here
            e.printStackTrace(); // Or use logger to log the exception
        }
    }

    // Method to edit a member's information in the database
    public void editMember(int memberId, String name, String lastname, String email, String password, String role) {
        try {
            String query = "UPDATE user SET name=?, lastname=?, email=?, password=?, role=? WHERE id=?";
            PreparedStatement statement = dataBase.getConnection().prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, lastname);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, role);
            statement.setInt(6, memberId); // Assuming memberId is the primary key in the Member table
            // Execute the query to update the member's information
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle any potential database-related exceptions here
            e.printStackTrace(); // Or use logger to log the exception
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try {
            String query = "SELECT * FROM user";
            PreparedStatement statement = dataBase.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                UserRole role = UserRole.valueOf(resultSet.getString("role")); // Assuming UserRole is an enum

                User user = new User(name, lastname, email, password,role);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Or use logger to log the exception
        }

        return users;
    }

        // Method to search the database by email and password and return true if found
        public boolean authenticate(String email, String password) {
            try {
                String query = "SELECT * FROM user WHERE email=? AND password=?";
                PreparedStatement statement = dataBase.getConnection().prepareStatement(query);
                statement.setString(1, email);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next(); // If a record is found, returns true; otherwise, returns false
            } catch (SQLException e) {
                e.printStackTrace(); // Or use logger to log the exception
            }

            return false; // Return false if an exception occurred
        }

    public User getUserByEmail(String email) {
        try {
            String query = "SELECT * FROM user WHERE email=?";
            PreparedStatement statement = dataBase.getConnection().prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                String password = resultSet.getString("password");
                UserRole role = UserRole.valueOf(resultSet.getString("role")); // Assuming UserRole is an enum

                return new User(name, lastname, email, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Or use logger to log the exception
        }

        return null; // Return null if no user is found for the given email
    }

    public boolean isDatabaseEmpty() {
        try {
            String query = "SELECT COUNT(*) AS count FROM user";
            PreparedStatement statement = dataBase.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Or use logger to log the exception
        }

        return true; // Return true if an exception occurred or if no count is retrieved from the database
    }


}

