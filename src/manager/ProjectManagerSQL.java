package manager;

import model.Project;

import model.User;
import model.UserRole;
import model.dataBase.DataBase;

import javax.management.relation.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectManagerSQL {
    private static ProjectManagerSQL instance;

    private final DataBase dataBase = DataBase.getInstance();

    // Private constructor to prevent direct instantiation from other classes
    private ProjectManagerSQL() {
    }

    // Public static method to get the single instance of UserManagerSQL
    public static ProjectManagerSQL getInstance() {
        if (instance == null) {
            instance = new ProjectManagerSQL();
        }
        return instance;
    }

    public void addProject(String name, String description) {
        String query = "INSERT INTO `ngi`.`project` (`name`, `description`) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
            System.out.println("Project added successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding project: " + e.getMessage());
        }
    }

    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<>();

        try {
            String query = "SELECT * FROM project";
            PreparedStatement statement = dataBase.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int projectId = resultSet.getInt("idproject");

                Project project = new Project(name, description, projectId);
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Or use logger to log the exception
        }

        return projects;
    }

    public Project getProjectByName(String projectName) {
        try {
            String query = "SELECT * FROM project WHERE name = ?";
            PreparedStatement statement = dataBase.getConnection().prepareStatement(query);
            statement.setString(1, projectName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int projectId = resultSet.getInt("idproject");

                return new Project(name, description, projectId);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Or use logger to log the exception
        }

        return null; // Project not found with the given name
    }

    public boolean assignUserToProject(int userId, int projectId) {
        try {
            // Check if the relationship already exists
            String checkQuery = "SELECT * FROM `user_project` WHERE `user_id` = ? AND `project_id` = ?;";
            PreparedStatement checkStatement = dataBase.getConnection().prepareStatement(checkQuery);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, projectId);
            ResultSet existingRelation = checkStatement.executeQuery();

            if (existingRelation.next()) {
                System.out.println("The relationship already exists.");
                return false; // Relationship already exists, return false
            }

            // Prepare the SQL query to insert the assignment into the user_project table
            String insertQuery = "INSERT INTO `user_project` (`user_id`, `project_id`) VALUES (?, ?);";

            // Create a PreparedStatement to safely execute the insert query
            PreparedStatement insertStatement = dataBase.getConnection().prepareStatement(insertQuery);
            insertStatement.setInt(1, userId);
            insertStatement.setInt(2, projectId);

            // Execute the query to insert the assignment
            insertStatement.executeUpdate();
            System.out.println("Assigned user to project successfully");
            return true; // Assignment successful, return true
        } catch (SQLException e) {
            throw new RuntimeException("Error assigning user to project: " + e.getMessage());
        }
    }

    public List<User> getUsersByProject(Project project) {
        List<User> users = new ArrayList<>();

        try {
            if (project == null) {
                System.out.println("Project not found.");
                return users;
            }

            int projectId = project.getProjectId(); // Assuming getId() is a method in your Project class

            // Query to fetch users associated with the given project
            String query = "SELECT u.* FROM `user` u " +
                    "JOIN `user_project` up ON u.`id` = up.`user_id` " +
                    "WHERE up.`project_id` = ?";
            PreparedStatement statement = dataBase.getConnection().prepareStatement(query);
            statement.setInt(1, projectId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String userName = resultSet.getString("name");
                String userLastName = resultSet.getString("lastname");
                String userEmail = resultSet.getString("email");
                String passWord = resultSet.getString("password");
                UserRole role = UserRole.valueOf(resultSet.getString("role")); // Assuming UserRole is an enum

                User user = new User(userName, userLastName, userEmail,passWord, role);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Or use logger to log the exception
        }

        return users;
    }

    public boolean softDeleteUserFromProject(int userId, int projectId) {
        try {
            // Prepare the SQL query to update the status of the relationship
            String updateQuery = "DELETE FROM `user_project` WHERE `user_id` = ? AND `project_id` = ?;";

            // Create a PreparedStatement to safely execute the update query
            PreparedStatement updateStatement = dataBase.getConnection().prepareStatement(updateQuery);
            updateStatement.setInt(1, userId);
            updateStatement.setInt(2, projectId);

            // Execute the query to update the status
            int rowsUpdated = updateStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Soft deleted user from project successfully");
                return true; // Soft delete successful
            } else {
                System.out.println("User was not assigned to the project or an error occurred.");
                return false; // Soft delete not performed
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error performing soft delete: " + e.getMessage());
        }
    }

    public void editProject(int projectId, String newName, String newDescription) {
        try {
            String query = "UPDATE project SET name=?, description=? WHERE idproject=?";
            PreparedStatement statement = dataBase.getConnection().prepareStatement(query);
            statement.setString(1, newName);
            statement.setString(2, newDescription);
            statement.setInt(3, projectId);

            // Execute the query to update the project's information
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                // If no rows were affected, it means the project with the given id doesn't exist in the database.
                // You may handle this scenario as per your requirement (e.g., throw an exception, log a message, etc.).
                System.out.println("Project with ID '" + projectId + "' not found in the database.");
            } else {
                // If the query was successful, you can print a message indicating the project was updated.
                System.out.println("Project with ID '" + projectId + "' updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Or use logger to log the exception
        }
    }

    public void removeProject(int projectId) {
        try {
            // First, delete the relationship entries in the user_project table for the project
            String deleteUserProjectQuery = "DELETE FROM user_project WHERE project_id=?";
            PreparedStatement deleteUserProjectStatement = dataBase.getConnection().prepareStatement(deleteUserProjectQuery);
            deleteUserProjectStatement.setInt(1, projectId);
            deleteUserProjectStatement.executeUpdate();

            // Then, delete the project entry from the project table
            String deleteProjectQuery = "DELETE FROM project WHERE idproject=?";
            PreparedStatement deleteProjectStatement = dataBase.getConnection().prepareStatement(deleteProjectQuery);
            deleteProjectStatement.setInt(1, projectId);
            int rowsAffected = deleteProjectStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Project with ID '" + projectId + "' removed successfully.");
            } else {
                System.out.println("Project with ID '" + projectId + "' not found in the database.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error removing project: " + e.getMessage());
        }
    }

}

