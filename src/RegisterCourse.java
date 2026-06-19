import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class RegisterCourse {

    public static void registerCourse() {

        try {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Student ID: ");
            int sid = sc.nextInt();

            System.out.print("Enter Course ID: ");
            int cid = sc.nextInt();

            Connection con = DBConnection.getConnection();

            String query =
                    "INSERT INTO registration(student_id,course_id) VALUES(?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1, sid);
            ps.setInt(2, cid);

            ps.executeUpdate();

            System.out.println("✅ Course Registered Successfully");

            con.close();

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}