package com.evan.calc;

import java.util.ArrayList;
import java.util.List;
//Position Array object that holds integers that represent button selections and is used in the ticTacToe class
public class PositionArray {

List<Integer> positionArray = new ArrayList<Integer>();

	//method to add an integer to the end of an array
	public void setArray(int i) {
		positionArray.add(i);
	}
	//method to return the integer at the specified location
	public Object getArray(int i){
		return (Object) positionArray.get(i);
	}
	//method to find the size of the array
	public int size() {
		return positionArray.size();
	}
	//method to check if an object contains the specified number
	public boolean contains(int i) {
		if (positionArray.contains(i)){
		return true;
		} else {
		return false;
		}
	}
	//clears all spaces in the array
	public void clearArray(){
		positionArray.clear();
	}
	
	//method that checks the 8 possible ways that there can be a 3 in a row.  If the array contains those three integers, it returns a 'true',
	//otherwise it returns a 'false'
	public boolean threeInARowCheck(){
		if (positionArray.contains(1)&&positionArray.contains(2)&&positionArray.contains(3)){
			return true;
		} else if (positionArray.contains(1)&&positionArray.contains(4)&&positionArray.contains(7)){
			return true;
		} else if (positionArray.contains(1)&&positionArray.contains(5)&&positionArray.contains(9)){
			return true;
		} else if (positionArray.contains(4)&&positionArray.contains(5)&&positionArray.contains(6)){
			return true;
		} else if (positionArray.contains(7)&&positionArray.contains(8)&&positionArray.contains(9)){
			return true;
		} else if (positionArray.contains(7)&&positionArray.contains(5)&&positionArray.contains(3)){
			return true;
		} else if (positionArray.contains(2)&&positionArray.contains(5)&&positionArray.contains(8)){
			return true;
		} else if (positionArray.contains(3)&&positionArray.contains(6)&&positionArray.contains(9)){
			return true;
		}
		return false;
	}

}
