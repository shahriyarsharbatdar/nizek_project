package ui.project;

import manager.ProjectManagerSQL;
import manager.UserManagerSQL;
import model.Project;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProjectUsersTable  extends JPanel implements TableModel {
    JTable jt = new JTable();
    JScrollPane sp = new JScrollPane(jt);
    ProjectManagerSQL projectManagerSQL;
    Project project;

    public ProjectUsersTable(ProjectManagerSQL projectManagerSQL, Project project) {
        this.projectManagerSQL = projectManagerSQL;
        this.project = project;
        setLayout(null);
        setBounds(550, 150, 300, 400);
        setBackground(Color.white);
        sp.setBounds(0, 0, 300, 400);
        add(sp);
        jt.setModel(this);

    }

    @Override
    public int getRowCount() {
        return projectManagerSQL.getUsersByProject(project).size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "Name";
            case 1 -> "last name";
            case 3 -> "email";
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
            case 0 -> projectManagerSQL.getUsersByProject(project).get(rowIndex).getName();
            case 1 -> projectManagerSQL.getUsersByProject(project).get(rowIndex).getLastName();
            case 2 -> projectManagerSQL.getUsersByProject(project).get(rowIndex).getEmail();
            default -> "";
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
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
