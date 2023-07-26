package ui.mainPage;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    JLabel welcomeLabel = new JLabel("Welcome,Admin");
    public MainPanel() {
        setBounds(0, 0, 900, 700);
        setLayout(null);
        setVisible(false);
        setBackground(new Color(20,195,142));
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.BLACK);

        int rectWidth = 200;
        int rectHeight = 600;
        int rectX = 20; // Offset from the left

        // Set the bounds of the label to position it at the top of the rectangle
        int labelX = rectX + (rectWidth - welcomeLabel.getPreferredSize().width) / 2;
        int labelY = 130 + welcomeLabel.getPreferredSize().height / 2; // At the top of the rectangle

        welcomeLabel.setBounds(labelX, labelY, welcomeLabel.getPreferredSize().width, welcomeLabel.getPreferredSize().height);
        add(welcomeLabel);

    }
        //sidebar for profile


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int rectWidth = 200;
        int rectHeight = 600;
        int rectX = 20; // Offset from the left
        int rectY = (panelHeight - rectHeight) / 2;

        g2D.setColor(new Color(255, 244, 244));
        g2D.fillRoundRect(rectX, rectY, rectWidth, rectHeight, 30, 30);
    }

}
