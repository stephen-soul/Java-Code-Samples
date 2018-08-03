package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Font;
import java.awt.FontFormatException;

public class Console {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application & Set the sizes.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Console window = new Console();
					window.frame.setSize(640, 480);
					window.frame.setResizable(false);
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
		// Setup fonts
		InputStream fontStream = Console.class.getResourceAsStream("Fonts/ShareTechMono-Regular.ttf");
		Font font;
		Font gameFont = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
			gameFont = font.deriveFont(15f);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		JLabel lblName = new JLabel("Name: Bob");
		lblName.setForeground(Color.WHITE);
		lblName.setBackground(Color.BLACK);
		lblName.setBounds(10, 12, 135, 15);
		lblName.setFont(gameFont);
		frame.getContentPane().add(lblName);
		JLabel lblGold = new JLabel("Gold: 10g");
		lblGold.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGold.setForeground(new Color(204, 204, 0));
		lblGold.setBackground(Color.BLACK);
		lblGold.setBounds(504, 12, 120, 15);
		lblGold.setFont(gameFont);
		frame.getContentPane().add(lblGold);
		
		JLabel lblHp = new JLabel("HP: 100/100");
		lblHp.setForeground(Color.RED);
		lblHp.setBackground(Color.BLACK);
		lblHp.setBounds(175, 12, 120, 15);
		lblHp.setFont(gameFont);
		frame.getContentPane().add(lblHp);
		
		JLabel lblMp = new JLabel("MP: 100/100");
		lblMp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMp.setForeground(new Color(0, 102, 255));
		lblMp.setBackground(Color.BLACK);
		lblMp.setBounds(358, 12, 120, 15);
		lblMp.setFont(gameFont);
		frame.getContentPane().add(lblMp);
		
		JTextArea txtConsole = new JTextArea();
		txtConsole.setText("Welcome to the world of Black Magic RPG");
		txtConsole.setForeground(Color.WHITE);
		txtConsole.setBackground(Color.BLACK);
		txtConsole.setLineWrap(true);
		txtConsole.setEditable(false);
		txtConsole.setBounds(26, 271, 598, 135);
		txtConsole.setFont(gameFont);
		frame.getContentPane().add(txtConsole);
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				switch(e.getKeyChar()) {
				case KeyEvent.VK_ENTER:
					txtConsole.append(textField.getText());
					textField.setText(null);
					break;
				default:
					break;
				}
				if(e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_TAB) {
					textField.requestFocusInWindow();
				}
			}
		});
		textField.setEditable(true);
		textField.setForeground(Color.WHITE);
		textField.setBackground(Color.BLACK);
		textField.setBounds(26, 423, 598, 19);
		textField.setFont(gameFont);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u00BB");
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		label.setFont(new Font("Dialog", Font.BOLD, 24));
		label.setBounds(10, 417, 28, 24);
		frame.getContentPane().add(label);
		frame.setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.setTitle("Black Magic RPG");
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textField.requestFocusInWindow();
		frame.setFocusTraversalKeysEnabled(false);
	}
}
