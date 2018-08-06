/**
 * LOGIC FOR THE GAME - STEPHEN FETINKO 2018
 */

package game;
import ui.Console;
import game.GameState;

public class GameLogic {
	
	private GameText gameText = new GameText();
	private Console newConsole;
	private String userInput = ""; // Track the user input
	private String playerName = ""; // Keep the player name until the player is made
	private String playerClass = ""; // Keep the player class until the player is made
	private boolean playerMade = false; // Boolean to keep track of the player being made or not
	private GameState gameState = GameState.GAMESTATE_MAINMENU; // Track game state through ENUMS
	private GameTextState textState = GameTextState.GAMETEXT_MAINMENU; // Track the game text state through ENUMS
	
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
		if(textState == GameTextState.GAMETEXT_GETNAME)
			userInput = input;
		else
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
			case GAMETEXT_GETNAME:
				getPlayerName();
			case GAMETEXT_GETCLASS:
				getPlayerClass();
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
			textState = GameTextState.GAMETEXT_INTRO;
			clearConsole();
			updateConsole();
			textState = GameTextState.GAMETEXT_GETNAME;
			updateConsole();
		}
		else if(userInput.equals("2") || userInput.equals("exit"))
			newConsole.updateConsole("quit");
	}
	
	/**
	 * Function to get the players name
	 */
	private void getPlayerName() {
		gameText.appendPlayerName(userInput);
		textState = GameTextState.GAMETEXT_GETCLASS;
		updateConsole();
	}
	
	private void getPlayerClass() {
		if(userInput.equals("1") || userInput.equals("warrior")) {
			
		}
		if(playerMade) {
			textState = GameTextState.GAMETEXT_FINALIZEINTRO;
			updateConsole();
		}
	}
	
	/**
	 * Function to send game text to the console based on our current state
	 */
	private void updateConsole() {
		// Update the console with the main menu text - ordinal gets the INT for the ENUM state
		newConsole.updateConsole(gameText.getGameText(textState.ordinal()));
	}
	
	/**
	 * Function to clear the contents of the game console
	 */
	private void clearConsole() {
		newConsole.updateConsole("cls");
	}
}
