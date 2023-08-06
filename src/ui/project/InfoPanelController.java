package ui.project;

import manager.ProjectManagerSQL;
import model.Project;

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

    public Project projectInfo(String name){
        return projectManagerSQL.getProjectByName(name);
    }

}
