package ui.project;

import manager.IssueManagerSQL;
import manager.UserManagerSQL;
import model.*;
import model.components.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IssueFormPanel extends JFrame {
    private final JTextField titleField;
    private final JTextField issueAssigneTo;
    private final JTextField descriptionField;
    private final JComboBox<Status> statusComboBox = new JComboBox<>(Status.values());
    private final JComboBox<IssueType> issueTypeComboBox = new JComboBox<>(IssueType.values());
    private final JComboBox<Priority> priorityComboBox = new JComboBox<>(Priority.values());
    private final JComboBox<String> userComboBox = new JComboBox<>();
    private Issue issue;

    public IssueFormPanel(IssuesPanel issuesPanel, Issue issue1) {
        if (issue1!=null) this.issue = IssueManagerSQL.getInstance().getIssueById(issue1.getIssueId());



        // Set frame properties
        setTitle((issue == null) ? "New Issue" : issue.getTitle());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(240, 240, 240));


        // Labels and fields
        issueAssigneTo = new JTextField();
        JLabel assigneeTo = new JLabel("Assigned to:");
        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        JLabel statusLabel = new JLabel("Status:");
        JLabel typeLabel = new JLabel("Type:");
        JLabel priorityLabel = new JLabel("Priority:");
        JLabel userLabel = new JLabel("User:");

        // Set positions and values of components
        assigneeTo.setBounds(250,40,100,20);
        issueAssigneTo.setBounds(330, 40, 80, 20);
        titleLabel.setBounds(10, 10, 50, 20);
        titleField.setBounds(70, 10, 300, 20);
        descriptionLabel.setBounds(10, 40, 80, 20);
        descriptionField.setBounds(10, 70, 360, 80);
        statusLabel.setBounds(10, 160, 50, 20);
        statusComboBox.setBounds(60, 160, 130, 30);
        typeLabel.setBounds(200, 160, 50, 20);
        issueTypeComboBox.setBounds(240, 160, 130, 30);
        priorityLabel.setBounds(10, 200, 50, 20);
        priorityComboBox.setBounds(60, 200, 130, 30);
        userLabel.setBounds(200, 200, 50, 20);
        userComboBox.setBounds(240, 200, 130, 30);

        // Set component values if issue is not null
        if (issue != null) {
            titleField.setText(issue.getTitle());
            descriptionField.setText(issue.getDescription());
            statusComboBox.setSelectedItem(issue.getStatus());
            issueTypeComboBox.setSelectedItem(issue.getIssueType());
            priorityComboBox.setSelectedItem(issue.getPriority());
            if(UserManagerSQL.getInstance().getUserById(issue.getUserId()) != null){issueAssigneTo.setText(UserManagerSQL.getInstance().getUserById(issue.getUserId()).getName());}
        }

        // Buttons
        JButton createButton = new JButton("Create");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

// Apply styles to buttons
        issueAssigneTo.setBackground(new Color(240, 240, 240));
        issueAssigneTo.setBorder(null);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        createButton.setFont(buttonFont);
        editButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        createButton.setForeground(Color.white);
        editButton.setForeground(Color.white);
        deleteButton.setForeground(Color.white);
        createButton.setFocusPainted(false);
        editButton.setFocusPainted(false);
        deleteButton.setFocusPainted(false);
        createButton.setBorderPainted(false); // Remove default border
        editButton.setBorderPainted(false);   // Remove default border
        deleteButton.setBorderPainted(false); // Remove default border
        createButton.setOpaque(true);
        editButton.setOpaque(true);
        deleteButton.setOpaque(true);
        createButton.setBackground(new Color(76, 175, 80)); // Green color
        editButton.setBackground(new Color(33, 150, 243)); // Blue color
        deleteButton.setBackground(new Color(244, 67, 54)); // Red color

// Set custom rounded border
        createButton.setBorder(new RoundedBorder(new Color(76, 175, 80)));
        editButton.setBorder(new RoundedBorder(new Color(33, 150, 243)));
        deleteButton.setBorder(new RoundedBorder(new Color(244, 67, 54)));

        // Set button positions
        createButton.setBounds(150, 240, 80, 25);
        editButton.setBounds(50, 240, 80, 25);
        deleteButton.setBounds(250, 240, 80, 25);

        // Create listeners for buttons
        createButton.addActionListener(createButtonListener(issuesPanel));
        editButton.addActionListener(editButtonListener(issuesPanel));
        deleteButton.addActionListener(deleteButtonListener(issuesPanel));

        // Populate userComboBox
        userComboBox.addItem(null);
        for (User user : UserManagerSQL.getInstance().getUsers()) {
            userComboBox.addItem(user.getName());
        }

        // Add components to panel
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(statusLabel);
        panel.add(statusComboBox);
        panel.add(typeLabel);
        panel.add(issueTypeComboBox);
        panel.add(priorityLabel);
        panel.add(priorityComboBox);
        panel.add(userLabel);
        panel.add(userComboBox);
        panel.add(createButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(issueAssigneTo);
        panel.add(assigneeTo);

        // Add panel to frame
        getContentPane().add(panel);
    }

    private ActionListener createButtonListener(IssuesPanel issuesPanel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (statusComboBox.getSelectedItem() == Status.TO_DO) {
                    // Create new issue
                    Issue newIssue = IssueManagerSQL.getInstance().addIssue(
                            titleField.getText(), descriptionField.getText(),
                            statusComboBox.getItemAt(statusComboBox.getSelectedIndex()).toString(),
                            issueTypeComboBox.getItemAt(issueTypeComboBox.getSelectedIndex()).toString(),
                            priorityComboBox.getItemAt(priorityComboBox.getSelectedIndex()).toString(),
                            issuesPanel.getProject().getProjectId()
                    );
                    issuesPanel.createNewBoardPanel(newIssue.getIssueId());
                    issuesPanel.getProjectPageFrame().issuesButton.doClick();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Status for creating issue must be 'TO_DO'");
                }
            }
        };
    }

    private ActionListener editButtonListener(IssuesPanel issuesPanel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (issue != null) {
                    // Edit issue
                    String title = titleField.getText();
                    String description = descriptionField.getText();
                    Status status = (Status) statusComboBox.getSelectedItem();
                    IssueType issueType = (IssueType) issueTypeComboBox.getSelectedItem();
                    Priority priority = (Priority) priorityComboBox.getSelectedItem();
                    User user =  UserManagerSQL.getInstance().getUserByName((String) userComboBox.getSelectedItem());
                    System.out.println(userComboBox.getSelectedItem());
                    IssueManagerSQL.getInstance().updateIssue(issue.getIssueId(), user.getUserId(), title, description, status, issueType, priority);
                    issuesPanel.updateIssuePanel(issue.getIssueId());
                }
                dispose(); // Close the form after editing
            }
        };
    }

    private ActionListener deleteButtonListener(IssuesPanel issuesPanel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (issue != null) {
                    // Delete issue
                    IssueManagerSQL.getInstance().removeIssue(issue.getIssueId());
                    issuesPanel.removeIssuePanel(issue.getIssueId());
                    issuesPanel.revalidate();
                    issuesPanel.repaint();
                    issuesPanel.updateUI();
                    dispose(); // Close the form after deletion
                }
            }
        };
    }
}
