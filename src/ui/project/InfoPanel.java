package ui.project;

import manager.ProjectManagerSQL;
import manager.UserManagerSQL;
import model.Project;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoPanel extends JPanel {
    JLabel nameText = new JLabel("Name");
    JTextArea projectName = new JTextArea();
    JLabel detailText = new JLabel("Detail");
    JTextArea projectDetail = new JTextArea();
    JLabel allUsers = new JLabel("All members");
    JComboBox<String> allUsersBox = new JComboBox<>();
    ProjectUsersTable projectUsersTable;
    InfoPanelController infoPanelController = InfoPanelController.getInstance();


    public InfoPanel(Project project, ActionListener disposeActionListener) {
        setBounds(0, 50, 900, 650);
        setBackground(new Color(255, 240, 220)); // Light peach background
        setVisible(false);
        setLayout(null);
        projectUsersTable = new ProjectUsersTable(ProjectManagerSQL.getInstance(), project);
        add(projectUsersTable);


        nameText.setBounds(100, 70, 100, 30);
        nameText.setFont(new Font("Arial", Font.PLAIN, 16));
        add(nameText);

        projectName.setBounds(100, 100, 200, 30);
        projectName.setBackground(Color.white);
        projectName.setFont(new Font("Arial", Font.PLAIN, 14));
        projectName.setEditable(true);
        add(projectName);

        detailText.setBounds(100, 370, 100, 30);
        detailText.setFont(new Font("Arial", Font.PLAIN, 16));
        add(detailText);

        projectDetail.setBounds(100, 400, 200, 150);
        projectDetail.setBackground(Color.white);
        projectDetail.setFont(new Font("Arial", Font.PLAIN, 14));
        projectDetail.setEditable(true);
        projectDetail.setLineWrap(true);
        projectDetail.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        JScrollPane scrollPane = new JScrollPane(projectDetail);
        scrollPane.setBounds(100, 400, 200, 150);
        add(scrollPane);


        //user combo box

        allUsers.setBounds(710, 30, 100, 50);
        for (User user : UserManagerSQL.getInstance().getUsers()) {
            allUsersBox.addItem(user.getName());
        }
        add(allUsers);

        allUsersBox.setBounds(600, 70, 200, 25);
        add(allUsersBox);

        //fill name and description box
        setProjectInfo(project);


//        USER IN BOX = UserManagerSQL.getInstance().getUsers().get(allUsersBox.getSelectedIndex());


        JButton addUserToProjectButton = createRoundedButton("Add");
        addUserToProjectButton.setBounds(600, 100, 100, 30);
        addUserToProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (infoPanelController.assigningToProject(UserManagerSQL.getInstance().getUsers().get(allUsersBox.getSelectedIndex()), project)) {
                    projectUsersTable.refreshTable();
                } else {
                    JOptionPane.showMessageDialog(null, "member is already exist!");

                }
            }
        });
        add(addUserToProjectButton);

        JButton removeUserFromProjectButton = createRoundedButton("Remove");
        removeUserFromProjectButton.setBounds(710, 100, 100, 30);
        removeUserFromProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectManagerSQL.getInstance().softDeleteUserFromProject(UserManagerSQL.getInstance().getUsers().get(allUsersBox.getSelectedIndex()).getUserId(),
                        project.getProjectId());
                projectUsersTable.refreshTable();

            }
        });
        add(removeUserFromProjectButton);

        //save and delete project button

        JButton editProject = createRoundedButton("Edit project");
        editProject.setBounds(250, 250, 170, 30);
        editProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectManagerSQL.getInstance().editProject(project.getProjectId(), projectName.getText(), projectDetail.getText());
            }
        });
        add(editProject);

        JButton removeProject = createRoundedButton("Remove project");
        removeProject.setBounds(40, 250, 170, 30);
        removeProject.addActionListener(disposeActionListener);
        removeProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectManagerSQL.getInstance().removeProject(project.getProjectId());

            }
        });

        add(removeProject);
    }


    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(145, 206, 252)); // Darker blue when clicked
                } else {
                    g.setColor(new Color(66, 165, 245)); // Light blue button
                }

                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        button.setForeground(Color.WHITE); // Set the font color to white
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(150, 30));
        return button;

    }


    // method to set project name and detail
    public void setProjectInfo(Project project) {
        projectName.setText(project.getName());
        projectDetail.setText(project.getDescription());
    }
}
