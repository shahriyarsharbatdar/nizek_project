package ui.project;

import manager.ProjectManagerSQL;
import model.Project;
import model.User;

public class InfoPanelController {
    private static InfoPanelController instance = null;
    ProjectManagerSQL projectManagerSQL = ProjectManagerSQL.getInstance();

    private InfoPanelController() {
        // Private constructor to prevent direct instantiation
    }

    public static InfoPanelController getInstance() {
        if (instance == null) {
            synchronized (InfoPanelController.class) {
                if (instance == null) {
                    instance = new InfoPanelController();
                }
            }
        }
        return instance;
    }

    public boolean assigningToProject(User user, Project project) {
        return projectManagerSQL.assignUserToProject(user.getUserId(), project.getProjectId());
    }

}
