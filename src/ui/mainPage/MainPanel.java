package ui.mainPage;

import ui.login.Controller;
import ui.login.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class MainPanel extends JPanel {

    private AddMemberPanel addMemberPanel = new AddMemberPanel();
    private ViewProfilePanel viewProfilePanel = new ViewProfilePanel();
    private AddProjectPanel addProjectPanel = new AddProjectPanel();

    private JPanel sidebarPanel;

    public MainPanel() {
        setPreferredSize(new Dimension(900, 700));
        setBackground(new Color(245, 245, 245)); // Light gray background

        // Create the sidebar panel on the left
        sidebarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2D = (Graphics2D) g;
                int rectWidth = 200;
                int rectHeight = 700;
                int rectX = 0; // Offset from the left
                int rectY = 0;

                g2D.setColor(new Color(166, 208, 221)); // Blue background for the sidebar
                g2D.fillRect(rectX, rectY, rectWidth, rectHeight);
            }
        };
        sidebarPanel.setPreferredSize(new Dimension(210, 0)); // Set a fixed width for the sidebar
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

        // Add welcome label to the sidebar
        JLabel welcomeLabel = new JLabel("Welcome, Admin");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 17));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(Box.createVerticalStrut(150)); // Vertical spacing
        sidebarPanel.add(welcomeLabel);
        sidebarPanel.add(Box.createVerticalStrut(150));


        // Create and add the rounded "View Profile" button
        JButton viewProfileButton = createRoundedButton("View Profile");
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMemberPanel.setVisible(false);
                viewProfilePanel.setVisible(true);
                viewProfilePanel.setData(Controller.getInstance().getLoginUser());
            }
        });
        sidebarPanel.add(viewProfileButton);
        sidebarPanel.add(Box.createVerticalStrut(20));


        // Create and add the rounded "Add Member" button
        JButton addMemberButton = createRoundedButton("Add Member");
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMemberPanel.setVisible(true);
                viewProfilePanel.setVisible(false);
                addProjectPanel.setVisible(false);
            }
        });
        sidebarPanel.add(addMemberButton);
        sidebarPanel.add(Box.createVerticalStrut(20));


        // Create and add the rounded "Add Project" button
        JButton addProjectButton = createRoundedButton("Add Project");
        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMemberPanel.setVisible(false);
                viewProfilePanel.setVisible(false);
                addProjectPanel.setVisible(true);
            }
        });
        sidebarPanel.add(addProjectButton);
        sidebarPanel.add(Box.createVerticalStrut(20));


        // Create and add the rounded "Main" button
        JButton mainButton = createRoundedButton("Main");
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMemberPanel.setVisible(false);
                viewProfilePanel.setVisible(false);
                addProjectPanel.setVisible(false);
            }
        });
        sidebarPanel.add(mainButton);
        sidebarPanel.add(Box.createVerticalStrut(20));


        // Create and add the rounded "Logout" button
        JButton logoutButton = createRoundedButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logout functionality here
            }
        });
        sidebarPanel.add(logoutButton);

        // Add the sidebar panel to the main panel using BorderLayout
        setLayout(new BorderLayout());
        add(sidebarPanel, BorderLayout.WEST);

        // Add other panels
        add(addMemberPanel);
        add(viewProfilePanel);
        add(addProjectPanel);

        addMemberPanel.setVisible(false);
        viewProfilePanel.setVisible(false);
        addProjectPanel.setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Define the gradient colors
        Color startColor = new Color(255, 211, 176);
        Color endColor = new Color(255, 249, 222);

        // Create a gradient paint object from top to bottom
        GradientPaint gradientPaint = new GradientPaint(
                new Point2D.Double(width / 2.0, 0),
                startColor,
                new Point2D.Double(width / 2.0, height),
                endColor
        );

        // Set the paint to the graphics object
        g2D.setPaint(gradientPaint);

        // Fill the MainPanel with the gradient
        g2D.fillRect(0, 0, width, height);
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
}
