import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;


/**
 * The menubar of the mainframe
 * 
 * @author K. Vogel & B. Suhr
 *
 */
public class KodeKsMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 42L;
	
 public KodeKsMenuBar(final GUI parent){
	 		
		/*
		 * GameMenu
		 */
		JMenu GameMenu = new JMenu("Game");
		
		/*
		 * GameMenu-Items
		 */
		
		//NEWGAME
		JMenuItem GameMenuItem_NGame = new JMenuItem("New Game");
		GameMenuItem_NGame.addActionListener(new ActionListener(){
	       	 public void actionPerformed(ActionEvent e){
	       		 		 new NewGameDialog();	       	}
	       	 });
		GameMenu.add(GameMenuItem_NGame);
		JSeparator separator = new JSeparator();
		GameMenu.add(separator);
		
		//LOAD
		JMenuItem GameMenuItem_Load = new JMenuItem("Load...");
		GameMenuItem_Load.addActionListener(new ActionListener(){
     	 public void actionPerformed(ActionEvent e){
     		new LoadGame();
     	        }
     	 
		});
		GameMenu.add(GameMenuItem_Load);
		
		//SAVE
		JMenuItem GameMenuItem_Save = new JMenuItem("Save...");
		GameMenuItem_Save.addActionListener(new ActionListener(){
    	 public void actionPerformed(ActionEvent e){
    	        new SaveGame();
    	 }
		});
		GameMenu.add(GameMenuItem_Save);
		
		JSeparator separator_1 = new JSeparator();
		GameMenu.add(separator_1);
		
		//QUIT
		JMenuItem GameMenuItem_Quit = new JMenuItem("Quit");
		GameMenuItem_Quit.setActionCommand("-11");
		GameMenuItem_Quit.addActionListener(parent);
		GameMenu.add(GameMenuItem_Quit);
		
		/*
		 * OptionsMenu
		 */
		JMenu OptionsMenu = new JMenu("Options");
			
		//OptionsMenuItem to choose the layout of the Frame and its items
		
		OptionsMenu.add(new LayoutChooser(OptionsMenu));
		
		JSeparator separator_2 = new JSeparator();	
		OptionsMenu.add(separator_2);
		
		
		final JCheckBoxMenuItem OptionsMenuItem_ToolBar = new JCheckBoxMenuItem("Toolbar");
		OptionsMenuItem_ToolBar.setState(true);
		OptionsMenuItem_ToolBar.addActionListener(new ActionListener(){
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (OptionsMenuItem_ToolBar.getState()==false)
					parent.toolBar.setVisible(false);
				else parent.toolBar.setVisible(true);
		}});
		OptionsMenu.add(OptionsMenuItem_ToolBar);
		
		
		/*
		 * HelpMenu -> Informations about the authors and playing rules
		 */
		JMenu HelpMenu = new JMenu("Help");
				
		//HelpMenuItem_HowToPlay
		JMenuItem HelpMenuItem_HowToPlay = new JMenuItem("How to play");
		HelpMenuItem_HowToPlay.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
					new HowToPlayFrame();;
					}
					});
		HelpMenu.add(HelpMenuItem_HowToPlay);
		
		//HelpMenuItem_About
		JMenuItem HelpMenuItem_About = new JMenuItem("About");
		HelpMenuItem_About.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
					new About();;
					}
					});
		HelpMenu.add(HelpMenuItem_About);
		
		add(GameMenu);
		add(OptionsMenu);
		add(HelpMenu);
 }
}
