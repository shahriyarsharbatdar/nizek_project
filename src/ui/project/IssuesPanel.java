// IssuesPanel.java
package ui.project;

import manager.IssueManagerSQL;
import model.Issue;
import model.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class IssuesPanel extends JPanel {
    HashMap<Integer, JPanel> issuePanels;
    HashMap<Integer, JLabel> issueLabels;

    ArrayList<JPanel> boardPanel;
    ArrayList<Issue> issues;
    private JPanel buttonContainer;
    Project project;
    ProjectPageFrame projectPageFrame;

    public IssuesPanel(Project project,ProjectPageFrame projectPageFrame) {
        this.project = project;
        this.projectPageFrame=projectPageFrame;
        setBounds(0, 50, 900, 650);
        setBackground(new Color(240, 240, 240));
        setLayout(null);
        issues = IssueManagerSQL.getInstance().getIssuesByProjectId(project.getProjectId());
        issuePanels = new HashMap<>();
        issueLabels = new HashMap<>();
        boardPanel = new ArrayList<>();

        buttonContainer = new JPanel();
        buttonContainer.setBounds(0, 0, 900, 650);
        buttonContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        add(buttonContainer, BorderLayout.NORTH);

        JButton addBoardButton = new JButton("+");
        addBoardButton.setFont(new Font("Arial", Font.BOLD, 40));
        addBoardButton.setBackground(new Color(46, 139, 87));
        addBoardButton.setForeground(Color.black);
        addBoardButton.setPreferredSize(new Dimension(70, 70));
        addBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openIssueForm(null);
            }
        });
        buttonContainer.add(addBoardButton);
        for (Issue issue : issues) {
            createNewBoardPanel(issue.getIssueId());
        }
    }

    public void createNewBoardPanel(int issueId) {
        Issue issue = IssueManagerSQL.getInstance().getIssueById(issueId);

        if (issue != null) {
            int panelWidth = 200;
            int panelHeight = 100;
            int borderRadius = 20;

            JPanel newBoardButton = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setColor(new Color(239, 115, 115));
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
                    g2d.dispose();
                }
            };

            newBoardButton.setLayout(null);
            JLabel label = new JLabel(issue.getTitle());
            Dimension labelSize = label.getPreferredSize();
            int labelX = (panelWidth - labelSize.width) / 2;
            int labelY = (panelHeight - labelSize.height) / 2;
            label.setBounds(labelX, labelY, labelSize.width, labelSize.height);
            newBoardButton.add(label);

            newBoardButton.setFont(new Font("Arial", Font.BOLD, 14));
            newBoardButton.setPreferredSize(new Dimension(panelWidth, panelHeight));
            newBoardButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        openIssueForm(issue);
                    }
                }
            });

            boardPanel.add(newBoardButton);
            buttonContainer.add(newBoardButton, 1);
            issuePanels.put(issueId, newBoardButton);
            issueLabels.put(issueId, label);

            revalidate();
            repaint();
        }
    }

    public void removeIssuePanel(int issueId) {
        JPanel panelToRemove = issuePanels.get(issueId);
        JLabel labelToUpdate = issueLabels.get(issueId);
        if (panelToRemove != null && labelToUpdate != null) {
            boardPanel.remove(panelToRemove);
            buttonContainer.remove(panelToRemove);
            issuePanels.remove(issueId);
            issueLabels.remove(issueId);
            revalidate();
            repaint();
        }
    }

    public void updateIssuePanel(int issueId) {
        JLabel labelToUpdate = issueLabels.get(issueId);
        if (labelToUpdate != null) {
            labelToUpdate.setText(IssueManagerSQL.getInstance().getIssueById(issueId).getTitle());
        }
    }

    private void openIssueForm(Issue issue) {
        IssueFormPanel issueForm = new IssueFormPanel(this, issue);
        issueForm.setVisible(true);
    }

    public Project getProject() {
        return project;
    }

    public ProjectPageFrame getProjectPageFrame() {
        return projectPageFrame;
    }
}

