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
	    fixedPlayer1=new String(KodeKsBoard.Player1Name.getText());
	    fixedPlayer2=new String(KodeKsBoard.Player2Name.getText());
		//Create the title of the save-file
	    String saveGameTitle = fixedPlayer1 + "VS" + fixedPlayer2 + "_" + dateFormat.format(date) + ".kdk";
		
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
    		File save = new File(saveGameTitle);
    		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(save));    		
    		oos.writeObject(RunKodeKs.main);
    		oos.writeObject(KodeKsBoard.board);
    		oos.writeObject(GUI.board.boardState);
    		oos.writeObject(GUI.analysePanel);  		
    		oos.writeObject(GUI.statusPanel);
    		oos.writeObject(GUI.board.Player1_Stones);
    		oos.writeObject(GUI.board.Player2_Stones);
    		
    		oos.close();
    	}
    catch (Exception es) {
        es.printStackTrace();
    }
		JOptionPane.showMessageDialog(null, "File Name: '" + saveGameTitle + "'", "Game successfully saved!", JOptionPane.INFORMATION_MESSAGE);
		
		
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
