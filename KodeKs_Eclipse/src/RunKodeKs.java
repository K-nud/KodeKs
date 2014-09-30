import java.awt.EventQueue;
import java.io.Serializable;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class RunKodeKs implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
static GUI mainGUI;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					mainGUI=new GUI(new KodeKsData());
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException | UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					SwingUtilities.updateComponentTreeUI(mainGUI);
					mainGUI.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
