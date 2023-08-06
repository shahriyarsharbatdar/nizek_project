package ui;
import manager.ProjectManagerSQL;
import ui.login.LoginView;
import ui.mainPage.MainPanel;
import ui.project.ProjectPageFrame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    public MainPanel mainPanel = new MainPanel();
    public ProjectPageFrame projectPagePanel = new ProjectPageFrame(ProjectManagerSQL.getInstance(),1);

    private MainFrame() {
        super("Nizek");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(20, 195, 142));
        mainPanel.setBounds(0,0,getWidth(),getHeight());
//        add(LoginView.getInstance());
        add(mainPanel);
//        add(projectPagePanel);
        mainPanel.setVisible(true);
        setLayout(null);
        setVisible(true);
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }


}
