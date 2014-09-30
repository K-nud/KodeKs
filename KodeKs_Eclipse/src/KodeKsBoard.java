import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 * 
 * @author Knud Vogel
 * 
 */
public class KodeKsBoard extends KodeKsData implements ActionListener,
		MouseListener {

	private static final long serialVersionUID = 12341234L;

	KodeKsData boardState; // The data for the KodeKs board is kept here.
									// This board is also responsible for
									// generating lists of legal moves.
	protected static JLabel message; // a message label which shows who's turn
										// it is

	protected JPanel Player1_Stones; // On these labels are the taken Stones
	protected JPanel Player2_Stones;
	protected static JLabel Player1Name; // On these Labels are the names of the
											// two players
	protected static JLabel Player2Name;

	static int NumberOfRedStones = 0; // these inegers represents the left
										// stones of each player
	static int NumberOfBlueStones = 0;
	protected static JPanel board; // This panel contains the board, the
									// statuspanel and the "stonepanels"

	boolean gameInProgress; // Is a game currently in progress?

	/* The next five variables are valid only when the game is in progress. */

	public static int currentPlayer; // Whose turn is it now? The possible
										// values are 1(red player) and 2(blue
										// player)

	int selectedRow, selectedCol; // If the current player has selected a piece
									// to
	// move, these give the row and column
	// containing that piece. If no piece is
	// yet selected, then selectedRow is -1.

	static ThreatenStone[] getThreatenStones; // An array containing the threaten
										// stones by the current player.

	KodeKsMove[] legalMoves; // An array containing the legal moves for the
								// current player.

	/**
	 * Constructor. Create the labels, the board and starts a new game. Listens
	 * for mouse. clicks
	 * 
	 * @param parent
	 *            - Gui
	 * @param boardstate
	 *            - KodeksData
	 * @param size
	 *            - Dimension
	 */
	public KodeKsBoard(KodeKsData boardstate, GUI parent, Dimension size) {
		/*
		 * creates a panel for the game
		 */
		board = new JPanel();
		setLayout(new BorderLayout());

		/*
		 * adds the game board to the game panel
		 */
		boardState = new KodeKsData();
		board.add(boardState, BorderLayout.CENTER);
		board.setPreferredSize(new Dimension(824, 640));
		setBackground(new Color(0, 150, 0));

		/*
		 * adds a panel for each player on which he collect his taken pieces
		 */
		Player1_Stones = new JPanel();
		Player1_Stones.setPreferredSize(new Dimension(65, 625));
		Player1_Stones
				.setLayout(new BoxLayout(Player1_Stones, BoxLayout.Y_AXIS));
		Player1_Stones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(
				0, 0, 0)));
		add(Player1_Stones, BorderLayout.WEST);

		Player1Name = new JLabel("Player 1");
		Player1Name.setAlignmentX(CENTER_ALIGNMENT);
		Player1_Stones.add(Player1Name);

		Player2_Stones = new JPanel();
		Player2_Stones.setPreferredSize(new Dimension(65, 625));
		Player2_Stones
				.setLayout(new BoxLayout(Player2_Stones, BoxLayout.Y_AXIS));
		Player2_Stones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(
				0, 0, 0)));
		add(Player2_Stones, BorderLayout.EAST);

		Player2Name = new JLabel("Player 2");
		Player2Name.setAlignmentX(CENTER_ALIGNMENT);
		Player2_Stones.add(Player2Name);

		addMouseListener(this);
		message = new JLabel("");
		message.setAlignmentX(CENTER_ALIGNMENT);
		message.setFont(new Font("Courier", Font.BOLD, 32));
		message.setForeground(Color.BLACK);
		NumberOfRedStones = boardState.getNumberOfStones(RED);
		NumberOfBlueStones = boardState.getNumberOfStones(BLUE);
		doNewGame();
	}

	/**
	 * Start a new game
	 */
	public void doNewGame() {

		boardState.setUpGame(); // Set up the pieces.
		Player1_Stones.removeAll();
//		Player1Name = new JLabel("Player 1");
		Player1Name.setAlignmentX(CENTER_ALIGNMENT);
		Player1_Stones.add(Player1Name);
		Player2_Stones.removeAll();
//		Player2Name = new JLabel("Player 2");
		Player2Name.setAlignmentX(CENTER_ALIGNMENT);
		Player2_Stones.add(Player2Name);
		currentPlayer = KodeKsData.RED; // RED moves first.
		getThreatenStones = getThreatenStone(KodeKsData.RED); // Get the BLUE
																// stones which
																// are threaten
																// by RED (is
																// empty at the
																// beginning)
		legalMoves = getLegalMoves(KodeKsData.RED); // Get RED's legal moves.
		selectedRow = -1; // RED has not yet selected a piece to move.
		message.setText(Player1Name.getText() + ": Make your move.");
		NumberOfRedStones = getNumberOfStones(RED);
		NumberOfBlueStones = getNumberOfStones(BLUE);
		gameInProgress = true;
		repaint();
	}

	/**
	 * Current player resigns. Game ends. Opponent wins.
	 */
	public void doResign() {

		if (currentPlayer == KodeKsData.RED)
			gameOver("RED resigns.  BLUE wins.");
		else
			gameOver("BLUE resigns.  RED wins.");
	}

	/**
	 * The game ends. The parameter, str, is displayed as a message to the user.
	 * The states of the buttons are adjusted so players can start a new game.
	 * This method is called when the game ends at any point in this class.
	 * 
	 * @param str
	 *            - String
	 */
	void gameOver(String str) {
		message.setText(str);
		gameInProgress = false;
	}

	/**
	 * This is called by mousePressed() when a player clicks on the square in
	 * the specified row and col. It has already been checked that a game is, in
	 * fact, in progress. If the player clicked on one of the pieces that the
	 * player can move, mark this row and col as selected and return. (This
	 * might change a previous selection.) Reset the message, in case it was
	 * previously displaying an error message.
	 * 
	 * @param row
	 *            - int
	 * @param col
	 *            - int
	 */
	void doClickToMove(int row, int col) {

		for (int i = 0; i < legalMoves.length; i++)
			if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
				selectedRow = row;
				selectedCol = col;
				if (currentPlayer == KodeKsData.RED)
					message.setText(Player1Name.getText() +": Make your move.");
				else
					message.setText(Player2Name.getText() +": Make your move.");
				repaint();
				return;
			}

		/*
		 * If no piece has been selected to be moved, the user must first select
		 * a piece. Show an error message and return.
		 */

		if (selectedRow < 0) {
			message.setText("Click the piece you want to move.");
			return;
		}

		/*
		 * If the user clicked on a square where the selected piece can be
		 * legally moved, then make the move and return.
		 */

		for (int i = 0; i < legalMoves.length; i++)
			if (legalMoves[i].fromRow == selectedRow
					&& legalMoves[i].fromCol == selectedCol
					&& legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
				doMakeMove(legalMoves[i]);
				return;
			}

		/*
		 * If we get to this point, there is a piece selected, and the square
		 * where the user just clicked is not one where that piece can be
		 * legally moved. Show an error message.
		 */

		message.setText("Click the square you want to move to.");

	} // end doClickSquare()

	/**
	 * This is called when the current player can take an opponents Piece.
	 * 
	 * @param take
	 *            - ThreatenStone
	 */
	void doTakeStone(ThreatenStone take) {

		boardState.takeStone(take);
		
		/* Make sure the board is redrawn in its new state. */

		repaint();
		
	} // end doTakeStone();

	/**
	 * This is called when the current player has chosen the specified move.
	 * Make the move, and then either end or continue the game appropriately.
	 * 
	 * @param move
	 *            - KodeKsMove
	 */
	void doMakeMove(KodeKsMove move) {

		boardState.makeMove(move);

		/*
		 * The current player's turn is ended, so change to the other player.
		 * Get that player's ThreatenStones, legal moves and the
		 * FoolStoneCombinations of the opponent. If the player has less the 5
		 * stones, then the game ends.
		 */
		Location start = new Location(move.fromRow, move.fromCol);
		Location end = new Location(move.toRow, move.toCol);
		GUI.analysePanel.updateAnalyse(start, end, currentPlayer);
		if (currentPlayer == KodeKsData.RED) {
			currentPlayer = KodeKsData.BLUE;
			getThreatenStones = boardState.getThreatenStone(currentPlayer);
			legalMoves = boardState.getLegalMoves(currentPlayer);
			NumberOfBlueStones = boardState.getNumberOfStones(BLUE);
			StatusPanel.bStones.setText("" + NumberOfBlueStones);
			if (NumberOfBlueStones <= 4)
				GUI.gameOver(RED);
			else
				message.setText(Player2Name.getText() +": Make your move.");
		} else {
			currentPlayer = KodeKsData.RED;
			getThreatenStones = boardState.getThreatenStone(currentPlayer);
			legalMoves = boardState.getLegalMoves(currentPlayer);
			NumberOfRedStones = boardState.getNumberOfStones(RED);
			StatusPanel.rStones.setText("" + NumberOfRedStones);
			if (NumberOfRedStones <= 4)
				GUI.gameOver(BLUE);
			else
				message.setText(Player1Name.getText() +": Make your move.");
		}

		/*
		 * Set selectedRow = -1 to record that the player has not yet selected a
		 * piece to move.
		 */

		selectedRow = -1;
		/* Make sure the board is redrawn in its new state. */

		repaint();

	} // end doMakeMove();

	/**
	 * (Draw a KodeKsboard pattern in gray and lightGray. Draw the pieces.) Load
	 * the images of the board and the pieces. Draw the names of rows and
	 * columns If a game is in progress, hilite the legal moves and the pieces
	 * to take
	 * 
	 * @param g
	 *            - Graphics
	 */

	public void paintComponent(Graphics g) {
		g.setColor(new Color(0, 150, 0));
		g.fillRect(65, 0, 1024, 768);
		/*
		 * Draw the Background of the board
		 */
		// g.setColor(Color.DARK_GRAY);
		// g.fillRect(120, 60, 520, 520);

		// load the image of the board
		ImageIcon board_game = new ImageIcon(getClass().getClassLoader()
				.getResource("game_board.png"));
		board_game.paintIcon(this, g, 63, 2);

		/* Draw the names of each row and col */
		int[] charOfCols = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (int col = 0; col < 10; col++) {
			g.setColor(new Color(42, 0, 0));
			Font coordinates = new Font("Gothic", Font.BOLD, 28);
			g.setFont(coordinates);
			g.drawString(String.valueOf(charOfCols[col]), 145 + col * 50, 57);
		}

		String[] charOfRows = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j" };
		for (int row = 0; row < 10; row++) {
			g.setColor(new Color(42, 0, 0));
			Font coordinates = new Font("Gothic", Font.BOLD, 28);
			g.setFont(coordinates);
			g.drawString(charOfRows[row], 95, 105 + row * 50);
		}

		/* Draw (the squares of the board and) the pieces. */
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {

				/*
				 * if( row % 2 == col % 2 ) g.setColor(Color.LIGHT_GRAY); else
				 * g.setColor(Color.GRAY);
				 * 
				 * g.fillRect(130 + col*50, 70 + row*50, 50, 50);
				 */

				/* Draw the Pieces on the board */
				switch (boardState.pieceAt(row, col)) {
				case RED:
					ImageIcon red = new ImageIcon(getClass().getClassLoader()
							.getResource("redStone.png"));
					red.paintIcon(this, g, 133 + col * 50, 77 + row * 50);
					// g.setColor(Color.RED);
					// g.fillOval(132 + col*50, 76 + row*50, 40, 40);
					break;
				case BLUE:
					ImageIcon blue = new ImageIcon(getClass().getClassLoader()
							.getResource("blueStone.png"));
					blue.paintIcon(this, g, 133 + col * 50, 77 + row * 50);
					// g.setColor(Color.BLUE);
					// g.fillOval(132 + col*50, 76 + row*50, 40, 40);
					break;
				}
			}
		}

		/*
		 * (Draw the fieldvalues on the board) Load the images of the
		 * fieldvalues and set them up like given in KodeKsData
		 */
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				/*
				 * g.setColor(Color.BLACK); Font value=new Font("Gothic",
				 * Font.BOLD, 24); g.setFont(value);
				 * g.drawString(String.valueOf(fieldvalue[row][col]), 145 +
				 * col*50, 105 + row*50);
				 */
				if (fieldvalue[row][col] == 1) {
					ImageIcon one = new ImageIcon(getClass().getClassLoader()
							.getResource("1.png"));
					one.paintIcon(this, g, 132 + col * 50, 76 + row * 50);
				}
				if (fieldvalue[row][col] == 2) {
					ImageIcon two = new ImageIcon(getClass().getClassLoader()
							.getResource("2.png"));
					two.paintIcon(this, g, 132 + col * 50, 76 + row * 50);
				}
				if (fieldvalue[row][col] == 3) {
					ImageIcon three = new ImageIcon(getClass().getClassLoader()
							.getResource("3.png"));
					three.paintIcon(this, g, 132 + col * 50, 76 + row * 50);
				}
				if (fieldvalue[row][col] == 4) {
					ImageIcon four = new ImageIcon(getClass().getClassLoader()
							.getResource("4.png"));
					four.paintIcon(this, g, 132 + col * 50, 76 + row * 50);
				}
			}
		}

		/*
		 * If a game is in progress, highlight the legal moves and the possible
		 * Pieces to take. Note that legalMoves is never null while a game is in
		 * progress.
		 */
		if (gameInProgress) {
			/*
			 * First, draw a 2-pixel red border around the pieces that can be
			 * taken.
			 */
			if (getThreatenStones != null) {
				g.setColor(Color.red);
				for (int i = 0; i < getThreatenStones.length; i++) {
					g.drawRect(130 + getThreatenStones[i].threatenCol * 50,
							74 + getThreatenStones[i].threatenRow * 50, 45, 45);
				}
			}
			/*
			 * Second, draw a 2-pixel cyan border around the pieces that can be
			 * moved.
			 */
			g.setColor(Color.cyan);
			for (int i = 0; i < legalMoves.length; i++) {
				g.drawRect(130 + legalMoves[i].fromCol * 50,
						74 + legalMoves[i].fromRow * 50, 45, 45);
			}
			/*
			 * If a piece is selected for moving (i.e. if selectedRow >= 0),
			 * then draw a 2-pixel white border around that piece and draw green
			 * borders around each square that that piece can be moved to.
			 */
			if (selectedRow >= 0) {
				g.setColor(Color.white);
				g.drawRect(130 + selectedCol * 50, 74 + selectedRow * 50, 45,
						45);

				g.setColor(Color.green);
				for (int i = 0; i < legalMoves.length; i++) {
					if (legalMoves[i].fromCol == selectedCol
							&& legalMoves[i].fromRow == selectedRow) {
						g.drawRect(130 + legalMoves[i].toCol * 50,
								74 + legalMoves[i].toRow * 50, 45, 45);
					}
				}
			}
		}
	} // end paintComponent()

	/**
	 * Respond to a user click on the board. If no game is in progress, show an
	 * error message. Otherwise, find the row and column that the user clicked
	 * and call doClickSquare() to handle it.
	 * 
	 * @param evt
	 *            - MouseEvent
	 */
	public void mousePressed(MouseEvent evt) {
		if (gameInProgress == false)
			message.setText("Click \"New Game\" to start a new game.");
		else {
			int col = (evt.getX() - 130) / 50;
			int row = (evt.getY() - 74) / 50;
			if (col >= 0 && col < 10 && row >= 0 && row < 10) {
				if (getThreatenStones != null) {
					for (int i = 0; i < getThreatenStones.length; i++) {
						if (getThreatenStones[i].threatenRow == row
								&& getThreatenStones[i].threatenCol == col)
							doTakeStone(getThreatenStones[i]);
					}
					legalMoves = boardState.getLegalMoves(currentPlayer);
				}
				doClickToMove(row, col);
			}
		}
	}

	public void mouseReleased(MouseEvent evt) {
	}

	public void mouseClicked(MouseEvent evt) {
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void updateBoard(KodeKsData boardState) {
		// TODO Auto-generated method stub
	}

} // end KodeKsBoard

