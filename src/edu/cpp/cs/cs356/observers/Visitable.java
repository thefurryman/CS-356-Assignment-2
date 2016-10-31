package edu.cpp.cs.cs356.observers;

public interface Visitable {

	public void accept(Visitor visitor);
	public void notifyVisitors();
	//public void remove(Visitor visitor);
}
