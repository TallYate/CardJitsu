package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;

import game.enums.CardType;
import game.enums.EffectType;
import game.objects.Achievement;
import game.objects.Card;
import game.objects.Side;
import game.swing.GameFrame;

public class Game implements ActionListener {
	
	public Side player = new Side(26);
	public Side enemy = new Side(26);

	GameFrame frame;
	boolean playing = true;
	public Random random = new Random();
	
	EnemyAttackPattern attackPattern = new EnemyAttackPattern();
	
	//	0 is achievements, 1 is wins
	public char[] saveData = new char[2]; 
	
	public static void main(String[] args) {
		try {
			new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Game() throws IOException {
		frame = new GameFrame(this, "Card Jitsu");
		File file = new File("CardJitsuSaveData.txt");
		file.createNewFile();
		FileReader reader = new FileReader(file);
		reader.read(saveData);
		reader.close();
		
		cButton.setActionCommand("continue");
		cButton.addActionListener(this);
	}
	
	public void tryAddAchievement(Achievement achievement) {
		if((saveData[0] >> achievement.id & 1) == 0) {
			frame.achievements.buffer.add(achievement);
			saveData[0] += 1 << achievement.id;
			saveData();
		}
	}
	
	public void addWin() {
		saveData[1]++;
		saveData();
	}
	
	public void saveData() {
		try {
			File file = new File("CardJitsuSaveData.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file, false);
			writer.write(saveData);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!playing) {
			if(e.getActionCommand().equals("continue")){
				frame.bar.remove(cButton);
				playing = true;
				player = new Side(26);
				enemy = new Side(26);
				frame.loadCards();
				frame.setVisible(true);
			}
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
				tryAddAchievement(Achievement.TIE_ROUND);
				frame.top.midText.setText("=");
				frame.top.topText.setText("Tie");
				frame.top.topText.setForeground(new Color(80, 80, 40));
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
			tryAddAchievement(Achievement.WIN_ROUND);
			
			enemy.remove(enemyIndex);
			frame.top.midText.setText(">");
			frame.top.topText.setText("You Win");
			frame.top.topText.setForeground(new Color(40, 80, 40));
		} else {
			
			tryAddAchievement(Achievement.LOSE_ROUND);
			
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
			addWin();
			tryAddAchievement(Achievement.WIN_GAME);
			
			frame.top.midText.setText("You Won!");
			frame.top.midText.setForeground(new Color(40, 80, 40));
			frame.top.topText.setText("");
			
			finishGame();
		} else if (player.size == 0) {
			tryAddAchievement(Achievement.LOSE_GAME);
			
			frame.top.midText.setText("You Lost :(");
			frame.top.midText.setForeground(new Color(80, 40, 40));
			frame.top.topText.setText("");
			
			finishGame();
		}
	}
	
	JButton cButton = new JButton("Continue");
	
	private void finishGame() {
		playing = false;
		frame.bar.add(cButton);
	}
}