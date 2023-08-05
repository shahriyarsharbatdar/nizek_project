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
}
