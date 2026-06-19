import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewCoursesFrame extends JFrame {

    JTable table;
    DefaultTableModel model;
    JButton btnBack;

    public ViewCoursesFrame() {

        setTitle("View Courses");
        setSize(500, 350);
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Course");
        model.addColumn("Credits");

        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        btnBack = new JButton("Back");
        JPanel bottom = new JPanel();
        bottom.add(btnBack);
        add(bottom, BorderLayout.SOUTH);

        btnBack.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });

        loadCourses();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadCourses() {

        try {

            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM courses");

            while(rs.next()) {

                model.addRow(new Object[] {
                        rs.getInt("id"),
                        rs.getString("course_name"),
                        rs.getInt("credits")
                });
            }

            con.close();

        } catch(Exception ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
