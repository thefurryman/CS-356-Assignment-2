package edu.cpp.cs.cs356.observers;

import java.util.ArrayList;
import java.util.List;

import edu.cpp.cs.cs356.userinterface.NewsFeed;

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
	private String tweet;
	private List<Visitor> followers;
	private List<Visitor> following;
	private List<String> newsFeed;
	private int totalMessages;
	private int totalPositiveMes;
	private NewsFeed feed;
	private final String [] POSITIVE_TERMS;

	public User(String userID) {
		this.userID = userID;
		followers = new ArrayList<>();
		following = new ArrayList<>();
		newsFeed = new ArrayList<>();
		totalMessages = 0;
		totalPositiveMes = 0;
		POSITIVE_TERMS = new String[] {
				"NICE", "GOOD", "FINE", "HELLO", "GREAT"
		};
	}
	
	public void setNewsFeed(NewsFeed feed) {
		this.feed = feed;
	}
	
	public void updateFeed() {
		this.feed.update();
	}
	
	@Override
	public void accept(Visitor visitor) {
		followers.add(visitor);
	}

	@Override
	public void notifyVisitors() {
		for (Visitor vis : followers) {
			User user = (User) vis;
			vis.update(this);
			user.updateFeed();
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
		this.tweet = tweet;
		newsFeed.add(tweet);
		totalMessages++;
		
		for (String pos : POSITIVE_TERMS) {
			if (tweet.toUpperCase().contains(pos)) {
				totalPositiveMes++;
				break;
			}
		}
		notifyVisitors();
	}
	
	public int getPositiveMessagesCountNewsFeed() {
		int positiveCount = 0;
		for (int i = 0; i < newsFeed.size(); i++) {
			if (containsPositiveMessage(newsFeed.get(i))) {
				positiveCount++;
			}
		}
			
		return positiveCount;
	}
	
	private boolean containsPositiveMessage(String message) {
		for (String pos : POSITIVE_TERMS) {
			if (message.toUpperCase().contains(pos)) {
				return true;
			}
		}
		return false;
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
