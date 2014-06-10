//import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class KodeKsToolBar extends JToolBar{
	
	private static final long serialVersionUID = 1L;
	
	public static JLabel timeLabel;
	public static JButton toolBarItem_PauseResume;
	public static GameTimer t = new GameTimer();
	
	public KodeKsToolBar(GUI listener, Dimension size){
		
		setPreferredSize(size);
		setOpaque(true);
		setBorder(BorderFactory.createBevelBorder(VERTICAL));
		
		JButton ToolBarItem_NGame = new JButton("   New Game   ");
		ToolBarItem_NGame.setPreferredSize(new Dimension(128, size.height));
		ToolBarItem_NGame.setBorder(BorderFactory.createRaisedBevelBorder());
		ToolBarItem_NGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new NewGameDialog();
				GameTimer.resetRunning();
			}
       	});
		
		add(ToolBarItem_NGame);
	
		JButton ToolBarItem_Load = new JButton("   Load   ");
		ToolBarItem_Load.setPreferredSize(new Dimension(128, size.height));
		ToolBarItem_Load.setBorder(BorderFactory.createRaisedBevelBorder());
		ToolBarItem_Load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new LoadGame();

			}
		});
		
	
		JButton ToolBarItem_Save = new JButton("   Save   ");
		ToolBarItem_Save.setPreferredSize(new Dimension(128, size.height));
		ToolBarItem_Save.setBorder(BorderFactory.createRaisedBevelBorder());
		ToolBarItem_Save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new SaveGame();
				
			}
       	});
		
	
		JButton ToolBarItem_Resign = new JButton("   Resign   ");
		ToolBarItem_Resign.setPreferredSize(new Dimension(128, size.height));
		ToolBarItem_Resign.setBorder(BorderFactory.createRaisedBevelBorder());
		ToolBarItem_Resign.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new ResignDialog();
			}
		});
		
		
		
		
		toolBarItem_PauseResume = new JButton("   Pause   ");
		toolBarItem_PauseResume.setPreferredSize(new Dimension(128, size.height));
		toolBarItem_PauseResume.setBorder(BorderFactory.createRaisedBevelBorder());
		toolBarItem_PauseResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameTimer.setRunning();
			}
		});
		
		timeLabel = new JLabel("  " + GameTimer.currentTime + "  ");		
		t.start();

		setFloatable(false);
		add(ToolBarItem_NGame);
		add(ToolBarItem_Load);
		add(ToolBarItem_Save);
		add(ToolBarItem_Resign);
		
		add(Box.createHorizontalGlue());
		add(toolBarItem_PauseResume);
		add(timeLabel);
	}
}
