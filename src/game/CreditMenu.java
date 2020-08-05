package game;

import java.awt.Container;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CreditMenu {
	private JFrame frame = new JFrame("Credit");

	CreditMenu() throws IOException {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 360, 150);

		Container container = frame.getContentPane();
		container.setLayout(new FlowLayout());

		
		
		File file = new File("src\\game\\credit.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));

		String string;
		while ((string = br.readLine()) != null) {
			JLabel text = new JLabel(string);
			container.add(text);
		}
		
		br.close();
		
		frame.setVisible(true);
	}
}
