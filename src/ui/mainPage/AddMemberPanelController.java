package ui.mainPage;

import manager.UserManagerSQL;
import model.UserRole;

public class AddMemberPanelController {

    UserManagerSQL userManagerSQL = UserManagerSQL.getInstance();
    private static AddMemberPanelController instance = null;

    private AddMemberPanelController() {

    }

    public static AddMemberPanelController getInstance() {
        if (instance == null) {
            instance = new AddMemberPanelController();
        }
        return instance;
    }

    public void addMemberSql(String name, String lastName, String email, String password, UserRole role){
        userManagerSQL.addUser(name,lastName,email,password,role);
    }


}
