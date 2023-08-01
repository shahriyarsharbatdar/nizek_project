package ui.mainPage;

import manager.UserManager;
import model.Repository;
import model.UserRole;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;

public class UserTablePanel extends JPanel implements TableModel {
    Repository repository = Repository.getInstance();

    JTable jt = new JTable();
    JScrollPane sp = new JScrollPane(jt);
    UserManager userManager;


    public UserTablePanel(UserManager userManager) {
        this.userManager = userManager;
        setLayout(null);
        setBounds(250,100,600,200);
        setBackground(Color.white);
        sp.setBounds(0,0,600,200);
        add(sp);

        jt.setModel(this);
    }

    @Override
    public int getRowCount() {
        return userManager.getAllUsers().size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "UserID";
            case 1 -> "Name";
            case 2 -> "LastName";
            case 3 -> "Email";
            case 4 -> "Role";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 4 -> UserRole.class;
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
            case 0 -> userManager.getAllUsers().get(rowIndex).getUserId();
            case 1 -> userManager.getAllUsers().get(rowIndex).getName();
            case 2 -> userManager.getAllUsers().get(rowIndex).getLastName();
            case 3 -> userManager.getAllUsers().get(rowIndex).getEmail();
            case 4 -> userManager.getAllUsers().get(rowIndex).getRole();
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
}
