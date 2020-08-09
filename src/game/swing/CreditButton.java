package game.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import game.Game;

public class CreditButton extends JButton implements ActionListener {
	private static final long serialVersionUID = -695658491080406046L;

	@Override
	public boolean isManagingFocus() {
		return false;
	}

	public CreditButton(String label) {
		super(label);
		this.addActionListener(this);
		this.setBackground(Color.WHITE);
	}

	@Override
	public void actionPerformed(ActionEvent event){
		try {
			createFrame();
		}
		catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public void createFrame() throws IOException{
		JFrame frame = new JFrame("Credit");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 400, 120);
		frame.setResizable(false);
		Container container = frame.getContentPane();
		container.setLayout(new FlowLayout());

		InputStream stream;
		stream = Game.class.getResource("credit.txt").openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		StringBuilder builder = new StringBuilder();
		String string;
		string = reader.readLine();
		while (string != null) {
			builder.append(string);
			builder.append('\n');
			string = reader.readLine();
		}
		reader.close();
		string = builder.toString();

		JTextArea area = new JTextArea(string);
		area.setEditable(false);
		area.setBackground(new Color(238, 238, 238));
		frame.add(area);
		frame.setVisible(true);
	}
}
