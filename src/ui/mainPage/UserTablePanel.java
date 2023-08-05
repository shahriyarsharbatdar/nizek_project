package ui.mainPage;

import manager.UserManagerSQL;
import model.User;
import model.UserRole;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class UserTablePanel extends JPanel implements TableModel {

    JTable jt = new JTable();
    JScrollPane sp = new JScrollPane(jt);
    UserManagerSQL userManagerSQL;
    private int selectedRow = -1; // Initialize to -1 to indicate no row is selected initially

    private String selectedEmail;
    private User selctedUser;



    public UserTablePanel(UserManagerSQL userManagerSQL) {
        this.userManagerSQL = userManagerSQL;
        setLayout(null);
        setBounds(250,100,600,200);
        setBackground(Color.white);
        sp.setBounds(0,0,600,200);
        add(sp);
        jt.setModel(this);

        jt.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = jt.getSelectedRow();
                if (selectedRow >= 0) {
                    // Row is selected, get the email from the selected row
                    selectedEmail = (String) getValueAt(selectedRow, 2);}
                EditUserPanelController editUserPanelController = EditUserPanelController.getInstance();
                  selctedUser = editUserPanelController.userInfo(selectedEmail);

                EditUserPanel editUserPanel = new EditUserPanel(selctedUser);
                editUserPanel.setVisible(true);
//                selectedProject = jt.getSelectedRow();
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
