package com.evan.calc;

import java.util.ArrayList;

public class AIStrategy {
	
	//creates Position Arrays
	ArrayList<Integer> userArray = new ArrayList();
	ArrayList<Integer> computerArray = new ArrayList();
	ArrayList<Integer> spacesWithTokensArray = new ArrayList();
	ArrayList<Integer> spacesWithNoTokensArray = new ArrayList();
	ArrayList<Integer> totalMoves = new ArrayList();
	
	//calculated arrays that are recreated when the calculations are passed
	ArrayList<Integer> calculatedUserArray = new ArrayList();
	ArrayList<Integer> calculatedComputerArray = new ArrayList();
	
	
	
	boolean humanPlayer;
	boolean computerTurn;
	
	AIStrategy(boolean humanPlayer){
//		this.userArray = userArray;
//		this.computerArray = computerArray;
		this.humanPlayer = humanPlayer;
		totalMoves.add(0);
		totalMoves.add(1);
		totalMoves.add(2);
		totalMoves.add(3);
		totalMoves.add(4);
		totalMoves.add(5);
		totalMoves.add(6);
		totalMoves.add(7);
		totalMoves.add(8);
//		spacesWithTokensArray = userArray;
//		spacesWithTokensArray.addAll(computerArray);
		
	}
	
	public void setUserArray(int value){
		spacesWithTokensArray.clear();
		userArray.add(value);
		spacesWithTokensArray.addAll(userArray);
		spacesWithTokensArray.addAll(computerArray);
	}
	public void setComputerArray(int value){
		spacesWithTokensArray.clear();
		computerArray.add(value);
		spacesWithTokensArray.addAll(userArray);
		spacesWithTokensArray.addAll(computerArray);
	}

	public void clearAllArrays(){
		userArray.clear();
		computerArray.clear();
		spacesWithTokensArray.clear();
		calculatedUserArray.clear();
		calculatedComputerArray.clear();
		spacesWithNoTokensArray.clear();
	}
	
	public int returnBestMove(int gameTurn){
		int[] bestMove = null;
			
			calculatedUserArray.addAll(userArray);
			calculatedComputerArray.addAll(computerArray);
			
//			tempUserArray.addAll(userArray);
//			tempComputerArray.addAll(computerArray);
//			tempSpacesWithTokensArray.addAll(spacesWithTokensArray);
//			
			//start minimax

			bestMove = minimax(2, true);
			
//			tempUserArray.clear();
//			tempComputerArray.clear();
//			tempSpacesWithTokensArray.clear();
//		
		return bestMove[1]; 
	}
	
	//recursive Min-Max algorithm that returns an int[2] object (score, index)
	public int[] minimax(int depth, boolean computerPlayer){
		ArrayList<Integer> tempUserArray = new ArrayList();
		ArrayList<Integer> tempComputerArray = new ArrayList();
		ArrayList<Integer> tempSpacesWithTokensArray = new ArrayList();
		ArrayList<Integer> nextMoves = new ArrayList();
		ArrayList<Integer> possibleMoves = new ArrayList();
		
		tempUserArray.addAll(calculatedUserArray);
		tempComputerArray.addAll(calculatedComputerArray);
		tempSpacesWithTokensArray.addAll(calculatedUserArray);
		tempSpacesWithTokensArray.addAll(calculatedComputerArray);
		
		possibleMoves.addAll(totalMoves);
		possibleMoves.removeAll(tempSpacesWithTokensArray);
		
		nextMoves.addAll(possibleMoves);
		
		//minimized if human, maximized if computer
		int bestScore = (computerPlayer) ? -100: 100;
		int currentScore;
		int bestIndex = -1;
		

		if (depth==0 || nextMoves.isEmpty()){
			
//			.addAll(tempComputerArray);
//			calculatedUserArray.addAll(tempUserArray);
			bestScore = checkAllLinesOnTheBoard();
//			calculatedComputerArray.clear();
//			calculatedUserArray.clear();
		} else {
			for (int move : nextMoves){
				//try this move for the 'current' player
				if (computerPlayer) { //if the computer's 'turn'
					tempComputerArray.add(move);
					calculatedComputerArray.add(move);
					tempSpacesWithTokensArray.add(move);
					
					currentScore = minimax(depth-1, false)[0];
					
					
					//assigns top score and index
					if (currentScore > bestScore){
						bestScore = currentScore;
						bestIndex = move;
					}
				} else { //if the human's 'turn'
					tempUserArray.add(move);
					calculatedUserArray.add(move);
					tempSpacesWithTokensArray.add(move);
					
					
					currentScore = minimax(depth-1, true)[0];
					
					
					//assigns top score and index
					if (currentScore < bestScore){
						bestScore = currentScore;
						bestIndex = move;
					}
				}
				//NEED TO UNDO MOVE
				if (computerPlayer){
					tempComputerArray.remove(new Integer(move));
					calculatedComputerArray.remove(new Integer(move));
				} else {
					tempUserArray.remove(new Integer(move));
					calculatedUserArray.remove(new Integer(move));
				}
				tempSpacesWithTokensArray.remove(new Integer(move));
			}
		}
		
		
		return new int[] {bestScore, bestIndex};
		
		
	}
	

	//Checks the lines on the board and returns the current 'score' of the passed in board, which is a sum of the score of all 8 lines
	public int checkAllLinesOnTheBoard(){
		int score = 0;
//		int score0 = 0;
//		int score1 = 0;
//		int score2 = 0;
//		int score3 = 0;
//		int score4 = 0;
//		int score5 = 0;
//		int score6 = 0;
//		int score7 = 0;
//		int score8 = 0;
//		int highestScore = 0;
//		int returnValue=-1;
		
		score += checkRows(0,1,2);
		score += checkRows(3,4,5);
		score += checkRows(6,7,8);
		score += checkRows(0,3,6);
		score += checkRows(1,4,7);
		score += checkRows(2,5,8);
		score += checkRows(2,4,6);
		score += checkRows(0,4,8);

//			if (!spacesWithTokensArray.contains(0)){
//				score0 += checkRows(0,1,2);
//				score0 += checkRows(0,4,8);
//				score0 += checkRows(0,3,6);
//				if (highestScore < score0){
//					highestScore = score0;
//					returnValue = 0;
//				}
//			}
//			if (!spacesWithTokensArray.contains(1)){
//				score1 += checkRows(1,0,2);
//				score1 += checkRows(1,4,7);
//				if (highestScore < score1){
//					highestScore = score1;
//					returnValue = 1;
//				}
//			}
//			if (!spacesWithTokensArray.contains(2)){
//				score2 += checkRows(2,1,0);
//				score2 += checkRows(2,4,6);
//				score2 += checkRows(2,5,8);
//				if (highestScore < score2){
//					highestScore = score2;
//					returnValue = 2;
//				}
//			}
//			if (!spacesWithTokensArray.contains(3)){
//				score3 += checkRows(3,0,6);
//				score3 += checkRows(3,4,5);
//				if (highestScore < score3){
//					highestScore = score3;
//					returnValue = 3;
//				}
//			}
//			if (!spacesWithTokensArray.contains(4)){
//				score4 += checkRows(4,0,8);
//				score4 += checkRows(4,2,6);
//				score4 += checkRows(4,3,5);
//				score4 += checkRows(4,1,7);
//				if (highestScore < score4){
//					highestScore = score4;
//					returnValue = 4;
//				}
//			}
//			if (!spacesWithTokensArray.contains(5)){
//				score5 += checkRows(5,2,8);
//				score5 += checkRows(5,4,3);
//				if (highestScore < score5){
//					highestScore = score5;
//					returnValue = 5;
//				}
//			}
//			if (!spacesWithTokensArray.contains(6)){
//				score6 += checkRows(6,3,0);
//				score6 += checkRows(6,4,2);
//				score6 += checkRows(6,7,8);
//				if (highestScore < score6){
//					highestScore = score6;
//					returnValue = 6;
//				}
//			}
//			if (!spacesWithTokensArray.contains(7)){
//				score7 += checkRows(7,4,1);
//				score7 += checkRows(7,6,8);
//				if (highestScore < score7){
//					highestScore = score7;
//					returnValue = 7;
//				}
//			}
//			if (!spacesWithTokensArray.contains(8)){
//				score8 += checkRows(8,4,0);
//				score8 += checkRows(8,5,2);
//				score8 += checkRows(8,7,6);
//				if (highestScore < score8){
//					highestScore = score8;
//					returnValue = 8;
//				}
//			}
//			
//			while (returnValue == -1){
//				for (int i =0; i < 9; i++){
//					if (!spacesWithTokensArray.contains(i)){
//						returnValue = i;
//					}
//				}
//			}

		return score;
		
		
		
	}
	public int checkRows(int x, int y, int z){
		int score = 0;
		int userCount = 0;
		int computerCount = 0;

		if (calculatedUserArray.contains(x)){
			userCount += 1;
		}
		if (calculatedUserArray.contains(y)){
			userCount += 1;
		}
		if (calculatedUserArray.contains(z)){
			userCount += 1;
		}
		if (calculatedComputerArray.contains(x)){
			computerCount += 1;
		}
		if (calculatedComputerArray.contains(y)){
			computerCount += 1;
		}
		if (calculatedComputerArray.contains(z)){
			computerCount += 1;
		}
		
		if (computerCount==3){
			score = 100;
		}else if (computerCount==2&&userCount==0){
			score = 10;
		}else if (computerCount==1&&userCount==0){
			score = 1;
		}else if (computerCount==0&&userCount==1){
			score = -1;
		}else if (computerCount==0&&userCount==2){
			score = -10;
		}else if (userCount==3){
			score = -100;
		}else {
			score = 0;
		}
		return score;
	}
	
//	//method that returns true if passed in variable is odd and false if it is even 
	public boolean designateTurn(int turn){
		if(turn%2==0){
			return true;
		}else{
			return false;
		}
	}
}
