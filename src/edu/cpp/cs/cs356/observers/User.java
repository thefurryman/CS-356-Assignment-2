package edu.cpp.cs.cs356.observers;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author tofum
 * User class that contains various attributes of a user such as:
 * its followers, who its following, total messages, total positive messages,
 * its newsFeed, and of course its userID. The User is both a Subject and
 * Observer.
 */
public class User extends Visitable implements Visitor, TreeElement {

	private String userID;
	private int numFollowers;
	private String tweet;
	private List<Visitor> followers;
	private List<Visitor> following;
	private List<String> newsFeed;
	private int totalMessages;
	private int totalPositiveMes;

	public User(String userID) {
		this.userID = userID;
		followers = new ArrayList<>();
		following = new ArrayList<>();
		newsFeed = new ArrayList<>();
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
	public void addFollowing(Visitor vis) {
		following.add(vis);
	}
	
	public List<Visitor> getFollowing() {
		return following;
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
		notifyVisitors();
	}
	
	public int getTotalPositiveMessages() {
		return totalPositiveMes;
	}
	
	public int getTotalMessages() {
		return totalMessages;
	}
	
	@Override
	public String getID() {
		return userID;
	}
	
	public List<Visitor> getFollowers() {
		return followers;
	}

	@Override
	public void update(Visitable vis) {
		if (vis instanceof User) {
			displayTweet(((User) vis).getTweet());
		}
	}
	
	public void displayTweet(String tweet) {
		newsFeed.add(tweet);
	}
	
	public List<String> getNewsFeed() {
		return newsFeed;
	}
	
	public String toString() {
		return userID;
	}
}
