package ui.project;

import manager.IssueManagerSQL;
import model.Issue;
import model.IssueType;
import model.Priority;
import model.Status;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IssueFormPanel extends JFrame {
    private final JTextField titleField;
    private final JTextField descriptionField;
    private final JComboBox<Status> statusComboBox = new JComboBox<>(Status.values());
    private final JComboBox<IssueType> issueTypeComboBox = new JComboBox<>(IssueType.values());
    private final JComboBox<Priority> priorityComboBox = new JComboBox<>(Priority.values());
    private Issue issue;

    public IssueFormPanel(IssuesPanel issuesPanel, Issue issue1) {
        if (issue1!=null) this.issue = IssueManagerSQL.getInstance().getIssueById(issue1.getIssueId());

        if (issue == null) {
            setTitle("New Issue");
        } else {
            setTitle(issue.getTitle());
        }

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 10, 50, 20);
        panel.add(titleLabel);

        titleField = new JTextField();
        if (issue != null) {
            titleField.setText(issue.getTitle());
        }
        titleField.setBounds(70, 10, 300, 20);
        panel.add(titleField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(10, 40, 80, 20);
        panel.add(descriptionLabel);

        descriptionField = new JTextField();
        if (issue != null) {
            descriptionField.setText(issue.getDescription());
        }
        descriptionField.setBounds(10, 70, 360, 80);
        panel.add(descriptionField);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(10, 160, 50, 20);
        panel.add(statusLabel);
        if (issue != null) {
            statusComboBox.setSelectedItem(issue.getStatus());
        }
        statusComboBox.setBounds(70, 160, 100, 20);
        panel.add(statusComboBox);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(190, 160, 50, 20);
        panel.add(typeLabel);

        if (issue != null) {
            issueTypeComboBox.setSelectedItem(issue.getIssueType());
        }
        issueTypeComboBox.setBounds(250, 160, 100, 20);
        panel.add(issueTypeComboBox);

        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setBounds(10, 200, 50, 20);
        panel.add(priorityLabel);

        if (issue != null) {
            priorityComboBox.setSelectedItem(issue.getPriority());
        }
        priorityComboBox.setBounds(70, 200, 100, 20);
        panel.add(priorityComboBox);

        JButton createButton = new JButton("create");
        createButton.setBounds(150, 240, 80, 25);
        panel.add(createButton);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (statusComboBox.getSelectedItem() == Status.TO_DO) {
                    Issue issue = IssueManagerSQL.getInstance().addIssue(titleField.getText(), descriptionField.getText(), Status.values()[statusComboBox.getSelectedIndex()]
                            , IssueType.values()[issueTypeComboBox.getSelectedIndex()], Priority.values()[priorityComboBox.getSelectedIndex()]);
                    issuesPanel.createNewBoardPanel(issue.getIssueId());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "status for creating issue have to be to_do");
                }
            }
        });
        JButton editButton = new JButton("Edit");
        editButton.setBounds(50, 240, 80, 25);
        panel.add(editButton);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (issue != null) {
                    String title = titleField.getText();
                    String description = descriptionField.getText();
                    Status status = (Status) statusComboBox.getSelectedItem();
                    IssueType issueType = (IssueType) issueTypeComboBox.getSelectedItem();
                    Priority priority = (Priority) priorityComboBox.getSelectedItem();
                    IssueManagerSQL.getInstance().updateIssue(issue.getIssueId(),title,description,status,issueType,priority);
                    issuesPanel.updateIssuePanel(issue.getIssueId());
                }
                dispose(); // Close the form after editing
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(250, 240, 80, 25);
        panel.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IssueManagerSQL.getInstance().removeIssue(issue.getIssueId());
                issuesPanel.removeIssuePanel(issue.getIssueId());
                issuesPanel.revalidate();
                issuesPanel.repaint();
                issuesPanel.updateUI();
                dispose(); // Close the form after deletion
            }
        });

        getContentPane().add(panel);
        getContentPane().add(panel);
    }

}
