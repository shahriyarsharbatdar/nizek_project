package ui.mainPage;

import manager.UserManagerSQL;
import model.User;

public class EditUserPanelController {
    UserManagerSQL userManagerSQL = UserManagerSQL.getInstance();
//    EditUserPanel editUserPanel = new EditUserPanel()
    public User userInfo(String email){

         return userManagerSQL.getUserByEmail(email);
    }

}
