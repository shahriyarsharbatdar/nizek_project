package ui.mainPage;

import manager.UserManager;
import manager.UserManagerSQL;
import model.Repository;
import model.User;
import model.UserRole;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

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
        return UserManagerSQL.getInstance().getUsers().size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "Name";
            case 1 -> "LastName";
            case 2 -> "Email";
            case 3 -> "Role";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 3 -> UserRole.class;
            default -> String.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<User> users = UserManagerSQL.getInstance().getUsers();
        return switch (columnIndex) {
            case 0 -> users.get(rowIndex).getName();
            case 1 -> users.get(rowIndex).getLastName();
            case 2 -> users.get(rowIndex).getEmail();
            case 3 -> users.get(rowIndex).getRole();
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
        jt.updateUI();
    }
}
