package ui.login;

import manager.UserManagerSQL;
import model.User;

import java.util.regex.Pattern;

public class Controller {
    private final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final String passwordRegex = "^.{8,}$";

    final private Pattern emailPattern = Pattern.compile(emailRegex);
    final private Pattern passwordPattern = Pattern.compile(passwordRegex);
    private static Controller instance = null;
    private User loginUser;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }


    public boolean isValidEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    public boolean isValidPassword(String password) {
        return passwordPattern.matcher(password).matches();
    }

    public boolean regexAuthenticate() {

        // Validate email and password
        if (!isValidEmail(LoginView.getInstance().userEmail)) {
            // Invalid email format, show an error message to the user
            System.out.println("Invalid email format");
            return false;
        }

        if (!isValidPassword(LoginView.getInstance().userPassword)) {
            // Invalid password format, show an error message to the user
            System.out.println("Invalid password format. Password should be at least 8 characters long.");
            return false;
        }
        if (UserManagerSQL.getInstance().authenticate(LoginView.getInstance().userEmail, LoginView.getInstance().userPassword))
            loginUser = UserManagerSQL.getInstance().getUserByEmail(LoginView.getInstance().userEmail);
        return true;
    }



    public User getLoginUser() {
        return loginUser;
    }
}