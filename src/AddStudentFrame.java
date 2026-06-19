import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStudentFrame extends JFrame {

    JTextField txtName, txtEmail, txtDept;
    JButton btnSave, btnBack;

    public AddStudentFrame() {

        setTitle("Add Student");
        setSize(500, 350);
        setLayout(null);

        JLabel heading = new JLabel("ADD STUDENT");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(170, 20, 200, 30);
        add(heading);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(60, 80, 100, 30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(180, 80, 220, 30);
        add(txtName);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(60, 130, 100, 30);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(180, 130, 220, 30);
        add(txtEmail);

        JLabel lblDept = new JLabel("Department:");
        lblDept.setBounds(60, 180, 100, 30);
        add(lblDept);

        txtDept = new JTextField();
        txtDept.setBounds(180, 180, 220, 30);
        add(txtDept);

        btnSave = new JButton("Save");
        btnSave.setBounds(120, 250, 100, 35);
        add(btnSave);

        btnBack = new JButton("Back");
        btnBack.setBounds(260, 250, 100, 35);
        add(btnBack);

        btnSave.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                saveStudent();
            }
        });

        btnBack.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveStudent() {

        try {

            String name = txtName.getText();
            String email = txtEmail.getText();
            String dept = txtDept.getText();

            Connection con = DBConnection.getConnection();

            String query =
                    "INSERT INTO students(name,email,department) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, dept);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Student Added Successfully!");

            txtName.setText("");
            txtEmail.setText("");
            txtDept.setText("");

            con.close();

        } catch(Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage());
        }
    }
}