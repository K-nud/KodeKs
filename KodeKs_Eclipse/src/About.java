import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;


/**
 * Dialog shows informations about the authors and the version of the 
 * implementation of KodeKs
 * @author K. Vogel & B. Suhr
 */
public class About extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel insetsPanel1 = new JPanel();
	JPanel insetsPanel2 = new JPanel();
	JPanel insetsPanel3 = new JPanel();
	JButton close = new JButton();
	JLabel imageLabel = new JLabel();
	JLabel appNameTextfield = new JLabel();
	JLabel versionTextfield = new JLabel();
	JLabel copyrightTextfield = new JLabel();
	JLabel authorTextfield = new JLabel();
	BorderLayout borderLayout1 = new BorderLayout();
	BorderLayout borderLayout2 = new BorderLayout();
	FlowLayout flowLayout1 = new FlowLayout();
	String about = "About KodeKs";
	String product = "KodeKs";
	String version = "Version 1.0";
	String copyright = "Copyright (c) 2014 by";
	String comments = "Knud Vogel & Benjamin Suhr";
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	
	/**
	* Dialog About
	* 
	*/
	public About() {
	super();
	enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	try {
	initialize();
	}
	catch(Exception e) {
	e.printStackTrace();
	}
	pack();
	setVisible(true);
	}
	
	//Initialise the components
	private void initialize() throws Exception {
	this.setTitle("About KodeKs");
	this.setBounds(325,360, 200, 200);
	setResizable(false);
	panel1.setLayout(borderLayout1);
	panel2.setLayout(borderLayout2);
	insetsPanel1.setLayout(flowLayout1);
	insetsPanel2.setLayout(flowLayout1);
	insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	insetsPanel2.setMinimumSize(new Dimension(60, 47));
	insetsPanel2.setPreferredSize(new Dimension(60, 47));
	appNameTextfield.setText(product);
	versionTextfield.setText(version);
	copyrightTextfield.setText(copyright);
	authorTextfield.setText(comments);
	insetsPanel3.setLayout(gridBagLayout1);
	insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
	insetsPanel3.setMinimumSize(new Dimension(200, 88));
	insetsPanel3.setPreferredSize(new Dimension(200, 88));
	close.setText("close");
	close.addActionListener(this);
	panel1.setMinimumSize(new Dimension(200, 125));	
	this.getContentPane().add(panel1, null);
	insetsPanel3.add(appNameTextfield, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,	GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 35, 0, 0), 181, 0));
	insetsPanel3.add(versionTextfield, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 35, 0, 0), 163, 0));
	insetsPanel3.add(copyrightTextfield,
	new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 35, 0, 0), 143, 0));
	insetsPanel3.add(authorTextfield, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 35, 0, 0), 171, 0));
	panel2.add(insetsPanel2, BorderLayout.WEST);
	insetsPanel2.add(imageLabel, null);
	panel2.add(insetsPanel3, BorderLayout.CENTER);
	insetsPanel1.add(close, null);
	panel1.add(insetsPanel1, BorderLayout.SOUTH);
	panel1.add(panel2, BorderLayout.NORTH);
	}
	
	//overwrite to close the window
	protected void processWindowEvent(WindowEvent e) {
	if (e.getID() == WindowEvent.WINDOW_CLOSING) {
	cancel();
	}
	super.processWindowEvent(e);
	}
	//close dialog
	void cancel() {
	dispose();
	}
	//close dialog after actionevent
	public void actionPerformed(ActionEvent e) {
	if (e.getSource() == close) {
	cancel();
	}
	}

}
