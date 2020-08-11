package game.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import game.objects.Achievement;

public class StatsButton extends JButton implements ActionListener {
	private static final long serialVersionUID = -695658491080406046L;

	private char[] saveData;
	
	@Override
	public boolean isManagingFocus() {
		return false;
	}

	public StatsButton(String label, char[] saveData) {
		super(label);
		this.addActionListener(this);
		this.setBackground(Color.WHITE);
		this.saveData = saveData;
	}

	@Override
	public void actionPerformed(ActionEvent event){
		createFrame();
	}
	
	public void createFrame(){
		JFrame frame = new JFrame("Credit");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 360);
		Container container = frame.getContentPane();
		container.setLayout(new FlowLayout());

		JLabel wins = new JLabel("Wins: " + (int) saveData[1]);
		wins.setPreferredSize(new Dimension(200, 100));
		wins.setFont(new Font("Arial", Font.BOLD, 32));
		wins.setHorizontalAlignment(SwingConstants.CENTER);
		wins.setBorder(BorderFactory.createEtchedBorder());
		container.add(wins);
		
		int binary = saveData[0];
		for(Achievement achievement: Achievement.values()) {
			JPanel panel = new JPanel(new GridLayout(2, 1));
			JLabel title = new JLabel(achievement.title);
			JTextArea text = new JTextArea(achievement.text);
			
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setBorder(BorderFactory.createRaisedBevelBorder());
			
			text.setBackground(new Color(225, 225, 225, 150));
			text.setEditable(false);
			text.setMargin(new Insets(5, 8, 8, 5));
			text.setLineWrap(true);
			text.setWrapStyleWord(true);
			
			panel.setBorder(BorderFactory.createEtchedBorder());
			
			title.setFont(new Font("Arial", Font.BOLD, 24));
			text.setFont(new Font("Arial", Font.BOLD, 20));
			
			panel.add(title);
			panel.add(text);
			panel.setPreferredSize(new Dimension(200, 100));
			
			if((binary & 1) == 1) {
				panel.setBackground(Color.GREEN);
			}
			else {
				panel.setBackground(Color.RED);
			}
			binary >>= 1;
			
			container.add(panel);
		}
		
		frame.setVisible(true);
	}
}
