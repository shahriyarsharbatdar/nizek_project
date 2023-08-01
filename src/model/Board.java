package model;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private String name;
    private BoardCategory category; // Board category (To Do, In Progress, QA, or Done)
    private User user; // User associated with this board
    private List<Issue> issues;

    public Board(String name, BoardCategory category, User user) {
        this.name = name;
        this.category = category;
        this.user = user;
        this.issues = new ArrayList<>();
    }

    // Other methods as needed...
}
