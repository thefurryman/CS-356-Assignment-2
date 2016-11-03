package edu.cpp.cs.cs356.observers;

import java.util.ArrayList;
import java.util.List;

public abstract class Visitable {
	//subject breaking news, gamescore
	
	private List<Visitor> visitors = new ArrayList<Visitor>();
	
	public void accept(Visitor visitor) {
		visitors.add(visitor);
	}
	public void notifyVisitors() {
		for (Visitor vis : visitors) {
			vis.update(this);
		}
	}
}
