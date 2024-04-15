import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;

public class Welcome extends JFrame{
    public Welcome() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setTitle("Welcome Page");

        // Set background image to the JFrame
        ImageIcon backgroundImage = new ImageIcon("images/bg4.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        
        frame.setContentPane(backgroundLabel);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        // Set panel backgrounds to transparent
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);

        panel1.setBackground(Color.red);
        panel2.setBackground(Color.magenta);
        panel3.setBackground(Color.yellow);
        panel4.setBackground(Color.blue);

        // Set preferred width to fill the entire width
        panel1.setPreferredSize(new Dimension(1000, 100)); 
        panel2.setPreferredSize(new Dimension(1000, 500)); 
        panel3.setPreferredSize(new Dimension(1000, 60));
        panel4.setPreferredSize(new Dimension(1000, 100)); 

        // Create a vertical Box layout to hold the panels
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(panel1);

        // Add logo to Panel 2
        // Image for Logo
        ImageIcon logoimage = new ImageIcon("images//logo.png");
        frame.setIconImage(logoimage.getImage());

        //Setting the Front
        ImageIcon image = new ImageIcon("images//logo2.png");
        frame.setIconImage(image.getImage());
        // Resizing the image to fit within the label
        Image img = image.getImage();
        int logoWidth = 300; // Adjust the width as desired
        int logoHeight = 300; // Adjust the height as desired
        Image resizedImg = img.getScaledInstance(logoWidth, logoHeight, Image.SCALE_FAST);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);

        JLabel logoLabel = new JLabel(resizedIcon);
        panel2.setLayout(new BorderLayout()); // Set layout for panel2
        panel2.add(logoLabel, BorderLayout.CENTER);

        verticalBox.add(panel2);

        JButton loginAsUserButton = new JButton("Login As User");
        JButton loginAsAdminButton = new JButton("Login As Admin");
        JButton signUpButton = new JButton("Sign Up");

        loginAsAdminButton.setBackground(Color.DARK_GRAY);
        loginAsAdminButton.setForeground(Color.WHITE);
        loginAsAdminButton.setPreferredSize(new Dimension(150, 40)); 
        loginAsUserButton.setBackground(Color.DARK_GRAY);
        loginAsUserButton.setForeground(Color.WHITE);
        loginAsUserButton.setPreferredSize(new Dimension(150, 40)); 
        signUpButton.setBackground(Color.DARK_GRAY);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setPreferredSize(new Dimension(150, 40));

        loginAsAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try{
                    AdminPage adminPage = new AdminPage();
                    frame.dispose();
                } catch(IOException ex){
                    ex.printStackTrace();
                } 
                // catch (SQLException e1) {
                //     e1.printStackTrace();
                // }
            }
        });

        loginAsUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try{
                    Login loginFrame = new Login();
                    frame.dispose();
                } catch(IOException ex){
                    ex.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try{
                    SignUpForm signUpFrame = new SignUpForm();
                    frame.dispose();
                } catch(IOException ex){
                    ex.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        panel3.add(loginAsAdminButton);
        panel3.add(Box.createHorizontalStrut(100));
        panel3.add(signUpButton);        
        panel3.add(Box.createHorizontalStrut(100));
        panel3.add(loginAsUserButton);

        verticalBox.add(panel3);

        // Sub-panel 1 (North)
        JPanel subPanel1 = new JPanel();
        subPanel1.setBackground(Color.RED); // Set background color to red
        subPanel1.setPreferredSize(new Dimension(200, 100)); // Set preferred size
        
        // JLabel googJLabel = new JLabel("GoogleImage");
        // googJLabel.setForeground(Color.WHITE);
        // Create image label
        ImageIcon googleIcon = new ImageIcon("images/google.png"); // Path to Google icon image
        JLabel googJLabel = new JLabel(googleIcon);
        googJLabel.setForeground(Color.WHITE);
        googJLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.google.com"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        subPanel1.setLayout(new BorderLayout());
        subPanel1.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 50));//Trick
        subPanel1.add(googJLabel, BorderLayout.SOUTH);

        panel4.add(subPanel1, BorderLayout.WEST);

        // Sub-panel 2 (Center)
        JPanel subPanel2 = new JPanel();
        subPanel2.setBackground(Color.GREEN); // Set background color to green
        subPanel2.setPreferredSize(new Dimension(600, 100)); // Set preferred size
        
        JLabel textLabel = new JLabel("Created with Love at BITS Pilani - Pilani Campus");
        textLabel.setForeground(Color.WHITE);
        
        subPanel2.setLayout(new BorderLayout());
        subPanel2.setBorder(BorderFactory.createEmptyBorder(0, 130, 20, 0));//Trick
        subPanel2.add(textLabel, BorderLayout.SOUTH);

        panel4.add(subPanel2, BorderLayout.CENTER);

        // Sub-panel 3 (South)
        JPanel subPanel3 = new JPanel();
        subPanel3.setBackground(Color.RED); // Set background color to blue
        subPanel3.setPreferredSize(new Dimension(170, 100)); // Set preferred size
        // JLabel gitLabel = new JLabel("GitImage");
        // gitLabel.setForeground(Color.WHITE);

        ImageIcon gitIcon = new ImageIcon("images/github.png"); // Path to Google icon image
        JLabel gitLabel = new JLabel(gitIcon);
        gitLabel.setForeground(Color.WHITE);
        gitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.google.com"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
              
        subPanel3.setLayout(new BorderLayout());
        subPanel3.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 50));//Trick
        subPanel3.add(gitLabel, BorderLayout.SOUTH);

        panel4.add(subPanel3, BorderLayout.EAST);

        subPanel1.setOpaque(false);
        subPanel2.setOpaque(false);
        subPanel3.setOpaque(false);

        verticalBox.add(panel4);
        frame.add(verticalBox); // Add the verticalBox to the frame
        frame.setVisible(true);
    }
}


