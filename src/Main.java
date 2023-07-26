import manager.UserManager;
import ui.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              MainFrame.getInstance();
            }
        });


        UserManager userManager = new UserManager();
//        userManager.createUser(user);
//        userManager.createUser(user1);
//        SaveLoginInfo save = new SaveLoginInfo();
//        save.saveToFile(SaveLoginInfo.Repository.getUsers());
    }
}