package edu.cpp.cs.cs356.userinterface;

import javax.swing.JTextField;

public class TextField {

	protected JTextField textField;
	
	public TextField(String text, int value) {
		textField.setText(text);
		textField.setColumns(value);
	}
}
