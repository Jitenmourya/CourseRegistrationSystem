import javax.swing.*;
import java.awt.event.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Course Registration System");
        setSize(400, 350);
        setLayout(null);

        JButton btnAddStudent = new JButton("Add Student");
        btnAddStudent.setBounds(100, 30, 180, 30);
        add(btnAddStudent);

        btnAddStudent.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                new AddStudentFrame();
            }
        });

        JButton btnViewStudents = new JButton("View Students");
        btnViewStudents.setBounds(100, 80, 180, 30);
        add(btnViewStudents);

        btnViewStudents.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                new ViewStudentsFrame();
            }
        });

        JButton btnAddCourse = new JButton("Add Course");
        btnAddCourse.setBounds(100, 130, 180, 30);
        add(btnAddCourse);

        btnAddCourse.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                new AddCourseFrame();
            }
        });

        JButton btnViewCourses = new JButton("View Courses");
        btnViewCourses.setBounds(100, 180, 180, 30);
        add(btnViewCourses);

        btnViewCourses.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                new ViewCoursesFrame();
            }
        });

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(100, 230, 180, 30);
        add(btnExit);

        btnExit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}