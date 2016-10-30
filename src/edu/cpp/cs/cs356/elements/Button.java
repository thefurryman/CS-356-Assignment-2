package edu.cpp.cs.cs356.elements;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class Button extends JButton implements WindowElement  {

	public Button(String name) {
		super();
		super.setText(name);
		super.setSize(400, 300);
	}
	
	public Button() {
		super();
	}

	public Button(Action a) {
		super(a);
	}

	public Button(Icon icon) {
		super(icon);
	}

	public Button(String text, Icon icon) {
		super(text, icon);
	}

	@Override
	public void display() {
		
	}

}
