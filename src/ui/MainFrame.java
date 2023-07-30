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
//        add(loginView);
        MainPanel mainPanel1=new MainPanel();
        add(mainPanel1);
        setLayout(null);
        setVisible(true);
        add(mainPanel);
        mainPanel.setVisible(false);
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }


}
