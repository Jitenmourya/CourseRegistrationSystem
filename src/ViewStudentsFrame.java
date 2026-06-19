import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewStudentsFrame extends JFrame {

    JTable table;
    DefaultTableModel model;
    JButton btnBack;

    public ViewStudentsFrame() {

        setTitle("View Students");
        setSize(600, 400);
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Department");

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

        loadStudents();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadStudents() {

        try {

            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");

            while(rs.next()) {

                model.addRow(new Object[] {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department")
                });
            }

            con.close();

        } catch(Exception ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
