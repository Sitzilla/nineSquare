package src.main.java.com.evan.tictactoe;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

public class StartClass {

	public static void main(String args[]) throws IOException {
		JFrame window = new JFrame("Tic Tac Toe");
		BoardNew content = new BoardNew();
		window.setContentPane(content);
		Dimension d = new Dimension(600,600);
		window.setPreferredSize(d);
		window.setResizable(false);
		window.pack();


		window.setLocation(200,200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		content.play();
	}
}

