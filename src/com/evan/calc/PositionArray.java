package com.evan.calc;

import java.util.ArrayList;
import java.util.List;

public class PositionArray {

List<Integer> positionArray = new ArrayList<Integer>();


	public void setArray(int i) {
		positionArray.add(i);
	}
	
	public Object getArray(int i){
		return (Object) positionArray.get(i);
	}

	public int size() {
		return positionArray.size();
	}

	public boolean contains(int i) {
		if (positionArray.contains(i)){
		return true;
		} else {
		return false;
		}
	}
	
	//checks the 8 possible ways that there can be a 3 in a row.  If the array contains those three integers, it returns a 'true',
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
