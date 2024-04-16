import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) throws IOException {
        // Establish database connection

        Connection con = ConnectionProvider.getConnection();
        if (con == null) {
            JOptionPane.showMessageDialog(null, "Failed to establish database connection.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the program if connection is not established
        }
        
        Welcome welcomeFrame = new Welcome();


        // try {
        //     CustomTry customTry = new CustomTry();
        // } catch (IOException | SQLException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        // Welcome welcomeFrame = new Welcome();
        // AppConfig.itineary_ID = 73;
        // AppConfig.text_days = 7;
                // FinalPage welcomeFrame = new FinalPage();
                // try {
                //     CustomPackage customPackage = new CustomPackage();
                // } catch (IOException | SQLException e) {
                //     e.printStackTrace();
                // }
        // welcomeFrame.setVisible(true);

    }
}
