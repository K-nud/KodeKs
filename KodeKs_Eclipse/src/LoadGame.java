import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JPanel;


public class LoadGame {
	
	String 	tokenPositionList = null,
    		tokenLeftRed = null,
    		tokenLeftBlue = null,
    		time = null,
    		player1Name = null,
    		player2Name = null;
	
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
    		RunKodeKs.main = (GUI) ois.readObject();
    		//GUI.toolBar = (KodeKsToolBar) ois.readObject();
    		GUI.board.boardState = (KodeKsData)ois.readObject();
    		GUI.analysePanel= (AnalysePanel) ois.readObject();
    		GUI.statusPanel = (StatusPanel) ois.readObject();
    		GUI.board.Player1_Stones = (JPanel) ois.readObject();
    		GUI.board.Player2_Stones = (JPanel) ois.readObject();
    		
    		ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
