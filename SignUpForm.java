import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SignUpForm extends JFrame {
    private JTextField firstName = new JTextField("Enter your First Name");
    private JTextField lastName = new JTextField("Enter your Last Name");
    private JTextField mobileField = new JTextField("Mobile Number");
    private JTextField sexField = new JTextField("Male/ Female/ Others");
    private JTextField dobField = new JTextField("YYYY-MM-DD");
    private JTextField cityField = new JTextField("City you live in");
    private JTextField email = new JTextField("Email Address");
    private JPasswordField password = new JPasswordField("Set Password");
    private JButton signUpButton = new JButton("Sign In");
    private JButton resetButton = new JButton("Reset");
    private JLabel nameError = new JLabel();
    private JLabel usernameError = new JLabel();
    private JLabel passwordError = new JLabel();
    private boolean firstNameFirstFocus = true;
    private boolean lastNameFirstFocus = true;
    private boolean mobileFieldFirstFocus = true;
    private boolean sexFieldFirstFocus = true;
    private boolean dobFieldFirstFocus = true;
    private boolean cityFieldFirstFocus = true;
    private boolean emailFirstFocus = true;
    private boolean passwordFirstFocus = true;
    public String finalfirstNameString;
    public String finallastNameString;
    public String finalmobileFieldString;
    public String finalsexString;
    public String finaldobFString;
    public String finalcityString;
    public String finalemailString;
    public String finalpasswordString;

    public SignUpForm() throws IOException, SQLException {
        // Set the frame properties

        Connection con = ConnectionProvider.getConnection();
        String q = "INSERT INTO User (First_Name, Last_Name, Sex, DOB, City, Password, Email_ID, Mobile_Number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(q);

        setTitle("Sign Up For Free");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        // Event Listeners

        // The Text Disappears on Clicking for all the TextBoxes
        // focus listner is when you put cursor on text box
        // focus listener verified

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
                    mobileField.setText("Mobile Number");
                    mobileField.setForeground(Color.gray);
                    mobileFieldFirstFocus = true;
                }

                if (!isValidMobileNumber(mobileNumber)) {
                    JOptionPane.showMessageDialog(null, "Invalid Mobile Number", "Error", JOptionPane.ERROR_MESSAGE);
                    mobileField.setText("Mobile Number");
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
                    sexField.setText("Male/ Female/ Others");
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
                        JOptionPane.showMessageDialog(SignUpForm.this, "Please enter the date in the format YYYY-MM-DD",
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
                    cityField.setText("City you live in");
                    cityField.setForeground(Color.gray);
                    cityFieldFirstFocus = true;
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

        password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordFirstFocus) {
                    password.setText("");
                    password.setEchoChar('•'); // Set echo character to bullet
                    passwordFirstFocus = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                char[] passwordChars = password.getPassword();
                if (passwordChars.length == 0) {
                    password.setText("Set password");
                    password.setForeground(Color.black);
                    password.setEchoChar((char) 0); // Show the text as it is
                }
            }
        });

        // get document : takes text //
        // complete it
        // gives variable's data

        sexField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateSex();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateSex();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateSex();
            }

            private void validateSex() {
                String sexText = sexField.getText().trim();
                if (!sexText.isEmpty() && !sexText.equals("Male/ Female/ Others")) {
                    if (sexText.equalsIgnoreCase("Male") || sexText.equalsIgnoreCase("Female")
                            || sexText.equalsIgnoreCase("Others")) {
                        finalsexString = sexText;
                        nameError.setText("");
                    } else {
                        nameError.setForeground(Color.RED);
                        nameError.setText("Invalid Sex");
                    }
                } else {
                    nameError.setText("");
                }
            }
        });

        email.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateEmail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateEmail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateEmail();
            }

            private void validateEmail() {
                String emailText = email.getText().trim();
                if (emailText.length() > 0 && !emailText.equals("Email Address")) {
                    if (isValidEmail(emailText)) {
                        usernameError.setForeground(new Color(50, 168, 58));
                        usernameError.setText("Email is valid");
                        finalemailString = emailText;
                    } else {
                        usernameError.setForeground(Color.RED);
                        usernameError.setText("Email is not valid");
                    }
                }
            }

            private boolean isValidEmail(String email) {
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                return email.matches(emailRegex);
            }
        });

        password.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validatePasswordForBox(String.valueOf(password.getPassword()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validatePasswordForBox(String.valueOf(password.getPassword()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validatePasswordForBox(String.valueOf(password.getPassword()));
            }

            private void validatePasswordForBox(String passwordText) {
                passwordText = passwordText.trim();
                if (passwordText.length() > 0 && !passwordText.equals("Set password")) {
                    if (passwordText.length() < 8) {
                        passwordError.setForeground(Color.RED);
                        passwordError.setText("Password must be at least 8 characters long");
                    } else if (!passwordText.matches(".*[a-zA-Z]+.*")) {
                        passwordError.setForeground(Color.RED);
                        passwordError.setText("Password must contain at least one letter");
                    } else if (!passwordText.matches(".*\\d+.*")) {
                        passwordError.setForeground(Color.RED);
                        passwordError.setText("Password must contain at least one digit");
                    } else {
                        passwordError.setForeground(new Color(50, 168, 58));
                        passwordError.setText("Valid Password");
                        finalpasswordString = passwordText;
                    }
                }
            }
        });

        // SignUp Button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstNameText = firstName.getText().trim();
                String lastNameText = lastName.getText().trim();
                String mobileText = mobileField.getText().trim();
                String sexText = sexField.getText().trim();
                String dobText = dobField.getText().trim();
                String cityText = cityField.getText().trim();
                String emailText = email.getText().trim();
                String passwordText = String.valueOf(password.getPassword()).trim();

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

                if (cityText.isEmpty() || cityText.equals("Enter your City")) {
                    nameError.setForeground(Color.RED);
                    nameError.setText("Please enter your city");
                    isValid = false;
                }

                if (!isValidEmail(emailText)) {
                    usernameError.setForeground(Color.RED);
                    usernameError.setText("Invalid email");
                    isValid = false;
                }

                if (!validatePassword(passwordText)) {
                    passwordError.setForeground(Color.RED);
                    passwordError.setText("Invalid password");
                    isValid = false;
                }

                if (isValid) {
                    try {
                        pstmt.setString(1, finalfirstNameString);
                        pstmt.setString(2, finallastNameString);
                        pstmt.setString(3, finalsexString);
                        pstmt.setString(4, finaldobFString);
                        pstmt.setString(5, finalcityString);
                        pstmt.setString(6, finalpasswordString);
                        pstmt.setString(7, finalemailString);
                        pstmt.setString(8, finalmobileFieldString);

                        // Execute the prepared statement
                        int rowsAffected = pstmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(SignUpForm.this, "Successfully Registered!!!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            @SuppressWarnings("unused")
                            Welcome welcomeFrame = new Welcome();
                            dispose(); // Close the current SignUpForm
                        } else {
                            JOptionPane.showMessageDialog(SignUpForm.this, "Failed to register. Please try again.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(SignUpForm.this, "Error: " + ex.getMessage(), "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(SignUpForm.this, "Please check and correct the invalid fields",
                            "Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            }

            private boolean isValidEmail(String email) {
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                return email.matches(emailRegex);
            }

            private boolean validatePassword(String passwordText) {
                if (passwordText.length() < 8) {
                    return false;
                } else if (!passwordText.matches(".*[a-zA-Z]+.*")) {
                    return false;
                } else if (!passwordText.matches(".*\\d+.*")) {
                    return false;
                } else {
                    
                    return true;
                }
            }

        });

        resetButton.addActionListener((ActionEvent e) -> {
            firstNameFirstFocus = true;
            lastNameFirstFocus = true;
            mobileFieldFirstFocus = true;
            sexFieldFirstFocus = true;
            dobFieldFirstFocus = true;
            cityFieldFirstFocus = true;
            emailFirstFocus = true;
            passwordFirstFocus = true;
            nameError.setText("");
            passwordError.setText("");
            usernameError.setText("");
            firstName.setText("Enter your First Name");
            lastName.setText("Enter your Last Name");
            mobileField.setText("Mobile Number");
            dobField.setText("YYYY-MM-DD");
            sexField.setText("Male/ Female/ Others");
            cityField.setText("City you live in");
            email.setText("Email Address");
            password.setText("Set Password");
            
            System.out.println("First Name: " + finalfirstNameString);
            System.out.println("Last Name: " + finallastNameString);
            System.out.println("Mobile: " + finalmobileFieldString);
            System.out.println("Sex: " + finalsexString);
            System.out.println("Date of Birth: " + finaldobFString);
            System.out.println("City: " + finalcityString);
            System.out.println("Email: " + finalemailString);
            System.out.println("Password: " + finalpasswordString);
        });

        // Create the panel for the form
        JPanel panel = new JPanel() {
            BufferedImage image;
            {
                try {
                    image = ImageIO.read(getClass().getResource("/images/signupbg.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Add the "Sign Up For Free" text
        JLabel signUpLabel = new JLabel("Sign Up For Free");
        signUpLabel.setForeground(Color.WHITE);
        signUpLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(signUpLabel, gbc);

        // Add the form elements
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setForeground(Color.WHITE);
        panel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        firstName.setPreferredSize(new Dimension(300, 30));
        panel.add(firstName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setForeground(Color.WHITE);
        panel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        lastName.setPreferredSize(new Dimension(300, 30));
        panel.add(lastName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel mobileLabel = new JLabel("Mobile:");
        mobileLabel.setForeground(Color.WHITE);
        panel.add(mobileLabel, gbc);
        gbc.gridx = 1;
        mobileField.setPreferredSize(new Dimension(300, 30));
        panel.add(mobileField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel sexLabel = new JLabel("Sex:");
        sexLabel.setForeground(Color.WHITE);
        panel.add(sexLabel, gbc);
        gbc.gridx = 1;
        sexField.setPreferredSize(new Dimension(300, 30));
        panel.add(sexField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setForeground(Color.WHITE);
        panel.add(dobLabel, gbc);
        gbc.gridx = 1;
        dobField.setPreferredSize(new Dimension(300, 30));
        panel.add(dobField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setForeground(Color.WHITE);
        panel.add(cityLabel, gbc);
        gbc.gridx = 1;
        cityField.setPreferredSize(new Dimension(300, 30));
        panel.add(cityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        email.setPreferredSize(new Dimension(300, 30));
        panel.add(email, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        password.setPreferredSize(new Dimension(300, 30));
        panel.add(password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        signUpButton.setPreferredSize(new Dimension(150, 40));
        panel.add(signUpButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        resetButton.setPreferredSize(new Dimension(150, 40));
        panel.add(resetButton, gbc);

        // Add the error labels with white text
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        nameError.setForeground(Color.WHITE);
        panel.add(nameError, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        usernameError.setForeground(Color.WHITE);
        panel.add(usernameError, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        passwordError.setForeground(Color.WHITE);
        panel.add(passwordError, gbc);

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);
    }
}
