import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * 
 * @author K. Vogel & B. Suhr
 *
 */
public class KodeKsToolBar extends JToolBar{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param listener
	 * 				- GUI
	 * @param size
	 * 				- Dimension
	 */
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
		
		
		
		
		

		setFloatable(false);
		add(ToolBarItem_NGame);
		add(ToolBarItem_Load);
		add(ToolBarItem_Save);
		add(ToolBarItem_Resign);
		
		add(Box.createHorizontalGlue());
		
		
	}
}
