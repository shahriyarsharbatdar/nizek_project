package ui.login;
import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    private static LoginView instance = null;

    JLabel LoginText = new JLabel();
    JLabel EmailText = new JLabel();
    JTextField EmailField = new JTextField();
    JLabel PasswordText = new JLabel();
    JPasswordField PasswordField = new JPasswordField();
    Button LoginButton = new Button("login");
    JLabel Alert = new JLabel();
    String userEmail;
    String userPassword;


    private LoginView() {
        setBackground(new Color(255, 244, 244));
        setBounds(250,80,400,500);
        //login text
        LoginText.setText("Login");
        LoginText.setBounds(165,25,100,200);
        LoginText.setFont(new Font("Serif",Font.BOLD,30));
        add(LoginText);
        //email text and field
        EmailText.setText("Email");  //email text
        EmailText.setBounds(70,175,100,100);
        EmailText.setFont(new Font("Serif",Font.BOLD,15));
        add(EmailText);
        EmailField.setBounds(120, 200, 200, 50);
        add(EmailField);
        //pass text and field
        PasswordText.setText("Password");  //pass text
        PasswordText.setBounds(50,270,100,50);
        PasswordText.setFont(new Font("Serif",Font.BOLD,15));
        add(PasswordText);
        PasswordField.setBounds(120, 270, 200, 50);
        add(PasswordField);
        //login button
        LoginButton.setBounds(120,350,100,100);
        LoginButton.addActionListener(e -> {
            userEmail = EmailField.getText();
            userPassword = PasswordField.getText();
            if(!(Controller.getInstance().regexAuthenticate())){
                Alert.setText("Username or password is invalid");
                Alert.setBounds(70,370,300,200);
                Alert.setFont(new Font("Serif",Font.BOLD,20));
                Alert.setForeground(Color.RED);
                add(Alert);
                repaint();
            }else{
                Alert.setText("successfully");
                System.out.println("successfully");
            }
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

