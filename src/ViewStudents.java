import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewStudents {

    public static void viewStudents() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM students");

            System.out.println("\n===== STUDENT LIST =====");

            System.out.println(
                    "ID\tNAME\tEMAIL\t\t\tDEPARTMENT");

            while(rs.next()) {

                System.out.println(
                        rs.getInt("id") + "\t"
                      + rs.getString("name") + "\t"
                      + rs.getString("email") + "\t"
                      + rs.getString("department"));
            }

            con.close();

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}