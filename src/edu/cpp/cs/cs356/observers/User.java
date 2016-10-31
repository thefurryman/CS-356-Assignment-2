package edu.cpp.cs.cs356.observers;

import java.util.ArrayList;
import java.util.List;

public class User implements Visitable, Visitor {

	private int numFollowers;
	private String tweet;
	private List<Visitor> followers;
	

	public User() {
		followers = new ArrayList<>();
		numFollowers = 0;
	}
	
	@Override
	public void accept(Visitor visitor) {
		followers.add(visitor);
	}

	@Override
	public void notifyVisitors() {
		for (Visitor vis : followers) {
			vis.update(this);
		}
	}
	
	public String getTweet() {
		return tweet;
	}
	
	public void postTweet(String tweet) {
		this.tweet = tweet;
	}

	@Override
	public void visit(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}
}
