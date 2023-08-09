package model;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private String name;
    private Status status; // Board category (To Do, In Progress, QA, or Done)
    private User user; // User associated with this board
    private List<Issue> issues;

    public Board(String name, Status status, User user) {
        this.name = name;
        this.status = status;
        this.user = user;
        this.issues = new ArrayList<>();
    }

    // Other methods as needed...
}
