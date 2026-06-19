import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {

            String url =
            "jdbc:postgresql://localhost:5432/course_db";

            String user = "postgres";

            String password = "Jiten@12345";

            Connection con =
            DriverManager.getConnection(
                    url,
                    user,
                    password);

            return con;

        }
        catch(Exception e) {

            System.out.println(e);
        }

        return null;
    }

    public static void main(String[] args) {

        Connection con = getConnection();

        if(con != null) {

            System.out.println("✅ Database Connected Successfully");

        } else {

            System.out.println("❌ Connection Failed");
        }
    }
}