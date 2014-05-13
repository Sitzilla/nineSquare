package com.evan.calc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

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
	ButtonArray buttonArray = new ButtonArray();
	
	//creates Position Arrays
	PositionArray userArray = new PositionArray();
	PositionArray computerArray = new PositionArray();
	
	private Font bSize40 = new Font("Arial", Font.PLAIN, 40); //font size for the button
	int gameTurns = 0; //number of times the user selects a button
	
	public ticTacToe(){
		
		//assigns three buttons to panel one
		panelOne.setLayout( new GridLayout(1,3));
		(buttonArray.getButton(0)).addActionListener(this);
		panelOne.add(buttonArray.getButton(0));
		buttonArray.getButton(1).addActionListener(this);
		panelOne.add(buttonArray.getButton(1));
		buttonArray.getButton(2).addActionListener(this);
		panelOne.add(buttonArray.getButton(2));
		
		//assigns three buttons to panel two
		panelTwo.setLayout( new GridLayout(1,3));
		buttonArray.getButton(3).addActionListener(this);
		panelTwo.add(buttonArray.getButton(3));
		buttonArray.getButton(4).addActionListener(this);
		panelTwo.add(buttonArray.getButton(4));
		buttonArray.getButton(5).addActionListener(this);
		panelTwo.add(buttonArray.getButton(5));
		
		//assigns three buttons to panel three
		panelThree.setLayout( new GridLayout(1,3));
		buttonArray.getButton(6).addActionListener(this);
		panelThree.add(buttonArray.getButton(6));
		buttonArray.getButton(7).addActionListener(this);
		panelThree.add(buttonArray.getButton(7));
		buttonArray.getButton(8).addActionListener(this);
		panelThree.add(buttonArray.getButton(8));
		
		//sets the font size on the buttons
		for (int i=0;i<=8;i++){
			buttonArray.getButton(i).setFont(bSize40);
		}
		
		//adds all of the rows to the frame
		setLayout( new GridLayout(3,1));
		add(panelOne);
		add(panelTwo);
		add(panelThree);
		
	}
	
	public void actionPerformed(ActionEvent evt) {
		boolean userTurn = true; //variable keeping track of whether it is the user's turn or not
		int randomPosition = 0; //variable used by computer to randomly identify a move
		Object source = evt.getSource(); //finds the source of the objects that triggers the event
		
		//when the user clicks on a JButton this code finds that button and assigns it to the positionArray for that user
		while(userTurn==true){
			for (int i=0;i<=8;i++){
				if (source==buttonArray.getButton(i)){
					if (userArray.contains(i+1)||computerArray.contains(i+1)) { //makes sure these numbers are not already in the array
						return;
					} else {
						userArray.setArray(i+1);
						userTurn=false;
							}
						}
					}
				}
		((AbstractButton) source).setText("X");//Sets the user selected button as an 'X'
		
		//checks if the user wins the game
		if (userArray.threeInARowCheck()==true){
			endOfGame("You win!!");
			return;
		}
		//keeps count of the number of turns in the game
		gameTurns++;
		if (gameTurns>=5){
			endOfGame("Cat's game... noone wins");
			return;
		}
		//needs to have enemy turn go here
		Random random = new Random();
		randomPosition = random.nextInt(9 - 1 + 1) + 1;
		while (userArray.contains(randomPosition) || computerArray.contains(randomPosition)){ //checks if either array contains the selection
			randomPosition = random.nextInt(9 - 1 + 1) + 1;	
		}
		//sets the computer selection to the board
		buttonArray.getButton(randomPosition-1).setText("O");
		computerArray.setArray(randomPosition);
		//checks if the user wins the game
		if (computerArray.threeInARowCheck()==true){
			endOfGame("Sorry... the computer wins");
			return;
		}
	}
	
	//method to go through and blank out all of the buttons when called and asks if the user wants a rematch
	public void endOfGame(String message){
		for (int i=0;i<=8;i++){
			buttonArray.getButton(i).setOpaque(false);
			buttonArray.getButton(i).setContentAreaFilled(false);
			buttonArray.getButton(i).setBorderPainted(false);
			buttonArray.getButton(i).setEnabled(false);
		}
		JOptionPane.showMessageDialog(null, message);
		int reply = JOptionPane.showConfirmDialog(null, "Play again?", "Rematch", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
        	restartGame();
        }
	}
	
	//a method that resets the game back to its original state
	public void restartGame(){
		for (int i=0;i<=8;i++){
			buttonArray.getButton(i).setOpaque(true);
			buttonArray.getButton(i).setContentAreaFilled(true);
			buttonArray.getButton(i).setBorderPainted(true);
			buttonArray.getButton(i).setEnabled(true);
			buttonArray.getButton(i).setText("");
		}
		computerArray.clearArray();
		userArray.clearArray();
		gameTurns=0;
	}
}