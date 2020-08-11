package game.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.objects.Achievement;

public class AchievementPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -4193543957474874819L;
	
	private JLabel title = new JLabel();
	private JLabel text = new JLabel();
	
	Timer timer;
	int c = 0;
	public LinkedList<Achievement> buffer = new LinkedList<Achievement>();
	
	int r;
	int g;
	int b;
	
	public AchievementPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createRigidArea(new Dimension(5, 0)));
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(16, 0)));
		this.add(text);
		
		title.setFont(new Font("Arial", Font.BOLD, 25));
		
		text.setFont(new Font("Arial", Font.PLAIN, 16));
		text.setAlignmentY(0.42F);;
		
		this.setBackground(new Color(0, 0, 0, 0));
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEtchedBorder());
		timer = new Timer(64, this);
		timer.setRepeats(true);
		timer.start();
	}
	
	final int DURATION = 128;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Achievement achievement = buffer.peekFirst();
		boolean needsPaint = false;
		//	handle countdown
		if(c!=0) {
			c--;
		}
		//	if c == 0, do nothing if next achievement is null
		else if(achievement==null) {
			return;
		}
		//	if c==0 and there is a next achievement, remove first achievement set text to that achievement
		else {
			buffer.removeFirst();
			this.title.setText(achievement.title);
			this.text.setText(achievement.text);
			this.r = achievement.r;
			this.g = achievement.g;
			this.b = achievement.b;
			c=DURATION;
		}
		
		//handle fading
		
		if(c>0 && c<12) {
			needsPaint = true;
		}
		
		int opacity = 255;
		switch(c) {
		case DURATION:
			needsPaint = true;
			opacity = 64;
			break;
		case DURATION-1:
			needsPaint = true;
			opacity = 128;
			break;
		case DURATION-2:
			needsPaint = true;
			opacity = 192;
			break;
		case DURATION-3:
			needsPaint = true;
			break;
		case 11:
			opacity = 250;
			break;
		case 10:
			opacity = 225;
			break;
		case 9:
			opacity = 200;
			break;
		case 8:
			opacity = 175;
			break;
		case 7:
			opacity = 150;
			break;
		case 6:
			opacity = 125;
			break;
		case 5:
			opacity = 100;
			break;
		case 4:
			opacity = 75;
			break;
		case 3:
			opacity = 50;
			break;
		case 2:
			opacity = 25;
			break;
		case 1:
			opacity = 0;
			break;
		}
		
		if(needsPaint) {
			this.setBackground(new Color(r, g, b, opacity));
			title.setForeground(new Color(0, 0, 0, opacity));
			text.setForeground(new Color(0, 0, 0, opacity));
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		super.paintComponent(g);
	}

}
