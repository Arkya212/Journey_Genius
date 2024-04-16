import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

public class PrePackage extends JFrame {
    ButtonGroup buttonGroup = new ButtonGroup();
    List<List<String>> data = new ArrayList<>();
    int totalTravelDays;
    String feedback;
    int price;
    int packid;

    public PrePackage() throws IOException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        // AppConfig.prePackageSelectId = 6;
        // AppConfig.text_city = "Kolkata";
        // AppConfig.priceOfItinerary = 1000;
        // AppConfig.text_days = 8;

        con = ConnectionProvider.getConnection();
        String sql = "SELECT Total_Travel_Days, Feedback, Price, Package_ID FROM pre_package WHERE City = ?";
        String sql2 = "SELECT COUNT(*) AS ct FROM pre_package WHERE City = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, AppConfig.text_city);
        pstmt2 = con.prepareStatement(sql2);
        pstmt2.setString(1, AppConfig.text_city);
        rs = pstmt.executeQuery();
        rs2 = pstmt2.executeQuery();

        while (rs.next()) {
            List<String> row = new ArrayList<>();
            row.add(rs.getString("Package_ID"));
            row.add(rs.getString("Total_Travel_Days"));
            row.add(rs.getString("Feedback"));
            row.add(rs.getString("Price"));
            data.add(row);
        }

        // Print the data
        for (List<String> row : data) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }

        rs = pstmt.executeQuery(); // To reset the rs pointer.

        int count = 0;
        if (rs2.next()) {
            count = rs2.getInt("ct");
        }

        if (count > 5) {
            count = 5;
        }

        JFrame frame = new JFrame("Pre-Package Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout(3, 3));
        frame.setVisible(true);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.red);
        panel2.setBackground(Color.green);
        panel4.setBackground(Color.BLACK);
        panel5.setBackground(Color.BLACK);

        panel1.setPreferredSize(new Dimension(1000, 80)); // Create a div
        panel2.setPreferredSize(new Dimension(250, 500)); // Create a div
        panel4.setPreferredSize(new Dimension(200, 100)); // Create a div
        panel5.setPreferredSize(new Dimension(1000, 30)); // Create a div

        // Panel 1 Layout
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
        JLabel titleLabel = new JLabel("Pre-Packages Itenaries");
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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Instantiate the PrePackage frame
                PlanPage PlanPageFrame;
                try {
                    PlanPageFrame = new PlanPage();
                    frame.dispose();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Panel 2 Layout
        // SubPanel1 Layout
        panel2.setLayout(new BorderLayout());
        JPanel subPanel12 = new JPanel();
        JPanel subPanel22 = new JPanel();
        JPanel subPanel32 = new JPanel();

        subPanel12.setBackground(Color.BLACK);
        subPanel22.setBackground(Color.BLACK);
        subPanel32.setBackground(Color.BLACK);

        subPanel12.setPreferredSize(new Dimension(100, 200));
        subPanel22.setPreferredSize(new Dimension(100, 80));
        subPanel32.setPreferredSize(new Dimension(100, 80));

        // subPanel12 Layout
        JLabel packageTitleLabel = new JLabel(
                "<html><center>Choose<br>your<br>Pre-Package<br>Itineraries</center></html>");
        packageTitleLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        packageTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        packageTitleLabel.setForeground(Color.WHITE);
        packageTitleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        subPanel12.add(packageTitleLabel);
        panel2.add(subPanel12, BorderLayout.NORTH);

        // subPanel22 Layout
        GridBagLayout gridBagLayout = new GridBagLayout();
        subPanel22.setLayout(gridBagLayout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        panel2.add(subPanel22, BorderLayout.CENTER);

        // subPanel32 Layout
        JButton ProceedButton = new JButton("<html><center>Proceed for <br> check out</center></html>");
        ProceedButton.setPreferredSize(new Dimension(100, 50));
        subPanel32.add(ProceedButton);

        ProceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Enumeration<AbstractButton> buttons = buttonGroup.getElements();
                while (buttons.hasMoreElements()) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        AppConfig.prePackageSelectId = (int) button.getClientProperty("packid");
                        Connection con = null;
                        PreparedStatement pstmt = null;

                        try {
                            con = ConnectionProvider.getConnection();
                            String sql = "INSERT INTO pre_itt (pre_id, itt_id) VALUES (?, ?)";
                            pstmt = con.prepareStatement(sql);
                            pstmt.setInt(1, AppConfig.prePackageSelectId); // Set the pre_id value
                            pstmt.setInt(2, AppConfig.itineary_ID); // Set the itt_id value

                            int rowsInserted = pstmt.executeUpdate();

                            if (rowsInserted > 0) {
                                System.out.println("Data inserted successfully!");
                            } else {
                                System.out.println("No rows inserted.");
                            }

                        } catch (SQLException ep) {
                            System.err.println("Error executing SQL insert: " + ep.getMessage());
                            ep.printStackTrace();
                        } catch (Exception ep) {
                            System.err.println("Error: " + ep.getMessage());
                            ep.printStackTrace();
                        } finally {
                            try {
                                if (pstmt != null) {
                                    pstmt.close();
                                }
                                if (con != null) {
                                    con.close();
                                }
                            } catch (SQLException ep) {
                                System.err.println("Error closing connection: " + ep.getMessage());
                                ep.printStackTrace();
                            }
                        }


                        System.out.println("Selected Pack ID: " + AppConfig.prePackageSelectId);
                        getPriceAndTotalTravelDays(data, AppConfig.prePackageSelectId);
                        System.out.println("Total Price" + AppConfig.prePackagePriceOfItinerary);
                        System.out.println("Total Days: " + AppConfig.prePackagetext_days);
                        try {
                            PrePackageFinalPage prePackageFinalPage = new PrePackageFinalPage();
                            frame.dispose();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    }
                }
            }
        });

        panel2.add(subPanel32, BorderLayout.SOUTH);

        frame.add(panel2, BorderLayout.WEST);

        // Panel 4 Layout
        panel4.setLayout(new GridLayout(count, 1, 10, 20));
        if (count == 0) {
            JPanel subPanel = new JPanel();
            subPanel.setLayout(new GridLayout(1, 1));

            JLabel messageLabel = new JLabel(
                    "Sorry for the inconvenience but currently no tours are offered for the current selection.");
            messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            subPanel.setLayout(new BorderLayout());
            subPanel.add(messageLabel, BorderLayout.CENTER);

            panel4.add(subPanel);
        } else {
            // Create a ButtonGroup
            for (int i = 0; i < count; i++) {
                JPanel subPanel = new JPanel();
                subPanel.setLayout(new GridLayout(1, 4));

                // Fetch the data from rs
                if (rs.next()) {
                    totalTravelDays = rs.getInt("Total_Travel_Days");
                    feedback = rs.getString("Feedback");
                    price = rs.getInt("Price");
                    packid = rs.getInt("Package_ID");

                    // Determine the color scheme based on the current index
                    Color color;
                    switch (i % 5) {
                        case 0:
                            color = Color.LIGHT_GRAY;
                            break;
                        case 1:
                            color = Color.RED;
                            break;
                        case 2:
                            color = Color.LIGHT_GRAY;
                            break;
                        case 3:
                            color = Color.RED;
                            break;
                        case 4:
                            color = Color.GREEN;
                            break;
                        default:
                            color = Color.LIGHT_GRAY;
                            break;
                    }

                    addSubPanel(subPanel, color, "Number of Days: " + totalTravelDays, true, buttonGroup, packid);
                    addSubPanel(subPanel, Color.GREEN, "Travel Name: " + feedback, false, buttonGroup, -1);
                    addSubPanel(subPanel, Color.YELLOW, "Price: ₹" + price, false, buttonGroup, -1);

                    panel4.add(subPanel);
                }
            }
        }

        frame.add(panel4, BorderLayout.CENTER);

        // Panel 5 Layout
        panel5.setLayout(new BorderLayout());

        // Add label with text "Text 1" to the center of panel5
        JLabel label = new JLabel("Made with Love at BITS Pilani");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
        panel5.add(label, BorderLayout.CENTER);

        frame.add(panel5, BorderLayout.SOUTH);
    }

    private void addSubPanel(JPanel parent, Color color, String labelText, boolean addRadioButton,
            ButtonGroup buttonGroup, int packid) {
        JPanel subPanel = new JPanel(new BorderLayout(10, 0));
        subPanel.setBackground(color);
        subPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        label.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 0));
        labelPanel.add(label, BorderLayout.CENTER);

        if (addRadioButton) {
            JPanel radioButtonPanel = new JPanel();
            radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.X_AXIS)); // Use BoxLayout for
                                                                                           // centering
            radioButtonPanel.setBackground(Color.BLACK);
            radioButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // Add padding

            JRadioButton radioButton = new JRadioButton();
            radioButton.setOpaque(false); // Make the background transparent
            radioButton.putClientProperty("packid", packid); // Set packid as client property

            buttonGroup.add(radioButton); // Add the radio button to the ButtonGroup

            radioButtonPanel.add(Box.createHorizontalGlue());
            radioButtonPanel.add(radioButton);
            radioButtonPanel.add(Box.createHorizontalGlue());

            subPanel.add(radioButtonPanel, BorderLayout.WEST);
        }

        subPanel.add(labelPanel, BorderLayout.CENTER);
        parent.add(subPanel);
    }

    public void getPriceAndTotalTravelDays(List<List<String>> data, int packageIdToSearch) {
        for (List<String> row : data) {
            if (row.get(0).equals(String.valueOf(packageIdToSearch))) {
                String price = row.get(3);
                String totalTravelDays = row.get(1);

                AppConfig.prePackagetext_days = Integer.parseInt(totalTravelDays);
                AppConfig.prePackagePriceOfItinerary = Integer.parseInt(price);
                return;
            }
        }
        System.out.println("Package ID not found: " + packageIdToSearch);
    }

}