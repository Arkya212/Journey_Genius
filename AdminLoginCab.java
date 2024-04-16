import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Color;
import java.text.ParseException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminLoginCab extends JFrame {
    private JTextField firstName = new JTextField("Enter your First Name");
    private JTextField lastName = new JTextField("Enter your Last Name");
    private JTextField mobileField = new JTextField("Mobile Number");
    private JTextField Cab_Number = new JTextField("Cab Number");
    private JTextField Price = new JTextField("Price in rupees");
    private JTextField cityField = new JTextField("City you ride in ");
    private JTextField Review = new JTextField("Review");
    private JTextField CabType = new JTextField("Car Type");
    private boolean firstNameFirstFocus = true;
    private boolean lastNameFirstFocus = true;
    private boolean mobileFieldFirstFocus = true;
    private boolean Cab_NumberFirstFocus = true;
    private boolean PriceFirstFocus = true;
    private boolean cityFieldFirstFocus = true;
    private boolean ReviewFirstFocus = true;
    private boolean CabTypeFirstFocus = true;
    private JLabel nameError = new JLabel();
    private JLabel usernameError = new JLabel();
    public String finalfirstNameString;
    public String finallastNameString;
    public String finalmobileFieldString;
    public String finalCab_NumberString;
    public String finalPriceFString;
    public String finalcityString;
    public String finalReviewString;
    public String finalCabTypeString;
    private JButton signUpButton = new JButton("Add Cab");
    private JButton resetButton = new JButton("Reset");

    public AdminLoginCab() throws IOException, SQLException {
        Connection con = ConnectionProvider.getConnection();
        String q = "INSERT INTO Cab (City, First_Name, Last_Name, Cab_Number, Cab_Type, Phone_Number, Price, Review) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(q);

        JFrame frame = new JFrame("Admin Access for Cab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout(3, 3));
        frame.setVisible(true);

        JPanel panel1 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.red);
        panel3.setBackground(Color.black);
        panel5.setBackground(Color.blue);

        panel1.setPreferredSize(new Dimension(1000, 80));
        panel3.setPreferredSize(new Dimension(210, 100));
        panel5.setPreferredSize(new Dimension(1000, 20));

        firstName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (firstNameFirstFocus) {
                    firstName.setText("");
                    firstNameFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (firstName.getText().isEmpty() && !firstNameFirstFocus) {
                    firstName.setText("Enter your First Name");
                    firstName.setForeground(Color.gray);
                    firstNameFirstFocus = true;
                } else {
                    finalfirstNameString = firstName.getText();
                }
            }
        });

        lastName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (lastNameFirstFocus) {
                    lastName.setText("");
                    lastNameFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (lastName.getText().isEmpty() && !lastNameFirstFocus) {
                    lastName.setText("Enter your Last Name");
                    lastName.setForeground(Color.gray);
                    lastNameFirstFocus = true;
                } else {
                    finallastNameString = lastName.getText();
                }
            }
        });

        mobileField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (mobileFieldFirstFocus) {
                    mobileField.setText("");
                    mobileFieldFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String mobileNumber = mobileField.getText().trim();
                if (mobileField.getText().isEmpty()) {
                    mobileField.setText("Phone Number");
                    mobileField.setForeground(Color.gray);
                    mobileFieldFirstFocus = true;
                }

                if (!isValidMobileNumber(mobileNumber)) {
                    JOptionPane.showMessageDialog(null, "Invalid Mobile Number", "Error", JOptionPane.ERROR_MESSAGE);
                    mobileField.setText("Phone Number");
                } else {
                    finalmobileFieldString = mobileNumber;
                }
            }

            private boolean isValidMobileNumber(String mobileNumber) {
                return mobileNumber.matches("\\d{10}");
            }
        });

        Cab_Number.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Cab_NumberFirstFocus) {
                    Cab_Number.setText("");
                    Cab_NumberFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Cab_Number.getText().isEmpty()) {
                    Cab_Number.setText("Cab Number");
                    Cab_Number.setForeground(Color.gray);
                    Cab_NumberFirstFocus = true;
                }
            }
        });

        Price.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (Price.getText().isEmpty()) {
                    Price.setText("Price in rupees");
                    Price.setForeground(Color.gray);
                    nameError.setText("");
                    PriceFirstFocus = true;
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (PriceFirstFocus) {
                    Price.setText("");
                    PriceFirstFocus = false;
                }

                if (Price.getText().equals("Price in rupees")) {
                    Price.setText("");
                    Price.setForeground(Color.black);
                }
            }
        });

        CabType.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (CabTypeFirstFocus) {
                    CabType.setText("");
                    CabTypeFirstFocus = false;
                } else {
                    finalCabTypeString = CabType.getText();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (CabType.getText().isEmpty()) {
                    CabType.setText("Car Type");
                    CabType.setForeground(Color.gray);
                    CabTypeFirstFocus = true;
                }
            }
        });

        cityField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cityFieldFirstFocus) {
                    cityField.setText("");
                    cityFieldFirstFocus = false;
                } else {
                    finalcityString = cityField.getText();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cityField.getText().isEmpty()) {
                    cityField.setText("City");
                    cityField.setForeground(Color.gray);
                    cityFieldFirstFocus = true;
                }
            }
        });

        Review.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ReviewFirstFocus) {
                    Review.setText("");
                    ReviewFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Review.getText().isEmpty()) {
                    Review.setText("Reviews");
                    Review.setForeground(Color.gray);
                    ReviewFirstFocus = true;
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstNameText = firstName.getText().trim();
                String lastNameText = lastName.getText().trim();
                String mobileText = mobileField.getText().trim();
                String Cab_NumberText = Cab_Number.getText().trim();
                String PriceText = Price.getText().trim();
                String CabTypeText = CabType.getText().trim();
                String cityText = cityField.getText().trim();
                String ReviewText = Review.getText().trim();

                boolean isValid = true;

                if (firstNameText.isEmpty() || firstNameText.equals("Enter your First Name")) {
                    nameError.setForeground(Color.RED);
                    nameError.setText("Please enter your first name");
                    isValid = false;
                }

                if (lastNameText.isEmpty() || lastNameText.equals("Enter your Last Name")) {
                    nameError.setForeground(Color.RED);
                    nameError.setText("Please enter your last name");
                    isValid = false;
                }

                if (mobileText.isEmpty() || mobileText.equals("Mobile Number")) {
                    nameError.setForeground(Color.RED);
                    nameError.setText("Please enter your mobile number");
                    isValid = false;
                }

                if (PriceText.isEmpty() || PriceText.equals("Price in rupees")) {
                    nameError.setForeground(Color.RED);
                    nameError.setText("Please enter the price");
                    isValid = false;
                } else {
                    try {
                        Double.parseDouble(PriceText);
                    } catch (NumberFormatException ex) {
                        nameError.setForeground(Color.RED);
                        nameError.setText("Invalid price format");
                        isValid = false;
                    }
                }

                if (CabTypeText.isEmpty() || CabTypeText.equals("Car Type")) {
                    nameError.setForeground(Color.RED);
                    nameError.setText("Please enter the cab type");
                    isValid = false;
                }

                if (cityText.isEmpty() || cityText.equals("City")) {
                    nameError.setForeground(Color.RED);
                    nameError.setText("Please enter the city");
                    isValid = false;
                }

                if (isValid) {
                    JOptionPane.showMessageDialog(AdminLoginCab.this, "Cab details added successfully",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        pstmt.setString(1, cityText);
                        pstmt.setString(2, firstNameText);
                        pstmt.setString(3, lastNameText);
                        pstmt.setString(4, Cab_NumberText);
                        pstmt.setString(5, CabTypeText);
                        pstmt.setString(6, mobileText);
                        pstmt.setString(7, PriceText);
                        pstmt.setString(8, ReviewText);
                        int rowsAffected = pstmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Cab details added successfully");
                        } else {
                            JOptionPane.showMessageDialog(AdminLoginCab.this, "Failed to add cab details. Please try again.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(AdminLoginCab.this, "Error: " + ex.getMessage(), "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(AdminLoginCab.this, "Please check and correct the invalid fields",
                            "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNameFirstFocus = true;
                lastNameFirstFocus = true;
                mobileFieldFirstFocus = true;
                Cab_NumberFirstFocus = true;
                PriceFirstFocus = true;
                cityFieldFirstFocus = true;
                ReviewFirstFocus = true;
                nameError.setText("");
                usernameError.setText("");
                firstName.setText("Enter your First Name");
                lastName.setText("Enter your Last Name");
                mobileField.setText("Mobile Number");
                Price.setText("Price in rupees");
                Cab_Number.setText("Cab Number");
                CabType.setText("Car Type");
                cityField.setText("City");
                Review.setText("Reviews");
            }
        });

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

        JLabel imageLabel = new JLabel(resizedIcon);
        subPanel11.add(imageLabel);

        panel1.add(subPanel11, BorderLayout.WEST);

        // Sub Panel 2 Layout
        JLabel titleLabel = new JLabel("Admin Access for CAB");
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
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent click) {
                try {
                    AdminPage admin = new AdminPage();
                    frame.dispose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        subPanel31.add(backButton);
        panel1.add(subPanel31, BorderLayout.EAST);
        frame.add(panel1, BorderLayout.NORTH);

        // Panel 3 Layout
        panel3.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Add the "Register your Cab Details" text
        JLabel signUpLabel = new JLabel("Register your Cab Details");
        signUpLabel.setForeground(Color.WHITE);
        signUpLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel3.add(signUpLabel, gbc);

        // Add the form elements
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setForeground(Color.WHITE);
        panel3.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        firstName.setPreferredSize(new Dimension(300, 30));
        panel3.add(firstName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setForeground(Color.WHITE);
        panel3.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        lastName.setPreferredSize(new Dimension(300, 30));
        panel3.add(lastName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel mobileLabel = new JLabel("Mobile Number:");
        mobileLabel.setForeground(Color.WHITE);
        panel3.add(mobileLabel, gbc);
        gbc.gridx = 1;
        mobileField.setPreferredSize(new Dimension(300, 30));
        panel3.add(mobileField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel Cab_NumberLabel = new JLabel("Cab Number:");
        Cab_NumberLabel.setForeground(Color.WHITE);
        panel3.add(Cab_NumberLabel, gbc);
        gbc.gridx = 1;
        Cab_Number.setPreferredSize(new Dimension(300, 30));
        panel3.add(Cab_Number, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel PriceLabel = new JLabel("Price:");
        PriceLabel.setForeground(Color.WHITE);
        panel3.add(PriceLabel, gbc);
        gbc.gridx = 1;
        Price.setPreferredSize(new Dimension(300, 30));
        panel3.add(Price, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel CabTypeLabel = new JLabel("Cab Type:");
        CabTypeLabel.setForeground(Color.WHITE);
        panel3.add(CabTypeLabel, gbc);
        gbc.gridx = 1;
        CabType.setPreferredSize(new Dimension(300, 30));
        panel3.add(CabType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setForeground(Color.WHITE);
        panel3.add(cityLabel, gbc);
        gbc.gridx = 1;
        cityField.setPreferredSize(new Dimension(300, 30));
        panel3.add(cityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel ReviewLabel = new JLabel("Review:");
        ReviewLabel.setForeground(Color.WHITE);
        panel3.add(ReviewLabel, gbc);
        gbc.gridx = 1;
        Review.setPreferredSize(new Dimension(300, 30));
        panel3.add(Review, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        signUpButton.setPreferredSize(new Dimension(100, 40));
        panel3.add(signUpButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        resetButton.setPreferredSize(new Dimension(100, 40));
        panel3.add(resetButton, gbc);

        // Panel 5 Layout
        panel5.setBackground(Color.blue);
        panel5.setPreferredSize(new Dimension(500, 30));
        JLabel label = new JLabel();
        label.setForeground(Color.green);
        label.setText("Welcome to Journey Genius");
        panel5.add(label);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel3, BorderLayout.CENTER);
        frame.add(panel5, BorderLayout.SOUTH);
    }
}