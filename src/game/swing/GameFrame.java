package game.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import game.Game;
import game.objects.Card;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 6289970174692073519L;

	JButton credit = new CreditButton("credit");
	public TopPanel top = new TopPanel();
	JPanel panel = new JPanel(new GridBagLayout());
	JPanel cards = new JPanel(new GridLayout(0, 10, 2, 2));
	JMenuBar bar = new JMenuBar();

	public AchievementPanel achievements = new AchievementPanel();

	Game game;
	GridBagConstraints gbc = new GridBagConstraints();

	JLabel padding = new JLabel();

	public GameFrame(Game game, String title) {
		super(title);

		this.game = game;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 600);
		this.setMinimumSize(new Dimension(1200, 400));
		this.setMaximumSize(new Dimension(9600, 3200));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0D;
		gbc.weighty = 1.0D;
		top.setBackground(new Color(180, 180, 150));
		panel.add(top, gbc);

		gbc.gridy = 1;
		gbc.weighty = 0.0D;

		cards.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		cards.setBackground(null);
		panel.setBackground(Color.GRAY);
		panel.add(cards, gbc);

		gbc.gridy = 2;
		padding.setBackground(null);
		padding.setPreferredSize(new Dimension(0, 0));
		panel.add(padding, gbc);

		// bar.setLayout(null);
		bar.add(credit);

		this.setJMenuBar(bar);
		this.add(panel);

		achievements.setBounds(67, 0, this.getMaximumSize().width, 27);
		this.getLayeredPane().add(achievements, new Integer(10), 100);

		this.loadCards();
		this.setVisible(true);
	}

	public void loadCards() {
		cards.removeAll();
		this.revalidate();
		for (int i = 0; i < game.player.size; i++) {
			if (game.player.cards[i] != null) {
				Card entry = game.player.cards[i];
				JButton button = new JButton();
				button.setLayout(new GridLayout(1, 2));

				switch (i + 1) {
				case 1:
					button.setMnemonic(KeyEvent.VK_1);
					break;
				case 2:
					button.setMnemonic(KeyEvent.VK_2);
					break;
				case 3:
					button.setMnemonic(KeyEvent.VK_3);
					break;
				case 4:
					button.setMnemonic(KeyEvent.VK_4);
					break;
				case 5:
					button.setMnemonic(KeyEvent.VK_5);
					break;
				case 6:
					button.setMnemonic(KeyEvent.VK_6);
					break;
				case 7:
					button.setMnemonic(KeyEvent.VK_7);
					break;
				case 8:
					button.setMnemonic(KeyEvent.VK_8);
					break;
				case 9:
					button.setMnemonic(KeyEvent.VK_9);
					break;
				case 10:
					button.setMnemonic(KeyEvent.VK_0);
					break;
				}

				if (i < 10) {
					button.setBackground(new Color(255, 235, 100));
				} else {
					button.setBackground(new Color(240, 245, 250));
				}

				button.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

				JLabel image = new JLabel(
						new ImageIcon(Game.class.getResource("textures/" + entry.type.toString() + ".png")),
						SwingConstants.LEFT);
				JLabel label = new JLabel(entry.power + " ", SwingConstants.RIGHT);
				label.setFont(Fonts.BOLD.font);

				button.setActionCommand(Integer.toString(i));
				button.addActionListener(game);

				button.setPreferredSize(new Dimension(100, 60));

				button.add(image);
				button.add(label);

				cards.add(button);
				}
		}
		
		if (game.player.size % 10 == 0) {
			padding.setPreferredSize(new Dimension(1, 50));
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
		}
		
	}

	ActionListener tp1 = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			padding.setPreferredSize(new Dimension(1, 40));
			cards.revalidate();
		}
	};

	ActionListener tp2 = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			padding.setPreferredSize(new Dimension(1, 30));
			cards.revalidate();
		}
	};

	ActionListener tp3 = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			padding.setPreferredSize(new Dimension(1, 20));
			cards.revalidate();
		}
	};

	ActionListener tp4 = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			padding.setPreferredSize(new Dimension(1, 10));
			cards.revalidate();
		}
	};

	ActionListener tp5 = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			padding.setPreferredSize(new Dimension(1, 0));
			cards.revalidate();
		}
	};

	Timer t1 = new Timer(100, tp1);
	Timer t2 = new Timer(200, tp2);
	Timer t3 = new Timer(300, tp3);
	Timer t4 = new Timer(400, tp4);
	Timer t5 = new Timer(500, tp5);

	{
		t1.setRepeats(false);
		t2.setRepeats(false);
		t3.setRepeats(false);
		t4.setRepeats(false);
		t5.setRepeats(false);
	}
}
