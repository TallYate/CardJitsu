package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Game.Side.Entry;

public class Game implements ActionListener {

	public Random random = new Random();
	public Side player = new Side();
	public Side enemy = new Side();

	JFrame frame = new JFrame("Card Jitsu");
	JPanel panel = new JPanel(new GridBagLayout());
	JPanel cards = new JPanel(new GridLayout(0, 10, 2, 2));
	TopPanel top = new TopPanel();	//	extends JPanel
	boolean stopped = false;
	
	JButton credit = new JButton("credit");
	
	public Game()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 600);
		frame.setMinimumSize(new Dimension(1200, 400));
		
		credit.setActionCommand("credit");
		credit.addActionListener(this);
		credit.setBorderPainted(false);
		credit.setBackground(new Color(150, 150, 121));
		panel.add(credit);
		
		panel.setBackground(new Color(150, 150, 120));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0D;
		gbc.weighty = 1.0D;
		gbc.gridx = 0;
		gbc.gridy = 0;
		top.setBackground(new Color(180,180,150));
		panel.add(top, gbc);
		
		gbc.gridy = 1;
		gbc.weighty = 0.0D;
		
		panel.add(cards, gbc);
		cards.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		cards.setBackground(Color.GRAY);
		frame.add(panel);
		
		this.loadCards();
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Game();
	}
	
	boolean test = false;

	public void loadCards() {
		cards.removeAll();
		frame.revalidate();
		for (int i = 0; i < player.entries.length; i++) {
			if (player.entries[i] != null) {
				Entry entry = player.entries[i];
				JButton button = new JButton();
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
					button.setBackground(Color.YELLOW);
				}
				button.setActionCommand(Integer.toString(i));
				button.addActionListener(this);
				button.setIcon(
						new ImageIcon(this.getClass().getResource("textures/" + entry.card.toString() + ".png")));
				button.setText(Integer.toString(entry.power));
				button.setFont(TopPanel.BOLD);
				cards.add(button);
			}
		}
		
		frame.setVisible(true);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("credit")){
			try {
				System.out.println("credit");
				new CreditMenu();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return;
		}
		else if(stopped) {
			return;
		}
		int playerIndex = Integer.parseInt(e.getActionCommand());
		Entry playerEntry = player.entries[playerIndex];

		int enemyIndex = random.nextInt(enemy.size);
		Entry enemyEntry = enemy.entries[enemyIndex];
		
		Type type = Card.compare(playerEntry.card, enemyEntry.card);
		
		boolean win = false;
		
		switch (type) {
		case STRONG:
			win = true;
			break;
		case NEUTRAL:
			//	player gets slight advantage (if they are equal, then the player wins)
			if (playerEntry.power >= enemyEntry.power) {
				win = true;
			} else {
				win = false;
			}
			break;
		case WEAK:
			win = false;
			break;
		}

		
		
		if (win) {
			enemy.size--;
			remove(enemy.entries, enemyIndex);
			top.midText.setText(">");
			top.topText.setText("You Win");
			top.topText.setForeground(new Color(0, 100, 0));
		} else {
			player.size--;
			remove(player.entries, playerIndex);
			top.midText.setText("<");
			top.topText.setText("You Lose");
			top.topText.setForeground(Color.RED);
		}

		top.setEntry(playerEntry, true);
		top.setEntry(enemyEntry, false);
		frame.setVisible(true);
		
		top.pSt.setText(Integer.toString(player.size) + " [you]");
		top.eSt.setText(Integer.toString(enemy.size));

		if (enemy.size == 0) {
			top.midText.setText("You Won!");
			top.midText.setForeground(new Color(0, 100, 0));
			top.topText.setText("");
			
			stopped = true;
		} else if (player.size == 0) {
			top.midText.setText("You Lost :(");
			top.midText.setForeground(Color.RED);
			top.topText.setText("");
			
			stopped = true;
		}
		
		this.loadCards();
	}

	public static void remove(Object[] array, int index) {
		while (index+1 < array.length) {
			array[index] = array[index+1];
			index++;
		}
		array[index] = null;
	}

	class Side {
		public int size = 26;
		public Entry[] entries = new Entry[size];

		public Side() {
			for (int i = 0; i < size; i++) {
				int randomIndex = random.nextInt(Card.values().length);
				Card card = Card.values()[randomIndex];
				int power = random.nextInt(16) + 1;
				entries[i] = new Entry(card, power);
			}
		}

		class Entry {
			Card card;
			int power;

			Entry(Card cardIn, int powerIn) {
				this.card = cardIn;
				this.power = powerIn;
			}
		}
	}

}
