package edu.cpp.cs.cs356.assignment2;

import edu.cpp.cs.cs356.userinterface.ControlPanel;

public class Driver {

	public static void main(String[] args) {
		new Driver();
	}
	
	public Driver() {
		ControlPanel cp = ControlPanel.getInstance();
		cp.display();
	}
}
