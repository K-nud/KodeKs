
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Knud Vogel
 *
 */
public class GameOverPopup extends PopupWindow
{
	private static final long serialVersionUID = 1400666790706351389L;
	private JLabel text; //shows a message who won the game
	private JButton newGame, exit; //Buttons to start a new game or quit
	
	/**
	 * Constructer of a "game over" popup. When this popup is visible the players can decide 
	 * if they want to start a new game of KodeKs or if they want to quit.
	 * @param parent - GUI
	 * @param title - String
	 */
	public GameOverPopup(GUI parent, String title)
	{
		super(parent, title);
		
		text = new JLabel(""+title+"!");
		newGame = new JButton("New Game");
		exit = new JButton("Exit");
		newGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
	       		 new NewGameDialog();
	       		 dispose();}
		});
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				RunKodeKs.mainGUI.quitDlg();	       	}
		});
		newGame.addActionListener(parent);
		exit.addActionListener(parent);
		
		JPanel top = new JPanel();
		top.setBackground(new Color(0,0,0,0));
		top.add(text);
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(0,0,0,0));
		bottom.add(newGame);
		bottom.add(exit);
		add(top);
		add(bottom);
		
		pack();
		resetLocation();
	}
}
