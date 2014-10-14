import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Dialog appears if a player resigned or a player had win a game
 * Gives the possibility to start a new game
 * 
 * @author K. Vogel & B. Suhr
 *
 */
public class NewGameDialog implements ActionListener {
//Constructor
	String contentNameP1Field;
	String contentNameP2Field;
	NewGameDialog(){
		
		
	final JFrame newgame=new JFrame("New Game");
	 //Fenster
	 newgame.setBackground(Color.lightGray);
	 newgame.setLayout(new BorderLayout());
	 newgame.setBounds(325, 360, 300, 400);
	 //newgame.setResizable(false); //Hinweis im Text beachten
	 
	 //Message
	 newgame.add(new Label("Let's start a new game of KodeKs!"), BorderLayout.NORTH);
	 //Player IDs
	 JPanel NGLabel= new JPanel();
	 newgame.add(NGLabel, BorderLayout.CENTER);
	 NGLabel.setLayout(new GridLayout(3,3));
	 NGLabel.add(new Label("Player 1:"));	
	 final JTextField nameP1 = new JTextField("RED");
	 NGLabel.add(nameP1);
	 NGLabel.add(new Label(null));
	 NGLabel.add(new Label("Player 2:"));
	 final JTextField nameP2 = new JTextField("BLUE");
	 NGLabel.add(nameP2);
	 NGLabel.add(new Label(null));
	 NGLabel.add(new Label(null));
	 
	 //Buttons
	 Panel panel = new Panel();
	 panel.setLayout(new FlowLayout(FlowLayout.CENTER));
	 //NewGameButton
	 Button ngbutton = new Button("new game");
	 ngbutton.addActionListener(new ActionListener(){
	   	 public void actionPerformed(ActionEvent e){   		
	   			
 	   		GUI.analysePanel.clearAnalyse();
	   		RunKodeKs.mainGUI.repaint();
	   		
	   		KodeKsBoard.player1Name.setText(nameP1.getText());
	   		KodeKsBoard.player2Name.setText(nameP2.getText());
	   		AnalysePanel.player1Name.setText(nameP1.getText());
	   		AnalysePanel.player2Name.setText(nameP2.getText());
	   		StatusPanel.player1Name.setText(nameP1.getText());
	   		StatusPanel.player2Name.setText(nameP2.getText());
	   		StatusPanel.rStones.setText("14");
	   		StatusPanel.bStones.setText("14");
	   		
	   		GUI.board.doNewGame();	 		
	   		
	   		newgame.dispose();
	   		}});
	 NGLabel.add(ngbutton);
	 
	 //CancelButton
	 Button cbutton = new Button("cancel");
	 cbutton.addActionListener(new ActionListener(){
	   	 public void actionPerformed(ActionEvent e){
	   		newgame.dispose();}});
	 NGLabel.add(cbutton);
	 newgame.pack();
	 newgame.setVisible(true);
	 }

public void actionPerformed(ActionEvent arg0) {
	}
	 	
}
	 
	 
