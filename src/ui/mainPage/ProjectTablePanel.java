package ui.mainPage;

import manager.ProjectManager;
import manager.UserManager;
import model.Repository;
import model.UserRole;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProjectTablePanel extends JPanel implements TableModel {

    JTable jt = new JTable();
    JScrollPane sp = new JScrollPane(jt);
    ProjectManager projectManager;

    public ProjectTablePanel(ProjectManager projectManager) {
        this.projectManager = projectManager;
        setLayout(null);
        setBounds(250,400,600,200);
        setBackground(Color.white);
        sp.setBounds(0,0,600,200);
        add(sp);

        jt.setModel(this);
        jt.addMouseListener(new TableMouseListener());
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
            case 0 -> "ProjectID";
            case 1 -> "Name";
            case 2 -> "Description";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
//            case 4 -> UserRole.class;
            default -> String.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> false;
            default -> true;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> projectManager.getAllProject().get(rowIndex).getProjectId();
            case 1 -> projectManager.getAllProject().get(rowIndex).getName();
            case 2 -> projectManager.getAllProject().get(rowIndex).getDescription();
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
    public void refreshTable(){
        jt.tableChanged(new TableModelEvent(this));
    }
    public class TableMouseListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("hi");
            AddMemberPanel addMemberPanel = new AddMemberPanel();
            addMemberPanel.setVisible(true);
            setBounds(0,0,100,100);
            add(addMemberPanel);
        }
    }
}
