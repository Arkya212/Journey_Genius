import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.Color;


public class NextPage extends JFrame {
    public NextPage(int It_ID) throws IOException {
        JFrame frame = new JFrame("Plan Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout(3, 3));
        frame.setVisible(true);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.red);
        panel2.setBackground(Color.green);
        panel3.setBackground(Color.yellow);
        panel4.setBackground(Color.magenta);
        panel5.setBackground(Color.blue);

        panel1.setPreferredSize(new Dimension(1000, 80));// Create a div
        panel2.setPreferredSize(new Dimension(250, 500));// Create a div
        panel3.setPreferredSize(new Dimension(210, 100));// Create a div
        panel4.setPreferredSize(new Dimension(200, 100));// Create a div
        panel5.setPreferredSize(new Dimension(1000, 20));// Create a div

        // Arkya mc tha, hai , aur rehega

        frame.add(panel1, BorderLayout.NORTH);
        // Arkya
        frame.add(panel2, BorderLayout.WEST);

        // Chitresh
        frame.add(panel3, BorderLayout.EAST);

        // Create two subpanels
        JPanel subPanel1 = new JPanel();
        JPanel subPanel2 = new JPanel();

        // Set backgrounds
        subPanel1.setBackground(Color.BLACK);
        subPanel2.setBackground(Color.WHITE);

        // Set layout for panel4 to GridLayout with 1 row and 2 columns
        panel4.setLayout(new GridLayout(2, 1));

        // Add subpanels to panel4
        panel4.add(subPanel1);
        panel4.add(subPanel2);

        frame.add(panel4, BorderLayout.CENTER);

        frame.add(panel5, BorderLayout.SOUTH);
    }
}