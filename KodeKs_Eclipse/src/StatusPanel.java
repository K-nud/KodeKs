import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 * 
 * @author K. Vogel & B. Suhr
 * 
 */
public class StatusPanel extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static JLabel player1Name;
	protected static JLabel player2Name;
	protected static JLabel rStones;
	protected static JLabel bStones;

	int numberOfRedStones = 14;
	int numberOfBlueStones = 14;

	/**
	 * 
	 * @param boardstate
	 *            - KodeKsData
	 * @param parent
	 *            - GUI
	 * @param size
	 *            - Dimension
	 */
	public StatusPanel(KodeKsData boardstate, GUI parent, Dimension size) {

		setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));

		setLayout(new GridLayout(0, 1, 0, 0));
		JPanel messagePanel = new JPanel();
		add(messagePanel);
		messagePanel.add(KodeKsBoard.message);
		setPreferredSize(size);

		JPanel statPanel = new JPanel();
		add(statPanel);
		statPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel leftstat = new JPanel();
		player1Name = new JLabel("Player 1");
		ImageIcon iconP1 = new ImageIcon(getClass().getClassLoader().getResource("redStone.png"));
		JLabel P1Stat = new JLabel(iconP1, JLabel.LEFT);
		leftstat.add(P1Stat);
		leftstat.add(player1Name);
		JLabel P1StonesLeft = new JLabel("|  Stones left:", JLabel.LEFT);
		leftstat.add(P1StonesLeft);
		rStones = new JLabel("" + numberOfRedStones);
		leftstat.add(rStones);
		statPanel.add(leftstat);

		JPanel rightstat = new JPanel();
		player2Name = new JLabel("Player 2");
		ImageIcon iconP2 = new ImageIcon(getClass().getClassLoader().getResource("blueStone.png"));
		JLabel P2Stat = new JLabel(iconP2, JLabel.LEFT);
		rightstat.add(P2Stat);
		rightstat.add(player2Name);
		JLabel P2StonesLeft = new JLabel("|  Stones left:", JLabel.LEFT);
		rightstat.add(P2StonesLeft);
		bStones = new JLabel("" + numberOfRedStones);
		rightstat.add(bStones);
		statPanel.add(rightstat);

	}
}
