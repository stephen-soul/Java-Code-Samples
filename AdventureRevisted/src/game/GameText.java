/**
 * CLASS TO HANDLE READING GAME DATA FROM A TEXT FILE AND FILLING AN ARRAY WITH THE INFORMATION
 * STEPHEN FETINKO 2018
 */


package game;

import java.io.*;
import java.util.*; 

public class GameText {
	
	private List<String> gameTextAsList = new ArrayList<>();
	
	/**
	 * Function to read the game text from the file and fill the game text list
	 */
	public void readGameText() {
		try {
			BufferedReader gameTextBuffer = new BufferedReader(new FileReader("resources/TextForGame.txt"));	
			String line = "";
			String lineToCopy = "";
			do {
				String[] gameTextSplit = line.split("\\n");
				for (String part : gameTextSplit) {
		            if(!part.contains("#"))
		            	lineToCopy+=part;
		            if(part.equals(""))
		            	lineToCopy+="\n";
		            if(part.contains("@")) {
						lineToCopy = lineToCopy.substring(0, lineToCopy.length()-1);
						gameTextAsList.add(lineToCopy);
						lineToCopy = "";
		            }
		        }
			} while ((line = gameTextBuffer.readLine()) != null);
			gameTextBuffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function to return a part of the game text list at a specific spot.
	 * @param position
	 * This is the position of the array returned.
	 * @return
	 * Returns the string at the specified position.
	 */
	public String getGameText(int position) {
		return gameTextAsList.get(position);
	}
	
	/**
	 * Function to replace the instances of a player placeholder with the players name
	 * @param playerName
	 * Defined player name according to the player
	 */
	public void appendPlayerName(String playerName) {
		for(int i = 0; i < gameTextAsList.size(); i++) {
			String s = gameTextAsList.get(i);
			if(s.contains("name")) gameTextAsList.set(i, s.replaceAll("name", playerName));
		}
//		for(int i=0; i < gameTextAsList.size(); i++) {
//			gameTextAsList.set(i, gameTextAsList.get(i).replace("name", playerName));
//		}
	}
}
