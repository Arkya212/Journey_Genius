import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FinalPage extends JFrame {
    private JPanel buttonPanel;
    private JPanel contentPanel;
    private static final int numberOfButtons = 5;
    private static String stringItineraryFinal = "<html>Delhi is the worst City<br>and<br> Approx and wanna be Delhi people <br>are stinky just like south people</html>";

    public FinalPage() throws IOException {
        JFrame frame = new JFrame("Final Itinerary Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		frame.setLayout(new BorderLayout(5, 3));
		frame.setVisible(true);


        // Panel 1 Layout
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel1.setBackground(Color.red);
		panel2.setBackground(Color.black);
        panel3.setBackground(Color.black);
        panel4.setBackground(Color.black);

        panel1.setPreferredSize(new Dimension(1000, 100));
		panel2.setPreferredSize(new Dimension(150, 100));
		panel3.setPreferredSize(new Dimension(500, 100));
        panel4.setPreferredSize(new Dimension(1000, 30));

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
        JLabel titleLabel = new JLabel("Your Itineraries");
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
        
        //Layout for Panel 2
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(Color.black);

        panel2.setLayout(new GridLayout(numberOfButtons, 1));
        ButtonGroup buttonGroup = new ButtonGroup();

        for (int i = 1; i <= numberOfButtons; i++){
            JButton button=createNumberButton(i);
            button.setFont(new Font("TimesNewRoman", Font.PLAIN, 18));
            buttonGroup.add(button);
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(button);
            buttonPanel.setBackground(Color.BLACK);
            panel2.add(buttonPanel);
        }

        frame.add(panel2, BorderLayout.WEST);

        //Layout for Panel 3
        panel3.setLayout(new BorderLayout());
        JLabel stringFinalItninerary = new JLabel(stringItineraryFinal);
        stringFinalItninerary.setFont(new Font("Monospaced", Font.PLAIN, 20));
        stringFinalItninerary.setHorizontalAlignment(SwingConstants.CENTER);
        stringFinalItninerary.setForeground(Color.WHITE);
        panel3.add(stringFinalItninerary, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.CENTER);

        // Layout for Panel 4
		panel4.setLayout(new BorderLayout());
		JLabel label = new JLabel("Made with Love at BITS Pilani");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
		panel4.add(label, BorderLayout.CENTER);
		frame.add(panel4, BorderLayout.SOUTH);       
    }

    public static JButton createNumberButton(int number) {
        JButton button = new JButton("Day " + number);
        return button;
    }
    private void updateContentPanel(int number) {
        contentPanel.removeAll();
        JLabel label=new JLabel();
        label.setText("getName()");
        contentPanel.add(label);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

}