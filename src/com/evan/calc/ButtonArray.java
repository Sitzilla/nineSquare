package com.evan.calc;

import javax.swing.JButton;

//Button Array object that holds 9 buttons and is used in the ticTacToe class
public class ButtonArray {

	private Object[] buttonArray;
	
	//method to create an array with 9 buttons in it
	public ButtonArray(){
		buttonArray = new Object[9];
		for (int i=0; i<=8; i++) {
			buttonArray[i] = new JButton("");
				}
			}
	
	//method to return the button at the specified location
	public JButton getButton(int i){
		return (JButton) buttonArray[i];
	}
	

	
}
