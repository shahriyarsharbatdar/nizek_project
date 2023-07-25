package ui;
import ui.login.LoginView;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Login");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(20, 195, 142));
        LoginView loginView = new LoginView();
        add(loginView);
        setLayout(null);
        setVisible(true);
    }
}
