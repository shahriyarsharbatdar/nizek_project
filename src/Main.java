import manager.UserManager;
import model.UserRole;
import ui.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              MainFrame.getInstance();
            }
        });

        UserManager.getInstance().addUser("ali", "asghari", "a@a.com", "12345678", UserRole.SUPER_ADMIN);
        UserManager.getInstance().addUser("shahriyar", "sharbati", "a@2a.com", "1234", UserRole.PO);
        UserManager.getInstance().addUser("shahriyar", "sharbati", "a@2a.com", "1234", UserRole.PO);
        UserManager.getInstance().addUser("shahriyar", "sharbati", "a@2a.com", "1234", UserRole.PO);
        UserManager.getInstance().addUser("shahriyar", "sharbati", "a@2a.com", "1234", UserRole.PO);
        UserManager.getInstance().addUser("shahriyar", "sharbati", "a@2a.com", "1234", UserRole.PO);
        UserManager.getInstance().addUser("shahriyar", "sharbati", "a@2a.com", "1234", UserRole.PO);
        UserManager.getInstance().addUser("mostafa", "sharbati", "a@2a.com", "1234", UserRole.PO);

    }
}