package manager;

import model.Project;
import model.User;
import model.UserRole;
import model.dataBase.DataBase;

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
        String query = "INSERT INTO `nizekproject`.`project` (`name`, `description`) VALUES (?, ?)";

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


                Project project = new Project(name,description);
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

                return new Project(name, description);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Or use logger to log the exception
        }

        return null; // Project not found with the given name
    }

    public void assignUserToProject(int userId, int projectId) {
        try {
            // Prepare the SQL query to insert the assignment into the user_project table
            String query = "INSERT INTO `nizekproject`.`user_project` (`user_id`, `project_id`) VALUES (?, ?);";

            // Create a PreparedStatement to safely execute the query
            PreparedStatement statement = dataBase.getConnection().prepareStatement(query);

            // Set the user_id and project_id values in the PreparedStatement
            statement.setInt(1, userId);
            statement.setInt(2, projectId);

            // Execute the query to insert the assignment
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error assigning user to project: " + e.getMessage());
        }
    }


}
