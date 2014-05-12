package com.evan.calc;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ticTacToe extends JPanel implements ActionListener {
	public static void main(String args[]) {
		JFrame window = new JFrame("Tic Tac Toe");
		ticTacToe content = new ticTacToe();
		window.setContentPane(content);
		Dimension d = new Dimension(600,600);
		window.setPreferredSize(d);
		window.setResizable(false);
		window.pack();
		window.setLocation(200,200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	//creates the panes for each row
	JPanel panelOne = new JPanel();
	JPanel panelTwo = new JPanel();
	JPanel panelThree = new JPanel();
	
	//initializes JButtons
	JButton buttonOne = new JButton("1");
	JButton buttonTwo = new JButton("2");
	JButton buttonThree = new JButton("3");
	JButton buttonFour = new JButton("4");
	JButton buttonFive = new JButton("5");
	JButton buttonSix = new JButton("6");
	JButton buttonSeven = new JButton("7");
	JButton buttonEight = new JButton("8");
	JButton buttonNine = new JButton("9");
	
	public void ticTacToe(){
		
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}