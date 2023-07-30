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

    }
}