import javax.swing.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin, btnExit;

    public LoginFrame() {

        setTitle("Course Registration System - Login");
        setSize(400, 250);
        setLayout(null);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(50, 50, 100, 30);
        add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(150, 50, 150, 30);
        add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(50, 100, 100, 30);
        add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(150, 100, 150, 30);
        add(txtPass);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(70, 160, 100, 30);
        add(btnLogin);

        btnExit = new JButton("Exit");
        btnExit.setBounds(200, 160, 100, 30);
        add(btnExit);

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String user = txtUser.getText();
                String pass = new String(txtPass.getPassword());

                if(user.equals("admin") && pass.equals("admin")) {

                    dispose();
                    new Dashboard();

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid Login");
                }
            }
        });

        btnExit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}