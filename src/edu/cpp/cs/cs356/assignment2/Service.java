package edu.cpp.cs.cs356.assignment2;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs356.observers.User;
import edu.cpp.cs.cs356.userinterface.ControlPanel;

public class Service {

	private List<User> users;
	private int totalUsers;
	private int totalGroups;
	private int totalMessages;
	private int totalPosMes;
	
	public Service () {
		users = new ArrayList<>();
		totalUsers = 0;
		totalGroups = 0;
		totalMessages = 0;
		totalPosMes = 0;
	}
	
	public int getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}
	
	public int getTotalGroups() {
		return totalGroups;
	}

	public void setTotalGroups(int totalGroups) {
		this.totalGroups = totalGroups;
	}

	public int getTotalMessages() {
		return totalMessages;
	}

	public void setTotalMessages(int totalMessages) {
		this.totalMessages = totalMessages;
	}
	
	public int getTotalPosMes() {
		return totalPosMes;
	}

	public void setTotalPosMes(int totalPosMes) {
		this.totalPosMes = totalPosMes;
	}

}
