package manager;

import model.Issue;
import model.IssueType;
import model.Priority;
import model.Status;
import model.dataBase.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IssueManagerSQL {
    private static IssueManagerSQL instance;

    private final DataBase dataBase = DataBase.getInstance();

    // Private constructor to prevent direct instantiation from other classes
    private IssueManagerSQL() {
    }

    // Public static method to get the single instance of UserManagerSQL
    public static IssueManagerSQL getInstance() {
        if (instance == null) {
            instance = new IssueManagerSQL();
        }
        return instance;
    }

    // add issue method
    public Issue addIssue(String title, String description, Status status, IssueType issueType, Priority priority) {
        try {
            String insertQuery = "INSERT INTO nizekproject.issue (title, description, status, createDate, type, priority) " +
                    "VALUES (?, ?, ?, NOW(), ?, ?)";

            PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, status.toString());
            preparedStatement.setString(4, issueType.toString());
            preparedStatement.setString(5, priority.toString());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Insertion failed, no rows affected.");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int issueId = generatedKeys.getInt(1); // Assuming the ID column is the first column
                return getIssueById(issueId);
            } else {
                throw new RuntimeException("Failed to retrieve the generated issue ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding issue: " + e.getMessage());
        }
    }

    // update issue method
    public Issue getIssueById(int issueId) {
        try {
            String selectQuery = "SELECT * FROM nizekproject.issue WHERE id = ?";

            PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(selectQuery);
            preparedStatement.setInt(1, issueId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Status status = Status.valueOf(resultSet.getString("status"));
                IssueType issueType = IssueType.valueOf(resultSet.getString("type"));
                Priority priority = Priority.valueOf(resultSet.getString("priority"));

                Issue issue = new Issue(title, description, status, issueType, priority);
                issue.setIssueId(issueId);
                System.out.println(issueId);
                return issue;

            } else {
                return null; // Issue with the specified ID not found
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving issue: " + e.getMessage());
        }
    }

    public void updateIssue(int issueId, String title, String description, Status status, IssueType issueType, Priority priority) {
        try {
            String updateQuery = "UPDATE nizekproject.issue " +
                    "SET title = ?, description = ?, status = ?, type = ?, priority = ? " +
                    "WHERE id = ?";

            PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(updateQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, status.toString());
            preparedStatement.setString(4, issueType.toString());
            preparedStatement.setString(5, priority.toString());
            preparedStatement.setInt(6, issueId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RuntimeException("Issue with ID " + issueId + " not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating issue: " + e.getMessage());
        }
    }

        public void removeIssue(int issueId) {
            try {
                String deleteQuery = "DELETE FROM nizekproject.issue WHERE id = ?";

                PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(deleteQuery);
                preparedStatement.setInt(1, issueId);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted == 0) {
                    throw new RuntimeException("Issue with ID " + issueId + " not found.");
                }else{
                    System.out.println("issue with ID '" + issueId + "' removed successfully.");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error removing issue: " + e.getMessage());
            }
        }
    public ArrayList<Issue> getAllIssues() {
        ArrayList<Issue> issues = new ArrayList<>();

        try {
            String selectAllQuery = "SELECT * FROM nizekproject.issue";

            PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(selectAllQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int issueId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Status status = Status.valueOf(resultSet.getString("status"));
                IssueType issueType = IssueType.valueOf(resultSet.getString("type"));
                Priority priority = Priority.valueOf(resultSet.getString("priority"));

                Issue issue = new Issue(title, description, status, issueType, priority);
                issue.setIssueId(issueId);
                issues.add(issue);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving issues: " + e.getMessage());
        }

        return issues;
    }
    }


