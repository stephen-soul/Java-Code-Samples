/**
 * CLASS TO HANDLE READING GAME DATA FROM A TEXT FILE
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
		            if(!part.contains("#") && !part.contains("*"))
		            	lineToCopy+=part;
		            if(part.contains("*"))
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
}
