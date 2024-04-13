import java.io.IOException;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) throws IOException {
        // Establish database connection
        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(null, "Failed to establish database connection.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the program if connection is not established
        }
        
        // Start the application
        Welcome welcomeFrame = new Welcome();
        welcomeFrame.setVisible(true);
    }
}
