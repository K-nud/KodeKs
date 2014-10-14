import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

import javax.swing.JOptionPane;

/**
 * 
 * @author K. Vogel & B. Suhr
 * 
 */
public class SaveGame {
	static String fixedPlayer1;
	static String fixedPlayer2;
	static int newValue;

	public SaveGame() {
		// Sets the Format for the Date we use
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy'-'HH-mm");
		Date date = new Date();
		fixedPlayer1 = new String(KodeKsBoard.player1Name.getText());
		fixedPlayer2 = new String(KodeKsBoard.player2Name.getText());
		// Create the title of the save-file
		String saveGameTitle = fixedPlayer1 + "VS" + fixedPlayer2 + "_" + dateFormat.format(date) + ".kdk";

		/*
		 * Saves the the following into a file line-by-line: TokenPositionList() redTokenLeft blueTokenLeft minutes and seconds of current game time Player1Name
		 * Player2Name
		 */
		// BufferedWriter writeObject = null;
		try {
			File file = new File(saveGameTitle);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(GUI.board.boardState);
			oos.writeObject(KodeKsBoard.currentPlayer);
			oos.writeObject(KodeKsBoard.player1Name);
			oos.writeObject(KodeKsBoard.player2Name);
			oos.writeObject(GUI.board.boardState);
			oos.writeObject(KodeKsBoard.message);
			// oos.writeObject(GUI.analysePanel);
			// oos.writeObject(GUI.statusPanel);
			// oos.writeObject(GUI.board.Player1_Stones);
			// oos.writeObject(GUI.board.Player2_Stones);

			oos.close();
		} catch (Exception es) {
			es.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "File Name: '" + saveGameTitle + "'", "Game successfully saved!", JOptionPane.INFORMATION_MESSAGE);

	}

}
