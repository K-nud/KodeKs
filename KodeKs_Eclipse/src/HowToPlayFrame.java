import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Frame contains informations about the rules of the game
 * 
 * @author K. Vogel & B. Suhr
 *
 */
public class HowToPlayFrame extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public HowToPlayFrame() {
		final JFrame HowToPlay=new JFrame();
		HowToPlay.setDefaultCloseOperation(HIDE_ON_CLOSE);
		HowToPlay.setBounds(200, 200, 600, 600);
		HowToPlay.setPreferredSize(new Dimension(600, 600));
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		HowToPlay.setContentPane(contentPane);
		
		JLabel lblHowToPlay = new JLabel("How to play Kodeks");
		lblHowToPlay.setFont(new Font("Dialog", Font.BOLD, 18));
		lblHowToPlay.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblHowToPlay, BorderLayout.NORTH);
		
		JScrollPane textPane = new JScrollPane();
		contentPane.add(textPane, BorderLayout.CENTER);
		
		final JTextPane informationsKodeks = new JTextPane();
		textPane.setViewportView(informationsKodeks);
		informationsKodeks.setEditable(false);
		informationsKodeks.setFont(new Font("Dialog", Font.PLAIN, 14));
		informationsKodeks.setText("Welcom to KodeKs!\n" +
				"On the right you can press on the buttons to learn how to move Stones, to get Stones of the opponent player and how to win a game of KodeKs...");
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.WEST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {100, 0};
		gbl_panel_1.rowHeights = new int[] {25, 25, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_panel_1);
		
		/*
		 * Construction of the different buttons to get informations how to play KodeKs
		 */
		
		//Home-Button
		JButton home_Button = new JButton("Home");
		home_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informationsKodeks.setText("Welcom to KodeKs! \n" +
						"On the right you can press on the buttons to learn how to move Stones, to get Stones of the opponent player and how to win agame of KodeKs...");
				}
		});
		GridBagConstraints gbc_home_Button = new GridBagConstraints();
		gbc_home_Button.fill = GridBagConstraints.HORIZONTAL;
		gbc_home_Button.insets = new Insets(0, 0, 5, 0);
		gbc_home_Button.gridx = 0;
		gbc_home_Button.gridy = 1;
		buttonPanel.add(home_Button, gbc_home_Button);
		
		//How To Move???
		JButton howToMove_Button = new JButton("Move Stones");
		howToMove_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informationsKodeks.setText("Movement Rules \n\n" +
						"Red and Blue move one of their stones in alternate order. Each player has to move a stone in their turn - even if it's harmful.\n" +
						"The range of a stone is determined by the amount of dots beneath the stone itself. If there are 1-3 dots, the stone can only move the amount of fields according to the amount of buttons. A stone with 4 dots have a variable range between 1 and 4.\n"	+
						"A player may move a stone into any direction: horizontally, vertically or diagonally onto a free field. It's allowed to leap over existing buttons.\n\n" +
						"Forbidden combination:\nIt's not allowed to have a stone next to 2 other stones.\n\n" +
						"If a player ignores this rule, the other player is allowed to take one of the 3 stones from the board.\n\n" +
						"The goal of a turn is to build up threat on your opponent. But also try to avoid the loss of your own stones.\n");
				}
		});
		GridBagConstraints gbc_howToMove_Button = new GridBagConstraints();
		gbc_howToMove_Button.fill = GridBagConstraints.HORIZONTAL;
		gbc_howToMove_Button.insets = new Insets(0, 0, 5, 0);
		gbc_howToMove_Button.gridx = 0;
		gbc_howToMove_Button.gridy = 2;
		buttonPanel.add(howToMove_Button, gbc_howToMove_Button);
		
		//HowTo Take???
		JButton howToTake_Button = new JButton("Take Stones");
		howToTake_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informationsKodeks.setText("Strike Stones:\n" +
						"Only two stones directly next to each other (horizontally, vertically or diagonally) represent a binding and are able to destroy a enemy stone.\n" +
						"The range of such a binding is determined by the amount of dots beneath those two stones. Enemy stones within this range are threatened.\n" +
						"Only the nearest opponent stone within the 2 directions may be striked - the ones covered by stones infront of them are 'save' and can't be striked.\n\n" +
						"Attack: \n" +
						"1. Active threat\n" +
						"2. Reaction\n" +
						"3. Strike!\n\n" +
						"A threat occurs if a player builds up a binding, which range threatens an opponent stones.\n" +
						"As a reaction the opponent has the possibility to save one of his threatened stones.\n" +
						"The threat could also occur passive, if a player moves one of his stones into the threat range of an enemies binding. The enemy may strike the stoned moved in his next turn - without a waiting penalty.\n" +
						"The time to strike enemy stones is right before the turn of the attacker (before he moves his stone!). The player may remove every enemy stone which is threatened by one or more bindings.\n" +
						"A player strikes without moving his own stones, but with the power of bindings and their range.\n\n" +
						"After striking the player starts his turn by moving one of his stones. This player can't strike until the next round.");
				}
		});
		GridBagConstraints gbc_howToTake_Button = new GridBagConstraints();
		gbc_howToTake_Button.fill = GridBagConstraints.HORIZONTAL;
		gbc_howToTake_Button.insets = new Insets(0, 0, 5, 0);
		gbc_howToTake_Button.gridx = 0;
		gbc_howToTake_Button.gridy = 3;
		buttonPanel.add(howToTake_Button, gbc_howToTake_Button);
		
		//How To Win???
		JButton howToWin_Button = new JButton("How to win");
		howToWin_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informationsKodeks.setText("The goal of the game is to collect 10 stones from your opponent.\n");
				}
		});
		GridBagConstraints gbc_howToWin_Button = new GridBagConstraints();
		gbc_howToWin_Button.fill = GridBagConstraints.HORIZONTAL;
		gbc_howToWin_Button.insets = new Insets(0, 0, 5, 0);
		gbc_howToWin_Button.gridx = 0;
		gbc_howToWin_Button.gridy = 4;
		buttonPanel.add(howToWin_Button, gbc_howToWin_Button);
		
				
		
		JButton btnClose = new JButton("close");
		contentPane.add(btnClose, BorderLayout.SOUTH);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HowToPlay.dispose();
			}
		});
		
		HowToPlay.pack();
		HowToPlay.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
