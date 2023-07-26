package manager;

import model.Repository;
import model.User;
import model.UserRole;

import java.util.Iterator;
import java.util.List;

public class UserManager {
    final private Repository repository;

    public UserManager() {
        repository = Repository.getInstance();
    }

    public void addUser(String name, String lastName, String email, String password, UserRole role) {
        User user = new User(name, lastName, email, password, role);
        repository.getUsers().add(user);
    }

}


