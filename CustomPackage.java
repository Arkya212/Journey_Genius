import javax.swing.*;
import java.awt.*;
import java.util.regex.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.*;
import java.util.Random;

public class CustomPackage extends JFrame {

    JPanel subPanel14 = new JPanel();
    JPanel subPanel24 = new JPanel();
    JPanel subPanel34 = new JPanel();
    JPanel subPanel44 = new JPanel();
    JPanel subPanel45 = new JPanel();
    JPanel subPanel22;
    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gbc;
    private static int scheduleNumber = 1;
    private static Vector<String> monuments;
    private static Vector<String> restaurants;
    private static Vector<String> cabs;
    private static Vector<String> hotels;
    private static Vector<String> misc;
    private static Vector<Color> monumentsColors;
    private static Vector<Color> restaurantsColors;
    private static Vector<Color> cabsColors;
    private static Vector<Color> hotelsColors;
    private static Vector<Color> miscColors;

    private JLabel label1;
    private static String extracted_day_itt;
    private JLabel packageTitleLabel;

    // AppConfig.priceOfItinerary = 0;
    // names.getText() is the Variable that needs to be pushed into Days Table;
    Color[] titleColors = { Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK };
    // Color[] titleColors = { Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,
    // Color.PINK, Color.CYAN };
    String[] titleLabels = { "Your Itinerary", "Hotels", "Restaurants", "Monuments", "Miscellaneous", "Cabs" };
    private static Vector<Vector<String>> restaurantMatrix = new Vector<>();
    private static Vector<Vector<String>> monumentsMatrix = new Vector<>();
    private static Vector<Vector<String>> hotelsMatrix = new Vector<>();
    private static Vector<Vector<String>> cabsMatrix = new Vector<>();
    private static Vector<Vector<String>> miscMatrix = new Vector<>();

    public CustomPackage() throws IOException, SQLException {

        // AppConfig.text_city = "delhi";
        // AppConfig.text_days = 4;
        // try {
        //     Connection con = ConnectionProvider.getConnection();
        //     String sq = "SELECT Itinerary_ID FROM itinerary WHERE Itinerary_Name = ?";
        //     PreparedStatement pstmt2 = con.prepareStatement(sq);
        //     pstmt2.setString(1, AppConfig.text_name);
        //     ResultSet rs = pstmt2.executeQuery();
        
        //     if (rs.next()) {
        //         AppConfig.itineary_ID = rs.getInt("Itinerary_ID");
        //     }
        
        //     // Close resources
        //     rs.close();
        //     pstmt2.close();
        //     con.close();
        
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
        

        System.out.println(AppConfig.itineary_ID);
        // AppConfig.itineary_ID = 39;

        AppConfig.priceOfItinerary = 0;

        restaurants = new Vector<>();
        monuments = new Vector<>();
        hotels = new Vector<>();
        cabs = new Vector<>();
        misc = new Vector<>();
        restaurantsColors = new Vector<>();
        monumentsColors = new Vector<>();
        hotelsColors = new Vector<>();
        cabsColors = new Vector<>();
        miscColors = new Vector<>();

        populateRestaurantMatrix();
        populateMonumentsMatrix();
        populateHotelsMatrix();
        populateCabsMatrix();
        populateMiscMatrix();

        // System.out.println("Misc Size" + misc.size());
        // System.out.println(restaurants.size());

        convertToHTMLFormat(restaurants, restaurantMatrix);
        convertToHTMLFormat(monuments, monumentsMatrix);
        convertToHTMLFormat(cabs, cabsMatrix);
        convertToHTMLFormat(hotels, hotelsMatrix);
        convertToHTMLFormat(misc, miscMatrix);

        // for (String item : misc) {
        // System.out.println(item);
        // }

        // Create a Random object
        Random random = new Random();

        // Generate random colors based on the size of the each vector
        // System.out.println(sizeOfLabels);
        for (int i = 0; i < restaurants.size(); i++) {
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color randomColor = new Color(red, green, blue , 100 );
            restaurantsColors.add(randomColor);
        }

        for (int i = 0; i < hotels.size(); i++) {
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color randomColor = new Color(red, green, blue , 100 );
            hotelsColors.add(randomColor);
        }

        for (int i = 0; i < monuments.size(); i++) {
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color randomColor = new Color(red, green, blue , 100);
            monumentsColors.add(randomColor);
        }

        for (int i = 0; i < cabs.size(); i++) {
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256 );
            Color randomColor = new Color(red, green, blue , 100);
            cabsColors.add(randomColor);
        }

        for (int i = 0; i < misc.size(); i++) {
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color randomColor = new Color(red, green, blue, 100);
            miscColors.add(randomColor);
        }

        JFrame frame = new JFrame("Custom Itinerary Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 800);
        frame.setLayout(new BorderLayout(3, 3));
        frame.setVisible(true);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel4 = new JPanel(); // Contains Everything
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.red);
        panel2.setBackground(Color.green);
        panel4.setBackground(Color.magenta);
        panel5.setBackground(Color.BLACK);

        panel1.setPreferredSize(new Dimension(1000, 140));
        panel2.setPreferredSize(new Dimension(255, 500));
        panel4.setPreferredSize(new Dimension(200, 100));
        panel5.setPreferredSize(new Dimension(1000, 20));

        // Layout for Panel 1
        panel1.setLayout(new BorderLayout());
        panel1.setPreferredSize(new Dimension(1000, 140));

        // Layout of subPanel 1
        JPanel titleSubPanel1 = new JPanel();
        titleSubPanel1.setBackground(Color.BLACK);
        titleSubPanel1.setPreferredSize(new Dimension(1000, 100));
        titleSubPanel1.setLayout(new BorderLayout(5, 5));

        JPanel subPanel11 = new JPanel();
        JPanel subPanel21 = new JPanel();
        JPanel subPanel31 = new JPanel();
        subPanel11.setBackground(Color.BLACK);
        subPanel21.setBackground(Color.BLACK);
        subPanel31.setBackground(Color.BLACK);
        subPanel11.setPreferredSize(new Dimension(100, 100));
        subPanel21.setPreferredSize(new Dimension(100, 100));
        subPanel31.setPreferredSize(new Dimension(100, 100));
        // subPanel 1 Layout
        ImageIcon icon = new ImageIcon("images/logo2.png");
        Image img = icon.getImage();
        int logoWidth = 80;
        int logoHeight = 80;
        Image resizedImg = img.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        JLabel imageLabel = new JLabel(resizedIcon);
        subPanel11.add(imageLabel);
        titleSubPanel1.add(subPanel11, BorderLayout.WEST);

        // Sub Panel 2 Layout
        JLabel titleLabel = new JLabel("Custom Package Itenaries");
        titleLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        subPanel21.add(titleLabel);
        titleSubPanel1.add(subPanel21, BorderLayout.CENTER);

        // SubPanel 3 Layout
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 90));
        backButton.setBackground(Color.GRAY);
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setForeground(Color.white);
        subPanel31.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            PlanPage planPage = new PlanPage();
                            frame.dispose();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        titleSubPanel1.add(subPanel31, BorderLayout.EAST);

        panel1.add(titleSubPanel1, BorderLayout.NORTH);

        // Layout of subPanel 2
        JPanel titleSubPanel2 = new JPanel(new GridLayout(1, 6));
        titleSubPanel2.setPreferredSize(new Dimension(0, 40));

        for (int i = 0; i < titleLabels.length; i++) {
            JPanel columnPanel = new JPanel(new BorderLayout());
            columnPanel.setBackground(titleColors[i]);

            JPanel buttonLabelPanel = new JPanel(new BorderLayout());

            buttonLabelPanel.setOpaque(false);

            JLabel label = new JLabel(titleLabels[i], SwingConstants.CENTER);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("TimesNewRoman", Font.BOLD, 18));

            buttonLabelPanel.add(label, BorderLayout.CENTER);

            columnPanel.add(buttonLabelPanel, BorderLayout.CENTER);

            titleSubPanel2.add(columnPanel);
        }

        panel1.add(titleSubPanel2, BorderLayout.SOUTH);
        frame.add(panel1, BorderLayout.NORTH);

        // Layout for Panel 2
        panel2.setLayout(new BorderLayout());
        JPanel subPanel12 = new JPanel();
        subPanel22 = new JPanel();
        JPanel subPanel32 = new JPanel();

        subPanel12.setBackground(Color.MAGENTA);
        subPanel22.setBackground(Color.WHITE);
        subPanel32.setBackground(Color.BLACK);

        subPanel12.setPreferredSize(new Dimension(100, 100));
        subPanel22.setPreferredSize(new Dimension(100, 80));
        subPanel32.setPreferredSize(new Dimension(100, 70));

        // subPanel12 Layout
        packageTitleLabel = new JLabel(
                "<html><center> Day " + scheduleNumber + " Schedule <br> Number of Days Left: "
                        + (AppConfig.text_days - scheduleNumber) + "<br> Itinerary Price: "
                        + AppConfig.priceOfItinerary
                        + "</center></html>");
        System.out.println(AppConfig.priceOfItinerary);
        packageTitleLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
        packageTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        packageTitleLabel.setForeground(Color.WHITE);
        packageTitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        subPanel12.add(packageTitleLabel);
        panel2.add(subPanel12, BorderLayout.NORTH);

        // subPanel22 Layout
        gridBagLayout = new GridBagLayout();
        subPanel22.setLayout(gridBagLayout);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.anchor = GridBagConstraints.NORTH;

        // System.out.println(presentChoice);
        // Create and add labels above text fields
        label1 = new JLabel("<html></html>"); // Initialize label1 here
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label1.setForeground(Color.BLACK);
        subPanel22.add(label1, gbc);

        panel2.add(subPanel22, BorderLayout.CENTER);

        // subPanel32 Layout
        JButton ResetButton = new JButton("Reset");
        ResetButton.setPreferredSize(new Dimension(100, 50));
        subPanel32.add(ResetButton);
        panel2.add(subPanel32, BorderLayout.SOUTH);
        frame.add(panel2, BorderLayout.WEST);

        ResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component[] components = subPanel22.getComponents();
                for (Component component : components) {
                    if (component instanceof JLabel) {
                        ((JLabel) component).setText("");
                        extracted_day_itt = "";
                        AppConfig.priceOfItinerary = 0;
                    }
                }
            }
        });

        JButton SubmitPreferenceButton = new JButton("<html><center>Confirm<br>Itinerary</center></html>");
        SubmitPreferenceButton.setPreferredSize(new Dimension(100, 50));
        subPanel32.add(SubmitPreferenceButton);
        panel2.add(subPanel32, BorderLayout.SOUTH);

        SubmitPreferenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Component[] components = subPanel22.getComponents();
                String labelText = "";
                for (Component component : components) {
                    if (component instanceof JLabel) {
                        JLabel label = (JLabel) component;
                        labelText = label.getText();
                        // System.out.println(labelText);
                    }
                }

                if (labelText.equals("<html></html>") || labelText.equals("")) {
                    JOptionPane.showMessageDialog(null, "Itinerary is Empty!", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    // System.out.println(label1.getText());
                } else if ((AppConfig.text_days - scheduleNumber) == 0) {
                    packageTitleLabel.setText(
                            "<html><center> Day " + scheduleNumber + " Schedule <br> Number of Days Left: "
                                    + (AppConfig.text_days - scheduleNumber) + "<br> Itinerary Price: "
                                    + AppConfig.priceOfItinerary + "</center></html>");

                    AppConfig.day_itt = "<html>" + extracted_day_itt + "</html>";
                    AppConfig.day_itt = correctHtml(AppConfig.day_itt, scheduleNumber);
                    System.out.println(AppConfig.day_itt);
                    // System.out.println(AppConfig.day_itt);

                    Connection con = ConnectionProvider.getConnection();

                    try {
                        // Selecting Cus_pack_ID from custom_day_package table

                        // Inserting values into cust_day table
                        String insertSql = "INSERT INTO cust_day (Cus_pack_ID, Store) VALUES (?, ?)";
                        PreparedStatement insertPstmt = con.prepareStatement(insertSql);
                        insertPstmt.setInt(1, AppConfig.itineary_ID);
                        insertPstmt.setString(2, AppConfig.day_itt);
                        insertPstmt.executeUpdate();

                        // Close resources
                        insertPstmt.close();


                    } catch (SQLException ep) {
                        ep.printStackTrace();
                    }

                    //
                    extracted_day_itt = "";
                    subPanel22.removeAll();
                    subPanel22.revalidate();
                    subPanel22.repaint();
                    scheduleNumber++;
                    JOptionPane.showMessageDialog(null, "Itinerary created successfully.");
                    

                    try {
                        String updateSql = "UPDATE itinerary SET price = ? WHERE Itinerary_ID = ?";
                        PreparedStatement updatePstmt = con.prepareStatement(updateSql);
                        updatePstmt.setInt(1, AppConfig.priceOfItinerary); // Set the price value
                        updatePstmt.setInt(2, AppConfig.itineary_ID); // Set the Itinerary_ID value
                        int rowsUpdated = updatePstmt.executeUpdate();
                    
                        if (rowsUpdated > 0) {
                            System.out.println("Price updated successfully!");
                        } else {
                            System.out.println("No rows updated.");
                        }
                    } catch (SQLException epp) {
                        System.err.println("Error executing SQL update: " + epp.getMessage());
                        epp.printStackTrace();
                    } catch (Exception ep) {
                        System.err.println("Error: " + ep.getMessage());
                        ep.printStackTrace();
                    }
                    
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            try {
                                FinalPage finalPage = new FinalPage();
                                AppConfig.day_itt = "";
                                frame.dispose();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    });
                } else {
                    packageTitleLabel.setText(
                            "<html><center> Day " + scheduleNumber + " Schedule <br> Number of Days Left: "
                                    + (AppConfig.text_days - scheduleNumber) + "<br> Itinerary Price: "
                                    + AppConfig.priceOfItinerary + "</center></html>");

                    AppConfig.day_itt = "<html>" + extracted_day_itt + "</html>";
                    System.out.println(correctHtml(AppConfig.day_itt, scheduleNumber));
                    // System.out.println(AppConfig.day_itt);

                    Connection con = ConnectionProvider.getConnection();

                    try {
                        // Selecting Cus_pack_ID from custom_day_package table

                        // Inserting values into cust_day table
                        String insertSql = "INSERT INTO cust_day (Cus_pack_ID, Store) VALUES (?, ?)";
                        PreparedStatement insertPstmt = con.prepareStatement(insertSql);
                        insertPstmt.setInt(1, AppConfig.itineary_ID);
                        insertPstmt.setString(2, AppConfig.day_itt);
                        insertPstmt.executeUpdate();

                        // Close resources
                        insertPstmt.close();
                        con.close();

                    } catch (SQLException ep) {
                        ep.printStackTrace();
                    }

                    //
                    extracted_day_itt = "";
                    subPanel22.removeAll();
                    subPanel22.revalidate();
                    subPanel22.repaint();
                    scheduleNumber++;
                }
            }
        });

        frame.add(panel2, BorderLayout.WEST);

        // Layout for Panel 4
        subPanel14 = new JPanel();
        subPanel24 = new JPanel();
        subPanel34 = new JPanel();
        subPanel44 = new JPanel();
        subPanel45 = new JPanel();

        subPanel14.setBackground(Color.RED);
        subPanel24.setBackground(Color.WHITE);
        subPanel34.setBackground(Color.BLACK);
        subPanel44.setBackground(Color.MAGENTA);
        subPanel45.setBackground(Color.BLACK);

        panel4.setLayout(new GridLayout(0, 5, 0, 5));
        // Layout for subPane14
        // System.out.println(sizeOfLabels);

        subPanel14 = addReserves(hotelsColors, hotels, hotels.size(), gridBagLayout, gbc, subPanel22);
        panel4.add(subPanel14);

        // Layout for subPane24
        subPanel24 = addReserves(restaurantsColors, restaurants, restaurants.size(), gridBagLayout, gbc, subPanel22);
        panel4.add(subPanel24);

        // Layout for subPane34
        subPanel34 = addReserves(monumentsColors, monuments, monuments.size(), gridBagLayout, gbc, subPanel22);
        panel4.add(subPanel34);

        // Layout for subPane44
        subPanel44 = addReserves(miscColors, misc, misc.size(), gridBagLayout, gbc, subPanel22);
        panel4.add(subPanel44);

        // Layout for subPane45
        subPanel45 = addReserves(cabsColors, cabs, cabs.size(), gridBagLayout, gbc, subPanel22);
        panel4.add(subPanel45);
        frame.add(panel4, BorderLayout.CENTER);

        // Layout for Panel 5
        panel5.setLayout(new BorderLayout());
        JLabel label = new JLabel("Made with Love at BITS Pilani");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel5.add(label, BorderLayout.CENTER);
        frame.add(panel5, BorderLayout.SOUTH);
    }

    private JPanel addReserves(Vector<Color> color, Vector<String> names, int sizeOfLabels, GridBagLayout gridBagLayout,
            GridBagConstraints gbc, JPanel subPanel22) {
        JPanel subPanel = new JPanel(new GridLayout(sizeOfLabels, 1));
        subPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        for (int i = 0; i < sizeOfLabels; i++) {
            final int index = i;
            JPanel labelPanel = new JPanel(new BorderLayout());
            labelPanel.setBackground(color.get(i));

            // Extracting the required HTML tag
            String htmlReceivedString = names.get(i);
            String extractedInfo = extractInfo(htmlReceivedString);
            JLabel label = new JLabel(extractedInfo);

            label.setForeground(Color.BLACK);
            label.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            label.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
            labelPanel.add(label, BorderLayout.NORTH);

            JPanel checkBoxPanel = new JPanel();
            checkBoxPanel.setLayout(new GridLayout(1, 2));
            checkBoxPanel.setBackground(color.get(i));
            checkBoxPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

            JButton addButton = new JButton("Add to List");
            addButton.setForeground(Color.white);
            addButton.setBackground(Color.black);
            addButton.setOpaque(true);

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String indexString = names.get(index);
                    int startIndex = indexString.indexOf("<b>") + "<b>".length();
                    int endIndex = indexString.indexOf("</b>");
                    String extractedText = indexString.substring(startIndex, endIndex);
                    // presentChoice = extractedText;

                    int priceStartIndex = indexString.indexOf("<br>", endIndex) +
                            "<br>".length();

                    int priceEndIndex = indexString.indexOf("<br>", priceStartIndex);
                    if (priceEndIndex == -1) {
                        priceEndIndex = indexString.indexOf("</html>", priceStartIndex);
                    }

                    String priceSubstring = indexString.substring(priceStartIndex,
                            priceEndIndex);

                    // Extract the integer from the price substring
                    // int price = extractInteger(priceSubstring);
                    String numberOnly = priceSubstring.replaceAll("[^0-9]", "");
                    int price = Integer.parseInt(numberOnly);
                    // Add the extracted integer to the priceOfItinerary variable
                    AppConfig.priceOfItinerary += price;

                    // Create a new label and add it to the subPanel22
                    JLabel newLabel = new JLabel(extractedText);
                    newLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                    gbc.gridy++;
                    subPanel22.add(newLabel, gbc);
                    subPanel22.revalidate();
                    subPanel22.repaint();

                    packageTitleLabel.setText("<html><center> Day " + scheduleNumber
                            + " Schedule <br> Number of Days Left: "
                            + (AppConfig.text_days - scheduleNumber) + "<br> Itinerary Price: "
                            + AppConfig.priceOfItinerary
                            + "</center></html>");

                    // System.out.println(names.get(index));
                    extracted_day_itt = extracted_day_itt + "<br>" +
                            names.get(index).replaceAll("<html>|</html>", "");
                    correctHtml(extracted_day_itt, scheduleNumber);
                }
            });
            checkBoxPanel.add(addButton);

            JButton showInfButton = new JButton("<html><center>Show<br>More Info</html>");
            showInfButton.setForeground(Color.white);
            showInfButton.setBackground(Color.black);
            showInfButton.setOpaque(true);

            showInfButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String indexString = names.get(index);
                    String presentChoice = indexString;

                    // Create a new JFrame to display more info
                    JFrame moreInfoFrame = new JFrame("More Info");
                    moreInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    moreInfoFrame.setSize(400, 300);
                    moreInfoFrame.getContentPane().setBackground(Color.BLACK);
                    moreInfoFrame.setLayout(new BorderLayout());

                    // Create a JLabel with white text
                    JLabel infoLabel = new JLabel(presentChoice);
                    infoLabel.setForeground(Color.WHITE);
                    infoLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                    infoLabel.setHorizontalAlignment(JLabel.CENTER);

                    // Add the JLabel to the new JFrame's content pane
                    moreInfoFrame.add(infoLabel, BorderLayout.CENTER);

                    // Set the new JFrame visible
                    moreInfoFrame.setVisible(true);
                }
            });

            checkBoxPanel.add(showInfButton);

            labelPanel.add(checkBoxPanel, BorderLayout.SOUTH);
            subPanel.add(labelPanel, BorderLayout.CENTER);
        }

        // Adding JScrollPane to subPanel
        JScrollPane scrollPane = new JScrollPane(subPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Wrap JScrollPane inside a JPanel
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(scrollPane, BorderLayout.CENTER);

        return wrapperPanel;
    }

    private static String extractInfo(String htmlString) {
        // Define the regex pattern to match the string between <b> tag and the next two
        // <br> tags
        String pattern = "<b>(.*?)</b><br>(.*?)<br>(.*?)<br>";

        // Compile the pattern
        Pattern regex = Pattern.compile(pattern);

        // Match the pattern against the input HTML string
        Matcher matcher = regex.matcher(htmlString);

        // If a match is found, extract and return the desired information
        if (matcher.find()) {
            // Group 1 contains the text between <b> tag
            String name = matcher.group(1);
            // Group 2 contains the text between the first <br> tag
            String address = matcher.group(2);
            // Group 3 contains the text between the second <br> tag
            String city = matcher.group(3);
            // Concatenate the extracted information
            return "<html>" + "<b>" + name + "</b>" + "<br>" + address + "<br>" + city + "</html>";
        } else {
            // Return empty string if no match is found
            return "";
        }
    }

    private static void convertToHTMLFormat(Vector<String> attributeStrings, Vector<Vector<String>> attributeMatrix) {
        attributeStrings.clear();
        for (Vector<String> row : attributeMatrix) {
            StringBuilder htmlItem = new StringBuilder("<html><b>" + row.get(0) + "</b><br>");
            for (int i = 1; i < row.size(); i++) {
                htmlItem.append(row.get(i)).append("<br>");
            }
            htmlItem.append("</html>");
            attributeStrings.add(htmlItem.toString());
        }
    }

    private static void populateRestaurantMatrix() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionProvider.getConnection();
            String sql = "SELECT r.Name, r.Avg_Price_for_2, r.stars, t.opening, t.closing, r.Speciality FROM restaurants AS r INNER JOIN address AS a ON r.address_ID = a.address_ID INNER JOIN opening_closing_time AS t ON r.time_id = t.time_id WHERE a.City = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, AppConfig.text_city);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("Name")); // Restaurant Name
                row.add("Avg Price: ₹" + rs.getInt("Avg_Price_for_2")); // Avg Price for 2 in INR
                row.add("Rating: " + rs.getInt("stars") + " Stars"); // Rating
                row.add("Opening Time: " + rs.getString("opening")); // Opening Time
                row.add("Closing Time: " + rs.getString("closing")); // Closing Time
                row.add("Specialty: " + rs.getString("Speciality")); // Specialty
                row.add("City: " + AppConfig.text_city); // City

                restaurantMatrix.add(row);
                // System.out.println(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close ResultSet, PreparedStatement, and Connection
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void populateMonumentsMatrix() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionProvider.getConnection();
            String sql = "SELECT m.Name, m.Price, m.review, m.about, t.opening, t.closing FROM museums_and_monuments AS m INNER JOIN opening_closing_time AS t ON m.time_id = t.time_id WHERE m.City = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, AppConfig.text_city);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("Name")); // Monument Name
                row.add("Price: ₹" + rs.getInt("Price")); // Price in INR
                row.add("Review: " + rs.getString("review")); // Review
                row.add("About: " + rs.getString("about")); // About
                row.add("Opening Time: " + rs.getString("opening")); // Opening Time
                row.add("Closing Time: " + rs.getString("closing")); // Closing Time
                monumentsMatrix.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void populateHotelsMatrix() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionProvider.getConnection();
            String sql = "SELECT h.Name, h.price, h.rating, h.amenities, h.telephone, h.review FROM hotel AS h INNER JOIN address AS a ON h.address_ID = a.address_ID WHERE a.City = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, AppConfig.text_city);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("Name")); // Hotel Name
                row.add("Price: ₹" + rs.getInt("price")); // Price in INR
                row.add("Rating: " + rs.getInt("rating") + " Stars"); // Rating
                row.add("Amenities: " + rs.getString("amenities")); // Amenities
                row.add("Telephone: " + rs.getString("telephone")); // Telephone
                row.add("Review: " + rs.getString("review")); // Review
                hotelsMatrix.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void populateCabsMatrix() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionProvider.getConnection();
            String sql = "SELECT c.first_name, c.last_name, c.price, c.cab_number, c.cab_type, c.phone_number,  c.review FROM cab AS c WHERE City = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, AppConfig.text_city);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("first_name") + " " + rs.getString("last_name")); // Driver Name
                row.add("Price: ₹" + rs.getInt("price"));
                row.add("Cab Number: " + rs.getString("cab_number")); // Cab Number
                row.add("Cab Type: " + rs.getString("cab_type")); // Cab Type
                row.add("Phone: " + rs.getString("phone_number")); // Phone Number
                row.add("Review: " + rs.getString("review")); // Review
                cabsMatrix.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void populateMiscMatrix() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionProvider.getConnection();
            String sql = "SELECT h.Name, h.price, h.review, t.opening, t.closing FROM miscellaneous AS h INNER JOIN address AS a ON h.address_ID = a.address_ID INNER JOIN opening_closing_time AS t ON h.time_id = t.time_id WHERE a.City = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, AppConfig.text_city);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("Name")); // Name
                row.add("Price: ₹ " + rs.getInt("price")); // Price in INR
                row.add("Review: " + rs.getString("review")); // Review
                row.add("Opening Time: " + rs.getString("opening")); // Opening Time
                row.add("Closing Time: " + rs.getString("closing")); // Closing Time
                miscMatrix.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the ResultSet, PreparedStatement, and Connection
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String correctHtml(String input, int dayNumber) {
        input = input.replaceAll("null", "<br>");
        input = input.replaceAll("\\?", "₹"); // Unicode for ₹ symbol
        return input;
    }
}