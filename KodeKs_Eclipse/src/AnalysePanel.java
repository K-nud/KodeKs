import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.Serializable;


/**
 * Table of the moves each player did
 *  
 * @author K. Vogel & B. Suhr 
 * 
 */
public class AnalysePanel extends JPanel implements Serializable
{
	private static final long serialVersionUID = 266407155639L;
	
	private static JPanel analysePanel;
	private static ArrayList<JLabel> analyse;
	private static JLabel analyseLabel;
	protected static JLabel Player1Name;
	protected static JLabel Player2Name;
	protected static JScrollPane analyseScroll;
	
	/**
	 * Constructor of an analyse panel.
	 * Here the players can see which moves they made during the game
	 * 
	 * @param size - Dimension
	 * 
	 */
	public AnalysePanel(Dimension size)
	{
		analyse = new ArrayList<JLabel>();
		analyseLabel = new JLabel();
		setPreferredSize(size);
		setOpaque(true);
		setBackground(new Color(51,102,255));
		
		JLabel analyseTitle = new JLabel("<-- KODEKS -->", JLabel.CENTER);
		add(analyseTitle);
		analyseTitle.setPreferredSize(new Dimension(size.width-5,38));
		analyseTitle.setFont(new Font("Courier", analyseTitle.getFont().getStyle(), 20));
		analyseTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		analyseTitle.setOpaque(true);
		analyseTitle.setBackground(new Color(0,150,0));
		
		JPanel redblue = new JPanel();
		redblue.setLayout(new GridLayout(0, 3, 0, 0));
		Player1Name=new JLabel("Player 1");
		JLabel vs=new JLabel("      vs.");
		Player2Name=new JLabel("Player 2");
		
		add(redblue);
		redblue.add(Player1Name);
		redblue.add(vs);
		redblue.add(Player2Name);
		redblue.setBackground(new Color(51,102,255));
		redblue.setPreferredSize(new Dimension(275,16));
		
		analysePanel = new JPanel();
		add(analysePanel);
		analysePanel.setLayout(new GridLayout(0,1,0,0));

		analyseScroll = new JScrollPane(analysePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(analyseScroll);
		analyseScroll.setPreferredSize(new Dimension(size.width, size.height));
		
	}
	
	/**
	 * Method to update the Analysepanel after each turn.
	 * 
	 * @param start - Location
	 * @param end - Location
	 * @param player - int
	 */
	public void updateAnalyse(Location start, Location end, int player)
	{
		unfillAnalyse();
		String temp = analyseLabel.getText();
		if(player==1)
		{
			analyseLabel = new JLabel(" ", JLabel.LEFT);
			analyseLabel.setOpaque(true);
			analyseLabel.setFont(new Font("Monospaced", analyseLabel.getFont().getStyle(), analyseLabel.getFont().getSize()));
			analyseLabel.setForeground(Color.BLACK);
			if(analyse.size()%2==0)
				analyseLabel.setBackground(Color.WHITE);
			else
				analyseLabel.setBackground(Color.LIGHT_GRAY);
			analyse.add(analyseLabel);
			if(analyse.size()>99)
				analyseLabel.setText(analyse.size()+"."+start+" "+end+" :          ");
			else if(analyse.size()>9)
				analyseLabel.setText(analyse.size()+". "+start+" "+end+"  :         ");
			else
				analyseLabel.setText(analyse.size()+".  "+start+" "+end+"  :         ");
		}
		else
		{
			if(analyse.size()>99)
				analyseLabel.setText(temp.trim()+"  "+start+" "+end+"  ");
			else if(analyse.size()>9)
				analyseLabel.setText(temp.trim()+"  "+start+" "+end+"  ");
			else
				analyseLabel.setText(temp.trim()+"  "+start+" "+end+"  ");
		}
		fillAnalyse();
		redisplayAnalyse();
	}
	
	/**
	 * Method to redisplay the AnalysePanel
	 * 
	 */
	public static void redisplayAnalyse()
	{
		analysePanel.removeAll();
		for(JLabel l : analyse)
			analysePanel.add(l);
	}
	
		
	/**
	 * Method to fill the AnalysePanel
	 * 
	 */
	public static void fillAnalyse()
	{
		while(analyse.size()<60)
		{
			JLabel fillLabel = new JLabel(" ", JLabel.LEFT);
			analyse.add(fillLabel);
			if(analyse.size()>9)
				fillLabel.setText(analyse.size()+".                  ");
			else
				fillLabel.setText(analyse.size()+".                   ");
			fillLabel.setOpaque(true);
			fillLabel.setFont(new Font("Monospaced", fillLabel.getFont().getStyle(), fillLabel.getFont().getSize()));
			fillLabel.setForeground(Color.BLACK);
			if((analyse.size()-1)%2==0)
			{
				fillLabel.setBackground(Color.WHITE);
			}
			else
			{
				fillLabel.setBackground(Color.LIGHT_GRAY);
			}
		}
	}
	
	/**
	 * Method to unfill the AnalysePanel
	 * 
	 */
	public static void unfillAnalyse()
	{
		for(int x=0; x<analyse.size(); x++)
		{
			String temp = analyse.get(x).getText().substring(analyse.get(x).getText().indexOf(".")+1).trim();
			if(temp.length()==0)
			{
				analyse.remove(x);
				x--;
			}
		}
	}
	
	/**
	 * Method to clear the AnalysePanel
	 * 
	 */
	public void clearAnalyse()
	{
		analyse = new ArrayList<JLabel>();
		fillAnalyse();
		redisplayAnalyse();
	}
}