package game.swing;

import java.awt.Font;

public enum Fonts {
	TINY ( new Font("Consolas", Font.PLAIN, 12) ),
	PLAIN ( new Font("Courier", Font.PLAIN, 24) ),
	BOLD ( new Font("Courier", Font.BOLD, 24) ),
	BIG_BOLD ( new Font("Courier", Font.BOLD, 50) ),
	SMALL_PHAT ( new Font("Bowlby One SC", Font.BOLD, 20) ),
	PHAT ( new Font("Bowlby One SC", Font.BOLD, 40) ),
	VERY_PHAT ( new Font("Bowlby One SC", Font.BOLD, 90) );
	
	Font font;
	
	Fonts(Font font){
		this.font = font;
	}
}
