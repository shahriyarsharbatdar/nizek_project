package ui.login;
import ui.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JPanel {
    private static LoginView instance = null;
    JButton loginButton = new JButton("Login");
    JLabel loginText = new JLabel();
    JLabel emailText = new JLabel();
    JTextField emailField = new JTextField("a@a.com");
    JLabel passwordText = new JLabel("12345678");
    JPasswordField PasswordField = new JPasswordField();

    JLabel alert = new JLabel();
    String userEmail;
    String userPassword;


    private LoginView() {
        setBackground(new Color(255, 244, 244));
        setBounds(250, 80, 400, 500);
        setLayout(null);
        //login text
        loginText.setText("Login");
        loginText.setBounds(165, 25, 100, 200);
        loginText.setFont(new Font("Arial", Font.BOLD, 30));
        add(loginText);
        //email text and field
        emailText.setText("Email");  //email text
        emailText.setBounds(70, 175, 100, 100);
        emailText.setFont(new Font("Arial", Font.BOLD, 15));
        add(emailText);
        emailField.setBounds(120, 200, 200, 50);
        add(emailField);
        //pass text and field
        passwordText.setText("Password");  //pass text
        passwordText.setBounds(50, 270, 100, 50);
        passwordText.setFont(new Font("Arial", Font.BOLD, 15));
        add(passwordText);
        PasswordField.setBounds(120, 270, 200, 50);
        add(PasswordField);
        //login button
        loginButton.setBounds(120, 350, 100, 100);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userEmail = emailField.getText();
                userPassword = PasswordField.getText();
                if (!(Controller.getInstance().regexAuthenticate())) {
                    alert.setText("Username or password is invalid");
                    alert.setBounds(70, 370, 300, 200);
                    alert.setFont(new Font("Serif", Font.BOLD, 20));
                    alert.setForeground(Color.RED);
                    add(alert);
                } else {
                    alert.setText("successfully");
                    System.out.println("successfully");
                    setVisible(false);
                    loginButton.setVisible(false);
                    MainFrame.getInstance().mainPanel.setVisible(true);

                }
            }
        });

        add(loginButton);


        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition);
        ActionMap actionMap = getActionMap();

        String enterKey = "enterKey";
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), enterKey);
        actionMap.put(enterKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });
    }

    //singleton login view
    public static LoginView getInstance() {
        if (instance == null) {
            instance = new LoginView();
        }
        return instance;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}

