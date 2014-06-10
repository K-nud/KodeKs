import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


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
		
       /* tokenPositionList = new String("");
        		tokenLeftRed = new String("");
        		tokenLeftBlue = new String("");
        		time = new String("");
        		player1Name = new String("");
        		player2Name =new String("");*/
		try {

			FileInputStream is = new FileInputStream(loadGameTitle);
    		ObjectInputStream ois = new ObjectInputStream(is);
    		RunKodeKs.main = (GUI) ois.readObject();
    		GUI.toolBar = (KodeKsToolBar) ois.readObject();
    		GUI.board.boardState = (KodeKsData)ois.readObject();
    		AnalysePanel.analyseScroll= (JScrollPane) ois.readObject();
    		GUI.statusPanel = (StatusPanel) ois.readObject();
    		GUI.board.Player1_Stones = (JPanel) ois.readObject();
    		GUI.board.Player2_Stones = (JPanel) ois.readObject();
    		
    		ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//Initializes the positions of the token
		/*int[][] loadedBoard = new int[10][10];
		int cnt = 0;
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				loadedBoard[row][col] = tokenPositionList.charAt(cnt);
				cnt++;
			}     
		} 
		
		//TODO: get the right board array to overwrite with loaded data.
		//Load the right token positioning
		//GUI.board.boardState.board[][]=loadedBoard;
		
		//Player token left
		KodeKsBoard.NumberOfRedStones = Integer.parseInt(tokenLeftRed);
		KodeKsBoard.NumberOfBlueStones = Integer.parseInt(tokenLeftBlue);
		//change game time
		GameTimer.currentTime = time.charAt(0) + time.charAt(1) + ":" + time.charAt(2) + time.charAt(3);
		//change player names
		//new NewGameDialog(player1Name, player2Name); */
	}


}
