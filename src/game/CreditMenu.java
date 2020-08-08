package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class CreditMenu {
	private JFrame frame = new JFrame("Credit");

	CreditMenu() throws IOException {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 400, 120);
		frame.setResizable(false);
		Container container = frame.getContentPane();
		container.setLayout(new FlowLayout());
		
		InputStream stream = CreditMenu.class.getResource("credit.txt").openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		StringBuilder builder = new StringBuilder();
		String string;
		while((string = reader.readLine()) != null) {
			builder.append(string);
			builder.append('\n');
		}
		reader.close();
		string = builder.toString();
		
		JTextArea area = new JTextArea(string);
		area.setEditable(false);
		area.setBackground(new Color(238,238,238));
		frame.add(area);
		frame.setVisible(true);
	}
}
