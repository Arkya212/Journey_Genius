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

public class HotelRegistration extends JFrame {
    private JTextField hotelName = new JTextField("Enter Hotel Name");
    private JTextField price = new JTextField("Enter Price");
    private JTextField rating = new JTextField("Enter Rating (out of 5)");
    private JTextField amenities = new JTextField("Enter Amenities");
    private JTextField phoneNumber = new JTextField("Enter Phone Number");
    private JTextField addressId = new JTextField("Enter Address ID");
    private JTextField review = new JTextField("Enter reviews");
    private boolean hotelNameFirstFocus = true;
    private boolean priceFirstFocus = true;
    private boolean ratingFirstFocus = true;
    private boolean amenitiesFirstFocus = true;
    private boolean phoneNumberFirstFocus = true;
    private boolean addressIdFirstFocus = true;
    private boolean ReviewFirstFocus = true;

    private JLabel errorLabel = new JLabel();
    public String finalHotelName;
    public String finalPrice;
    public String finalRating;
    public String finalAmenities;
    public String finalPhoneNumber;
    public String finalAddressId;
    public String finalReview;
    private JButton registerButton = new JButton("Register Hotel");
    private JButton resetButton = new JButton("Reset");

    public HotelRegistration() throws IOException, SQLException {
        Connection con = ConnectionProvider.getConnection();
        String q = "INSERT INTO Hotel (Name, Price, Rating, Amenities, Telephone,Review, Address_ID) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(q);

        JFrame frame = new JFrame("Hotel Registration");
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

        hotelName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (hotelNameFirstFocus) {
                    hotelName.setText("");
                    hotelNameFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (hotelName.getText().isEmpty() && !hotelNameFirstFocus) {
                    hotelName.setText("Enter Hotel Name");
                    hotelName.setForeground(Color.gray);
                    hotelNameFirstFocus = true;
                } else {
                    finalHotelName = hotelName.getText();
                }
            }
        });

        price.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (priceFirstFocus) {
                    price.setText("");
                    priceFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (price.getText().isEmpty() && !priceFirstFocus) {
                    price.setText("Enter Price");
                    price.setForeground(Color.gray);
                    priceFirstFocus = true;
                } else {
                    finalPrice = price.getText();
                }
            }
        });

        rating.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ratingFirstFocus) {
                    rating.setText("");
                    ratingFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (rating.getText().isEmpty() && !ratingFirstFocus) {
                    rating.setText("Enter Rating (out of 5)");
                    rating.setForeground(Color.gray);
                    ratingFirstFocus = true;
                } else {
                    finalRating = rating.getText();
                }
            }
        });

        amenities.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (amenitiesFirstFocus) {
                    amenities.setText("");
                    amenitiesFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (amenities.getText().isEmpty() && !amenitiesFirstFocus) {
                    amenities.setText("Enter Amenities");
                    amenities.setForeground(Color.gray);
                    amenitiesFirstFocus = true;
                } else {
                    finalAmenities = amenities.getText();
                }
            }
        });

        phoneNumber.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (phoneNumberFirstFocus) {
                    phoneNumber.setText("");
                    phoneNumberFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String mobileNumber = phoneNumber.getText().trim();
                if (phoneNumber.getText().isEmpty()) {
                    phoneNumber.setText("Enter Phone Number");
                    phoneNumber.setForeground(Color.gray);
                    phoneNumberFirstFocus = true;
                }

                if (!isValidMobileNumber(mobileNumber)) {
                    JOptionPane.showMessageDialog(null, "Invalid Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
                    phoneNumber.setText("Enter Phone Number");
                } else {
                    finalPhoneNumber = mobileNumber;
                }
            }

            private boolean isValidMobileNumber(String mobileNumber) {
                return mobileNumber.matches("\\d{10}");
            }
        });

        addressId.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (addressIdFirstFocus) {
                    addressId.setText("");
                    addressIdFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (addressId.getText().isEmpty() && !addressIdFirstFocus) {
                    addressId.setText("Enter Address ID");
                    addressId.setForeground(Color.gray);
                    addressIdFirstFocus = true;
                } else {
                    finalAddressId = addressId.getText();
                }
            }
        });
        review.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ReviewFirstFocus) {
                    review.setText("");
                    ReviewFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (review.getText().isEmpty() && !ReviewFirstFocus) {
                    review.setText("Enter Review");
                    review.setForeground(Color.gray);
                    ReviewFirstFocus = true;
                } else {
                    finalReview = review.getText();
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelNameText = hotelName.getText().trim();
                String priceText = price.getText().trim();
                String ratingText = rating.getText().trim();
                String amenitiesText = amenities.getText().trim();
                String phoneNumberText = phoneNumber.getText().trim();
                String addressIdText = addressId.getText().trim();
                String reviewText = review.getText().trim();

                boolean isValid = true;

                if (hotelNameText.isEmpty() || hotelNameText.equals("Enter Hotel Name")) {
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setText("Please enter the hotel name");
                    isValid = false;
                }

                if (priceText.isEmpty() || priceText.equals("Enter Price")) {
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setText("Please enter the price");
                    isValid = false;
                } else {
                    try {
                        Double.parseDouble(priceText);
                    } catch (NumberFormatException ex) {
                        errorLabel.setForeground(Color.RED);
                        errorLabel.setText("Invalid price format");
                        isValid = false;
                    }
                }

                if (ratingText.isEmpty() || ratingText.equals("Enter Rating (out of 5)")) {
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setText("Please enter the rating");
                    isValid = false;
                } else {
                    try {
                        double ratingValue = Double.parseDouble(ratingText);
                        if (ratingValue < 0 || ratingValue > 5) {
                            errorLabel.setForeground(Color.RED);
                            errorLabel.setText("Rating must be between 0 and 5");
                            isValid = false;
                        }
                    } catch (NumberFormatException ex) {
                        errorLabel.setForeground(Color.RED);
                        errorLabel.setText("Invalid rating format");
                        isValid = false;
                    }
                }

                if (amenitiesText.isEmpty() || amenitiesText.equals("Enter Amenities")) {
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setText("Please enter the amenities");
                    isValid = false;
                }

                if (phoneNumberText.isEmpty() || phoneNumberText.equals("Enter Phone Number")) {
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setText("Please enter the phone number");
                    isValid = false;
                }

                if (addressIdText.isEmpty() || addressIdText.equals("Enter Address ID")) {
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setText("Please enter the address ID");
                    isValid = false;
                }
                if (reviewText.isEmpty() || reviewText.equals("Enter Review")) {
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setText("Please enter the Review");
                    isValid = false;
                }



                if (isValid) {
                    JOptionPane.showMessageDialog(HotelRegistration.this, "Hotel registered successfully",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        pstmt.setString(1, hotelNameText);
                        pstmt.setString(2, priceText);
                        pstmt.setString(3, ratingText);
                        pstmt.setString(4, amenitiesText);
                        pstmt.setString(5, phoneNumberText);
                        pstmt.setString(6, reviewText);
                        pstmt.setString(7, addressIdText);
                        
                        int rowsAffected = pstmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Hotel registered successfully");
                        } else {
                            JOptionPane.showMessageDialog(HotelRegistration.this, "Failed to register hotel. Please try again.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(HotelRegistration.this, "Error: " + ex.getMessage(), "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(HotelRegistration.this, "Please check and correct the invalid fields",
                            "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hotelNameFirstFocus = true;
                priceFirstFocus = true;
                ratingFirstFocus = true;
                amenitiesFirstFocus = true;
                phoneNumberFirstFocus = true;
                addressIdFirstFocus = true;
                ReviewFirstFocus = true;

                errorLabel.setText("");
                hotelName.setText("Enter Hotel Name");
                price.setText("Enter Price");
                rating.setText("Enter Rating (out of 5)");
                amenities.setText("Enter Amenities");
                phoneNumber.setText("Enter Phone Number");
                addressId.setText("Enter Address ID");
                review.setText("Enter review");
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
        ImageIcon icon = new ImageIcon("images/hotel_logo.png");
        Image img = icon.getImage();
        int logoWidth = 80;
        int logoHeight = 80;
        Image resizedImg = img.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);

        JLabel imageLabel = new JLabel(resizedIcon);
        subPanel11.add(imageLabel);

        panel1.add(subPanel11, BorderLayout.WEST);

        // Sub Panel 2 Layout
        JLabel titleLabel = new JLabel("Hotel Registration");
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

        // Add the "Hotel Registration" text
        JLabel registrationLabel = new JLabel("Hotel Registration");
        registrationLabel.setForeground(Color.WHITE);
        registrationLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel3.add(registrationLabel, gbc);

        // Add the form elements
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel hotelNameLabel = new JLabel("Hotel Name:");
        hotelNameLabel.setForeground(Color.WHITE);

        panel3.add(hotelNameLabel, gbc);
        gbc.gridx = 1;
        hotelName.setPreferredSize(new Dimension(300, 30));
        panel3.add(hotelName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setForeground(Color.WHITE);
        panel3.add(priceLabel, gbc);
        gbc.gridx = 1;
        price.setPreferredSize(new Dimension(300, 30));
        panel3.add(price, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setForeground(Color.WHITE);
        panel3.add(ratingLabel, gbc);
        gbc.gridx = 1;
        rating.setPreferredSize(new Dimension(300, 30));
        panel3.add(rating, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel amenitiesLabel = new JLabel("Amenities:");
        amenitiesLabel.setForeground(Color.WHITE);
        panel3.add(amenitiesLabel, gbc);
        gbc.gridx = 1;
        amenities.setPreferredSize(new Dimension(300, 30));
        panel3.add(amenities, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setForeground(Color.WHITE);
        panel3.add(phoneNumberLabel, gbc);
        gbc.gridx = 1;
        phoneNumber.setPreferredSize(new Dimension(300, 30));
        panel3.add(phoneNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel addressIdLabel = new JLabel("Address ID:");
        addressIdLabel.setForeground(Color.WHITE);
        panel3.add(addressIdLabel, gbc);
        gbc.gridx = 1;
        addressId.setPreferredSize(new Dimension(300, 30));
        panel3.add(addressId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel reviewLabel = new JLabel("review ");
        reviewLabel.setForeground(Color.WHITE);
        panel3.add(reviewLabel, gbc);
        gbc.gridx = 1;
        review.setPreferredSize(new Dimension(300, 30));
        panel3.add(review, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        registerButton.setPreferredSize(new Dimension(100, 40));
        panel3.add(registerButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        resetButton.setPreferredSize(new Dimension(100, 40));
        panel3.add(resetButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        errorLabel.setForeground(Color.RED);
        panel3.add(errorLabel, gbc);

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