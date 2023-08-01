package ui.mainPage;

import model.Repository;
import model.User;
import model.UserRole;
import ui.login.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class ViewProfilePanel extends JPanel {
    JLabel nameLabel = new JLabel("Name:");
    JTextField nameTextField = new JTextField(20);

    JLabel lastNameLabel = new JLabel("Last Name:");
    JTextField lastNameTextField = new JTextField(20);

    JLabel emailLabel = new JLabel("Email:");
    JTextField emailTextField = new JTextField(20);

    JLabel passwordLabel = new JLabel("Password:");
    JTextField passwordField = new JTextField(20);

    JLabel projectsLabel = new JLabel("Projects:");
    JLabel roleLabel = new JLabel("Role:");
    JTextField roleField = new JTextField(20);


    User loginUser = Controller.getInstance().getLoginUser();


    public ViewProfilePanel() {
        setBounds(200, 0, 700, 700);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 20, 20, 20); // Padding

        // Add labels and text fields for user information
        if (loginUser != null) {
            nameTextField.setText(Controller.getInstance().getLoginUser().getName());
        }
        nameTextField.setPreferredSize(new Dimension(100, 40));
        add(nameLabel, gbc);
        gbc.gridx++;
        add(nameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        if (loginUser != null) {
            nameTextField.setText(Controller.getInstance().getLoginUser().getLastName());
            System.out.println(Controller.getInstance().getLoginUser().getLastName());
        }
        lastNameTextField.setPreferredSize(new Dimension(100, 40));
        add(lastNameLabel, gbc);
        gbc.gridx++;
        add(lastNameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        if (loginUser != null) {
            nameTextField.setText(Controller.getInstance().getLoginUser().getEmail());
        }
        emailTextField.setPreferredSize(new Dimension(100, 40));
        add(emailLabel, gbc);
        gbc.gridx++;
        add(emailTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        if (loginUser != null) {
            nameTextField.setText(Controller.getInstance().getLoginUser().getPassword());
        }
        passwordField.setPreferredSize(new Dimension(100, 40));
        add(passwordLabel, gbc);
        gbc.gridx++;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JTextField projectsTextField = new JTextField(20);
        projectsTextField.setPreferredSize(new Dimension(100, 40));
        add(projectsLabel, gbc);
        gbc.gridx++;
        add(projectsTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        roleField.setPreferredSize(new Dimension(100, 40)); // Corrected line
        if (loginUser != null) {
            roleField.setText(Controller.getInstance().getLoginUser().getRole().toString());
        }
        add(roleLabel, gbc);
        gbc.gridx++;
        add(roleField, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Define the gradient colors for addMemberPanel
        Color startColor = new Color(253, 206, 223); // Light green
        Color endColor = new Color(248, 232, 238);   // Lighter green

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

    public void setData(User loginUser) {
        nameTextField.setText(loginUser.getName());
        lastNameTextField.setText(loginUser.getLastName());
        emailTextField.setText(loginUser.getEmail());
        passwordField.setText(loginUser.getPassword());
        roleField.setText(loginUser.getRole().toString());
    }

}

