import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;


/**
 * The mainframe of the GUI contains a menubar, a toolbar, a panel on which the board is displayed, etc.
 * 
 * @author K. Vogel & B. Suhr
 *
 */
public class GUI extends JFrame implements ActionListener,MouseListener, Serializable {
	
	private static final long serialVersionUID = 42;
	
	public static JFrame kodeksGUI;
	
	static JToolBar toolBar;
	
	private static GameOverPopup gameOver;
	
	private QuitDialog quitDlg;
	
	static KodeKsBoard board;
	
	static StatusPanel statusPanel;
	
	static AnalysePanel analysePanel;
		
	/**
	 * Construct a GUI to play KodeKs. It gets his data by the Board_At_Start of KodeKsData.
	 * The mainframe of the GUI contains a menubar, a toolbar, a panel on which the board is displayed, an AnaLysePanel to show 
	 * the maked moves during the game and a StatusPanel which displays messages (e.g. who's turn it is).
	 * 
	 * @param boardState 
	 * 				- KodeKsData
	 */
	public GUI(KodeKsData boardState) {
		
		super("KodeKs");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension boardSize = new Dimension(824,768);
		Dimension toolBarSize = new Dimension(1024,24);
		Dimension analysePanelSize = new Dimension(275,760);
		Dimension statusPanelSize = new Dimension(755, 110);
		
	
		
//"this" Main Frame***********************************************************
		setLayout(new BorderLayout());
		setResizable(false);
//****************************************************************************

/**
* Initialize the contents of the frame.
* 
*/
	
//Menubar***************************************************************************
		KodeKsMenuBar menuBar = new KodeKsMenuBar(this);
	    setJMenuBar(menuBar);
//**********************************************************************************
	    
//ToolBar****************************************************************
	    toolBar = new KodeKsToolBar(this, toolBarSize);
	  	add(toolBar,BorderLayout.NORTH);
//****************************************************************************
	  	
//Game Board******************************************************************
	  	board = new KodeKsBoard(boardState, this, boardSize);
	  	add(board,BorderLayout.CENTER);
//****************************************************************************
		  
//StatusPanel******************************************************************
	   	statusPanel = new StatusPanel(boardState, this, statusPanelSize);
	  	board.add(statusPanel,BorderLayout.SOUTH);
//****************************************************************************	
	  	  	
//AnalysePanel******************************************************************
	  	analysePanel = new AnalysePanel(analysePanelSize);
	  	add(analysePanel,BorderLayout.EAST);
//****************************************************************************	


	    setLocation(screen.width/2-512, screen.height/2-512);
		repaint();
		addMouseListener(this);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			
			e.printStackTrace();
		}

		pack();
	}
	

	public KodeKsBoard getKodeKsBoard()
	{
		return board;
	}
	
	public StatusPanel getStatusPanel()
	{
		return statusPanel;
	}
	
	public AnalysePanel getAnalysePanel()
	{
		return analysePanel;
	}
	
	/**
	 * 
	 * @param loc
	 * 			- Location
	 * @return
	 */
	public Location processLocation(Location loc)
	{
			return loc;
	}
	
	public void quitDlg() {
		quitDlg = new QuitDialog(this);
		setEnabled(false);
		quitDlg.setVisible(true);
	}
	
	/**
	 * 
	 * @param outcome
	 * 				- int
	 */
	public static void gameOver(int outcome)
	{
		if(outcome==1)
			gameOver = new GameOverPopup(RunKodeKs.mainGUI, "Red Win");
		else if(outcome==2)
			gameOver = new GameOverPopup(RunKodeKs.mainGUI, "Blue Win");
		board.gameInProgress=false;
		gameOver.setVisible(true);
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {		
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {	
	}
}
	
