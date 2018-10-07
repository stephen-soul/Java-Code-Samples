package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import database.DBConnection;

import java.awt.GridLayout;
import java.awt.Component;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Box;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;

public class GameWindow {

	// Make variables
	private JFrame frame;
	private JPanel panelMainMenu;
	private JLabel lblNewAdventure;
	private JLabel lblLogo;
	private JLabel lblLoadAdventure;
	private JLabel lblExit;
	Component verticalStrutMainMenu;
	private String[] mainMenuOptions = {"NEW ADVENTURE", "< NEW ADVENTURE >", "LOAD ADVENTURE", "< LOAD ADVENTURE >", "EXIT ADVENTURE", "< EXIT ADVENTURE >"};
	private Boolean saveExists = false;
	// Create the DB connection
	DBConnection db;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.setVisible(true);
					window.frame.setSize(640, 480);
					window.panelMainMenu.setSize(640, 480);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
		db = new DBConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Handle importing font from resources
		InputStream fontStream = getClass().getResourceAsStream("/resources/VarelaRound-Regular.ttf");
		
		Font font; // General font object containing the resource as stream
		Font mainMenuFont = null; // Font for main menu selection - takes font and adds 21f
		Font gameFont = null; // Font for general game text - takes font and adds 14f
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
			mainMenuFont = font.deriveFont(21f);
			gameFont = font.deriveFont(14f);
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
		}
		
		// Initialize all JPanels
		panelMainMenu = new JPanel();
		lblLogo = new JLabel("");
		lblNewAdventure = new JLabel(mainMenuOptions[1]);
		lblLoadAdventure = new JLabel(mainMenuOptions[2]);
		lblExit = new JLabel(mainMenuOptions[4]);

		// Setup the frame
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.getContentPane().add(panelMainMenu, "name_74581453016396");
		frame.setResizable(false);
		
		// Setup the main menu panel
		panelMainMenu.add(lblLogo);
		verticalStrutMainMenu = Box.createVerticalStrut(30);
		panelMainMenu.add(verticalStrutMainMenu);
		panelMainMenu.add(lblNewAdventure);
		panelMainMenu.add(lblLoadAdventure);
		panelMainMenu.add(lblExit);
		panelMainMenu.setBackground(new Color(20, 20, 20));
		panelMainMenu.setLayout(new GridLayout(0, 1, 0, -100));
		
		// Handle the main menu logo
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(GameWindow.class.getResource("/resources/logo.png")));
		
		// Handle the new adventure label
		lblNewAdventure.setFont(mainMenuFont);
		lblNewAdventure.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewAdventure.setForeground(Color.WHITE);
		
		// Handle the load adventure label
		lblLoadAdventure.setForeground(checkIfSaveExists());
		lblLoadAdventure.setFont(mainMenuFont);
		lblLoadAdventure.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoadAdventure.setForeground(Color.WHITE);
		
		// Handle the exit label
		lblExit.setFont(mainMenuFont);
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setForeground(Color.WHITE);
		
		// Add key bindings using the helper function
		addKeyBinding(panelMainMenu, KeyEvent.VK_UP, "MenuUp", (evt) -> {
			moveMenuUp();
		});
		
		addKeyBinding(panelMainMenu, KeyEvent.VK_W, "MenuUp", (evt) -> {
			moveMenuUp();
		});
		
		addKeyBinding(panelMainMenu, KeyEvent.VK_DOWN, "MenuDown", (evt) -> {
			moveMenuDown();
		});
		
		addKeyBinding(panelMainMenu, KeyEvent.VK_S, "MenuDown", (evt) -> {
			moveMenuDown();
		});
	}
	
	/**
	 * Function to check if a save exists; if a save does exist return WHITE otherwise,
	 * grey it out as it does not exist.
	 */
	private Color checkIfSaveExists() {
		return null;
//		 doesSaveExist = check db for save
//		 Return color
	}
	
	/**
	 * Logic for moving the main menu selection up
	 */
	private void moveMenuUp() {
		if (mainMenuOptions[3].equals(lblLoadAdventure.getText())) {
			lblLoadAdventure.setText(mainMenuOptions[2]);
			lblNewAdventure.setText(mainMenuOptions[1]);
		} else if (mainMenuOptions[5].equals(lblExit.getText())) {
			lblExit.setText(mainMenuOptions[4]);
			if (saveExists) {
				lblLoadAdventure.setText(mainMenuOptions[3]);
			} else {
				lblNewAdventure.setText(mainMenuOptions[1]);
			}
			
		}
	}
	
	/**
	 * Logic for moving the main menu selection down
	 */
	private void moveMenuDown() {
		if (mainMenuOptions[1].equals(lblNewAdventure.getText())) {
			lblNewAdventure.setText(mainMenuOptions[0]);
			if (saveExists) {
				lblLoadAdventure.setText(mainMenuOptions[2]);
			} else {
				lblExit.setText(mainMenuOptions[5]);
			}
		} else if (mainMenuOptions[3].equals(lblLoadAdventure.getText())) {
			lblLoadAdventure.setText(mainMenuOptions[3]);
			lblExit.setText(mainMenuOptions[5]);
		}
	}
	
	/**
	 * Helper function to assign a keyCode to a JComponent
	 * @param comp
	 * JComponent (eg. Panel)
	 * @param keyCode
	 * Key pressed (eg. VK_DOWN = Down Arrow)
	 * @param id
	 * ID for the key pressed
	 * @param actionListener
	 * Action to be performed
	 */
	@SuppressWarnings("serial")
	private static void addKeyBinding(JComponent comp, int keyCode, String id, ActionListener actionListener){
		// Handle an input map
		InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke(keyCode, 0, false), id);
		ActionMap ap = comp.getActionMap();
		ap.put(id, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionListener.actionPerformed(e);
			}
		});
	}
}
