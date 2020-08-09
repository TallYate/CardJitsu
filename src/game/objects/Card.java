package game.objects;

import game.enums.CardType;

public class Card {
	public CardType type;
	public int power;

	Card(CardType typeIn, int powerIn) {
		this.type = typeIn;
		this.power = powerIn;
	}
}