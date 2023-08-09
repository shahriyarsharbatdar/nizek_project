package ui.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BoardsPanel extends JPanel {
    private ArrayList<JPanel> boardPanel = new ArrayList<>();
    private JPanel buttonContainer;
    public BoardsPanel(BasePanelForBoard basePanelForBoard) {
        setBounds(0, 0, 900, 650);
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
                basePanelForBoard.boardsPanel.setVisible(false);
                basePanelForBoard.defaultBoard.setVisible(true);
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
                createNewBoardPanel();
            }
        });
        buttonContainer.add(addBoardButton);
    }

    private void createNewBoardPanel() {
        JPanel newBoardButton = new JPanel();
        newBoardButton.setLayout(null);
        JLabel label=new JLabel("Board");
        label.setBounds(60,0,100,100);
        newBoardButton.add(label);

        newBoardButton.setFont(new Font("Arial", Font.BOLD, 16));
        newBoardButton.setBackground(new Color(0, 102, 204)); // Dark blue background
        newBoardButton.setForeground(Color.black);
        newBoardButton.setPreferredSize(new Dimension(150, 100));
        newBoardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    System.out.println("hello");
                }
            }
        });

        boardPanel.add(newBoardButton);
        buttonContainer.add(newBoardButton, 1); // Add to the right of "Default" button

        revalidate(); // Revalidate the panel to update the layout
    }
}