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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PrePackage extends JFrame {
    public PrePackage() throws IOException {
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
        panel5.setBackground(Color.blue);

        // panel4.setOpaque(false);

        panel1.setPreferredSize(new Dimension(1000, 80));// Create a div
        panel2.setPreferredSize(new Dimension(250, 500));// Create a div
        panel4.setPreferredSize(new Dimension(200, 100));// Create a div
        panel5.setPreferredSize(new Dimension(1000, 30));// Create a div

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

        // Create and add labels above text fields
        JLabel label1 = new JLabel("Your City");
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label1.setForeground(Color.white);
        subPanel22.add(label1, gbc);

        gbc.gridy = 1;
        JTextField textField1 = new JTextField("Your City");
        textField1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textField1.setPreferredSize(new Dimension(200, 40));
        subPanel22.add(textField1, gbc);

        gbc.gridy = 2;
        JLabel label2 = new JLabel("Number of Travel Days");
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label2.setForeground(Color.WHITE);
        subPanel22.add(label2, gbc);

        gbc.gridy = 3;
        JTextField textField2 = new JTextField("Number of Travel Days");
        textField2.setForeground(Color.BLACK);
        textField2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        textField2.setPreferredSize(new Dimension(200, 40));
        subPanel22.add(textField2, gbc);

        panel2.add(subPanel22, BorderLayout.CENTER);

        // subPanel32 Layout
        JButton SubmitPreferenceButton = new JButton("<html><center>Submit<br>Preference</center></html>");
        SubmitPreferenceButton.setPreferredSize(new Dimension(100, 50));
        subPanel32.add(SubmitPreferenceButton);
        panel2.add(subPanel32, BorderLayout.SOUTH);

        JButton ShowMoreButton = new JButton("Show More");
        ShowMoreButton.setPreferredSize(new Dimension(100, 50));
        subPanel32.add(ShowMoreButton);
        panel2.add(subPanel32, BorderLayout.SOUTH);
        frame.add(panel2, BorderLayout.WEST);

        // Panel 4 Layout
        JPanel subPanel14 = new JPanel();
        JPanel subPanel24 = new JPanel();
        JPanel subPanel34 = new JPanel();
        JPanel subPanel44 = new JPanel();

        // subPanel14.setBackground(Color.RED);
        // subPanel24.setBackground(Color.WHITE);
        // subPanel34.setBackground(Color.BLACK);
        // subPanel44.setBackground(Color.MAGENTA);

        subPanel14.setOpaque(false);
        subPanel24.setOpaque(false);
        subPanel34.setOpaque(false);
        subPanel44.setOpaque(false);

        panel4.setLayout(new GridLayout(4, 1, 10, 20));

        //Layout for subPane14
        subPanel14.setLayout(new GridLayout(1, 4));
        addSubPanel(subPanel14, Color.LIGHT_GRAY, "Number of Days", true);
        addSubPanel(subPanel14, Color.GREEN, "Travel Name", false);
        addSubPanel(subPanel14, Color.YELLOW, "Price", false);
        panel4.add(subPanel14, BorderLayout.CENTER);

        panel4.add(subPanel14);

        //Layout for subPane24
        //Layout for subPane14
        subPanel24.setLayout(new GridLayout(1, 4));
        addSubPanel(subPanel24, Color.RED, "Number of Days", true);
        addSubPanel(subPanel24, Color.LIGHT_GRAY, "Travel Name", false);
        addSubPanel(subPanel24, Color.YELLOW, "Price", false);
        panel4.add(subPanel24, BorderLayout.CENTER);
        panel4.add(subPanel24);

        //Layout for subPane34
        subPanel34.setLayout(new GridLayout(1, 4));
        addSubPanel(subPanel34, Color.LIGHT_GRAY, "Number of Days", true);
        addSubPanel(subPanel34, Color.GREEN, "Travel Name", false);
        addSubPanel(subPanel34, Color.YELLOW, "Price", false);
        panel4.add(subPanel34, BorderLayout.CENTER);
        panel4.add(subPanel34);

        //Layout for subPane44
        subPanel44.setLayout(new GridLayout(1, 4));
        addSubPanel(subPanel44, Color.RED, "Number of Days", true);
        addSubPanel(subPanel44, Color.GREEN, "Travel Name", false);
        addSubPanel(subPanel44, Color.LIGHT_GRAY, "Price", false);
        panel4.add(subPanel44, BorderLayout.CENTER);
        panel4.add(subPanel44);
        frame.add(panel4, BorderLayout.CENTER);

        // Panel 5 Layout
        // panel5.setLayout(new BorderLayout());
        // JLabel downTextLabel = new JLabel("Your text here");
        // downTextLabel.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        // downTextLabel.setForeground(Color.WHITE);
        // panel5.add(downTextLabel, BorderLayout.CENTER);

        frame.add(panel5, BorderLayout.SOUTH);
    }

    private void addSubPanel(JPanel parent, Color color, String labelText, boolean addCheckbox) {
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
    
        if (addCheckbox) {
            JPanel checkBoxPanel = new JPanel();
            checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.X_AXIS)); // Use BoxLayout for centering
            checkBoxPanel.setBackground(Color.BLACK);
            checkBoxPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // Add padding
    
            JCheckBox checkBox = new JCheckBox();    
            checkBoxPanel.add(Box.createHorizontalGlue());
            checkBoxPanel.add(checkBox);
            checkBoxPanel.add(Box.createHorizontalGlue());
    
            subPanel.add(checkBoxPanel, BorderLayout.WEST);
        }
    
        subPanel.add(labelPanel, BorderLayout.CENTER);
        parent.add(subPanel);
    }
}