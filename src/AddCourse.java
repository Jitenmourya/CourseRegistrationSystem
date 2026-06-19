import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddCourse {

    public static void addCourse() {

        try {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Course Name: ");
            String cname = sc.nextLine();

            System.out.print("Enter Credits: ");
            int credits = sc.nextInt();

            Connection con = DBConnection.getConnection();

            String query =
                    "INSERT INTO courses(course_name,credits) VALUES(?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, cname);
            ps.setInt(2, credits);

            ps.executeUpdate();

            System.out.println("✅ Course Added Successfully");

            con.close();

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}