import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddStudent {

    public static void addStudent() {

        try {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            Connection con = DBConnection.getConnection();

            String query =
                    "INSERT INTO students(name,email,department) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, department);

            ps.executeUpdate();

            System.out.println("✅ Student Added Successfully");

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}