package ui.project;

import javax.swing.*;
import java.awt.*;

public class DefaultBoard extends JPanel {
    public DefaultBoard() {
        setBounds(0, 0, 900, 650);
        setBackground(Color.yellow);
        setLayout(new GridLayout(1, 4, 10, 0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] labels = {"To Do", "In Progress", "QA", "Done"};

        for (String label : labels) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel titleLabel = new JLabel(label, JLabel.CENTER);
            panel.add(titleLabel, BorderLayout.NORTH);
            add(panel);
        }

        JButton newIssueButton = new JButton("New Issue");
        newIssueButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newIssueButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
