import manager.UserManager;
import manager.UserManagerSQL;
import model.User;
import model.UserRole;
import ui.MainFrame;

import javax.management.relation.Role;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              MainFrame.getInstance();
            }
        });

        User user=new User("aa","aa","a@a.com","12345678",UserRole.SUPER_ADMIN);
        if (user == null){
            UserManagerSQL.getInstance().addUser(user.getName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRole());
        }


    }
}