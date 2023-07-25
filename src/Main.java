import model.DataBase.SaveLoginInfo;
import model.user.User;
import model.user.UserManager;
import model.user.UserRole;
import ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });


        User user = new User("ali","asghari","sh@gmail.com","sha2", UserRole.SUPER_ADMIN);
        User user1 = new User("azi","saraa","khaa@gmail.com","raw", UserRole.SUPER_ADMIN);
        System.out.println(user);
        UserManager userManager = new UserManager();
        userManager.createUser(user);
        userManager.createUser(user1);
        SaveLoginInfo save = new SaveLoginInfo();
        save.saveToFile(SaveLoginInfo.Repository.getUsers());
    }
}