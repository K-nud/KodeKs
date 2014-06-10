import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.MatteBorder;


public class HelpMenuItem_HowToPlay {

	public HelpMenuItem_HowToPlay() {
	JFrame howToPlay=new JFrame();
	JPanel buttonPanel=new JPanel();
	buttonPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
	howToPlay.getContentPane().add(buttonPanel, BorderLayout.WEST);
	buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));
	
	
	howToPlay.getContentPane().add(buttonPanel, BorderLayout.CENTER);
}
}