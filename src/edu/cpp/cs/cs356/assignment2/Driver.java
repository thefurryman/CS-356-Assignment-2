package edu.cpp.cs.cs356.assignment2;

import edu.cpp.cs.cs356.userinterface.ControlPanel;

public class Driver {

	/**
	 * Please note that when you add a group, the name will have a "*" in front
	 * since the icon will still look like a leaf node when it doesn't have any 
	 * children yet. The "*" denotes that it is a group not to be confused with a user
	 * in the list and the program will automatically remove the "*" once a 
	 * child leaf (User/Group) is added to it.
	 */
	public static void main(String[] args) {
		new Driver();
	}
	
	public Driver() {
		ControlPanel cp = ControlPanel.getInstance();
		cp.display();
	}
}
