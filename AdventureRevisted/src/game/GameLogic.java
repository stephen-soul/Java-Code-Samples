package game;
import ui.Console;
import game.GameState;

public class GameLogic {
	
	private GameText gameText = new GameText();
	private Console newConsole;
	private String userInput = ""; // Track the user input
	private GameState gameState = GameState.GAMESTATE_MAINMENU; // Track game state through ENUMS
	private GameTextState textState = GameTextState.GAMETEXT_INTRO;
	
	/**
	 * Function to initialize the UI without making it new
	 */
	public GameLogic(Console console) {
		newConsole = console;
	}
	
	/**
	 * Function to initialize the game text
	 */
	public void initialize() {
		gameText.readGameText();
		updateConsole();
	}
	
	/**
	 * Function to take game input, compare it, and then call the advance function
	 */
	public void handleInput(String input) {
		userInput = input.toLowerCase();
		advance();
	}
	
	/**
	 * Function to advance the game. Done with a nested state machine using ENUMS
	 */
	public void advance() {
		switch(gameState) {
		case GAMESTATE_MAINMENU:
			handleMainMenu();
			break;
		case GAMESTATE_ALIVE:
			switch(textState) {
			case GAMETEXT_INTRO:
				break;
			default:
				break;
				
			}
			break;
		case GAMESTATE_DEAD:
			break;
		default:
			break;
			
		}
		userInput = ""; // Clear the string after the advance function
	}
	
	/**
	 * Function to handle the main input options
	 */
	private void handleMainMenu() {
		if(userInput.equals("1") || userInput.equals("new")) {
			gameState = GameState.GAMESTATE_ALIVE;
			clearConsole();
			updateConsole();
		}
		else if(userInput.equals("2") || userInput.equals("exit"))
			newConsole.updateConsole("quit");
	}
	
	private void getPlayerName() {
		
	}
	
	/**
	 * Function to send game text to the console based on our current state
	 */
	private void updateConsole() {
		// Update the console with the main menu text (getGameText(0))
		newConsole.updateConsole(gameText.getGameText(gameState.ordinal()));
	}
	
	/**
	 * Function to clear the contents of the game console
	 */
	private void clearConsole() {
		newConsole.updateConsole("cls");
	}
}
