package game.enums;

import java.util.Random;

public enum PatternType {
	REPEAT(2),
	CYCLE_STRONGER(3),
	CYCLE_WEAKER(3),
	STRONGEST_CARD(1),
	WEAKEST_CARD(1);
	
	static Random random = new Random();
	int dur;
	
	public int getRandomDuration() {
		return this.dur + random.nextInt(3);
	}
	
	PatternType(int baseDuration){
		this.dur = baseDuration;
	}
}
