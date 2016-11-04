package edu.cpp.cs.cs356.observers;

public class Group implements TreeElement {

	private String groupName;
	
	public Group(String name) {
		this.groupName = name;
	}
	
	public String getID() {
		return groupName;
	}
	
	public String toString() {
		return groupName;
	}
	
	public void setID(String id) {
		this.groupName = id;
	}
}
