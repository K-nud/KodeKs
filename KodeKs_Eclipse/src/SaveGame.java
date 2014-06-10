import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.*;

import javax.swing.JOptionPane;


public class SaveGame {
	static String fixedPlayer1;
	static String fixedPlayer2;
	static int newValue;
	
	public SaveGame() {
		//Sets the Format for the Date we use
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
	    Date date = new Date();
	    //Get the numbers of TokenLeft for each player
	    //int redTokenLeft = KodeKsData.getNumberOfStones(1);
	    //int blueTokenLeft = KodeKsData.getNumberOfStones(2);
	    fixedPlayer1=new String(KodeKsBoard.Player1Label.getText());
	    fixedPlayer2=new String(KodeKsBoard.Player2Label.getText());
	    int redTokenLeft = KodeKsBoard.NumberOfRedStones;
	    //int blueTokenLeft = KodeKsBoard.NumberOfBlueStones;
		//Create the title of the save-file
	    String saveGameTitle = fixedPlayer1 + "Vs" + fixedPlayer2 + "(" + GameTimer.formatTime(1) + GameTimer.formatTime(2) + ")" + dateFormat.format(date) + ".kdk";
		
	    /*Saves the the following into a file line-by-line:
	     * TokenPositionList()
	     * redTokenLeft
	     * blueTokenLeft
	     * minutes and seconds of current game time
	     * Player1Name
	     * Player2Name
	     */
		//BufferedWriter writeObject = null;
		try {
    		File file = new File(saveGameTitle);
    		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));    		
    		oos.writeObject(RunKodeKs.main);
    		oos.defaultWriteObject();
    		//oos.writeObject(GUI.board.boardState);
    		//oos.writeObject(AnalysePanel.analyseScroll);
    		
    		//oos.writeObject(GUI.statusPanel);
    		//oos.writeObject(GUI.board.Player1_Stones);
    		//oos.writeObject(GUI.board.Player2_Stones);
    		
    		oos.close();
    	}
    catch (Exception es) {
        es.printStackTrace();
    }
		JOptionPane.showMessageDialog(null, "File Name: '" + saveGameTitle + "'", "Game successfully saved!", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, fixedPlayer1 + " hat " + redTokenLeft + " übrig.", "RedTokenLeft", JOptionPane.INFORMATION_MESSAGE);

		
	}
	
	//Saves the positioning of the tokens within a String
	static String getTokenPositionList() {
		String tokenPositionList = "";
        for (int row = 0; row < 10; row++) {
           for (int col = 0; col < 10; col++) {
        	   tokenPositionList = tokenPositionList + GUI.board.boardState.pieceAt(row, col);
           }
        }
        return tokenPositionList;
     }  // end saveTokenPosition()

}
