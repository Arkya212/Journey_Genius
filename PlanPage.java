import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlanPage extends JFrame {
    public int numberOfItinerary = 6;
    public String cityName = "Delhi";
    public String receivedString = "Capital of Country";

    public PlanPage(int user_id) throws IOException {
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

        // panel1.setBackground(Color.red);
        // panel2.setBackground(Color.green);
        // panel3.setBackground(Color.yellow);
        // panel4.setBackground(Color.magenta);
        // panel5.setBackground(Color.blue);
        panel1.setBackground(Color.red);
        panel2.setBackground(Color.green);
        panel3.setBackground(Color.yellow);
        panel4.setBackground(Color.magenta);
        panel5.setBackground(Color.black);

        panel1.setPreferredSize(new Dimension(100, 80));// Create a div
        panel2.setPreferredSize(new Dimension(250, 500));// Create a div
        panel3.setPreferredSize(new Dimension(210, 100));// Create a div
        panel4.setPreferredSize(new Dimension(250, 200));// Create a div
        panel5.setPreferredSize(new Dimension(1000, 20));// Create a div

        // Add Layout for Panel 1
        panel1.setLayout(new BorderLayout());
        JPanel subPanel11 = new JPanel();
        JPanel subPanel21 = new JPanel();
        JPanel subPanel31 = new JPanel();

        subPanel11.setBackground(Color.BLACK);
        subPanel21.setBackground(Color.BLACK);
        subPanel31.setBackground(Color.BLACK);

        subPanel11.setPreferredSize(new Dimension(100, 80));
        subPanel21.setPreferredSize(new Dimension(100, 80));
        subPanel31.setPreferredSize(new Dimension(100, 80));

        // subPanel 1 Layout
        ImageIcon icon = new ImageIcon("images/logo2.png");
        Image img = icon.getImage();
        int logoWidth = 80;
        int logoHeight = 80;
        Image resizedImg = img.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);

        JLabel imageLabel = new JLabel(resizedIcon); // Use resizedIcon here
        subPanel11.add(imageLabel);

        panel1.add(subPanel11, BorderLayout.WEST);

        // Sub Panel 2 Layout
        JLabel titleLabel = new JLabel("Plan Your Trip");
        titleLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        subPanel21.add(titleLabel);

        panel1.add(subPanel21, BorderLayout.CENTER);

        // SubPanel 3 Layout
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 90));
        backButton.setBackground(Color.GRAY);
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setForeground(Color.white);
        subPanel31.add(backButton);
        panel1.add(subPanel31, BorderLayout.EAST);

        frame.add(panel1, BorderLayout.NORTH);

        // Add Layout for Panel 2
        // SubPanel1 Layout
        panel2.setLayout(new BorderLayout());
        JPanel subPanel12 = new JPanel();
        JPanel subPanel22 = new JPanel();

        // subPanel12.setBackground(Color.RED);
        // subPanel22.setBackground(Color.YELLOW);
        subPanel12.setBackground(Color.BLACK);
        subPanel22.setBackground(Color.BLACK);

        subPanel12.setPreferredSize(new Dimension(100, 150));
        subPanel22.setPreferredSize(new Dimension(100, 80));

        // subPanel12 Layout
        JLabel packageTitleLabel = new JLabel(
                "<html><center>Where do you<br>want to<br>Travel?</center></html>");
        packageTitleLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        packageTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        packageTitleLabel.setForeground(Color.WHITE);
        packageTitleLabel.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0));
        subPanel12.add(packageTitleLabel);
        panel2.add(subPanel12, BorderLayout.NORTH);

        // subPanel22 Layout
        // Layout and constraints setup
        GridBagLayout gridBagLayout = new GridBagLayout();
        subPanel22.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0); // Add bottom margin of 20 pixels
        gbc.anchor = GridBagConstraints.CENTER;

        // Create and add label for "Your City"
        JLabel label1 = new JLabel("Your City");
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label1.setForeground(Color.WHITE);
        subPanel22.add(label1, gbc);

        // Create and add text field for "Your City"
        gbc.gridy = 1;
        JTextField textField1 = new JTextField("Your City");
        textField1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textField1.setPreferredSize(new Dimension(200, 40));
        subPanel22.add(textField1, gbc);

        // Create and add button "Know Your City"
        gbc.gridy = 2;
        JButton button = new JButton("Know Your City");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        subPanel22.add(button, gbc);

        // Add vertical spacing between the two groups
        gbc.gridy = 3;
        gbc.insets = new Insets(50, 0, 0, 0); // Add top margin of 50 pixels
        subPanel22.add(new JLabel(), gbc); // Add an empty label to create the spacing

        // Create and add label "Choose"
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 20, 0); // Reset bottom margin to 20 pixels
        JLabel label2 = new JLabel("Choose your Package");
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label2.setForeground(Color.WHITE);
        subPanel22.add(label2, gbc);

        gbc.gridy = 5;
        JButton button1 = new JButton("Custom Package");
        button1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        subPanel22.add(button1, gbc);

        gbc.gridy = 6;
        JButton button2 = new JButton("Pre Package");
        button2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        gbc.gridwidth = 2;
        subPanel22.add(button2, gbc);

        panel2.add(subPanel22, BorderLayout.CENTER);

        frame.add(panel2, BorderLayout.WEST);

        /*
         * Purchased Itineary 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         */

        JPanel subHistPanel1 = new JPanel();
        JPanel subHistPanel2 = new JPanel();
        JPanel subButtonPanel = new JPanel();
        // subHistPanel1.setBackground(Color.RED);
        // subHistPanel2.setBackground(Color.BLACK);
        // subButtonPanel.setBackground(Color.BLUE);
        subHistPanel1.setBackground(Color.BLACK);
        subHistPanel2.setBackground(Color.BLACK);
        subButtonPanel.setBackground(Color.BLACK);
        panel3.setLayout(new BorderLayout());

        // Add subHistPanel1 to panel3 with specified constraints
        subHistPanel1.setPreferredSize(new Dimension(100, 50));
        JLabel historyLabel = new JLabel("<html>Purchased<br>Itineraries</html>", SwingConstants.CENTER);
        historyLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        historyLabel.setForeground(Color.WHITE);
        subHistPanel1.add(historyLabel);
        panel3.add(subHistPanel1, BorderLayout.NORTH);

        subHistPanel2.setLayout(new GridLayout(numberOfItinerary, 1));
        ButtonGroup buttonGroup = new ButtonGroup();
        
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionProvider.getConnection();
            String sql = "SELECT Itinerary_Name FROM Itinerary WHERE User_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user_id);
            rs = pstmt.executeQuery();
        
            // Iterate over the ResultSet to retrieve itinerary names
            while (rs.next()) {
                String itineraryName = rs.getString("Itinerary_Name");
                // Generate radio button for each itinerary name
                JRadioButton radioButton = generateRadioButton(itineraryName);
                buttonGroup.add(radioButton);
                JPanel optionPanel = new JPanel();
                optionPanel.add(radioButton);
                optionPanel.setBackground(Color.BLACK);
                subHistPanel2.add(optionPanel);
            }
        
        } catch (SQLException e) {
            // Handle SQL exception appropriately, e.g., log or display error message
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation to prevent resource leaks
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        panel3.add(subHistPanel2, BorderLayout.CENTER);

        JButton submitButton = new JButton("Choose Itinerary");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        submitButton.setSize(new Dimension(50, 40));
        subButtonPanel.add(submitButton, BorderLayout.CENTER);
        panel3.add(subButtonPanel, BorderLayout.SOUTH);
        frame.add(panel3, BorderLayout.EAST);

        // Assuming this is where you define your submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected radio button's text (itinerary name)
                Enumeration<AbstractButton> buttons = buttonGroup.getElements();
                String selectedItineraryName = null;
                while (buttons.hasMoreElements()) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        selectedItineraryName = button.getText();
                        break;
                    }
                }

                // Query the database to retrieve the itinerary ID based on the selected itinerary name
                int selectedItineraryID = -1; // Initialize with a default value
                try (Connection con = ConnectionProvider.getConnection();
                    PreparedStatement pstmt = con.prepareStatement("SELECT Itinerary_ID FROM Itinerary WHERE Itinerary_Name = ?")) {
                    pstmt.setString(1, selectedItineraryName);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            selectedItineraryID = rs.getInt("Itinerary_ID");
                        }
                        if (selectedItineraryID != -1) {
                            NextPage nextPage = new NextPage(selectedItineraryID);
                            nextPage.setVisible(true);
                            // Close or hide the current frame if needed
                        } else {
                            // Handle the case where the selected itinerary ID is not found
                        }
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle SQL exception appropriately, e.g., log or display error message
                }

                // Redirect to the NextPage and pass the selected itinerary ID

            }
        });

        /*
         * 
         * 
         * 
         * 
         * purchased itineary over
         */













        // Add Layout for Panel 4
        // Create top and bottom panels
        JPanel topPanel = new JPanel(null);
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // topPanel.setBackground(Color.red);
        // bottomPanel.setBackground(Color.green);
        topPanel.setBackground(Color.black);
        bottomPanel.setBackground(Color.black);

        // Set preferred sizes for top and bottom panels
        topPanel.setPreferredSize(new Dimension(500, 330));
        bottomPanel.setPreferredSize(new Dimension(210, 450));

        // Load and resize the city image
        ImageIcon cityImageIcon = new ImageIcon("images/delhi.jpg");
        Image cityImg = cityImageIcon.getImage();
        int cityLogoWidth = 510; // Adjust the width as desired
        int cityLogoHeight = 320; // Adjust the height as desired
        Image cityResizedImg = cityImg.getScaledInstance(cityLogoWidth, cityLogoHeight, Image.SCALE_SMOOTH);
        ImageIcon cityResizedIcon = new ImageIcon(cityResizedImg);

        JLabel cityImageLabel = new JLabel(cityResizedIcon);
        // Set the position of the image (x, y)
        cityImageLabel.setBounds(0, 0, cityLogoWidth, cityLogoHeight);
        topPanel.add(cityImageLabel);
        // ImageIcon cityImageIcon = new ImageIcon("images/delhi.jpg");
        // // Image cityImg = cityImageIcon.getImage();
        // // int cityLogoWidth = 500; // Adjust the width as desired
        // // int cityLogoHeight = 400; // Adjust the height as desired
        // // Image cityResizedImg = cityImg.getScaledInstance(cityLogoWidth,
        // cityLogoHeight, Image.SCALE_SMOOTH);
        // // ImageIcon cityResizedIcon = new ImageIcon(cityResizedImg);

        // JLabel cityImageLabel = new JLabel(cityImageIcon);
        // topPanel.add(cityImageLabel);

        JLabel stringLabel = new JLabel(receivedString);
        stringLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        stringLabel.setHorizontalAlignment(SwingConstants.CENTER);
        stringLabel.setForeground(Color.WHITE);
        bottomPanel.add(stringLabel, BorderLayout.CENTER);

        Border thickBorder = BorderFactory.createLineBorder(Color.WHITE, 5);
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border compoundBorderTop = BorderFactory.createCompoundBorder(thickBorder, emptyBorder);
        Border compoundBorderBottom = BorderFactory.createCompoundBorder(thickBorder, emptyBorder);
        topPanel.setBorder(compoundBorderTop);
        bottomPanel.setBorder(compoundBorderBottom);

        panel4.setLayout(new BorderLayout());
        panel4.add(topPanel, BorderLayout.NORTH);
        panel4.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel4, BorderLayout.CENTER);

        // Layout for Panel 5
        panel5.setLayout(new BorderLayout());
        JLabel label = new JLabel("Made with Love at BITS Pilani");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
        panel5.add(label, BorderLayout.CENTER);

        frame.add(panel5, BorderLayout.SOUTH);
    }

    public static JRadioButton generateRadioButton(String labelText) {
        JRadioButton radioButton = new JRadioButton(labelText);
        radioButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        radioButton.setForeground(Color.WHITE);
        radioButton.setBackground(Color.BLACK); // Set background color to black
        radioButton.setOpaque(true); // Make the background color visible
        return radioButton;
    }
}