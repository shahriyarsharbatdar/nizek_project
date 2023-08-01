package manager;
import model.Repository;
import model.User;
import model.UserRole;

public class UserManager {
    private static UserManager instance = null;
    Repository repository = new Repository();

    private UserManager() {
//        repository = Repository.getInstance();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    public void addUser(String name, String lastName, String email, String password, UserRole role) {
        int newUserId = repository.getUserMap().size() + 1;
        User newUser = new User(name, lastName, email, password, newUserId, role);
        repository.getUserMap().put(newUserId, newUser);
        repository.getUserByEmail().put(newUser.getEmail(),newUser);
    }

    public void removeUser(User currentUser, int userIdToRemove) {
        if (currentUser.getRole() == UserRole.SUPER_ADMIN && userIdToRemove != currentUser.getUserId()) {
            repository.getUserMap().remove(userIdToRemove);
        }
    }

    public void editUser(User currentUser, int userIdToEdit, String name, String lastName, String email, String password, UserRole role) {
        if (currentUser.getRole() == UserRole.SUPER_ADMIN && userIdToEdit != currentUser.getUserId()) {
            User userToEdit = repository.getUserMap().get(userIdToEdit);
            if (userToEdit != null) {
                userToEdit.setName(name);
                userToEdit.setLastName(lastName);
                userToEdit.setEmail(email);
                userToEdit.setPassword(password);
                userToEdit.setRole(role);
            }
        }
    }

    public User getUserById(int userId) {
        return repository.getUserMap().get(userId);
    }

    public User getUserByEmail(String email) {
        return repository.getUserByEmail().get(email);
    }
}
