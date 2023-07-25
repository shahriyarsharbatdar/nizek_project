package ui.login;
import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    private static LoginView instance = null;

    JLabel LoginText = new JLabel();
    JTextField EmailField = new JTextField();
    JPasswordField PasswordField = new JPasswordField();
    Button LoginButton = new Button("login");
    String userEmail;
    String userPassword;


    private LoginView() {
        setBackground(new Color(255, 244, 244));
        setBounds(250,80,400,500);
        LoginText.setText("Login");
        LoginText.setBounds(165,25,100,200);
        LoginText.setFont(new Font("Serif",Font.BOLD,30));
        add(LoginText);
        EmailField.setBounds(120, 200, 200, 50);
        add(EmailField);
        PasswordField.setBounds(120, 300, 200, 50);
        add(PasswordField);
        LoginButton.setBounds(120,350,100,100);
        LoginButton.addActionListener(e -> {
            userEmail = EmailField.getText();
            userPassword = PasswordField.getText();
        });
        add(LoginButton);
        setLayout(null);
    }
    //singleton login view
    public static LoginView getInstance() {
        if (instance == null) {
            instance = new LoginView();
        }
        return instance;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
