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
    private JTextField sexField = new JTextField("Male/ Female/ Others");
    private JTextField dobField = new JTextField("YYYY-MM-DD");
    private JTextField cityField = new JTextField("City you live in");
    private JTextField email = new JTextField("Email Address");
    private JTextField CabType = new JTextField("Car Type");
    private boolean firstNameFirstFocus = true;
    private boolean lastNameFirstFocus = true;
    private boolean mobileFieldFirstFocus = true;
    private boolean sexFieldFirstFocus = true;
    private boolean dobFieldFirstFocus = true;
    private boolean cityFieldFirstFocus = true;
    private boolean emailFirstFocus = true;
    private boolean CabTypeFirstFocus = true;
    private JLabel nameError = new JLabel();
    private JLabel usernameError = new JLabel();
    public String finalfirstNameString;
    public String finallastNameString;
    public String finalmobileFieldString;
    public String finalsexString;
    public String finaldobFString;
    public String finalcityString;
    public String finalemailString;
    public String finalCabTypeString;
    private JButton signUpButton = new JButton("Add Driver");
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

        panel1.setPreferredSize(new Dimension(1000, 80));// Create a div
        panel3.setPreferredSize(new Dimension(210, 100));// Create a div
        panel5.setPreferredSize(new Dimension(1000, 20));// Create a div

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

        sexField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (sexFieldFirstFocus) {
                    sexField.setText("");
                    sexFieldFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (sexField.getText().isEmpty()) {
                    sexField.setText("City");
                    sexField.setForeground(Color.gray);
                    sexFieldFirstFocus = true;
                }
            }
        });

        dobField.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (dobField.getText().isEmpty()) {
                    dobField.setText("YYYY-MM-DD");
                    dobField.setForeground(Color.gray);
                    nameError.setText("");
                    dobFieldFirstFocus = true;
                } else {
                    // Validate the date format
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dateFormat.setLenient(false);
                        dateFormat.parse(dobField.getText());
                        finaldobFString = dobField.getText();
                        nameError.setText("");
                    } catch (ParseException ex) {
                        nameError.setForeground(Color.RED);
                        nameError.setText("Invalid date format");
                        JOptionPane.showMessageDialog(AdminLoginCab.this, "Please enter the date in the format YYYY-MM-DD",
                                "Invalid Date", JOptionPane.WARNING_MESSAGE);
                        dobField.setText("yyyy-MM-dd");
                    }
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (dobFieldFirstFocus) {
                    dobField.setText("");
                    dobFieldFirstFocus = false;
                }

                if (dobField.getText().equals("(YYYY-MM-DD)")) {
                    dobField.setText("");
                    dobField.setForeground(Color.black);
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
                    CabType.setText("City you live in");
                    CabType.setForeground(Color.gray);
                    CabTypeFirstFocus = true;
                }
            }
        });

        email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailFirstFocus) {
                    email.setText("");
                    emailFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (email.getText().isEmpty()) {
                    email.setText("Email Address");
                    email.setForeground(Color.gray);
                    emailFirstFocus = true;
                }
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstNameText = firstName.getText().trim();
                String lastNameText = lastName.getText().trim();
                String mobileText = mobileField.getText().trim();
                String sexText = sexField.getText().trim();
                String dobText = dobField.getText().trim();
                String CabTypeText = CabType.getText().trim();
                String emailText = email.getText().trim();
               
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

                if (mobileText.isEmpty() || mobileText.equals("Enter your Mobile Number")) {
                    nameError.setForeground(Color.RED);
                    nameError.setText("Please enter your mobile number");
                    isValid = false;
                }

                if (!sexText.equalsIgnoreCase("Male") && !sexText.equalsIgnoreCase("Female")
                        && !sexText.equalsIgnoreCase("Others")) {
                    nameError.setForeground(Color.RED);
                    nameError.setText("Invalid Sex");
                    isValid = false;
                }

                if (dobText.isEmpty() || dobText.equals("Enter your Date of Birth (YYYY-MM-DD)")) {
                    nameError.setForeground(Color.RED);
                    nameError.setText("Please enter your date of birth");
                    isValid = false;
                } else {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dateFormat.setLenient(false);
                        dateFormat.parse(dobText);
                    } catch (ParseException ex) {
                        nameError.setForeground(Color.RED);
                        nameError.setText("Invalid date format");
                        isValid = false;
                    }
                }

                if (CabTypeText.isEmpty() || CabTypeText.equals("Enter your City")) {
                    nameError.setForeground(Color.RED);
                    nameError.setText("Please enter your city");
                    isValid = false;
                }

                if (!isValidEmail(emailText)) {
                    usernameError.setForeground(Color.RED);
                    usernameError.setText("Invalid email");
                    isValid = false;
                }

                if (isValid) {
                    JOptionPane.showMessageDialog(AdminLoginCab.this, "Correct",
                            "Successful", JOptionPane.WARNING_MESSAGE);
                    // try {

                    //     System.out.println("Correct info");
                    //     // pstmt.setString(1, finalfirstNameString);
                    //     // pstmt.setString(2, finallastNameString);
                    //     // pstmt.setString(3, finalsexString);
                    //     // pstmt.setString(4, finaldobFString);
                    //     // pstmt.setString(5, finalcityString);
                    //     // pstmt.setString(6, finalpasswordString);
                    //     // pstmt.setString(7, finalemailString);
                    //     // pstmt.setString(8, finalmobileFieldString);

                    //     // Execute the prepared statement
                    //     // int rowsAffected = pstmt.executeUpdate();
                    //     // if (rowsAffected > 0) {
                    //     //     JOptionPane.showMessageDialog(SignUpForm.this, "Successfully Registered!!!", "Success",
                    //     //             JOptionPane.INFORMATION_MESSAGE);
                    //     //     Welcome welcomeFrame = new Welcome();
                    //     //     welcomeFrame.setVisible(true);
                    //     //     dispose(); // Close the current SignUpForm
                    //     // } else {
                    //     //     JOptionPane.showMessageDialog(SignUpForm.this, "Failed to register. Please try again.",
                    //     //             "Error", JOptionPane.ERROR_MESSAGE);
                    //     // }
                    // } catch ( ex) {
                    //     ex.printStackTrace();
                    //     JOptionPane.showMessageDialog(AdminLoginCab.this, "Error: " + ex.getMessage(), "Database Error",
                    //             JOptionPane.ERROR_MESSAGE);
                    // }
                } else {
                    JOptionPane.showMessageDialog(AdminLoginCab.this, "Please check and correct the invalid fields",
                            "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            }
            private boolean isValidEmail(String email) {
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                return email.matches(emailRegex);
            }
        }
    );

        resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    firstNameFirstFocus = true;
                    lastNameFirstFocus = true;
                    mobileFieldFirstFocus = true;
                    sexFieldFirstFocus = true;
                    dobFieldFirstFocus = true;
                    cityFieldFirstFocus = true;
                    emailFirstFocus = true;
                    nameError.setText("");
                    usernameError.setText("");
                    firstName.setText("Enter your First Name");
                    lastName.setText("Enter your Last Name");
                    mobileField.setText("Mobile Number");
                    dobField.setText("YYYY-MM-DD");
                    sexField.setText("Male/ Female/ Others");
                    CabType.setText("City you live in");
                    email.setText("Email Address");
    
                    System.out.println("First Name: " + finalfirstNameString);
                    System.out.println("Last Name: " + finallastNameString);
                    System.out.println("Mobile: " + finalmobileFieldString);
                    System.out.println("Sex: " + finalsexString);
                    System.out.println("Date of Birth: " + finaldobFString);
                    System.out.println("Cab Type: " + finalcityString);
                    System.out.println("Email: " + finalemailString);
                }
            }
            );


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

        // Add the "Sign Up For Free" text
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
        JLabel firstNameLabel = new JLabel("");
        firstNameLabel.setForeground(Color.WHITE);
        panel3.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        firstName.setPreferredSize(new Dimension(300, 30));
        panel3.add(firstName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lastNameLabel = new JLabel("");
        lastNameLabel.setForeground(Color.WHITE);
        panel3.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        lastName.setPreferredSize(new Dimension(300, 30));
        panel3.add(lastName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel mobileLabel = new JLabel("");
        mobileLabel.setForeground(Color.WHITE);
        panel3.add(mobileLabel, gbc);
        gbc.gridx = 1;
        mobileField.setPreferredSize(new Dimension(300, 30));
        panel3.add(mobileField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel sexLabel = new JLabel("");
        sexLabel.setForeground(Color.WHITE);
        panel3.add(sexLabel, gbc);
        gbc.gridx = 1;
        sexField.setPreferredSize(new Dimension(300, 30));
        panel3.add(sexField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel dobLabel = new JLabel("");
        dobLabel.setForeground(Color.WHITE);
        panel3.add(dobLabel, gbc);
        gbc.gridx = 1;
        dobField.setPreferredSize(new Dimension(300, 30));
        panel3.add(dobField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel CabTypeLabel = new JLabel();
        CabTypeLabel.setForeground(Color.WHITE);
        panel3.add(CabTypeLabel,gbc);
        gbc.gridx=1;
        CabType.setPreferredSize(new Dimension(300, 30));
        panel3.add(CabType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel emailLabel = new JLabel("");
        emailLabel.setForeground(Color.WHITE);
        panel3.add(emailLabel, gbc);
        gbc.gridx = 1;
        email.setPreferredSize(new Dimension(300, 30));
        panel3.add(email, gbc);
        frame.add(panel3, BorderLayout.CENTER);

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













        panel5.setBackground(Color.blue);
        panel5.setPreferredSize(new Dimension(500, 30));
        JLabel label = new JLabel();
        label.setForeground(Color.green);
        label.setText("Made with love by 10 marks");
        panel5.add(label);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel3, BorderLayout.CENTER);
        frame.add(panel5, BorderLayout.SOUTH);
    }
}