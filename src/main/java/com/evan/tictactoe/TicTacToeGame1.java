package src.main.java.com.evan.tictactoe;

import java.util.ArrayList;

import javax.swing.JButton;

public class TicTacToeGame1 {

	//creates Position Arrays that hold the user's and the computer's moves
	ArrayList<Integer> userArray = new ArrayList<Integer>();
	ArrayList<Integer>  computerArray = new ArrayList<Integer>();
	
	JButton[][] buttonArray;
	int gameTurns = 1; // starts the game out on turn one (odd values indicate the user's turn, even values the computer's turn)
	int indexPosition; // variable to represent the square selected by the user
	boolean userTurn; //variable keeping track of whether it is the user's turn or not
	boolean legalMove; //boolean that returns whether the user's selected move was legal or not
	int mostRecentComputerMove; // variable keeping track of the computer's most recent move
	
	AIStrategy computerStrategy = new AIStrategy(); //creates an instance of the 'AIStrategy' class
	
	//constructor that takes in the 'buttonArray' from the board class
	TicTacToeGame1(JButton[][] buttonArray){
		this.buttonArray = buttonArray;;
	}
	
	//method called whenever the user or computer selects a square.  returns 'true' or 'false' depending on whether the selected move was legal.
	//This method also adds the move to either the user's or the computer's array of moves.
	public boolean playTheGame(){
		userTurn = designateTurn(gameTurns); //returns true if the user's turn, false if the computer's turn
		if (userTurn){
			legalMove = manageUsersTurn(indexPosition); //returns true if selected move was a legal move
			if (legalMove){
				computerStrategy.setUserArray(indexPosition); //adds a legal move to the user's array of moves
			}
		} else {
			legalMove = true;
			manageComputersTurn(indexPosition); //determines the best move for the computer
			computerStrategy.setComputerArray(mostRecentComputerMove); //adds the computer's selection to the computer's array of moves
		}
		return legalMove;
	}
	
	//method that returns true if the user's move is legal and adds that move to the user's array of moves, and returns false if it is not legal
	public boolean manageUsersTurn(int indexPosition){
		if (userArray.contains(indexPosition)||computerArray.contains(indexPosition)) { //makes sure these numbers are not already in an array
			return false;
		}
		//sets the played position into the array and returns 'true' to symbolize a legal move
		userArray.add(indexPosition);
		return true; 
	}
	
	//method that calls the 'returnBestMove' method in the 'AIStrategy' class, which returns the computer's 'best move', and adds that move
	//to the computer's array of moves
	public void manageComputersTurn(int indexPosition){
		mostRecentComputerMove = computerStrategy.returnBestMove();
		computerArray.add(mostRecentComputerMove);
	}
	
	//getter method to return all moves in the user's array of moves
	public ArrayList<Integer> getUserArray(){
		return userArray;
	}
	
	//getter method to return all moves in the computer's array of moves
	public ArrayList<Integer> getComputerArray(){
		return computerArray;
	}
	
	//getter method to return the computer's most recent move
	public int getMostRecentComputerMove(){
		return mostRecentComputerMove;
	}
	
	//setter method to increase the game turn
	public void setGameTurns(int turnNumber){
		gameTurns = turnNumber;
	}
	//setter method to modify the 'indexPosition' variable in the 'TicTacToeGame1' class from the position selected
	//in the 'Board' class
	public void setIndexPosition(int pos){
		indexPosition = pos;
	}
	
	//method that returns true if passed in variable is odd (user's turn) and false if it is even (computer's turn)
	public boolean designateTurn(int turn){
		if(turn%2==0){
			return false;
		}else{
			return true;
		}
	}
	
	//resets all of the variables in the TicTacToeGame1.  This is used whenever resetting the game to play again
	public void reset(){
		computerArray.clear();
		userArray.clear();
		computerStrategy.clearAllArrays();
		mostRecentComputerMove = 0;
		gameTurns = 1;
	}
	
	//method that takes in an array (user's or computers) and returns true if they have a winning formation
	//and false if they do not
	public boolean winningArray(ArrayList<Integer> positionArray){
		if (threeInARowCheck(positionArray)){
			return true;
		}
		return false;
	}
	
	//method that checks the 8 possible ways that there can be a 3 in a row.  If the array contains those three integers, it returns a 'true',
		//otherwise it returns a 'false'
		public boolean threeInARowCheck(ArrayList<Integer> positionArray){
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
