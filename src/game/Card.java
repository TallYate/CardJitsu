package game;

public enum Card {
	
	FIRE,
	WATER,
	SNOW;
	
	public static Type compare(Card a, Card b) {
		if(a==b) {
			return Type.NEUTRAL;
		}
		switch(a) {
		case FIRE:
			if(b==SNOW) {return Type.STRONG;}
			return Type.WEAK;
		case WATER:
			if(b==FIRE) {return Type.STRONG;}
			return Type.WEAK;
		case SNOW:
			if(b==WATER) {return Type.STRONG;}
			return Type.WEAK;
		}
		return Type.WEAK;
	}
	
}
