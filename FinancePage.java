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
import javax.swing.border.Border;
import java.util.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FinancePage {
	public static String tripNameString = "Mumbai Trip!";
	Currency curr = Currency.getInstance("INR");
	public static String tripPriceString = "1000";

	public FinancePage() throws IOException {
		JFrame frame = new JFrame("Finance Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		frame.setLayout(new BorderLayout(3, 3));// (marginX,marginY)
		frame.setVisible(true);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();

		panel1.setBackground(Color.red);
		panel2.setBackground(Color.black);
		panel3.setBackground(Color.BLACK);
		panel4.setBackground(Color.black);

		// panel3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

		panel1.setPreferredSize(new Dimension(1000, 100));
		panel2.setPreferredSize(new Dimension(500, 100));
		panel3.setPreferredSize(new Dimension(500, 100));
		panel4.setPreferredSize(new Dimension(1000, 30));

		// Layout for Panel 1
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
		JLabel titleLabel = new JLabel("Finances");
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
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		backButton.setForeground(Color.white);
		subPanel31.add(backButton);
		panel1.add(subPanel31, BorderLayout.EAST);
		frame.add(panel1, BorderLayout.NORTH);

		// Layout for Panel 2
		JLabel tripNameLabel = new JLabel("<html>Trip Name<br><br>" + tripNameString + "</html>");
		tripNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		tripNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tripNameLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		tripNameLabel.setForeground(Color.WHITE);

		String symbol = curr.getSymbol();
		JLabel priceLabel = new JLabel("Price: " + symbol + tripPriceString);
		priceLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		priceLabel.setForeground(Color.WHITE);

		panel2.setLayout(new GridLayout(2, 1, 0, 0));
		panel2.add(tripNameLabel, BorderLayout.CENTER);
		panel2.add(priceLabel, BorderLayout.CENTER);

		frame.add(panel2, BorderLayout.WEST);

		// Layout for Panel 3
		panel3.setLayout(new GridLayout(5, 1));
		for (int i = 0; i < 5; i++) {
			JPanel gridPanel = new JPanel();
			// Set different background colors for each panel
			switch (i) {
				case 0:
					JLabel paymentLabel = new JLabel("<html>Payment Options</html>");
					paymentLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
					paymentLabel.setHorizontalAlignment(SwingConstants.CENTER);
					paymentLabel.setForeground(Color.WHITE);
					paymentLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
					gridPanel.add(paymentLabel, BorderLayout.CENTER);
					// gridPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
					gridPanel.setBackground(Color.black);
					break;
				case 1:
					JRadioButton blueRadioButton = new JRadioButton("HDFC Bank");
					blueRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
					blueRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
					blueRadioButton.setForeground(Color.black);
					blueRadioButton.setBackground(Color.lightGray);
					// blueRadioButton.setOpaque(false);
					gridPanel.setLayout(new BorderLayout());
					gridPanel.add(blueRadioButton, BorderLayout.CENTER);
					// gridPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
					// gridPanel.setBackground(Color.BLACK);
					break;
				case 2:
					JRadioButton greeRadioButton = new JRadioButton("SBI Bank");
					greeRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
					greeRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
					greeRadioButton.setForeground(Color.black);
					greeRadioButton.setBackground(Color.lightGray);
					// greeRadioButton.setOpaque(false);
					gridPanel.setLayout(new BorderLayout());
					gridPanel.add(greeRadioButton, BorderLayout.CENTER);
					// gridPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
					// gridPanel.setBackground(Color.BLUE);
					break;
				case 3:
					JRadioButton yelloRadioButton = new JRadioButton("UPI");
					yelloRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
					yelloRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
					yelloRadioButton.setForeground(Color.black);
					yelloRadioButton.setBackground(Color.lightGray);
					// yelloRadioButton.setOpaque(false);
					gridPanel.setLayout(new BorderLayout());
					gridPanel.add(yelloRadioButton, BorderLayout.CENTER);
					// gridPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
					// gridPanel.setBackground(Color.YELLOW);
					break;
				case 4:
					JButton chooseButton = new JButton("Confirm Payment");
					chooseButton.setFont(new Font("Times New Roman", Font.PLAIN ,25));
					gridPanel.setLayout(new BorderLayout());
					gridPanel.add(chooseButton, BorderLayout.CENTER);
					gridPanel.setBackground(Color.lightGray);
					break;
			}
			panel3.add(gridPanel);
		}
		frame.add(panel3, BorderLayout.EAST);

		// Layout for Panel 4
		panel4.setLayout(new BorderLayout());
		JLabel label = new JLabel("Made with Love at BITS Pilani");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
		panel4.add(label, BorderLayout.CENTER);
		frame.add(panel4, BorderLayout.SOUTH);
	}
}