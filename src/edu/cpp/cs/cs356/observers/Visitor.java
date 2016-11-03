package edu.cpp.cs.cs356.observers;

public interface Visitor {
	//observer espnmobile, cppnewspaper
	
	public String getID();
	public void update(Visitable vis);
}
