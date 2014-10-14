import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author K. Vogel & B. Suhr
 * 
 */
public class ResignDialog implements ActionListener {

	final JDialog resignDlg = new JDialog();
	static JLabel resignmessage;
	static JButton confirmButton, cancelButton;

	/**
	 * 
	 */
	ResignDialog() {

		// Fenster
		resignDlg.setBackground(Color.lightGray);
		resignDlg.setLayout(new BorderLayout());
		resignDlg.setBounds(325, 360, 200, 200);
		resignDlg.setResizable(false);

		// Message
		resignmessage = new JLabel("Do you realy want to resign??? \n You will loose the game...");
		resignDlg.add(resignmessage, BorderLayout.CENTER);
		// Buttons
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		confirmButton = new JButton("Yes");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doResign();
			}
		});
		panel.add(confirmButton);
		cancelButton = new JButton("No");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resignDlg.dispose();
			}
		});
		panel.add(cancelButton);
		resignDlg.add(panel, BorderLayout.SOUTH);
		resignDlg.pack();
		resignDlg.setVisible(true);
	}

	/**
	 * 	
	 */
	public void doResign() {

		if (KodeKsBoard.currentPlayer == KodeKsBoard.RED)
			gameOver("RED resigns.  BLUE wins.");
		else
			gameOver("BLUE resigns.  RED wins.");
	}

	/**
	 * The game ends. The parameter, str, is displayed as a message to the user. The states of the buttons are adjusted so players can start a new game. This
	 * method is called when the game ends at any point in this class
	 * 
	 * @param str
	 *            - String
	 */
	void gameOver(String str) {
		GUI.board.gameInProgress = false;
		ResignDialog.resignmessage.setText(str);
		cancelButton.setText("close");
		confirmButton.setText("new game");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resignDlg.dispose();
				new NewGameDialog();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
