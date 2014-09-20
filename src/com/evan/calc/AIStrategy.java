package com.evan.calc;

import java.util.ArrayList;

public class AIStrategy {
	private static final int ARRAYSIZE = 9; //total of 9 positions in the array of possible moves (0 to 8)
	
	//creates Position Arrays that hold the user's and the computer's moves thus far in the game
	ArrayList<Integer> userArray = new ArrayList<Integer>();
	ArrayList<Integer> computerArray = new ArrayList<Integer>();
	
	//arraylist of all possible moves on an empty board (0 through 8)
	ArrayList<Integer> totalMoves = new ArrayList<Integer>();
	
	//temporary arraylists that are used to hold MiniMax test moves
	ArrayList<Integer> tempUserArray = new ArrayList<Integer>();
	ArrayList<Integer> tempComputerArray = new ArrayList<Integer>();

	//constructor for the class
	AIStrategy(){
		//creates an arraylist of all possible moves on an empty board (0 through 8)
		for (int i = 0; i < ARRAYSIZE; i++){
			totalMoves.add(i);
		}
	}
	
	//setter method to add moves to the user's array of moves
	public void setUserArray(int value){
		userArray.add(value);
	}
	//setter method to add moves to the computer's array of moves
	public void setComputerArray(int value){
		computerArray.add(value);
	}
	
	//clears all of the arrays in the 'AIStrategy' class.  This is used whenever resetting the game to play again
	public void clearAllArrays(){
		userArray.clear();
		computerArray.clear();
		tempUserArray.clear();
		tempComputerArray.clear();
	}
	
	//method called from the 'TicTacToeGame' class that returns the 'best' move for the computer. 
	public int returnBestMove(){
		int[] bestMove = null;
			
			//fills these two temporary arraylists with 'base values', which is the current state of the 'Board' class whenever this method is called
			tempUserArray.addAll(userArray);
			tempComputerArray.addAll(computerArray);
			
			//calls the recursive minimax search algorithm to search to a depth of 2 turns ahead
			bestMove = minimax(2, true);

			
			tempUserArray.clear();
			tempComputerArray.clear();
			
		return bestMove[1]; 
	}
	
	//recursive Min-Max algorithm that returns an int[2] object (score, index)
	public int[] minimax(int depth, boolean computerPlayer){
		ArrayList<Integer> spacesWithTokensArray = new ArrayList<Integer>(); //array of all moves that have already been made
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>(); //array of moves available to be checked
		
		spacesWithTokensArray.addAll(tempUserArray);
		spacesWithTokensArray.addAll(tempComputerArray);
		
		possibleMoves.addAll(totalMoves);
		possibleMoves.removeAll(spacesWithTokensArray);
		
		//minimized if human, maximized if computer
		int bestScore = (computerPlayer) ? -1000: 1000; //variable that keeps track of the 'best' score returned
		int currentScore; //variable that store the current score
		int bestIndex = -1; //variable that holds the move associated with the 'bestScore' variable

		//if there are no more available moves or if the algorithm has searched to the necessary depth
		if (depth==0 || possibleMoves.isEmpty()){
			bestScore = checkAllLinesOnTheBoard(); //returns a score for the moves in the 'spacesWithTokensArray' arraylist
		} else {
			//goes through and checks all possible moves in the 'possibleMoves' arraylist
			for (int move : possibleMoves){
				//try this move for the 'current' player
				if (computerPlayer) { //if the computer's 'turn'
					tempComputerArray.add(move);
					spacesWithTokensArray.add(move);
					
					currentScore = minimax(depth-1, false)[0]; //calls minimax recursively to the next depth level
					
					//assigns top score and index
					if (currentScore > bestScore){
						bestScore = currentScore;
						bestIndex = move;
						
						//if the computer has a winning move, break the algorithm and return the winning move
						if (bestScore > 900){
							return new int[] {bestScore, bestIndex};
						}
					}
				} else { //if the human's 'turn'
					tempUserArray.add(move);
					spacesWithTokensArray.add(move);
					
					currentScore = minimax(depth-1, true)[0]; //calls minimax recursively to the next depth level
					
					//assigns top score and index
					if (currentScore < bestScore){
						bestScore = currentScore;
						bestIndex = move;
						
						//if the computer has a winning move, break the algorithm and return the winning move
						if (bestScore > 900){
							return new int[] {bestScore, bestIndex};
						}
					}
				}
				//Undoes the 'move'
				if (computerPlayer){
					tempComputerArray.remove(new Integer(move));
				} else {
					tempUserArray.remove(new Integer(move));
				}
				spacesWithTokensArray.remove(new Integer(move));
			}
		}
		return new int[] {bestScore, bestIndex};
	}

	//Checks all 8 lines on the board (3 horizontal, 3 vertical, 2 vertical) and returns the current 'score' of the passed in board,
	//which is a sum of the individual scores of all 8 lines
	public int checkAllLinesOnTheBoard(){
		int score = 0;

		score += checkRows(0,1,2);
		score += checkRows(3,4,5);
		score += checkRows(6,7,8);
		score += checkRows(0,3,6);
		score += checkRows(1,4,7);
		score += checkRows(2,5,8);
		score += checkRows(2,4,6);
		score += checkRows(0,4,8);

		return score;
	}
	
	//Heuristic Board Evaluation Function that returns a 'score' for a passed-in line on the 'Board'
	/*Returns scores with the following value:
	 * 100 if the computer will obtain 3 spaces in a line
	 * 10 if the computer will obtain 2 spaces in a line (the remaining space being blank)
	 * 1 if the computer will obtain 1 space in a line (the remaining spaces being blank)
	 * -1 if the user will obtain 1 space in a line (the remaining spaces being blank)
	 * -10 if the user will obtain 2 spaces in a line (the remaining space being blank)
	 * -100 if the user will obtain 3 spaces in a line
	 * 0 if all spaces in a line are empty or if the line contains moves from both the computer and the user
	 */ 
	public int checkRows(int x, int y, int z){
		int score = 0;
		int userCount = 0;
		int computerCount = 0;

		if (tempUserArray.contains(x)){
			userCount += 1;
		}
		if (tempUserArray.contains(y)){
			userCount += 1;
		}
		if (tempUserArray.contains(z)){
			userCount += 1;
		}
		if (tempComputerArray.contains(x)){
			computerCount += 1;
		}
		if (tempComputerArray.contains(y)){
			computerCount += 1;
		}
		if (tempComputerArray.contains(z)){
			computerCount += 1;
		}
		
		if (computerCount==3){
			score = 1000;
		}else if (computerCount==2&&userCount==0){
			score = 10;
		}else if (computerCount==1&&userCount==0){
			score = 1;
		}else if (computerCount==0&&userCount==1){
			score = -1;
		}else if (computerCount==0&&userCount==2){
			score = -10;
		}else if (userCount==3){
			score = -1000;
		}else {
			score = 0;
		}
		return score;
	}
}