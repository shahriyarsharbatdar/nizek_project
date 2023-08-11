package model;

import java.util.Date;

public class Issue {
    private String title;
    private String description;
    private User assignee;
    private IssueType Issuetype;
    private boolean isOpen;
    private Date dueDate;
    private Priority priority;
    private Status status;
    private int issueId;
    private int projectId;
    private int userId;


    public Issue(String title, String description, Status status, IssueType issueType, Priority priority) {
        this.title = title;
        this.description = description;
        this.Issuetype = issueType;
        this.priority = priority;
        this.status = status;
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

    public IssueType getIssueType() {
        return Issuetype;
    }

    public void setType(IssueType type) {
        this.Issuetype = type;
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

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", assignee=" + assignee +
                ", Issuetype=" + Issuetype +
                ", isOpen=" + isOpen +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", status=" + status +
                ", issueId=" + issueId +
                ", projectId=" + projectId +
                ", userId=" + userId +
                '}';
    }
}
