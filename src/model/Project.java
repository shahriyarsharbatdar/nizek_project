package model;

import java.util.ArrayList;

public class Project {
    private String name;
    private String description;
    private int projectId;
    private ArrayList<Board> boards;
    private ArrayList<User> users;
    private ArrayList<Issue> issues;

    public Project(int projectId,String name, String description) {
        this.name = name;
        this.description = description;
        this.projectId = projectId;
        this.boards = new ArrayList<>();
        this.users = new ArrayList<>();
        this.issues = new ArrayList<>();
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
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", projectId=" + projectId +
                ", boards=" + boards +
                ", users=" + users +
                ", issues=" + issues +
                '}';
    }
}
