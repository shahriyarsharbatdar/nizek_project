package ui.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardsPanel extends JPanel {
    private ArrayList<JButton> boardButtons = new ArrayList<>();
    private JPanel buttonContainer;
    DefaultBoard defaultBoard;
    public BoardsPanel() {
        setBounds(0, 50, 900, 650);
        setBackground(new Color(240, 240, 240)); // Light gray background
        setLayout(null);


        buttonContainer = new JPanel();
        buttonContainer.setBounds(0, 0, 900, 650);
        buttonContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // Left-aligned FlowLayout
        add(buttonContainer, BorderLayout.NORTH);

        JButton defaultButton = new JButton("Default");
        defaultButton.setFont(new Font("Arial", Font.BOLD, 16));
        defaultButton.setPreferredSize(new Dimension(150, 100));
        buttonContainer.add(defaultButton);
        defaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultBoard=new DefaultBoard();
                add(defaultBoard,0);
                isEnabled();
                revalidate();
                repaint();


            }
        });

        JButton addBoardButton = new JButton("+");
        addBoardButton.setFont(new Font("Arial", Font.BOLD, 40));
        addBoardButton.setBackground(new Color(46, 139, 87)); // Dark green background
        addBoardButton.setForeground(Color.black);
        addBoardButton.setPreferredSize(new Dimension(70, 70));
        addBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewBoardButton();
            }
        });
        buttonContainer.add(addBoardButton);
    }

    private void createNewBoardButton() {
        JButton newBoardButton = new JButton("Board " + (boardButtons.size() + 1));
        newBoardButton.setFont(new Font("Arial", Font.BOLD, 16));
        newBoardButton.setBackground(new Color(0, 102, 204)); // Dark blue background
        newBoardButton.setForeground(Color.black);
        newBoardButton.setPreferredSize(new Dimension(150, 100));

        boardButtons.add(newBoardButton);
        buttonContainer.add(newBoardButton, 1); // Add to the right of "Default" button

        revalidate(); // Revalidate the panel to update the layout
    }
}