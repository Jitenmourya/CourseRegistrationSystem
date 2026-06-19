import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterCourseFrame extends JFrame {

    JTextField txtStudentId, txtCourseId;
    JButton btnRegister, btnBack;

    public RegisterCourseFrame() {

        setTitle("Register Course");
        setSize(400, 260);
        setLayout(null);

        JLabel heading = new JLabel("REGISTER COURSE");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setBounds(110, 15, 200, 30);
        add(heading);

        JLabel lblSid = new JLabel("Student ID:");
        lblSid.setBounds(40, 70, 100, 30);
        add(lblSid);

        txtStudentId = new JTextField();
        txtStudentId.setBounds(150, 70, 180, 30);
        add(txtStudentId);

        JLabel lblCid = new JLabel("Course ID:");
        lblCid.setBounds(40, 120, 100, 30);
        add(lblCid);

        txtCourseId = new JTextField();
        txtCourseId.setBounds(150, 120, 180, 30);
        add(txtCourseId);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(80, 170, 100, 35);
        add(btnRegister);

        btnBack = new JButton("Back");
        btnBack.setBounds(210, 170, 100, 35);
        add(btnBack);

        btnRegister.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                registerCourse();
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

    private void registerCourse() {

        try {

            int sid = Integer.parseInt(txtStudentId.getText());
            int cid = Integer.parseInt(txtCourseId.getText());

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO registration(student_id,course_id) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, sid);
            ps.setInt(2, cid);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Course Registered Successfully!");

            txtStudentId.setText("");
            txtCourseId.setText("");

            con.close();

        } catch(Exception ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
