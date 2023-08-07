package model;

import java.util.ArrayList;

public class Project {
    private String name;
    private String description;
    private int projectId;

    public Project(String name, String description, int projectId) {
        this.name = name;
        this.description = description;
        this.projectId = projectId;
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


    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
