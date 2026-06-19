import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewCourses {

    public static void viewCourses() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM courses");

            System.out.println("\n===== COURSE LIST =====");

            System.out.println("ID\tCOURSE\tCREDITS");

            while(rs.next()) {

                System.out.println(
                        rs.getInt("id") + "\t"
                      + rs.getString("course_name") + "\t"
                      + rs.getInt("credits"));
            }

            con.close();

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}