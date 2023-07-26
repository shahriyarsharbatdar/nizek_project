package ui;

import ui.login.LoginView;
import ui.mainPage.MainPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    public MainPanel mainPanel = new MainPanel();

    private MainFrame() {
        super("Login");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(20, 195, 142));
        LoginView loginView = LoginView.getInstance();
        add(loginView);
        setLayout(null);
        setVisible(true);
        add(mainPanel);
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    // You can add other methods and functionalities here.

}
