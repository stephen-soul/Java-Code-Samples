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
import java.awt.GridLayout;
import java.awt.Component;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Box;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow {

	private JFrame frame;
	private JPanel panelMainMenu;
	private JLabel lblNewAdventure;
	private JLabel lblLogo;
	private JLabel lblLoadAdventure;
	private JLabel lblExit;
	Component verticalStrutMainMenu;
	
	private String[] mainMenuOptions = {"NEW ADVENTURE", "< NEW ADVENTURE >", "LOAD ADVENTURE", "< LOAD ADVENTURE >", "EXIT ADVENTURE", "< EXIT ADVENTURE >"};

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		panelMainMenu = new JPanel();
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.getContentPane().add(panelMainMenu, "name_74581453016396");
		panelMainMenu.setBackground(new Color(20, 20, 20));
		panelMainMenu.setLayout(new GridLayout(0, 1, 0, -100));
		
		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(GameWindow.class.getResource("/resources/logo.png")));
		panelMainMenu.add(lblLogo);
		
		verticalStrutMainMenu = Box.createVerticalStrut(20);
		panelMainMenu.add(verticalStrutMainMenu);
		
		lblNewAdventure = new JLabel(mainMenuOptions[1]);
		lblNewAdventure.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewAdventure.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewAdventure.setForeground(Color.WHITE);
		panelMainMenu.add(lblNewAdventure);
		
		lblLoadAdventure = new JLabel(mainMenuOptions[2]);
		lblLoadAdventure.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLoadAdventure.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoadAdventure.setForeground(Color.WHITE);
		panelMainMenu.add(lblLoadAdventure);
		
		lblExit = new JLabel(mainMenuOptions[4]);
		lblExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setForeground(Color.WHITE);
		panelMainMenu.add(lblExit);
		frame.setResizable(false);
		
		addKeyBinding(panelMainMenu, KeyEvent.VK_DOWN, "Menu Down", (evt) -> {
			if (mainMenuOptions[1].equals(lblNewAdventure.getText())) {
				lblNewAdventure.setText(mainMenuOptions[0]);
			} else if (mainMenuOptions[2].equals(lblLoadAdventure.getText())) {
				lblLoadAdventure.setText(mainMenuOptions[3]);
			}
		});
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
	public static void addKeyBinding(JComponent comp, int keyCode, String id, ActionListener actionListener){
		// Handle an input map
		InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "Menu Down");
		ActionMap ap = comp.getActionMap();
		ap.put("Menu Down", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionListener.actionPerformed(e);
			}
		});
	}
}
