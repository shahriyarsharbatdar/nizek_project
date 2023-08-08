package model;

import java.util.Date;

public class Issue {
    private String title;
    private String description;
    private User assignee;
    private IssueType type;
    private boolean isOpen;
    private Date dueDate;
    private Priority priority;
    private Status status;

    public Issue(String title, String description, User assignee, IssueType type) {
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.type = type;
        this.isOpen = true;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void closeIssue() {
        isOpen = false;
    }

    public void reopenIssue() {
        isOpen = true;
    }

}
