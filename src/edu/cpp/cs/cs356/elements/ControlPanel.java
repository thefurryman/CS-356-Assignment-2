package edu.cpp.cs.cs356.elements;

import java.util.List;
import javax.swing.JFrame;

public class ControlPanel extends JFrame implements WindowElement {

	List<WindowElement> list;
	
	public ControlPanel() {
		Button addUser = new Button("Add User");
	}
	
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

}
