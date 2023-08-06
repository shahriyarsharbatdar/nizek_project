package ui.mainPage;

import manager.UserManagerSQL;
import model.User;
import model.UserRole;

public class EditUserPanelController {
    private static EditUserPanelController instance;

    // Private constructor to prevent instantiation from other classes
    private EditUserPanelController() {
        // Initialization code here, if needed
    }

    public static EditUserPanelController getInstance() {
        if (instance == null) {
            synchronized (EditUserPanelController.class) {
                if (instance == null) {
                    instance = new EditUserPanelController();
                }
            }
        }
        return instance;
    }
    UserManagerSQL userManagerSQL = UserManagerSQL.getInstance();
    public User userInfo(String email){

         return userManagerSQL.getUserByEmail(email);
    }
    public void setNewUser(String name, String lastName, String email, String password, UserRole userRole){
        userManagerSQL.editUser(name,lastName,email,password,userRole);
    }

}
