package game.objects;

import java.util.Random;

import game.enums.CardType;

public class Side {
	Random random = new Random();
	
	public int size;
	public Card[] cards;

	public Side(int sizeIn) {
		this.size = sizeIn;
		this.cards = new Card[size];
		for (int i = 0; i < size; i++) {
			int randomIndex = random.nextInt(CardType.values().length);
			CardType type = CardType.values()[randomIndex];
			int power = random.nextInt(16) + 1;
			cards[i] = new Card(type, power);
		}
	}
	
	public void remove(int index) {
		while (index+1 < this.cards.length) {
			this.cards[index] = this.cards[index+1];
			index++;
		}
		cards[index] = null;
		this.size--;
	}

	
}