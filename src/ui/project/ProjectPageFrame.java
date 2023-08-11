package ui.project;

import manager.ProjectManagerSQL;
import model.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectPageFrame extends JFrame {
    JPanel topPanel = new JPanel();

    // Create the four buttons

    JButton infoButton = new JButton("info");
    JButton boardsButton = new JButton("boards");
    JButton issuesButton = new JButton("issues");
    JButton reportButton = new JButton("report");
    ProjectManagerSQL projectManagerSQL;
    InfoPanel infoPanel;
    BasePanelForBoard basePanelForBoard;
    IssuesPanel issuesPanel;
    ReportsPanel reportsPanel = new ReportsPanel();
    Project project;


    public ProjectPageFrame(ProjectManagerSQL projectManagerSQL, Project project) {
        this.project = project;
        this.projectManagerSQL = projectManagerSQL;
        basePanelForBoard = new BasePanelForBoard(project);
        setSize(900, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        add(basePanelForBoard);
        add(reportsPanel);
        setLayout(null);
        setVisible(false);

        //"info" button
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPanels();
                infoPanel = new InfoPanel(project, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                infoPanel.setVisible(true);
                add(infoPanel);
            }
        });
        infoButton.doClick();

        //"boards" button

        boardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPanels();
                System.out.println("hi");
                basePanelForBoard.setVisible(true);
            }
        });

        //"issues" button

        issuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPanels();
                issuesPanel = new IssuesPanel(project,ProjectPageFrame.this);
                add(issuesPanel);
                issuesPanel.setVisible(true);
            }
        });

        //"report" button

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPanels();

            }
        });

        // Create a top panel to hold the buttons
        topPanel.setBounds(0, 0, 900, 50);
        topPanel.setBackground(Color.lightGray);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Horizontally centered with spacing


        // Add the buttons to the top panel
        topPanel.add(infoButton);
        topPanel.add(boardsButton);
        topPanel.add(issuesButton);
        topPanel.add(reportButton);

        // Add the top panel to the main panel
        add(topPanel);

        // Set the layout for the main panel to null
        // you can manually set the positions of other components
    }

    public void resetPanels() {
        if (infoPanel != null) infoPanel.setVisible(false);
        if (basePanelForBoard != null) basePanelForBoard.setVisible(false);
        if (issuesPanel != null) issuesPanel.setVisible(false);
        if (reportsPanel != null) reportsPanel.setVisible(false);
    }

}
