import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class LayoutChooser extends AbstractAction implements ActionListener, Action{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public LayoutChooser(JMenu mother){
		
		JMenu frameLayout = new JMenu("Layout");
		
		JRadioButtonMenuItem frameLayout_System = new JRadioButtonMenuItem("system");
		frameLayout_System.setActionCommand("system");
		frameLayout_System.setSelected(true);
		
		JRadioButtonMenuItem frameLayout_Motif = new JRadioButtonMenuItem("motif");
		frameLayout_Motif.setActionCommand("motif");
		
		JRadioButtonMenuItem frameLayout_Metal = new JRadioButtonMenuItem("metal");
		frameLayout_Metal.setActionCommand("metal");
		
		JRadioButtonMenuItem frameLayout_Nimbus = new JRadioButtonMenuItem("nimbus");
		frameLayout_Nimbus.setActionCommand("nimbus");
		
		
	    //Group the radio buttons.
	    ButtonGroup chooseLayout = new ButtonGroup();
	    chooseLayout.add(frameLayout_System);
	    chooseLayout.add(frameLayout_Motif);
	    chooseLayout.add(frameLayout_Metal);
	    chooseLayout.add(frameLayout_Nimbus);	  
	    
	    //Register a listener for the radio buttons.
	    frameLayout_System.addActionListener(this);
	    frameLayout_Motif.addActionListener(this);
	    frameLayout_Metal.addActionListener(this);
	    frameLayout_Nimbus.addActionListener(this);
	    
	    // Add to MenuItem
	    frameLayout.add(frameLayout_System);
	    frameLayout.add(frameLayout_Motif);
	    frameLayout.add(frameLayout_Metal);
	    frameLayout.add(frameLayout_Nimbus);
	    
	    mother.add(frameLayout);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand()=="system")
			try {
				 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		if (e.getActionCommand()=="metal")
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		if (e.getActionCommand()=="motif")
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		if (e.getActionCommand()=="nimbus")
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		  SwingUtilities.updateComponentTreeUI(RunKodeKs.mainGUI);
		  
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void putValue(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setEnabled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
