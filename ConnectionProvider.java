import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) { // Check if connection is null or closed
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url = "jdbc:mysql://localhost:3306/itt";
                String username = "root";
                String password = "shrey";
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
