package game.objects;

public enum Achievement {
	//	reordering will break saveData achievements
	WIN_ROUND("Winner", "Win a round", 150, 200, 150),
	LOSE_ROUND("Loser", "Lose a round", 200, 150, 150),
	TIE_ROUND("Same", "Tie a round", 200, 200, 150),
	WIN_GAME("Ultimate Winner", "Win a game", 150, 200, 150),
	LOSE_GAME("Ultimate Loser", "Lose a game", 200, 150, 150),
	BELT_WHITE("White Belt", "Win your first game", 200, 200, 200),
	BELT_YELLOW("Yellow Belt", "Win 4 games", 200, 200, 150),
	BELT_ORANGE("Orange Belt", "Win 9 games", 255, 200, 150),
	BELT_GREEN("Green Belt", "Win 16 games", 150, 200, 150),
	BELT_BLUE("Blue Belt", "Win 25 games", 150, 150, 200),
	BELT_RED("Red Belt", "Win 36 games", 200, 150, 150),
	BELT_PURPLE("Purple Belt", "Win 49 games", 200, 150, 220),
	BELT_BROWN("Brown Belt", "Win 64 games", 150, 100, 50),
	BELT_BLACK("Black Belt", "Win 80 games", 100, 100, 100);
	
	public String title;
	public String text;
	public int id, r,g,b;
	
	Achievement(String title, String text, int r, int g, int b) {
		this.title = title;
		this.text = text;
		
		this.id = Counter.counter++;
		
		this.r=r;
		this.g=g;
		this.b=b;
	}
	
	static class Counter {
		static int counter = 0;
	}
}
