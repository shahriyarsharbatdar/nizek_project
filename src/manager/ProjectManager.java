package manager;

import model.Project;
import model.Repository;

public class ProjectManager {
    private static ProjectManager instance = null;
    private Repository repository = new Repository();

    private ProjectManager() {
        // repository = Repository.getInstance();
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
        int newProjectId = repository.getProjectMap().size() + 1;
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
}
