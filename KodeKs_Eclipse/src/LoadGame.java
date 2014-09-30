import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JLabel;




public class LoadGame {
	
	int currentPlayer;
	JLabel player1Name;
	JLabel player2Name;
	KodeKsData stateofdatboard;
	
	
	
	public LoadGame() {
		//Create JFileChooser-Object
		JFileChooser load = new JFileChooser();
		//Show dialog to choose a File to load
		load.showOpenDialog(null);
		String loadGameTitle = load.getSelectedFile().getName();
		

		try {

			FileInputStream is = new FileInputStream(loadGameTitle);
    		ObjectInputStream ois = new ObjectInputStream(is);
    //		GUI.kodeksGUI = (GUI) ois.readObject();
    		GUI.board.boardState = (KodeKsData)ois.readObject();
    		currentPlayer = (int)ois.readObject();
    		player1Name = (JLabel)ois.readObject();
    		player2Name = (JLabel)ois.readObject();
    		stateofdatboard = (KodeKsData)ois.readObject();
//    		GUI.analysePanel= (AnalysePanel) ois.readObject();
//    		GUI.statusPanel = (StatusPanel) ois.readObject();
//    		GUI.board.Player1Name = (JLabel) ois.readObject();
//    		GUI.board.Player2_Stones = (JPanel) ois.readObject();
    		
    		ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		KodeKsBoard.currentPlayer = currentPlayer;
		RunKodeKs.mainGUI.board.boardState = stateofdatboard;
//		KodeKsBoard.getThreatenStones = KodeKsData.getThreatenStone(currentPlayer);
		RunKodeKs.mainGUI.repaint();
		
		
		
	}

}
