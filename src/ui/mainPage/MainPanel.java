package ui.mainPage;

import model.UserRole;
import ui.MainFrame;
import ui.login.Controller;

import javax.management.relation.Role;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class MainPanel extends JPanel {
    AddMemberPanel addMemberPanel = new AddMemberPanel();


    JPanel sidebarPanel = new JPanel() {
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

    public MainPanel() {
        setBounds(0, 0, 900, 700);
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245)); // Light gray background
        // Create the sidebar panel on the left
        add(sidebarPanel);
        add(addMemberPanel);
        addMemberPanel.setVisible(false);

        sidebarPanel.setPreferredSize(new Dimension(220, 0)); // Set a fixed width for the sidebar

        // Add welcome label, profile button, and logout button to the sidebar
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.add(Box.createVerticalStrut(200)); // Vertical spacing

        JLabel welcomeLabel = new JLabel("Welcome, Admin");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(welcomeLabel);

        sidebarPanel.add(Box.createVerticalStrut(100)); // Vertical spacing

        // Create and add the rounded "View Profile" button
        JButton viewProfileButton = new JButton("View Profile") {
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

            @Override
            protected void paintBorder(Graphics g) {
                // No border
            }
        };
        viewProfileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewProfileButton.setForeground(Color.WHITE); // Set the font color to white
        viewProfileButton.setFocusPainted(false);
        viewProfileButton.setBorderPainted(false);
        viewProfileButton.setContentAreaFilled(false);
        sidebarPanel.add(viewProfileButton);

// Add vertical spacing between "View Profile" and "Logout" buttons
        sidebarPanel.add(Box.createVerticalStrut(20));
        // Create and add the rounded "Add Member" button
        JButton addMemberButton = new JButton("Add Member") {

            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(145, 206, 252)); // Darker blue when clicked
                } else {
                    g.setColor(new Color(66, 165, 245)); // Light blue button
                }

                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }



            @Override
            protected void paintBorder(Graphics g) {
                // No border
            }
        };

        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMemberPanel.setVisible(true);
            }
        });

        addMemberButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMemberButton.setForeground(Color.WHITE); // Set the font color to white
        addMemberButton.setFocusPainted(false);
        addMemberButton.setBorderPainted(false);
        addMemberButton.setContentAreaFilled(false);
        sidebarPanel.add(addMemberButton);

        add(sidebarPanel, BorderLayout.WEST);
        // Add vertical spacing between "View Profile" and "add member" buttons
        sidebarPanel.add(Box.createVerticalStrut(20));

        // Create and add the rounded "Logout" button
        JButton logoutButton = new JButton("Logout") {
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

            @Override
            protected void paintBorder(Graphics g) {
                // No border
            }
        };
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setForeground(Color.WHITE); // Set the font color to white
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        sidebarPanel.add(logoutButton);

        add(sidebarPanel, BorderLayout.WEST);

    }
    @Override
    protected void paintComponent (Graphics g){
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
}
