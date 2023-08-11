package ui.project;

import manager.IssueManagerSQL;
import model.Issue;
import model.Project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

    public class DefaultBoard extends JPanel {
        private final int BOARD_COLUMNS = 4;
        Project project;

        public DefaultBoard(Project project) {
            this.project = project;
            setBounds(0, 0, 900, 650);
            setBackground(Color.yellow);
            setLayout(new GridLayout(1, BOARD_COLUMNS, 10, 0));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            String[] statusLabels = {"To Do", "In Progress", "QA", "Done"};

            for (String statusLabel : statusLabels) {
                JPanel columnPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                columnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                ArrayList<Issue> issues = IssueManagerSQL.getInstance().getIssuesByProjectId(project.getProjectId());

                for (Issue issue : issues) {
                    if (issue.getStatus().toString().equalsIgnoreCase(statusLabel)) {
                        JPanel issuePanel = createIssuePanel(issue);
                        columnPanel.add(issuePanel);
                    }
                }

                add(columnPanel);
            }
            setVisible(true);
        }

        private JPanel createIssuePanel(Issue issue) {
            JPanel issuePanel = new JPanel(new BorderLayout());
            issuePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel titleLabel = new JLabel(issue.getTitle(), JLabel.CENTER);
            issuePanel.add(titleLabel, BorderLayout.NORTH);

            // Add more labels or information about the issue to the panel
            // Customize the appearance of the issue panel based on your requirements

            return issuePanel;
        }
    }
