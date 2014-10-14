import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JLabel;


/**
 * 
 * @author K. Vogel B. Suhr
 *
 */
public class LoadGame {
	
	int currentPlayer;
	JLabel player1Name;
	JLabel player2Name;
	KodeKsData stateofdatboard;
	JLabel message;
	
	
	@SuppressWarnings("static-access")
	public LoadGame() {
		//Create JFileChooser-Object
		JFileChooser load = new JFileChooser();
		//Show dialog to choose a File to load
		load.showOpenDialog(null);
		String loadGameTitle = load.getSelectedFile().getName();
		

		try {

			FileInputStream is = new FileInputStream(loadGameTitle);
    		ObjectInputStream ois = new ObjectInputStream(is);
    		GUI.board.boardState = (KodeKsData)ois.readObject();
    		KodeKsBoard.currentPlayer = (int)ois.readObject();
    		player1Name = (JLabel)ois.readObject();
    		player2Name = (JLabel)ois.readObject();
    		stateofdatboard = (KodeKsData)ois.readObject();
    		message = (JLabel)ois.readObject();
    		
    		ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		KodeKsBoard.message.setText(message.getText());
		
		//apply names to labels
   		KodeKsBoard.player1Name.setText(player1Name.getText());
   		KodeKsBoard.player2Name.setText(player2Name.getText());
   		AnalysePanel.player1Name.setText(player1Name.getText());
   		AnalysePanel.player2Name.setText(player2Name.getText());
   		StatusPanel.player1Name.setText(player1Name.getText());
   		StatusPanel.player2Name.setText(player2Name.getText());
   		
   		//update board to properly reflect allowed moves and threatened stones
		RunKodeKs.mainGUI.board.updateBoard(stateofdatboard);
		
		
		
		
	}

}
