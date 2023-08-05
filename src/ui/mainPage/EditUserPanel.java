package ui.mainPage;

import manager.UserManagerSQL;
import model.User;
import model.UserRole;

import javax.management.relation.Role;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditUserPanel extends JFrame {
    JTextField nameTextField = new JTextField();
    JTextField lastNameTextField = new JTextField();
    JTextField emailTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JComboBox<UserRole> roleTextField = new JComboBox<>(UserRole.values());

    // Create submit , cancel and remove buttons

    JButton submitButton = new JButton("Submit") {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();

            // Gradient background
            Color startColor = new Color(0x4CAF50);
            Color endColor = new Color(0x79DC82);
            GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, 0, height, endColor);
            g2D.setPaint(gradientPaint);
            g2D.fillRoundRect(0, 0, width, height, 20, 20);

            // Button text
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Arial", Font.BOLD, 16));
            FontMetrics metrics = g2D.getFontMetrics();
            int textX = (width - metrics.stringWidth(getText())) / 2;
            int textY = (height - metrics.getHeight()) / 2 + metrics.getAscent();
            g2D.drawString(getText(), textX, textY);
        }

    };

    JButton cancelButton = new JButton("Cancel") {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();

            // Gradient background
            Color startColor = new Color(0xF44336);
            Color endColor = new Color(0xEF7373);
            GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, 0, height, endColor);
            g2D.setPaint(gradientPaint);
            g2D.fillRoundRect(0, 0, width, height, 20, 20);

            // Button text
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Arial", Font.BOLD, 16));
            FontMetrics metrics = g2D.getFontMetrics();
            int textX = (width - metrics.stringWidth(getText())) / 2;
            int textY = (height - metrics.getHeight()) / 2 + metrics.getAscent();
            g2D.drawString(getText(), textX, textY);
        }
    };

    JButton removeButton = new JButton("Remove") {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();

            // Gradient background
            Color startColor = new Color(0xFF5722);
            Color endColor = new Color(0xFFA07A);
            GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, 0, height, endColor);
            g2D.setPaint(gradientPaint);
            g2D.fillRoundRect(0, 0, width, height, 20, 20);

            // Button text
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Arial", Font.BOLD, 10));
            FontMetrics metrics = g2D.getFontMetrics();
            int textX = (width - metrics.stringWidth(getText())) / 2;
            int textY = (height - metrics.getHeight()) / 2 + metrics.getAscent();
            g2D.drawString(getText(), textX, textY);
        }
    };


    User user;

    public EditUserPanel(User user) {
        this.user = user;
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);


        // Create the form panel
        JPanel formPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2D = (Graphics2D) g;
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();

                // Gradient background
                Color startColor = new Color(0x64B5F6);
                Color endColor = new Color(0x1976D2);
                GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, 0, height, endColor);
                g2D.setPaint(gradientPaint);
                g2D.fillRect(0, 0, width, height);
            }

        };
        this.setupEnterKeyBinding();

        formPanel.setBounds(0, 0, 700, 500);
        formPanel.setLayout(null);

        // Create labels and text fields for the form
        int labelWidth = 100;
        int labelHeight = 30;
        int textFieldWidth = 200;
        int textFieldHeight = 30;
        int labelX = 150;
        int labelY = 50;
        int textFieldX = 280;
        int textFieldY = 50;
        int verticalSpacing = 50;

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
        formPanel.add(nameLabel);

        nameTextField.setBounds(textFieldX, textFieldY, textFieldWidth, textFieldHeight);
        nameTextField.setText(user.getName());
        formPanel.add(nameTextField);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(labelX, labelY + verticalSpacing, labelWidth, labelHeight);
        nameTextField.setText(user.getName());

        formPanel.add(lastNameLabel);

        lastNameTextField.setText(user.getLastName());
        lastNameTextField.setBounds(textFieldX, textFieldY + verticalSpacing, textFieldWidth, textFieldHeight);
        formPanel.add(lastNameTextField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(labelX, labelY + 2 * verticalSpacing, labelWidth, labelHeight);
        formPanel.add(emailLabel);

        emailTextField.setText(user.getEmail());
        emailTextField.setBounds(textFieldX, textFieldY + 2 * verticalSpacing, textFieldWidth, textFieldHeight);
        formPanel.add(emailTextField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(labelX, labelY + 3 * verticalSpacing, labelWidth, labelHeight);
        formPanel.add(passwordLabel);

        passwordField.setText(user.getPassword());
        passwordField.setBounds(textFieldX, textFieldY + 3 * verticalSpacing, textFieldWidth, textFieldHeight);
        formPanel.add(passwordField);

        JLabel roleLabel = new JLabel("Role");
        roleLabel.setBounds(labelX, labelY + 4 * verticalSpacing, labelWidth, labelHeight);
        formPanel.add(roleLabel);

        roleTextField.setSelectedItem(user.getRole());
        roleTextField.setBounds(textFieldX, textFieldY + 4 * verticalSpacing, textFieldWidth, textFieldHeight);
        formPanel.add(roleTextField);

        //submit button detail
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String lastName = lastNameTextField.getText();
                String email = emailTextField.getText();
                String password = passwordField.getText();
                UserRole role = (UserRole) roleTextField.getSelectedItem();

                EditUserPanelController.getInstance().setNewUser(email, name, lastName, password, role);
                EditUserPanel.this.dispose();

            }
        });
        submitButton.setBounds(180, 350, 150, 40);
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setContentAreaFilled(false);
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submitButton.setCursor(Cursor.getDefaultCursor());
            }
        });
        formPanel.add(submitButton);

        //cancel button detail
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditUserPanel.this.dispose();
            }
        });
        cancelButton.setBounds(370, 350, 150, 40);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorderPainted(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancelButton.setCursor(Cursor.getDefaultCursor());
            }
        });
        formPanel.add(cancelButton);

        // Add the form panel to the frame
        add(formPanel);

        //remove button detail
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserManagerSQL.getInstance().removeUser(user.getEmail());
                EditUserPanel.this.dispose();
            }
        });

        removeButton.setBounds(30, 400, 50, 20);
        removeButton.setFocusPainted(false);
        removeButton.setBorderPainted(false);
        removeButton.setContentAreaFilled(false);
        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                removeButton.setCursor(Cursor.getDefaultCursor());
            }
        });
        formPanel.add(removeButton);

    }
    public void setupEnterKeyBinding() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();
        String enterKey = "enterKey";
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), enterKey);
        actionMap.put(enterKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton.doClick(); // Replace 'okButton' with the actual reference to your button
            }
        });
    }
}
