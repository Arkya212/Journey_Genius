import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Login extends JFrame {
    // private static Welcome welcome;
    private JFrame frame;
    private JButton loginButton;
    private JButton resetButton;
    private JTextField email;
    private JPasswordField password;
    private JLabel usernameError;
    private JLabel passwordError;
    public String finalemailString;
    public String finalpassString;

    public Login() throws IOException {
        frame = new JFrame("Login Form");
        email = new JTextField("Enter Email");
        password = new JPasswordField("Enter Password");
        loginButton = new JButton("LOGIN");
        usernameError = new JLabel();
        passwordError = new JLabel();
        resetButton = new JButton("Reset");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        // Set the background image
        JPanel backgroundPanel = new JPanel() {
            BufferedImage image = ImageIO.read(getClass().getResource("/images/bg2.jpg"));

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new GridLayout(1, 2));

        //Adding Event Listeners
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
                if (emailText.length() > 0 && !emailText.equals("Enter your email")) {
                    if (validateMail(emailText)) {
                        usernameError.setForeground(new Color(50, 168, 58));
                        finalemailString = emailText;
                        usernameError.setText("Email is valid");
                    } else {
                        usernameError.setForeground(Color.RED);
                        usernameError.setText("Email is not valid");
                    }
                } else {
                    usernameError.setText("");
                }
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
        
            private boolean validatePasswordForBox(String passwordText) {
                passwordText = passwordText.trim();
                if (passwordText.length() > 0 && !passwordText.equals("Enter your password")) {
                    if (passwordText.length() < 8) {
                        passwordError.setForeground(Color.RED);
                        passwordError.setText("Password must be of length 8");
                        return false;
                    } else if (!passwordText.matches(".*[a-zA-Z]+.*")) {
                        passwordError.setForeground(Color.RED);
                        passwordError.setText("Password must contain alphabets");
                        return false;
                    } else if (!passwordText.matches(".*\\d.*")) {
                        passwordError.setForeground(Color.RED);
                        passwordError.setText("Password must contain digits");
                        return false;
                    } else {
                        passwordError.setForeground(new Color(50, 168, 58));
                        finalpassString = passwordText;
                        passwordError.setText("Valid Password");
                        return true;
                    }
                } else {
                    passwordError.setText("");
                    return false;
                }
            }
        });


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailText = email.getText().trim();
                String passwordText = String.valueOf(password.getPassword()).trim();
        
                if (!validateMail(emailText)) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid email.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (!validatePassword(passwordText)) {
                    JOptionPane.showMessageDialog(frame, "Password must be of length 8 and contain both letters and digits.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Successfully Logged In !!!\nUsername: " + emailText + "\nPassword: " + passwordText);
                    password.setText(null);
                    email.setText(null);
                    Welcome welcomeFrame = new Welcome(); // Show the existing Welcome screen
                    welcomeFrame.setVisible(true);
                    frame.dispose();
                }
            }
        
            private boolean validatePassword(String passwordText) {
                passwordText = passwordText.trim();
                if (passwordText.length() > 0 && !passwordText.equals("Enter your password")) {
                    if (passwordText.length() < 8) {
                        passwordError.setForeground(Color.RED);
                        passwordError.setText("Password must be of length 8");
                        return false;
                    } else if (!passwordText.matches(".*[a-zA-Z]+.*")) {
                        passwordError.setForeground(Color.RED);
                        passwordError.setText("Password must contain alphabets");
                        return false;
                    } else if (!passwordText.matches(".*\\d.*")) {
                        passwordError.setForeground(Color.RED);
                        passwordError.setText("Password must contain digits");
                        return false;
                    } else {
                        passwordError.setText("");
                        return true;
                    }
                } else {
                    passwordError.setText("");
                    return false;
                }
            }
        });


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                email.setText("Your Email");
                password.setText("Your Password");
                passwordError.setText("");
                usernameError.setText("");

                System.out.println(finalemailString);
                System.out.println(finalpassString);
            }
        });
        
        
        FocusListener emailFocusListener = new FocusListener() {
            public void focusLost(FocusEvent e) {
                if(email.getText().equals("")) {
                    email.setText("Enter your email");
                    email.setForeground(Color.gray);
                }
            }
        
            public void focusGained(FocusEvent e) {
                if(email.getText().equals("Enter your email")) {
                    email.setText("");
                    email.setForeground(Color.black);
                }
            }
        };
        
        email.addFocusListener(emailFocusListener);
        
        password.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                char[] passwordChars = password.getPassword();
                if (passwordChars.length == 0) {
                    password.setText("Enter your password");
                    password.setForeground(Color.gray);
                    password.setEchoChar((char) 0); // Show the text as it is
                }
            }
        
            public void focusGained(FocusEvent e) {
                char[] passwordChars = password.getPassword();
                if (Arrays.equals(passwordChars, "Enter your password".toCharArray())) {
                    password.setText("");
                    password.setEchoChar('•'); // Set echo character to bullet
                    password.setForeground(Color.black);
                }
            }
        });        

        // Add components to the first half
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));

        ImageIcon sideIcon = new ImageIcon("images/logo2.png");
        // Resizing the image to fit within the label
        Image img = sideIcon.getImage();
        int logoWidth = 300; // Adjust the width as desired
        int logoHeight = 300; // Adjust the height as desired
        Image resizedImg = img.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);

        JLabel imageLabel = new JLabel(resizedIcon); // Use resizedIcon here
        topPanel.add(imageLabel);

        // Add components to the second half
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(Color.WHITE);
    

        JLabel loginLabel = new JLabel("Login Here for FREE!");
                
        Insets textInsets = new Insets(10, 10, 5, 10);
        Insets errorInsets = new Insets(0,20,0,0);
        
        GridBagConstraints input = new GridBagConstraints();

        Font font = new Font("Times New Roman", Font.BOLD, 16);
        loginLabel.setFont(font);

        input.anchor = GridBagConstraints.CENTER;
        input.insets = textInsets;
        input.gridy = 0; // Place the label at the top
        bottomPanel.add(loginLabel, input);

        input.anchor = GridBagConstraints.CENTER;
        input.insets = textInsets;
        input.gridy = 1;
        bottomPanel.add(email,input);
        email.setPreferredSize(new Dimension(230, 35));
        
        input.gridy = 2;
        input.insets = errorInsets;
        input.anchor = GridBagConstraints.WEST;
        bottomPanel.add(usernameError,input);
        
        input.gridy = 3;
        input.insets = textInsets;
        input.anchor = GridBagConstraints.CENTER;
        bottomPanel.add(password,input);
        password.setPreferredSize(new Dimension(230, 35));
        
        input.gridy = 4;
        input.insets = errorInsets;
        input.anchor = GridBagConstraints.WEST;
        bottomPanel.add(passwordError,input);
        
        input.insets = new Insets(20, 10, 10, 10);
        input.anchor = GridBagConstraints.WEST;
        input.gridx = 0;
        input.gridy = 5;
        bottomPanel.add(loginButton,input);
        loginButton.setPreferredSize(new Dimension(90, 35));

        input.insets = new Insets(20, -100, 10, 10);
        input.anchor = GridBagConstraints.WEST;
        input.gridx = 1;
        input.gridy = 5;
        bottomPanel.add(resetButton,input);
        resetButton.setPreferredSize(new Dimension(90, 35));

        // Add panels to the background panel
        backgroundPanel.add(topPanel);
        backgroundPanel.add(bottomPanel);

        // Add the background panel to the frame
        frame.add(backgroundPanel);
        frame.setVisible(true);
    }

    // The Event Listener Functions
    private boolean validateMail(String mail) {
        String regExp = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regExp);
        return pattern.matcher(mail).matches();
    }
}