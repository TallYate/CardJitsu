package game.enums;

public enum CardType {
	
	FIRE,
	WATER,
	SNOW;
	
	public static EffectType compare(CardType a, CardType b) {
		if(a==b) {
			return EffectType.NEUTRAL;
		}
		switch(a) {
		case FIRE:
			if(b==SNOW) {return EffectType.STRONG;}
			return EffectType.WEAK;
		case WATER:
			if(b==FIRE) {return EffectType.STRONG;}
			return EffectType.WEAK;
		case SNOW:
			if(b==WATER) {return EffectType.STRONG;}
			return EffectType.WEAK;
		}
		return EffectType.WEAK;
	}
	
	public CardType getStronger () {
		switch(this) {
		case FIRE:
			return WATER;
		case WATER:
			return SNOW;
		case SNOW:
			return FIRE;
		}
		return null;	//	should never happen unless type is null
	}
	
	public CardType getWeaker () {
		switch(this) {
		case FIRE:
			return SNOW;
		case WATER:
			return FIRE;
		case SNOW:
			return WATER;
		}
		return null;	//	should never happen unless type is null
	}
}
