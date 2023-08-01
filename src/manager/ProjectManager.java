package manager;

import model.Project;
import model.Repository;
import model.User;

import java.util.HashMap;

public class ProjectManager {
    private static ProjectManager instance = null;
    Repository repository = Repository.getInstance();

    private ProjectManager() {
    }

    public static ProjectManager getInstance() {
        if (instance == null) {
            synchronized (ProjectManager.class) {
                if (instance == null) {
                    instance = new ProjectManager();
                }
            }
        }
        return instance;
    }

    public void addProject(String name, String details) {
        int newProjectId = repository.getProjectMap().size();
        Project newProject = new Project(newProjectId, name, details);
        repository.getProjectMap().put(newProjectId, newProject);
        System.out.println(repository.getProjectMap());
    }

    public void removeProject(int projectId) {
        repository.getProjectMap().remove(projectId);
    }

    // You can add more methods to edit, retrieve projects, etc.
    // For example: public void editProject(int projectId, String name, String details) { ... }

    public Project getProjectById(int projectId) {
        return repository.getProjectMap().get(projectId);
    }

    public HashMap<Integer, Project> getAllProject() {
        return repository.getProjectMap();
    }
}
