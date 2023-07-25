package ui.login;

import model.DataBase.SaveLoginInfo;

public class Controller {

    public boolean authentication( String userInput, String userInDataBase) {
        userInput = LoginView.getInstance().getUserEmail();
    }
}