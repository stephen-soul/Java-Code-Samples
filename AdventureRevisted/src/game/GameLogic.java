package game;
import ui.Console;
import game.GameState;

public class GameLogic {
	
	private GameText gameText = new GameText();
	private Console newConsole;
	private String userInput = ""; // Track the user input
	private GameState gameState = GameState.GAMESTATE_MAINMENU; // Track game state through ENUMS
	
	/**
	 * Function to initialize ui without making it new
	 */
	public GameLogic(Console console) {
		newConsole = console;
	}
	
	/**
	 * Function to initialize the game text
	 */
	public void initialize() {
		gameText.readGameText();
		newConsole.updateConsole(gameText.getGameText(gameState.ordinal()));
	}
	
	/**
	 * Function to take game input, compare it, and then call the advance function
	 */
	public void handleInput(String input) {
		userInput = input;
		advance();
	}
	
	/**
	 * Function to advance the game.
	 */
	public void advance() {
		switch(gameState) {
		case GAMESTATE_MAINMENU:
			break;
		default:
			break;
		
		}
	}
}
