import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Move extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
		// Initialisierung der Variablen
		
		static int x_start = 0;		// x - Position des Balles
		static int y_start = 0;
		static int x_end = 0;		// x - Position des Balles
		static int y_end = 0;
		// y - Position des Balles
		static int player=0;
		int i=1;
		int m=0;
		Thread moving;
		
	public void getm(){
		if (x_start!=x_end)
			 m=(y_start-y_end / x_start-x_end); //Steigung berechnen
	}
	
	
		public void start(){
			getm();
			if (moving == null){
				moving = new Thread(this);
				moving.start();
			}
		};
		
		public void stop(){
			moving=null;
		}
		
	
		public void run () {
		// Solange true ist läuft der Thread weiterg
			
		if (x_start == x_end){
			if (y_start < y_end){
				while(y_start == y_end){ 				  
					y_start++;
					repaint();
				}
			}
			if (y_start > y_end){
				while(y_start == y_end){
					y_start--;
					repaint();
				}
			}	    		  
		}
		else if (y_start == y_end){
			if (x_start < x_end){
				while(x_start == x_end){
					x_start++;
					repaint();
				}
			}
			if (x_start > x_end){
				while(x_start == x_end){
					x_start--;
					repaint();
				}
			}	    		  
		}
		else if (x_start < x_end){
			if (y_start < y_end){
				while(y_start == y_end){
					x_start++;
					y_start=x_start+i*m;
					repaint();  
				}
			}
			if (y_start > y_end){
				while(y_start == y_end){
					x_start++;
					y_start=x_start-i*m;
					repaint();  
				}
			}
		}
		else if (y_start < y_end){
			if (x_start < x_end){
				while(y_start == y_end){
					y_start++;
					x_start=y_start+i*m;
					repaint();  
				}
			}
			if (x_start > x_end){
				while(y_start == y_end){
					y_start++;
					x_start=y_start-i*m;
					repaint();  
				}
			}
		}
		
		try
		{
			// Stoppen des Threads für in Klammern angegebene Millisekunden
			Thread.sleep (20);
				}
			catch (InterruptedException ex)
			{	
				// do nothing
			}

		}


	public void paint (Graphics g) {
		ImageIcon rollingStone=new ImageIcon();
		ImageIcon redIcon=new ImageIcon(getClass().getClassLoader().getResource("redStone.png"));
		ImageIcon blueIcon=new ImageIcon(getClass().getClassLoader().getResource("blueStone.png"));
		if (player == 1)
			rollingStone=redIcon;
		else rollingStone=blueIcon;
		
		rollingStone.paintIcon(KodeKsBoard.board, g, 133 + x_start*50, 77 + y_start*50);
		
		
	}
	

}
