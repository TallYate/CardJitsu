package game.swing;

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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import game.Game;
import game.objects.Card;

public class TopPanel extends JPanel {
	private static final long serialVersionUID = 1114719476271848722L;
	public JLabel pSi, eSi, pSt, eSt, topText, midText;
	public JPanel playerScore = new JPanel(new GridLayout(1,2));
	public JPanel enemyScore = new JPanel(new GridLayout(1,2));
	public JPanel left = new JPanel(new GridLayout(1,3));
	public JPanel right = new JPanel(new GridLayout(1,3));
	public JLabel lPic, lText, rPic, rText;
	ImageIcon cards = new ImageIcon(Game.class.getResource("textures/cards.png"));

	public TopPanel() {
		this.setLayout(new GridBagLayout());

		gbc.insets = new Insets(2, 2, 2, 2);

		pSi = new JLabel(cards);
		
		eSi = new JLabel(cards);
		
		pSt = new JLabel("26", SwingConstants.CENTER);
		pSt.setFont(Fonts.BOLD.font);
		
		eSt = new JLabel("26", SwingConstants.CENTER);
		eSt.setFont(Fonts.BOLD.font);
		
		JLabel you = new JLabel("you", SwingConstants.LEFT);
		you.setFont(Fonts.TINY.font);
		
		JLabel enemy = new JLabel("enemy", SwingConstants.RIGHT);
		enemy.setFont(Fonts.TINY.font);
		
		playerScore.add(pSi);
		playerScore.add(pSt);
		playerScore.add(you);
		
		enemyScore.add(enemy);
		enemyScore.add(eSt);
		enemyScore.add(eSi);
		
		playerScore.setBackground(null);
		enemyScore.setBackground(null);
		
		this.add(playerScore, 0, 0);
		this.add(enemyScore, 4, 0);

		gbc.weightx = 1.0;
		topText = new JLabel();
		topText.setFont(Fonts.PHAT.font);
		topText.setVerticalAlignment(SwingConstants.BOTTOM);
		
		this.add(topText, 2, 0);

		gbc.weighty = 0.5;
		midText = new JLabel();
		midText.setFont(new Font("Courier", Font.BOLD, 96));
		this.add(midText, 1, 1, 3, 1);

		gbc.gridwidth = 1;
		gbc.weightx = 0.2;
		
		lPic = new JLabel((ImageIcon)null, SwingConstants.LEFT);
		lText = new JLabel();
		lText.setFont(Fonts.BIG_BOLD.font);
		left.add(lPic);
		left.add(lText);
		left.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		left.setPreferredSize(new Dimension(210, 120));
		
		this.add(left, 0, 1);
		
		rPic = new JLabel();
		rText = new JLabel();
		rText.setFont(Fonts.BIG_BOLD.font);
		right.add(rPic);
		right.add(rText);
		right.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		right.setPreferredSize(new Dimension(210, 120));
		this.add(right, 4, 1);
		
	}
	
	public void setCard(Card card, boolean player) {
		ImageIcon icon = new ImageIcon(Game.class.getResource("textures/" + card.type.toString() + 96 + ".png"));
		if(player) {
			lText.setText(Integer.toString(card.power));
			lPic.setIcon(icon);
		}
		else {
			rText.setText(Integer.toString(card.power));
			rPic.setIcon(icon);
		}
	}

	GridBagConstraints gbc = new GridBagConstraints();

	public <T extends JComponent> void add(T j, int x, int y, int width, int height) {
		gbc.gridwidth = width;
		gbc.gridheight = height;
		this.add(j, x, y);
	}

	public <T extends JComponent> void add(T j, int x, int y) {
		gbc.gridx = x;
		gbc.gridy = y;
		this.add(j, gbc);
	}
}
