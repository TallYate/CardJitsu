package game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import game.Game.Side.Entry;

public class TopPanel extends JPanel {
	private static final long serialVersionUID = 1114719476271848722L;
	public JLabel pSi, eSi, pSt, eSt, topText, midText;
	public JPanel left = new JPanel(new GridLayout(1,2));
	public JPanel right = new JPanel(new GridLayout(1,2));
	public JLabel lPic, lText, rPic, rText;
	ImageIcon cards = new ImageIcon(this.getClass().getResource("textures/cards.png"));

	
	public static final Font PLAIN = new Font("Courier", Font.PLAIN, 24);
	public static final Font BOLD = new Font("Courier", Font.BOLD, 24);
	public static final Font BIG_BOLD = new Font("Courier", Font.BOLD, 50);
	public TopPanel() {
		setLayout(new GridBagLayout());

		gbc.insets = new Insets(2, 2, 2, 2);

		pSi = new JLabel(cards);
		add(pSi, 0, 0);

		pSt = new JLabel("26 [you]");
		pSt.setFont(BOLD);
		add(pSt, 1, 0);

		eSt = new JLabel("26");
		eSt.setFont(BOLD);
		add(eSt, 3, 0);

		eSi = new JLabel(cards);
		add(eSi, 4, 0);

		gbc.weightx = 1.0;
		topText = new JLabel();
		topText.setFont(new Font("Courier", Font.BOLD, 60));
		add(topText, 2, 0);

		gbc.weighty = 0.5;
		midText = new JLabel();
		midText.setFont(new Font("Courier", Font.BOLD, 96));
		add(midText, 1, 1, 3, 1);

		gbc.gridwidth = 1;
		gbc.weightx = 0.2;
		
		lPic = new JLabel();
		lText = new JLabel();
		lText.setFont(BIG_BOLD);
		left.add(lPic);
		left.add(lText);
		left.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		left.setPreferredSize(new Dimension(210, 120));
		add(left, 0, 1);
		
		rPic = new JLabel();
		rText = new JLabel();
		rText.setFont(BIG_BOLD);
		right.add(rPic);
		right.add(rText);
		right.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		right.setPreferredSize(new Dimension(210, 120));
		add(right, 4, 1);
		
	}
	
	public void setEntry(Entry entry, boolean player) {
		ImageIcon icon = new ImageIcon(this.getClass().getResource("textures/" + entry.card.toString() + 96 + ".png"));
		if(player) {
			lText.setText(Integer.toString(entry.power));
			lPic.setIcon(icon);
		}
		else {
			rText.setText(Integer.toString(entry.power));
			rPic.setIcon(icon);
		}
	}

	GridBagConstraints gbc = new GridBagConstraints();

	public <T extends JComponent> void add(T j, int x, int y, int width, int height) {
		gbc.gridwidth = width;
		gbc.gridheight = height;
		add(j, x, y);
	}

	public <T extends JComponent> void add(T j, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		add(j, gbc);
	}
}
