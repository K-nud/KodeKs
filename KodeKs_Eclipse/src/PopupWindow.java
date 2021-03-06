import java.awt.*;

import javax.swing.*;

/**
 * 
 * @author K. Vogel & B. Suhr
 * 
 */
public class PopupWindow extends JDialog {
	private static final long serialVersionUID = -6382879648233899539L;

	private Point parentLoc;
	GUI parent;

	/**
	 * 
	 * @param parent
	 *            - GUI
	 * @param title
	 *            - String
	 */
	public PopupWindow(GUI parent, String title) {
		super(parent, title);
		this.parent = parent;
		setSize(300, 100);
		if (parent.isVisible()) {
			parentLoc = parent.getLocationOnScreen();
			setLocation((int) parentLoc.getX() + (parent.getWidth() / 2 - getSize().width / 2), (int) parentLoc.getY()
					+ (parent.getHeight() / 2 - getSize().height / 2));
		}
		setResizable(false);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	/**
	 * 
	 */
	public void resetLocation() {
		if (parent.isVisible())
			setLocation((int) parentLoc.getX() + (parent.getWidth() / 2 - getSize().width / 2), (int) parentLoc.getY()
					+ (parent.getHeight() / 2 - getSize().height / 2));
	}
}