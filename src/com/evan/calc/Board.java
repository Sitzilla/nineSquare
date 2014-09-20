package com.evan.calc;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {
	private static final int ARRAYSIZE = 3; //the dimensions of the board are 3x3
	
	//creates the panes for each row
	JPanel panelOne = new JPanel();
	JPanel panelTwo = new JPanel();
	JPanel panelThree = new JPanel();
	
	//initializes JButton multidimensional array.  The first dimension is the columns and the second is the rows
	JButton[][] buttonArray = new JButton[ARRAYSIZE][ARRAYSIZE];
	
	private Font bSize40 = new Font("Arial", Font.PLAIN, 40); //font size for the button
	int gameTurns = 1; // starts the game out on turn one (odd values indicate the user's turn, even values the computer's turn)
	boolean legalMove; //boolean that returns whether the user's selected move was legal or not
	
	//creates an instance of the TicTacToeGame
	TicTacToeGame game = new TicTacToeGame(buttonArray);
	
	//constructor for the 'Board' class
	public Board(){
		
		//creates the JButtons, sets their font size, adds a client property to indicate their position (0-8), and adds an action listener
		for (int i=0;i<ARRAYSIZE;i++){
			for (int j=0;j<ARRAYSIZE;j++){
				int setPropertyIndex = (i*3) + j;
				buttonArray[i][j] = new JButton();
				buttonArray[i][j].setFont(bSize40);
				buttonArray[i][j].putClientProperty("index", setPropertyIndex);
				buttonArray[i][j].addActionListener(this);
			}
		}
		
		//assigns three buttons to panel one
		panelOne.setLayout( new GridLayout(1,3));
		panelOne.add(buttonArray[0][0]);
		panelOne.add(buttonArray[0][1]);
		panelOne.add(buttonArray[0][2]);
		
		//assigns three buttons to panel two
		panelTwo.setLayout( new GridLayout(1,3));
		panelTwo.add(buttonArray[1][0]);
		panelTwo.add(buttonArray[1][1]);
		panelTwo.add(buttonArray[1][2]);

		//assigns three buttons to panel three
		panelThree.setLayout( new GridLayout(1,3));
		panelThree.add(buttonArray[2][0]);
		panelThree.add(buttonArray[2][1]);
		panelThree.add(buttonArray[2][2]);
		
		//adds all of the rows to the frame
		setLayout( new GridLayout(3,1));
		add(panelOne);
		add(panelTwo);
		add(panelThree);
		
		}
	
	//method for when the user selects one of the buttons on the board
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource(); //finds the source of the objects that triggers the event
		int indexPosition = (Integer) ((JComponent) evt.getSource()).getClientProperty("index"); //variable that represents the buttons 'index' (0-8)
		//variables used in changing the buttons' text later
		int rowPos = 0;
		int columnPos = 0;

		game.setIndexPosition(indexPosition); //sets the selected buttons 'index' in the game instance of the 'TicTacToeGame' class
		legalMove = game.playTheGame(); //attempts to add the user's move to its array of moves. returns true if selected move is legal
		
		//if selected button was a legal move then mark the button.  If not then do nothing (allowing the user to select again)
		if (legalMove){
			gameTurns += 1;
			game.setGameTurns(gameTurns);
			((AbstractButton) source).setText("X"); //Sets the user selected button as an 'X'
		//checks if the user wins the game
			if (game.winningArray(game.getUserArray())){
				//***If this is ever true then there is something wrong with the computer's AI!  The computer should never lose
				endOfGame("You win!!");
				return;
			}
			
			//end the game if game is a draw
			if (gameTurns > 9){
				endOfGame("Cat's game... it's a draw!");
				return;
			}
			
		//since the player has gone, this will simulate the computers move
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
			
			buttonArray[rowPos][columnPos].setText("O"); //Sets the computer's selected button as an 'O'
		//checks if the computer wins the game
			if (game.winningArray(game.getComputerArray())){
				endOfGame("Sorry... the computer wins");
				return;
			}
			gameTurns += 1;
			game.setGameTurns(gameTurns);
		}
		
	}
	
	//end-of-game method to go through and blank out all of the buttons when called and asks if the user wants a rematch
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
		gameTurns = 1;
		game.reset();
		}
	
	}
}