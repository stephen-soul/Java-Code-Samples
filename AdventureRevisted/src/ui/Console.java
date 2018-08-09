/**
 * CLASS TO HANDLE THE UI FOR THE GAME
 * STEPHEN FETINKO 2018
 */

package ui;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.GameLogic;
import game.GameText;

import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Font;
import java.awt.FontFormatException;
import javax.swing.JScrollPane;

public class Console {

	// Setup the UI elements
	private JFrame frame;
	private JLabel lblName = new JLabel();
	private JLabel lblHp = new JLabel();
	private JLabel lblMp = new JLabel();
	private JLabel lblGold = new JLabel();
	private JTextArea txtConsole = new JTextArea(); // This is in 'console' for the game
	private JTextField txtInput = new JTextField(); // This is the 'input' for the game
	private JLabel lblChevron = new JLabel(); // This is the >> that's beside the input
	private final GameLogic gameLogic = new GameLogic(this);
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Launch the application & Set the sizes.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Console window = new Console();
					window.frame.setSize(640, 480); // Set a static size
					window.frame.setResizable(false); // Remove the resize option
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Console() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Setup the frame
		gameLogic.initialize();
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		frame.setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.setTitle("Black Magic RPG");
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set the frame icon to the one located in resources
		try {
		     ClassLoader cl = this.getClass().getClassLoader();
		     ImageIcon programIcon = new ImageIcon(cl.getResource("logo.png"));
		     frame.setIconImage(programIcon.getImage());
		  } catch (Exception e) {
		     System.out.println("Could not load program icon.");
		  }
		// Setup fonts
		InputStream fontStream = Console.class.getResourceAsStream("Fonts/ShareTechMono-Regular.ttf");
		Font font;
		Font gameFont = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
			gameFont = font.deriveFont(15f);
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
		}
		// Set the name label attributes
		lblName.setForeground(Color.WHITE);
		lblName.setBackground(Color.BLACK);
		lblName.setBounds(10, 12, 135, 15);
		lblName.setFont(gameFont);
		// Set the HP label attributes
		lblHp.setForeground(Color.RED);
		lblHp.setBackground(Color.BLACK);
		lblHp.setBounds(175, 12, 120, 15);
		lblHp.setFont(gameFont);
		// Set the MP label attributes
		lblMp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMp.setForeground(Color.CYAN);
		lblMp.setBackground(Color.BLACK);
		lblMp.setBounds(358, 12, 120, 15);
		lblMp.setFont(gameFont);
		// Set the gold label attributes
		lblGold.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGold.setForeground(new Color(204, 204, 0));
		lblGold.setBackground(Color.BLACK);
		lblGold.setBounds(504, 12, 120, 15);
		lblGold.setFont(gameFont);
		// Set the games input (JTextField) attributes
		txtInput.setEditable(true);
		txtInput.setForeground(Color.WHITE);
		txtInput.setBackground(Color.BLACK);
		txtInput.setBounds(26, 423, 598, 19);
		txtInput.setFont(gameFont);
		txtInput.setColumns(10);
		// Set up the bit of text beside the input (>>) 
		lblChevron.setForeground(Color.WHITE);
		lblChevron.setBackground(Color.BLACK);
		lblChevron.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblChevron.setBounds(10, 417, 28, 24);
		lblChevron.setText("\u00BB");
		// Add the UI elements to the frame
		frame.getContentPane().add(lblName);
		frame.getContentPane().add(lblHp);
		frame.getContentPane().add(lblMp);
		frame.getContentPane().add(lblGold);
		scrollPane.setBounds(26, 271, 598, 135);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(txtConsole);
		txtConsole.setWrapStyleWord(true);
		// Set the games console (JTextArea) attributes
		txtConsole.setForeground(Color.WHITE);
		txtConsole.setBackground(Color.BLACK);
		txtConsole.setLineWrap(true);
		txtConsole.setEditable(false);
		txtConsole.setFont(gameFont);
		scrollPane.getVerticalScrollBar().setForeground(Color.BLACK);
		frame.getContentPane().add(txtInput);
		frame.getContentPane().add(lblChevron);
		// Request focus to make sure the games input (JTextField) is focused at launch
		txtInput.requestFocusInWindow();
		// Set up a listener to the games input (JTextField) so that hitting enter can move the game along
		txtInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				switch(e.getKeyChar()) {
				case KeyEvent.VK_ENTER:
					gameLogic.handleInput(txtInput.getText());
					txtInput.setText("");
					break;
				default:
					break;
				}
			}
		});
	}
	
	/**
	 * Function to update the UI through text that's passed to the function
	 * @param text
	 * Text to pass to the UI
	 */
	public void updateConsole(String text) {
		if(text.equals("quit"))
			System.exit(0);
		if(text.equals("cls"))
			txtConsole.setText("");
		else
			txtConsole.append(text);
	}
}
