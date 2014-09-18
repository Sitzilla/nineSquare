package com.evan.calc;

import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class TicTacToeGame {

	
	//creates Position Arrays
	PositionArray userArray = new PositionArray();
	PositionArray computerArray = new PositionArray();
	
	JButton[][] buttonArray;
	int gameTurns;
	int indexPosition;
	boolean userTurn;//variable keeping track of whether it is the user's turn or not
	boolean legalMove;
	
	TicTacToeGame(JButton[][] buttonArray, int gameTurns, int indexPosition){
		this.buttonArray = buttonArray;
		this.gameTurns = gameTurns;
		this.indexPosition = indexPosition;
	}
	
	
	public boolean playTheGame(){
		userTurn = designateTurn(gameTurns);
		
		if (userTurn){
			legalMove = manageUsersTurn(indexPosition);
		}
		
		return legalMove;
//		//when the user clicks on a JButton this code finds that button and assigns it to the positionArray for that user
//		while(userTurn==true){
//			for (int i=0;i<=8;i++){
//				if (source==buttonArray.getButton(i)){
//					if (userArray.contains(i+1)||computerArray.contains(i+1)) { //makes sure these numbers are not already in the array
//						return;
//					} else {
//						userArray.setArray(i+1);
//						userTurn=false;
//							}
//						}
//					}
//				}
//		((AbstractButton) source).setText("X");//Sets the user selected button as an 'X'
//		
//		//checks if the user wins the game
//		if (userArray.threeInARowCheck()==true){
//			endOfGame("You win!!");
//			return;
//		}
//		//keeps count of the number of turns in the game
//		gameTurns++;
//		if (gameTurns>=5){
//			endOfGame("Cat's game... noone wins");
//			return;
//		}
//		//needs to have enemy turn go here
//		Random random = new Random();
//		randomPosition = random.nextInt(9 - 1 + 1) + 1;
//		while (userArray.contains(randomPosition) || computerArray.contains(randomPosition)){ //checks if either array contains the selection
//			randomPosition = random.nextInt(9 - 1 + 1) + 1;	
//		}
//		//sets the computer selection to the board
//		buttonArray.getButton(randomPosition-1).setText("O");
//		computerArray.setArray(randomPosition);
//		//checks if the user wins the game
//		if (computerArray.threeInARowCheck()==true){
//			endOfGame("Sorry... the computer wins");
//			return;
//		}
//		
//		
	}
//	
	//method that manages the users move
	public boolean manageUsersTurn(int indexPosition){
		if (userArray.contains(indexPosition)||computerArray.contains(indexPosition)) { //makes sure these numbers are not already in the array
			//returns 'false' to symbolize an illegal move
			return false;
	}
		//sets the played position into the array and returns 'true' to symbolize a legal move
		userArray.setArray(indexPosition);
		return true; 
		
	}
	
//	//method that returns true if passed in variable is odd and false if it is even 
	public boolean designateTurn(int turn){
		if(turn%2==0){
			return false;
		}else{
			return true;
		}
	}
	
	
	
	
	//method to go through and blank out all of the buttons when called and asks if the user wants a rematch
	public void endOfGame(String message){
//		for (int i=0;i<=8;i++){
//			buttonArray.getButton(i).setOpaque(false);
//			buttonArray.getButton(i).setContentAreaFilled(false);
//			buttonArray.getButton(i).setBorderPainted(false);
//			buttonArray.getButton(i).setEnabled(false);
//		}
//		JOptionPane.showMessageDialog(null, message);
//		int reply = JOptionPane.showConfirmDialog(null, "Play again?", "Rematch", JOptionPane.YES_NO_OPTION);
//		if (reply == JOptionPane.NO_OPTION) {
//            System.exit(0);
//        } else {
//        	restartGame();
//        }
	}
	
	//a method that resets the game back to its original state
	public void restartGame(){
//		for (int i=0;i<=8;i++){
//			buttonArray.getButton(i).setOpaque(true);
//			buttonArray.getButton(i).setContentAreaFilled(true);
//			buttonArray.getButton(i).setBorderPainted(true);
//			buttonArray.getButton(i).setEnabled(true);
//			buttonArray.getButton(i).setText("");
//		}
//		computerArray.clearArray();
//		userArray.clearArray();
//		gameTurns=0;
	}
	
}
