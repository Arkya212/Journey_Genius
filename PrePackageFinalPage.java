import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PrePackageFinalPage extends JFrame {
    private JPanel buttonPanel;
    private JPanel contentPanel;
    private static JLabel stringFinalItninerary;
    private static Vector<String> itineraryData = new Vector<>();
    private int priceOfTheDay=0;
    JLabel priceLabel;
    private static final int numberOfButtons = 7; // Main Variable that handles all the Items in this
    private static String stringItineraryFinal = "<html>This Page Shows your Itinerary. <br> Click on each Day to see what you selected</html>";

    // numberOfButtons, itineraryData (Update this with SQL Query for every Day),
    // buttonText.substring(4) (Take Day informaion from this)
    //Write Method to get the Price of the Day

    public PrePackageFinalPage() throws IOException {
        
        Vector<String> itineraryData = new Vector<>();
        itineraryData.add("Destination: Paris");
        itineraryData.add("Duration: 3 days");
        itineraryData.add("Hotel: ABC Hotel");
        itineraryData.add("Attractions: Eiffel Tower, Louvre Museum");

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
        JLabel titleLabel = new JLabel("Your Pre-Package Itinerary");
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(AppConfig.previousPage.equals("FinalCustomPage")){
                    try {
                        CustomPackage finalCustomPage = new CustomPackage();
                    } catch (IOException | SQLException exp){
                        // Handle the IOException here
                        exp.printStackTrace();
                    }
                }else{
                    // try {
                    //     PrePackage prePackage = new PrePackage();
                    // } catch (IOException exp){
                    //     // Handle the IOException here
                    //     exp.printStackTrace();
                    // }
                }
            }
        });
        panel1.add(subPanel31, BorderLayout.EAST);
        frame.add(panel1, BorderLayout.NORTH);

        // Layout for Panel 2
        // Create a new JPanel to hold the buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(numberOfButtons, 1, 5, 5));
        buttonsPanel.setBackground(Color.BLACK);

        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 1; i <= numberOfButtons; i++) {
            JButton dayButton = createNumberButton(i);
            dayButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            dayButton.setBackground(Color.BLACK);
            dayButton.setForeground(Color.WHITE);
            dayButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            dayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonText = button.getText();
                    System.out.println("Day Number: " + buttonText.substring(4));
                    //Write Method to get the Price of the Day
                    priceLabel.setText("Price: ₹" + priceOfTheDay);
                    updatePanel3WithRandomText();
                }
            });
            buttonGroup.add(dayButton);
            buttonsPanel.add(dayButton); // Add the button to the buttonsPanel
        }

        // Create a new JPanel for the bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.BLUE);

        // Add the label to the bottom panel
        priceLabel = new JLabel("Price: " + priceOfTheDay);
        priceLabel.setForeground(Color.WHITE);
        priceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adjust the padding as needed
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(priceLabel, BorderLayout.SOUTH);
        panel2.setLayout(new BorderLayout());
        panel2.add(buttonsPanel, BorderLayout.CENTER);
        panel2.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(panel2, BorderLayout.WEST);

        // Layout for Panel 3
        panel3.setLayout(new BorderLayout());

        stringFinalItninerary = new JLabel(stringItineraryFinal);
        stringFinalItninerary.setFont(new Font("Monospaced", Font.PLAIN, 20));
        stringFinalItninerary.setHorizontalAlignment(SwingConstants.CENTER);
        stringFinalItninerary.setForeground(Color.WHITE);
        panel3.add(stringFinalItninerary, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm Your Payment");
        confirmButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panel3.add(confirmButton, BorderLayout.SOUTH);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FinancePage financePage = new FinancePage();
                    frame.dispose();
                } catch (IOException exp) {
                    // Handle the IOException here
                    exp.printStackTrace();
                }
            }
        });

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

    public static void updatePanel3WithRandomText() {
        itineraryData.clear(); // Clear existing data
        String destination = getRandomCity();
        int price = getRandomPrice();
        int duration = new Random().nextInt(5) + 1;
        String hotel = getRandomHotel();
        String attractions = getRandomAttractions();
    
        itineraryData.add("Destination: " + destination + " - Price: \u20B9" + price); // \u20B9 is the Unicode for the Rupee symbol
        itineraryData.add("Duration: " + duration + " days");
        itineraryData.add("Hotel: " + hotel);
        itineraryData.add("Attractions: " + attractions);
    
        // Display updated itinerary data
        StringBuilder randomText = new StringBuilder("<html>");
        for (String data : itineraryData) {
            randomText.append(data).append("<br>");
        }
        randomText.append("</html>");
        stringFinalItninerary.setText(randomText.toString());
    }
    

    public static String getRandomCity() {
        String[] cities = { "Paris", "London", "New York", "Tokyo", "Rome", "Sydney" };
        return cities[new Random().nextInt(cities.length)];
    }

    public static String getRandomHotel() {
        String[] hotels = { "ABC Hotel", "XYZ Resort", "Sunrise Inn", "Grand Plaza", "Ocean View Hotel" };
        return hotels[new Random().nextInt(hotels.length)];
    }

    public static String getRandomAttractions() {
        String[] attractions = { "Eiffel Tower", "Statue of Liberty", "Colosseum", "Taj Mahal", "Great Wall of China" };
        return attractions[new Random().nextInt(attractions.length)];
    }

    public static int getRandomPrice() {
        return new Random().nextInt(901) + 100;
    }
    
}