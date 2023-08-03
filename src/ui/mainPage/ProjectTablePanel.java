package ui.mainPage;

import manager.ProjectManager;
import ui.project.ProjectPageFrame;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProjectTablePanel extends JPanel implements TableModel {

    JTable jt = new JTable();
    JScrollPane sp = new JScrollPane(jt);
    ProjectManager projectManager;
    int selectedProject ;

    public ProjectTablePanel(ProjectManager projectManager) {
        this.projectManager = projectManager;
        setLayout(null);
        setBounds(250, 400, 600, 200);
        setBackground(Color.white);
        sp.setBounds(0, 0, 600, 200);
        add(sp);

        jt.setModel(this);
        jt.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            ProjectPageFrame projectPage = new ProjectPageFrame(projectManager, jt.getSelectedRow());
            selectedProject = jt.getSelectedRow();
        }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
    }

    @Override
    public int getRowCount() {
        return projectManager.getAllProject().size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "Name";
            case 1 -> "Description";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
        }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> projectManager.getAllProject().get(rowIndex).getName();
            case 1 -> projectManager.getAllProject().get(rowIndex).getDescription();
            default -> "";
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        System.out.println(aValue);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    public void refreshTable() {
        jt.updateUI();
    }
}
