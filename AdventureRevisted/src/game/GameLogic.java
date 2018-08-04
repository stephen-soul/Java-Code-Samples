package game;
import ui.Console;

public class GameLogic {
	
	private GameText gameText = new GameText();
	private Console newConsole;
	/**
	 * Function to initialize the game text
	 */
	public GameLogic(Console console) {
		newConsole = console;
	}
	public void initialize() {
		gameText.readGameText();
		newConsole.updateConsole(gameText.getGameText(0));
	}
	
	/**
	 * Function to take game input, compare it, and then call the advance function
	 */
	public void handleInput() {
		
	}
	
	/**
	 * Function to advance the game.
	 */
	public void advance() {
		
	}
}
