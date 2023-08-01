package ui.mainPage;

import manager.ProjectManager;
import manager.UserManager;
import model.UserRole;

public class AddProjectController {
    AddProjectPanel addProjectPanel = new AddProjectPanel();
    ProjectManager projectManager = ProjectManager.getInstance();
    private static AddProjectController instance = null;
    private AddProjectController() {

    }

    public static AddProjectController getInstance() {
        if (instance == null) {
            instance = new AddProjectController();
        }
        return instance;
    }
    public void addProject(String name, String detail) {
        projectManager.addProject(name, detail);
    }

}
