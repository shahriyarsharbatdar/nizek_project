package model;

public class Issue {
    private String title;
    private String description;
    private User assignee;
    private IssueType type;
    private boolean isOpen;

    public Issue(String title, String description, User assignee, IssueType type) {
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.type = type;
        this.isOpen = true; // By default, the issue is open when created
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public IssueType getType() {
        return type;
    }

    public void setType(IssueType type) {
        this.type = type;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void closeIssue() {
        isOpen = false;
    }

    public void reopenIssue() {
        isOpen = true;
    }
}
