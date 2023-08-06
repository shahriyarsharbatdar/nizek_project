package ui.project;

import manager.ProjectManagerSQL;
import manager.UserManagerSQL;
import model.Project;
import model.User;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    JLabel nameText = new JLabel("Name");
    JTextArea projectName = new JTextArea();
    JLabel detailText = new JLabel("Detail");
    JTextArea projectDetail = new JTextArea();
    JComboBox allUsersBox = new JComboBox<>();


    public InfoPanel(Project project) {
        setBounds(0, 50, 900, 650);
        setBackground(new Color(255, 240, 220)); // Light peach background
        setVisible(false);
        setLayout(null);

        nameText.setBounds(100, 70, 100, 30);
        nameText.setFont(new Font("Arial", Font.PLAIN, 16));
        add(nameText);

        projectName.setBounds(100, 100, 200, 30);
        projectName.setBackground(Color.white);
        projectName.setFont(new Font("Arial", Font.PLAIN, 14));
        projectName.setEditable(false);
        add(projectName);

        detailText.setBounds(100, 370, 100, 30);
        detailText.setFont(new Font("Arial", Font.PLAIN, 16));
        add(detailText);

        projectDetail.setBounds(100, 400, 200, 150);
        projectDetail.setBackground(Color.white);
        projectDetail.setFont(new Font("Arial", Font.PLAIN, 14));
        projectDetail.setEditable(false);
        projectDetail.setLineWrap(true);
        projectDetail.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        JScrollPane scrollPane = new JScrollPane(projectDetail);
        scrollPane.setBounds(100, 400, 200, 150);
        add(scrollPane);

        allUsersBox.setBounds(600,60,200,100);
        add(allUsersBox);
        setProjectInfo(project);

        for (User user :UserManagerSQL.getInstance().getUsers()) {
            allUsersBox.addItem(user.getName());
        }
        UserManagerSQL.getInstance().getUsers().get(allUsersBox.getSelectedIndex());

    }

    // method to set project name and detail
    public void setProjectInfo(Project project) {
        projectName.setText(project.getName());
        projectDetail.setText(project.getDescription());
    }
}
