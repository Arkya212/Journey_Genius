import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) { // Check if connection is null or closed
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url = "";
                String username = "";
                String password = "";
                con = DriverManager.getConnection(url, username, password);
                if (con.isClosed()) {
                    System.out.println("Connection is Closed");
                } else {
                    System.out.println("Connection established");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}