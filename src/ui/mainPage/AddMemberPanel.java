package ui.mainPage;

import model.UserRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class AddMemberPanel extends JPanel {

    JPanel buttonPanel = new JPanel();

    public AddMemberPanel() {
        setBounds(200, 0, 700, 700);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 20, 20, 20); // Padding

        // Add labels and text fields for user information
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameTextField = new JTextField(20);
        nameTextField.setPreferredSize(new Dimension(100, 40));
        add(nameLabel, gbc);
        gbc.gridx++;
        add(nameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameTextField = new JTextField(20);
        lastNameTextField.setPreferredSize(new Dimension(100, 40));
        add(lastNameLabel, gbc);
        gbc.gridx++;
        add(lastNameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailTextField = new JTextField(20);
        emailTextField.setPreferredSize(new Dimension(100, 40));
        add(emailLabel, gbc);
        gbc.gridx++;
        add(emailTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField(20);
        passwordField.setPreferredSize(new Dimension(100, 40));
        add(passwordLabel, gbc);
        gbc.gridx++;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel projectsLabel = new JLabel("Projects:");
        JTextField projectsTextField = new JTextField(20);
        projectsTextField.setPreferredSize(new Dimension(100, 40));
        add(projectsLabel, gbc);
        gbc.gridx++;
        add(projectsTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel roleLabel = new JLabel("Role:");
        JComboBox<String> roleComboBox = new JComboBox<>();
        for (UserRole role : UserRole.values()) {
            String roleName = "";
            switch (role) {
                case PO:
                    roleName = "Project Owner";
                    break;
                case DEVELOPER:
                    roleName = "Developer";
                    break;
                case QA:
                    roleName = "QA";
                    break;
            }
            roleComboBox.addItem(roleName);
        }
        add(roleLabel, gbc);
        gbc.gridx++;
        add(roleComboBox, gbc);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setBackground(new Color(0, 0, 0, 0));
        // Create the "Cancel" button

        JButton cancelButton = new JButton("cancel") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(170, 65, 65));
                } else {
                    g.setColor(Color.red);
                }


                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // No border
            }
        };
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.setForeground(Color.WHITE); // Set the font color to white
        cancelButton.setFocusPainted(false);
        cancelButton.setBorderPainted(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setBorderPainted(false);

        // Create the "Submit" button
        JButton submitButton = new JButton("Submit") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(65, 170, 65)); // Darker blue when clicked
                } else {
                    g.setColor(Color.GREEN); // Darker blue when clicked
                }


                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // No border
            }
        };
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRole selectedRole = null;
                switch (roleComboBox.getSelectedIndex()) {
                    case 1 -> selectedRole = UserRole.PO;
                    case 2 -> selectedRole = UserRole.QA;
                    case 3 -> selectedRole = UserRole.DEVELOPER;
                }
                AddMemberPanelController.getInstance().addMemberSql(nameTextField.getText(), lastNameTextField.getText()
                        , emailTextField.getText(), passwordField.getText(), selectedRole);
            }
        });
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setForeground(Color.WHITE); // Set the font color to white
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);

        // Add the buttons to the buttonPanel
        buttonPanel.add(cancelButton);
        buttonPanel.add(submitButton);

        // Add the buttonPanel to the bottom of the addMemberView
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Make the buttons span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 5, 5, 5); // Padding for the buttons
        add(buttonPanel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Define the gradient colors for addMemberPanel
        Color startColor = new Color(200, 240, 200); // Light green
        Color endColor = new Color(220, 255, 220);   // Lighter green

        // Create a gradient paint object from top to bottom
        GradientPaint gradientPaint = new GradientPaint(
                new Point2D.Double(width / 2.0, 0),
                startColor,
                new Point2D.Double(width / 2.0, height),
                endColor
        );

        // Set the paint to the graphics object
        g2D.setPaint(gradientPaint);

        // Fill the addMemberPanel with the gradient
        g2D.fillRect(0, 0, width, height);
    }

}

