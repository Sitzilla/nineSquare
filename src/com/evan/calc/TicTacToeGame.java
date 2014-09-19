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
	
	AIStrategy computerStrategy = new AIStrategy(false);
	
	TicTacToeGame(JButton[][] buttonArray){
		this.buttonArray = buttonArray;;
	}
	
	
	public boolean playTheGame(){
		userTurn = designateTurn(gameTurns);
		
		if (userTurn){
			legalMove = manageUsersTurn(indexPosition);
			if (legalMove){
				computerStrategy.setUserArray(indexPosition);
			}
			
		} else {
			legalMove = manageComputersTurn(indexPosition);
			computerStrategy.setComputerArray(mostRecentComputerMove);
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
		mostRecentComputerMove = computerStrategy.simulateMoves();
		computerArray.add(mostRecentComputerMove);
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
	
	//resets all of the variables in the TicTacToeGame to create a new 'instance' basically
	public void reset(){
		computerArray.clear();
		userArray.clear();
		computerStrategy.clearComputerArray();
		computerStrategy.clearUserArray();
		mostRecentComputerMove = 0;
		gameTurns = 1;
		
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
	
	
	
}
