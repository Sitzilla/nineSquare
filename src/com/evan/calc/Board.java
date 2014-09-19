package com.evan.calc;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Board extends JPanel implements ActionListener {
	private static final int ARRAYSIZE = 3; 
	
	//creates the panes for each row
	JPanel panelOne = new JPanel();
	JPanel panelTwo = new JPanel();
	JPanel panelThree = new JPanel();
	
	//initializes JButton multidimensional array.  The first dimension is the columns and the second is the rows
	JButton[][] buttonArray = new JButton[ARRAYSIZE][ARRAYSIZE];
	
	private Font bSize40 = new Font("Arial", Font.PLAIN, 40); //font size for the button
	int gameTurns = 1; //number of times the user selects a button
	boolean legalMove;
	//creates an instance of the TicTacToeGame
	TicTacToeGame game = new TicTacToeGame(buttonArray);
	
	
	public Board(){
		
		//sets the font size on the buttons
		for (int i=0;i<ARRAYSIZE;i++){
			for (int j=0;j<ARRAYSIZE;j++){
				buttonArray[i][j] = new JButton();
				buttonArray[i][j].setFont(bSize40);
			}
		}
		
		//assigns three buttons to panel one
		panelOne.setLayout( new GridLayout(1,3));
		buttonArray[0][0].setText("0");
		buttonArray[0][0].putClientProperty("index", 0);
		buttonArray[0][0].putClientProperty("row", 0);
		buttonArray[0][0].putClientProperty("column", 0);
		buttonArray[0][0].addActionListener(this);
		panelOne.add(buttonArray[0][0]);
		
		buttonArray[0][1].setText("1");
		buttonArray[0][1].putClientProperty("index", 1);
		buttonArray[0][0].putClientProperty("row", 0);
		buttonArray[0][0].putClientProperty("column", 1);
		buttonArray[0][1].addActionListener(this);
		panelOne.add(buttonArray[0][1]);
		
		buttonArray[0][2].setText("2");
		buttonArray[0][2].putClientProperty("index", 2);
		buttonArray[0][0].putClientProperty("row", 0);
		buttonArray[0][0].putClientProperty("column", 2);
		buttonArray[0][2].addActionListener(this);
		panelOne.add(buttonArray[0][2]);
		
		//assigns three buttons to panel two
		panelTwo.setLayout( new GridLayout(1,3));
		buttonArray[1][0].setText("3");
		buttonArray[1][0].putClientProperty("index", 3);
		buttonArray[0][0].putClientProperty("row", 1);
		buttonArray[0][0].putClientProperty("column", 0);
		buttonArray[1][0].addActionListener(this);
		panelTwo.add(buttonArray[1][0]);

		buttonArray[1][1].setText("4");
		buttonArray[1][1].putClientProperty("index", 4);
		buttonArray[0][0].putClientProperty("row", 1);
		buttonArray[0][0].putClientProperty("column", 1);
		buttonArray[1][1].addActionListener(this);
		panelTwo.add(buttonArray[1][1]);
		
		buttonArray[1][2].setText("5");
		buttonArray[1][2].putClientProperty("index", 5);
		buttonArray[0][0].putClientProperty("row", 1);
		buttonArray[0][0].putClientProperty("column", 2);
		buttonArray[1][2].addActionListener(this);
		panelTwo.add(buttonArray[1][2]);
		
		//assigns three buttons to panel three
		panelThree.setLayout( new GridLayout(1,3));
		buttonArray[2][0].setText("6");
		buttonArray[2][0].putClientProperty("index", 6);
		buttonArray[0][0].putClientProperty("row", 2);
		buttonArray[0][0].putClientProperty("column", 0);
		buttonArray[2][0].addActionListener(this);
		panelThree.add(buttonArray[2][0]);
		
		buttonArray[2][1].setText("7");
		buttonArray[2][1].putClientProperty("index", 7);
		buttonArray[0][0].putClientProperty("row", 2);
		buttonArray[0][0].putClientProperty("column", 1);
		buttonArray[2][1].addActionListener(this);
		panelThree.add(buttonArray[2][1]);

		buttonArray[2][2].setText("8");
		buttonArray[2][2].putClientProperty("index", 8);
		buttonArray[0][0].putClientProperty("row", 2);
		buttonArray[0][0].putClientProperty("column", 2);
		buttonArray[2][2].addActionListener(this);
		panelThree.add(buttonArray[2][2]);
		
		
		//adds all of the rows to the frame
		setLayout( new GridLayout(3,1));
		add(panelOne);
		add(panelTwo);
		add(panelThree);
		
		}
	
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource(); //finds the source of the objects that triggers the event
		int indexPosition = (Integer) ((JComponent) evt.getSource()).getClientProperty("index");
		int rowPos = 0;
		int columnPos = 0;

		game.setIndexPosition(indexPosition);
		legalMove = game.playTheGame();
		
		//if selected button was a legal move then mark the button.  If not then do nothing.
		if (legalMove){
			gameTurns += 1;
			game.setGameTurns(gameTurns);
			((AbstractButton) source).setText("X"); //Sets the user selected button as an 'X'
		//checks if the user wins the game
			if (game.winningArray(game.getUserArray())){
				endOfGame("You win!!");
				return;
			}
			
			//end the game if cats game
			if (gameTurns > 9){
				endOfGame("Cat's game... it's a draw!");
				return;
			}
			
		//since the player has gone, this will simulater the computers move
			game.playTheGame();
			int mostRecentComputerMove = game.getMostRecentComputerMove();
			//matches the indexed computer move to the correct button
			for (int i=0;i<ARRAYSIZE;i++){
				for (int j=0;j<ARRAYSIZE;j++){
					if ((Integer) buttonArray[i][j].getClientProperty("index") == mostRecentComputerMove){
						rowPos = i;
						columnPos = j;
					}
				}
			}
			
			buttonArray[rowPos][columnPos].setText("O"); //Sets the user selected button as an 'O'
		//checks if the user wins the game
			if (game.winningArray(game.getComputerArray())){
				endOfGame("Sorry... the computer wins");
				return;
			}
			gameTurns += 1;
			game.setGameTurns(gameTurns);
		}
		
	}
	
	//method to go through and blank out all of the buttons when called and asks if the user wants a rematch
	public void endOfGame(String message){
		for (int i=0;i<ARRAYSIZE;i++){
			for (int j=0;j<ARRAYSIZE;j++){
			buttonArray[i][j].setOpaque(false);
			buttonArray[i][j].setContentAreaFilled(false);
			buttonArray[i][j].setBorderPainted(false);
			buttonArray[i][j].setEnabled(false);
			}
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
		for (int i=0;i<ARRAYSIZE;i++){
			for (int j=0;j<ARRAYSIZE;j++){
			buttonArray[i][j].setOpaque(true);
			buttonArray[i][j].setContentAreaFilled(true);
			buttonArray[i][j].setBorderPainted(true);
			buttonArray[i][j].setEnabled(true);
			buttonArray[i][j].setText("");
		}
			//################################################################
			//NEED TO ENSURE FULL RESET
			//################################################################
		gameTurns = 1;
		game.reset();
//		userArray.clearArray();
//		gameTurns=0;
	}
	
}
}