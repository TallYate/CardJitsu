package game;

import java.util.Random;

import game.enums.CardType;
import game.enums.PatternType;
import game.objects.Card;

public class EnemyAttackPattern {
	int turns = 0;
	PatternType pattern = null;
	Card previous = null;

	public EnemyAttackPattern() {
	}

	public int next(Card[] cards, int size) {
		if (this.turns == 0) {
			Random random = new Random();
			this.pattern = PatternType.values()[random.nextInt(PatternType.values().length)];
			this.turns = pattern.getRandomDuration();
		}

		if (previous == null) {
			previous = cards[0];
			return 0;
		}
		
		int index = 0;
		
		turns--;
		switch (this.pattern) {
		case REPEAT:
			for (int i = 0; i < size; i++) {
				if (cards[i].type == previous.type) {
					index = i;
					break;
				}
			}
			break;
		case CYCLE_STRONGER:
			CardType stronger = previous.type.getStronger();
			for (int i = 0; i < size; i++) {
				if (cards[i].type == stronger) {
					index = i;
					break;
				}
			}
			break;
		case CYCLE_WEAKER:
			CardType weaker = previous.type.getWeaker();
			for (int i = 0; i < size; i++) {
				if (cards[i].type == weaker) {
					index = i;
					break;
				}
			}
			break;
		case STRONGEST_CARD:
			int bestPower = 0;
			for (int i = 0; i < size; i++) {
				if (cards[i].power > bestPower) {
					bestPower = cards[i].power;
					index = i;
				}
			}
			break;
		case WEAKEST_CARD:
			int worstPower = 0;
			for (int i = 0; i < size; i++) {
				if (cards[i].power > worstPower) {
					bestPower = cards[i].power;
					index = i;
				}
			}
			break;
		}
		
		this.previous = cards[index];
		return index;
	}
}
