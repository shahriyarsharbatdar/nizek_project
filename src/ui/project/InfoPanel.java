package ui.project;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    JLabel nameText = new JLabel("Name");
    JTextArea projectName = new JTextArea();
    JLabel detailText = new JLabel("Detail");
    JTextArea projectDetail = new JTextArea();

    public InfoPanel() {
        setBounds(0, 50, 900, 650);
        setBackground(new Color(255, 240, 220)); // Light peach background
        setVisible(false);
        setLayout(null);

        nameText.setBounds(100, 70, 100, 30);
        nameText.setFont(new Font("Arial", Font.PLAIN, 16));
        add(nameText);

        projectName.setBounds(100, 100, 200, 30);
        projectName.setBackground(Color.white);
        projectName.setFont(new Font("Arial", Font.PLAIN, 14));
        projectName.setEditable(false);
        add(projectName);

        detailText.setBounds(100, 370, 100, 30);
        detailText.setFont(new Font("Arial", Font.PLAIN, 16));
        add(detailText);

        projectDetail.setBounds(100, 400, 200, 150);
        projectDetail.setBackground(Color.white);
        projectDetail.setFont(new Font("Arial", Font.PLAIN, 14));
        projectDetail.setEditable(false);
        projectDetail.setLineWrap(true);
        projectDetail.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        JScrollPane scrollPane = new JScrollPane(projectDetail);
        scrollPane.setBounds(100, 400, 200, 150);
        add(scrollPane);
    }

    // Sample method to set project name and detail
    public void setProjectInfo(String name, String detail) {
        projectName.setText(name);
        projectDetail.setText(detail);
    }
}
