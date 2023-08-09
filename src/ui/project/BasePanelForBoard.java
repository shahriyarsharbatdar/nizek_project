package ui.project;

import javax.swing.*;
import java.awt.*;

public class BasePanelForBoard extends JPanel {
    DefaultBoard defaultBoard = new DefaultBoard();
    BoardsPanel boardsPanel = new BoardsPanel(this);

    public BasePanelForBoard() {
        setBounds(0, 50, 900, 650);
        setBackground(new Color(255, 240, 220)); // Light peach background
        setVisible(false);
        setLayout(null);
        add(defaultBoard);
        defaultBoard.setVisible(false);
        add(boardsPanel);
        boardsPanel.setVisible(true);
    }

}
