package com.evan.calc;

import java.util.ArrayList;

public class AIStrategy {
	
	//creates Position Arrays
	ArrayList<Integer> userArray = new ArrayList();
	ArrayList<Integer> computerArray = new ArrayList();
	ArrayList<Integer> spacesWithTokensArray = new ArrayList();
	boolean humanPlayer;
	
	AIStrategy(ArrayList userArray, ArrayList computerArray, boolean humanPlayer){
		this.userArray = userArray;
		this.computerArray = computerArray;
		this.humanPlayer = humanPlayer;
		spacesWithTokensArray = userArray;
		spacesWithTokensArray.addAll(computerArray);
		
	}
	
	public int checkRows(){
		return 0;
		
		
		
	}
	
}
