package com.evan.calc;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class TicTacToeGame {

	
	//creates Position Arrays
	ArrayList<Integer> userArray = new ArrayList();
	ArrayList<Integer>  computerArray = new ArrayList();
	
	JButton[][] buttonArray;
	int gameTurns = 1;
	int indexPosition;
	boolean userTurn;//variable keeping track of whether it is the user's turn or not
	boolean legalMove;
	int randomPosition;
	int mostRecentComputerMove;
	
	TicTacToeGame(JButton[][] buttonArray){
		this.buttonArray = buttonArray;;
	}
	
	
	public boolean playTheGame(){
		userTurn = designateTurn(gameTurns);
		
		if (userTurn){
			legalMove = manageUsersTurn(indexPosition);
		} else {
			legalMove = manageComputersTurn(indexPosition);
			
		}
		
		return legalMove;
	}

	public boolean winningArray(ArrayList positionArray){
		
		if (threeInARowCheck(positionArray)){
			return true;
		}
		
		return false;
	}
	
	public void setGameTurns(int turnNumber){
		gameTurns = turnNumber;
	}
	public void setIndexPosition(int pos){
		indexPosition = pos;
	}
	
	//method that manages the users move
	public boolean manageUsersTurn(int indexPosition){
		if (userArray.contains(indexPosition)||computerArray.contains(indexPosition)) { //makes sure these numbers are not already in the array
			//returns 'false' to symbolize an illegal move
			return false;
	}
		//sets the played position into the array and returns 'true' to symbolize a legal move
		userArray.add(indexPosition);
		return true; 
		
	}
	
	//method that manages the computers move
	public boolean manageComputersTurn(int indexPosition){
		//needs to have enemy turn go here
		Random random = new Random();
		randomPosition = random.nextInt(9 - 1 + 1) + 1;
		while (userArray.contains(randomPosition) || computerArray.contains(randomPosition)){ //checks if either array contains the selection
			randomPosition = random.nextInt(9 - 1 + 1) + 1;	
		}
		
		computerArray.add(randomPosition);
		mostRecentComputerMove = randomPosition;
		return true;
	}
	
	public ArrayList getUserArray(){
		return userArray;
	}
	
	public ArrayList getComputerArray(){
		return computerArray;
	}
	
	public int getMostRecentComputerMove(){
		return mostRecentComputerMove;
	}
	
	
//	//method that returns true if passed in variable is odd and false if it is even 
	public boolean designateTurn(int turn){
		if(turn%2==0){
			return false;
		}else{
			return true;
		}
	}
	
	
	//method that checks the 8 possible ways that there can be a 3 in a row.  If the array contains those three integers, it returns a 'true',
		//otherwise it returns a 'false'
		public boolean threeInARowCheck(ArrayList positionArray){
			if (positionArray.contains(0)&&positionArray.contains(1)&&positionArray.contains(2)){
				return true;
			} else if (positionArray.contains(0)&&positionArray.contains(3)&&positionArray.contains(6)){
				return true;
			} else if (positionArray.contains(0)&&positionArray.contains(4)&&positionArray.contains(8)){
				return true;
			} else if (positionArray.contains(3)&&positionArray.contains(4)&&positionArray.contains(5)){
				return true;
			} else if (positionArray.contains(6)&&positionArray.contains(7)&&positionArray.contains(8)){
				return true;
			} else if (positionArray.contains(6)&&positionArray.contains(4)&&positionArray.contains(2)){
				return true;
			} else if (positionArray.contains(1)&&positionArray.contains(4)&&positionArray.contains(7)){
				return true;
			} else if (positionArray.contains(2)&&positionArray.contains(5)&&positionArray.contains(8)){
				return true;
			}
			return false;
		}
	
	//method to go through and blank out all of the buttons when called and asks if the user wants a rematch
	public void endOfGame(String message){
//		for (int i=0;i<=8;i++){
//			buttonArray.getButton(i).setOpaque(false);
//			buttonArray.getButton(i).setContentAreaFilled(false);
//			buttonArray.getButton(i).setBorderPainted(false);
//			buttonArray.getButton(i).setEnabled(false);
//		}
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

//
////checks if the user wins the game
//if (userArray.threeInARowCheck()==true){
//	endOfGame("You win!!");
//	return;
//}
////keeps count of the number of turns in the game
//gameTurns++;
//if (gameTurns>=5){
//	endOfGame("Cat's game... noone wins");
//	return;
//}
////needs to have enemy turn go here
//Random random = new Random();
//randomPosition = random.nextInt(9 - 1 + 1) + 1;
//while (userArray.contains(randomPosition) || computerArray.contains(randomPosition)){ //checks if either array contains the selection
//	randomPosition = random.nextInt(9 - 1 + 1) + 1;	
//}
////sets the computer selection to the board
//buttonArray.getButton(randomPosition-1).setText("O");
//computerArray.setArray(randomPosition);
////checks if the user wins the game
//if (computerArray.threeInARowCheck()==true){
//	endOfGame("Sorry... the computer wins");
//	return;
//}
//
//
