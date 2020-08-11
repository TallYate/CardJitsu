package game.objects;

public enum Achievement {
	//	reordering will break saveData achievements
	WIN_ROUND("Winner", "Win a round", 150, 200, 150),
	LOSE_ROUND("Loser", "Lose a round", 200, 150, 150),
	TIE_ROUND("Same", "Tie a round", 200, 200, 150),
	WIN_GAME("Ultimate Winner", "Win a game", 150, 200, 150),
	LOSE_GAME("Ultimate Loser", "Lose a game", 200, 150, 150);
	
	
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
