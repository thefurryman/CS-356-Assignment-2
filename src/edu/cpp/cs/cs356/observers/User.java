package edu.cpp.cs.cs356.observers;

import java.util.ArrayList;
import java.util.List;

public class User implements Visitable, Visitor {

	private String userID;
	private int numFollowers;
	private String tweet;
	private List<Visitor> followers;
	private int totalMessages;
	private int totalPositiveMes;

	public User(String userID) {
		this.userID = userID;
		followers = new ArrayList<>();
		numFollowers = 0;
		totalMessages = 0;
		totalPositiveMes = 0;
	}
	
	@Override
	public void accept(Visitor visitor) {
		followers.add(visitor);
		numFollowers++;
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
		String[] positiveMessages = {
				"NICE", "GOOD", "FINE", "HELLO", "GREAT"
		};
		this.tweet = tweet;
		totalMessages++;
		
		for (String pos : positiveMessages) {
			if (tweet.toUpperCase().contains(pos)) {
				totalPositiveMes++;
				break;
			}
		}
		update(this);
	}
	
	public String getID() {
		return userID;
	}

	@Override
	public void visit(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(User user) {
		user.getTweet();
		//send to ListView
	}
}
