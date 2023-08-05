package ui.mainPage;

import manager.ProjectManagerSQL;

public class AddProjectController {
    ProjectManagerSQL projectManagerSQL = ProjectManagerSQL.getInstance();
    private static AddProjectController instance = null;

    private AddProjectController() {

    }

    public static AddProjectController getInstance() {
        if (instance == null) {
            instance = new AddProjectController();
        }
        return instance;
    }

    public void addProjectSql(String name, String description) {
        projectManagerSQL.addProject(name, description);
    }

}
