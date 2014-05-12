package com.evan.calc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

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
	
	//creates Position Arrays
	PositionArray userArray = new PositionArray();
	PositionArray computerArray = new PositionArray();
	
	private Font bSize40 = new Font("Arial", Font.PLAIN, 40);
	
	public ticTacToe(){
		panelOne.setLayout( new GridLayout(1,3));
		buttonOne.addActionListener(this);
		panelOne.add(buttonOne);
		buttonTwo.addActionListener(this);
		panelOne.add(buttonTwo);
		buttonThree.addActionListener(this);
		panelOne.add(buttonThree);
		
		panelTwo.setLayout( new GridLayout(1,3));
		buttonFour.addActionListener(this);
		panelTwo.add(buttonFour);
		buttonFive.addActionListener(this);
		panelTwo.add(buttonFive);
		buttonSix.addActionListener(this);
		panelTwo.add(buttonSix);
		
		panelThree.setLayout( new GridLayout(1,3));
		buttonSeven.addActionListener(this);
		panelThree.add(buttonSeven);
		buttonEight.addActionListener(this);
		panelThree.add(buttonEight);
		buttonNine.addActionListener(this);
		panelThree.add(buttonNine);
		
		buttonOne.setFont(bSize40);
		buttonTwo.setFont(bSize40);
		buttonThree.setFont(bSize40);
		buttonFour.setFont(bSize40);
		buttonFive.setFont(bSize40);
		buttonSix.setFont(bSize40);
		buttonSeven.setFont(bSize40);
		buttonEight.setFont(bSize40);
		buttonNine.setFont(bSize40);
		
		//adds all of the rows to the frame
		setLayout( new GridLayout(3,1));
		add(panelOne);
		add(panelTwo);
		add(panelThree);
		
	}
	
	public void actionPerformed(ActionEvent evt) {
		int randomPosition = 0;
		int gameTurns = 0;
		Object source = evt.getSource(); //finds the source of the objects that triggers the event
		
//		if (evt.getID()==1001){
//		System.out.println(evt.getID());
		//		((AbstractButton) source).setText("Hello");
		//	if (source==buttonOne){
		//		System.out.println(((AbstractButton) source));
		//			}
		//		}
		
		
		
		
		if (source==buttonOne){
			if (userArray.contains(1)) {
				return;
			} else {
				userArray.setArray(1);
			}
		} else if (source==buttonTwo){
			if (userArray.contains(2)) {
				return;
			} else {
				userArray.setArray(2);
			}
		} else if (source==buttonThree){
			if (userArray.contains(3)) {
				return;
			} else {
				userArray.setArray(3);
			}
		} else if (source==buttonFour){
			if (userArray.contains(4)) {
				return;
			} else {
				userArray.setArray(4);
			}
		} else if (source==buttonFive){
			if (userArray.contains(5)) {
				return;
			} else {
				userArray.setArray(5);
			}
		} else if (source==buttonSix){
			if (userArray.contains(6)) {
				return;
			} else {
				userArray.setArray(6);
			}
		} else if (source==buttonSeven){
			if (userArray.contains(7)) {
				return;
			} else {
				userArray.setArray(7);
			}
		} else if (source==buttonEight){
			if (userArray.contains(8)) {
				return;
			} else {
				userArray.setArray(8);
			}
		} else if (source==buttonNine){
			if (userArray.contains(9)) {
				return;
			} else {
				userArray.setArray(9);
			}
		}
		
		((AbstractButton) source).setText("X");//Sets the selected button as an 'X'
		
		//checks if the user wins the game
		if (userArray.threeInARowCheck()==true){
			JOptionPane.showMessageDialog(null, "You win!!");
		}
		
		//keeps count of the number of turns in the game
		gameTurns++;
		if (gameTurns>=3){
			JOptionPane.showMessageDialog(null, "Cat's game... noone wins");
			return;
		}
		System.out.println(gameTurns);
		//needs to have enemy turn go here
		Random random = new Random();
		randomPosition = random.nextInt(9 - 1 + 1) + 1;
		while (userArray.contains(randomPosition) || computerArray.contains(randomPosition)){ //checks if either array contains the selection
			randomPosition = random.nextInt(9 - 1 + 1) + 1;	
		}
		//sets the computer selection to the board
		if (randomPosition==1){
			buttonOne.setText("O");
			computerArray.setArray(1);
		} else if (randomPosition==2){
			buttonTwo.setText("O");
			computerArray.setArray(2);
		} else if (randomPosition==3){
			buttonThree.setText("O");
			computerArray.setArray(3);
		} else if (randomPosition==4){
			buttonFour.setText("O");
			computerArray.setArray(4);
		} else if (randomPosition==5){
			buttonFive.setText("O");
			computerArray.setArray(5);
		} else if (randomPosition==6){
			buttonSix.setText("O");
			computerArray.setArray(6);
		} else if (randomPosition==7){
			buttonSeven.setText("O");
			computerArray.setArray(7);
		} else if (randomPosition==8){
			buttonEight.setText("O");
			computerArray.setArray(8);
		} else if (randomPosition==9){
			buttonNine.setText("O");
			computerArray.setArray(9);
		}
		
		//checks if the user wins the game
		if (computerArray.threeInARowCheck()==true){
			JOptionPane.showMessageDialog(null, "Sorry... the computer wins");
		}

	}
	
//	public void assignButtonPressedToArray(integer i){
//		if (userArray.getArray(i)==null) {
//		}
	}
	