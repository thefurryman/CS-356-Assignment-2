package edu.cpp.cs.cs356.observers;

public interface Visitor {
	
	public String getID();
	public void update(Visitable vis);
}
