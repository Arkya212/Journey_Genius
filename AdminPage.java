import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

public class AdminPage extends JFrame {
    // private JButton logincab;
    // private JButton loginhotel;

    public AdminPage() throws IOException {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
        frame.setLayout(new BorderLayout(10, 10));
        frame.setVisible(true);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.red);
        panel1.setPreferredSize(new Dimension(500, 50));

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
        int logoWidth = 50;
        int logoHeight = 50;
        Image resizedImg = img.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);

        JLabel imageLabel = new JLabel(resizedIcon); // Use resizedIcon here
        subPanel11.add(imageLabel);

        panel1.add(subPanel11, BorderLayout.WEST);

        // Sub Panel 2 Layout
        JLabel titleLabel = new JLabel("Admin Login");
        titleLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        subPanel21.add(titleLabel);

        panel1.add(subPanel21, BorderLayout.CENTER);

        // SubPanel 3 Layout
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(70, 50));
        backButton.setBackground(Color.GRAY);
        backButton.setFont(new Font("Arial", Font.BOLD, 10));
        backButton.setForeground(Color.white);
        subPanel31.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Welcome welcome = new Welcome();
                frame.dispose();
            }
        });

        panel1.add(subPanel31, BorderLayout.EAST);
        frame.add(panel1, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH;

        JButton logincab = new JButton("login for cab");
        logincab.setPreferredSize(new Dimension(100, 50));
        logincab.setBorder(BorderFactory.createBevelBorder(1, Color.white, Color.black));
        logincab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("cab");
                    AdminLoginCab cab = new AdminLoginCab(); // Ensure that AdminLoginCab()
                    frame.dispose();
                    // /structor is handled for
                    // any exceptions it might throw
                } catch (Exception ex) {
                    ex.printStackTrace(); // Handle the exception appropriately, this is just an
                }
            }
        });
        panel.add(logincab, gbc);

        gbc.gridy++;

        JLabel blank = new JLabel();
        blank.setPreferredSize(new Dimension(40, 40));
        panel.add(blank, gbc);

        gbc.gridy++;
        JButton loginhotel = new JButton("Login for hotel");
        loginhotel.setBorder(BorderFactory.createBevelBorder(0, Color.GRAY, Color.black));
        loginhotel.setPreferredSize(new Dimension(100, 50));
        loginhotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Route the button click to the Admin.java clas
                try {
                    HotelRegistration hotel = new HotelRegistration();
                    frame.dispose();
                } catch (IOException ez) {
                    ez.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        panel.add(loginhotel, gbc);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.blue);
        panel3.setPreferredSize(new Dimension(500, 30));
        JLabel label = new JLabel();
        label.setForeground(Color.green);
        label.setText("Welcome to Journey Genius");
        panel3.add(label);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.SOUTH);

    }
}