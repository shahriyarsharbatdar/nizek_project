package model;

import java.util.ArrayList;

public class Project {
    private String name;
    private String description;
    private int projectId;
    private ArrayList<Board> boards;
    private ArrayList<User> users;
    private ArrayList<Issue> issues;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.boards = new ArrayList<>();
        this.users = new ArrayList<>();
        this.issues = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public ArrayList<Board> getBoards() {
        return boards;
    }

    public void setBoards(ArrayList<Board> boards) {
        this.boards = boards;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Issue> getIssues() {
        return issues;
    }

    public void setIssues(ArrayList<Issue> issues) {
        this.issues = issues;
    }

    public void addBoard(Board board) {
        boards.add(board);
    }

    public void removeBoard(Board board) {
        boards.remove(board);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void addIssue(Issue issue) {
        issues.add(issue);
    }

    public void removeIssue(Issue issue) {
        issues.remove(issue);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", boards=" + boards +
                ", users=" + users +
                ", issues=" + issues +
                '}';
    }
}
