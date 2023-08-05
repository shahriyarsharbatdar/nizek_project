package ui.project;

import manager.ProjectManagerSQL;

import javax.swing.*;
import java.awt.*;

public class ProjectPageFrame extends JFrame {
    JPanel topPanel = new JPanel();
    ProjectManagerSQL projectManagerSQL;
    int selectedProject;

    public ProjectPageFrame(ProjectManagerSQL projectManagerSQL, int selectedProject) {
        this.projectManagerSQL = projectManagerSQL;
        this.selectedProject = selectedProject;
        System.out.println(selectedProject);
        setSize( 900, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setBackground(Color.blue);
        setLayout(null);
        setVisible(true);

        // Create a top panel to hold the buttons
        topPanel.setBounds(0, 0, 900, 50);
        topPanel.setBackground(Color.lightGray);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Horizontally centered with spacing

        // Create the four buttons
        JButton button1 = new JButton("info");
        JButton button2 = new JButton("board");
        JButton button3 = new JButton("issues");
        JButton button4 = new JButton("report");

        // Add the buttons to the top panel
        topPanel.add(button1);
        topPanel.add(button2);
        topPanel.add(button3);
        topPanel.add(button4);

        // Add the top panel to the main panel
        add(topPanel);

        // Set the layout for the main panel to null
        // so you can manually set the positions of other components
    }
}
