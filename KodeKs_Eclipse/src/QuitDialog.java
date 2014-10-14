import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 
 * @author K. Vogel & B. Suhr
 *
 */
 public class QuitDialog extends PopupWindow implements ActionListener{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel text;
	private JButton yes, no;
	
	/**
	 * 
	 * @param parent
	 * 				- GUI
	 */
	public QuitDialog(GUI parent){

			super(parent, "Quit?");
			
			this.parent = parent;
			
			text = new JLabel("Are you sure you want to exit KodeKs?");
			no = new JButton("No");
			no.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dispose();
					}
			});
			yes = new JButton("Yes");
			yes.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					RunKodeKs.mainGUI.dispose();
					dispose();
					}
			});
			
			JPanel top = new JPanel();
			top.setBackground(new Color(0,0,0,0));
			top.add(text);
			JPanel bottom = new JPanel();
			bottom.setBackground(new Color(0,0,0,0));
			bottom.add(no);
			bottom.add(yes);
			add(top);
			add(bottom);
			
			pack();
			resetLocation();
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
 }
 
 