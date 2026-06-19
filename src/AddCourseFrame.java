import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddCourseFrame extends JFrame {

    JTextField txtName, txtCredits;
    JButton btnSave, btnBack;

    public AddCourseFrame() {

        setTitle("Add Course");
        setSize(450, 300);
        setLayout(null);

        JLabel heading = new JLabel("ADD COURSE");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(150, 20, 200, 30);
        add(heading);

        JLabel lblName = new JLabel("Course Name:");
        lblName.setBounds(40, 80, 120, 30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(170, 80, 220, 30);
        add(txtName);

        JLabel lblCredits = new JLabel("Credits:");
        lblCredits.setBounds(40, 130, 120, 30);
        add(lblCredits);

        txtCredits = new JTextField();
        txtCredits.setBounds(170, 130, 220, 30);
        add(txtCredits);

        btnSave = new JButton("Save");
        btnSave.setBounds(110, 200, 90, 35);
        add(btnSave);

        btnBack = new JButton("Back");
        btnBack.setBounds(230, 200, 90, 35);
        add(btnBack);

        btnSave.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                saveCourse();
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

    private void saveCourse() {

        try {

            String cname = txtName.getText();
            int credits = Integer.parseInt(txtCredits.getText());

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO courses(course_name,credits) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, cname);
            ps.setInt(2, credits);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Course Added Successfully!");

            txtName.setText("");
            txtCredits.setText("");

            con.close();

        } catch(Exception ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
