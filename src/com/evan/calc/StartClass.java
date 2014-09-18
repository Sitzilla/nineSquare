package com.evan.calc;

import java.awt.Dimension;

import javax.swing.JFrame;

public class StartClass {

	
	public static void main(String args[]) {
		JFrame window = new JFrame("Tic Tac Toe");
		Board content = new Board();
		window.setContentPane(content);
		Dimension d = new Dimension(600,600);
		window.setPreferredSize(d);
		window.setResizable(false);
		window.pack();
		window.setLocation(200,200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}