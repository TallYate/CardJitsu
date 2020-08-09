package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import game.enums.CardType;
import game.enums.EffectType;
import game.objects.Card;
import game.objects.Side;
import game.swing.GameFrame;

public class Game implements ActionListener {
	
	public Side player = new Side(26);
	Side enemy = new Side(26);

	public GameFrame frame = new GameFrame(this, "Card Jitsu");
	
	boolean playing = true;
	public Random random = new Random();
	
	EnemyAttackPattern attackPattern = new EnemyAttackPattern();
	
	public static void main(String[] args) throws InterruptedException {
		new Game();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!playing) {
			return;
		}
		
		int playerIndex = Integer.parseInt(e.getActionCommand());
		Card playerCard = player.cards[playerIndex];

		int enemyIndex = attackPattern.next(enemy.cards, enemy.size);
		Card enemyCard = enemy.cards[enemyIndex];
		
		EffectType type = CardType.compare(playerCard.type, enemyCard.type);
		
		boolean win = false;
		
		frame.top.setCard(playerCard, true);
		frame.top.setCard(enemyCard, false);
		
		switch (type) {
		case STRONG:
			win = true;
			break;
		case NEUTRAL:
			if(playerCard.power == enemyCard.power) {
				frame.top.midText.setText("=");
				frame.top.topText.setText("Tie");
				frame.top.topText.setForeground(new Color(60, 60, 40));
				return;
			}
			else if (playerCard.power > enemyCard.power) {
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
			enemy.remove(enemyIndex);
			frame.top.midText.setText(">");
			frame.top.topText.setText("You Win");
			frame.top.topText.setForeground(new Color(40, 80, 40));
		} else {
			player.remove(playerIndex);
			frame.top.midText.setText("<");
			frame.top.topText.setText("You Lose");
			frame.top.topText.setForeground(new Color(80, 40, 40));
			//	shown cards only need reloading when you lose
			frame.loadCards();
		}

		frame.top.pSt.setText(Integer.toString(player.size));
		frame.top.eSt.setText(Integer.toString(enemy.size));

		if (enemy.size == 0) {
			frame.top.midText.setText("You Won!");
			frame.top.midText.setForeground(new Color(40, 80, 40));
			frame.top.topText.setText("");
			
			playing = false;
		} else if (player.size == 0) {
			frame.top.midText.setText("You Lost :(");
			frame.top.midText.setForeground(new Color(80, 40, 40));
			frame.top.topText.setText("");
			
			playing = false;
		}
	}
}