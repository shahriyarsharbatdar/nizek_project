package ui.project;

import model.Project;

import javax.swing.*;
import java.awt.*;

public class BasePanelForBoard extends JPanel {
    DefaultBoard defaultBoard;
    BoardsPanel boardsPanel = new BoardsPanel(this);
    Project project;

    public BasePanelForBoard(Project project) {
        this.project = project;
        defaultBoard = new DefaultBoard(project);
        setBounds(0, 50, 900, 650);
        setBackground(new Color(255, 240, 220)); // Light peach background
        setVisible(false);
        setLayout(null);
        add(defaultBoard);
        defaultBoard.setVisible(false);
        add(boardsPanel);
        boardsPanel.setVisible(true);
    }

    public Project getProject() {
        return project;
    }
}
