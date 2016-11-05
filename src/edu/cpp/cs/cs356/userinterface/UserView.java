package edu.cpp.cs.cs356.userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.cpp.cs.cs356.assignment2.Service;
import edu.cpp.cs.cs356.observers.TreeElement;
import edu.cpp.cs.cs356.observers.User;

/**
 * This class is the frame for the window that displays a User's information such as who the user is following
 * as well as updates from those users in a NewsFeed. Has functions such as posting a tweet and following users.
 */
public class UserView extends JFrame {

	private List<JComponent> elements;
	private Service service;
	private User user;
	private DefaultMutableTreeNode root;
	private JPanel userPanel;
	private JTextField userID;
	private JButton followUserBtn;
	private JTextArea messageArea;
	private JButton postTweetBtn;
	private ListFollowing curFollowing;
	private NewsFeed newsFeed;
	
	protected UserView(Service service, DefaultMutableTreeNode root, User user) {
		super("User View");
		
		userPanel = new JPanel();
		elements = new ArrayList<>();
		
		this.user = user;
		this.service = service;
		this.root = root;
		
		setSize(500,400);
		setResizable(true);
		setLocationRelativeTo(null);
		
		setUserIDField();
		setFollowUserBtn();
		setListFollowing();
		setMessageArea();
		setPostTweetBtn();
		setNewsFeed();
	}
	
	protected UserView() {
		
	}
	
	public void refreshNews() {
		newsFeed.insertFeed();
	}
	
	/** Initialize TextArea to input a tweet element */
	private void setMessageArea() {
		messageArea = new JTextArea("Enter a message", 4, 10);
		messageArea.setLocation(10, 5);
		elements.add(messageArea);
	}
	
	/** Initialize userID TextField element */
	private void setUserIDField() {
		userID = new JTextField("User ID", 10);
		elements.add(userID);
	}
	
	/** Initialize List of users that the selected user following */
	private void setListFollowing() {
		curFollowing = new ListFollowing(user);
		elements.add(curFollowing.getPanel());
	}
	
	/** Initialize NewsFeed element */
	private void setNewsFeed() {
		newsFeed = new NewsFeed(user);
		user.setNewsFeed(newsFeed);
		elements.add(newsFeed.getPanel());
	}
	
	/**
	 * Once the user clicks "Follow User" this update the List containing people that the user is following
	 * will add person as follower and for the user will add the person as following
	 */
	private void setFollowUserBtn() { 
		followUserBtn = new JButton("Follow User");
		followUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User userToFollow = findUser(root, userID.getText());
				if (userToFollow != null && !(userToFollow.getID().equals(user.getID()))) {
					boolean isDuplicate = false;
					for (int i = 0; i < user.getFollowing().size(); i++) {
						if (user.getFollowing().get(i).getID().equals(userToFollow.getID())) {
							isDuplicate = true;
						}
					}
					if (!isDuplicate) {
						userToFollow.accept(user);
						user.addFollowing(userToFollow);
						curFollowing.addUser(userToFollow);
					}
				}
			}
		});
		elements.add(followUserBtn);
	}
	
	/** Initialize the Post Tweet button and sets the action listener with button function */
	private void setPostTweetBtn() { 
		postTweetBtn = new JButton("Post Tweet");
		postTweetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tweet = messageArea.getText();
				user.postTweet(user.getID() + " - " + tweet);
				newsFeed.addTweet(user.getID() + " - " + tweet);
				messageArea.setText("*Tweet posted");
			}
		});
		elements.add(postTweetBtn);
	}
	
	/**
	 * Finds users by traversing through the tree and examining nodes
	 */
	protected User findUser(DefaultMutableTreeNode node, String userName) { 
		User tempUser;
		if  ((TreeElement) node.getUserObject() instanceof User) {
			tempUser = (User) node.getUserObject();
			if (tempUser.getID().equals(userName)) {
				return tempUser;
			}
		} else {
			List<DefaultMutableTreeNode> list = Collections.list(node.depthFirstEnumeration());
			for (int i = 0; i < list.size(); i++) {
				if ((TreeElement) list.get(i).getUserObject() instanceof User) {
					tempUser = (User) list.get(i).getUserObject();
					if (tempUser.getID().equals(userName)) {
						return tempUser;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * DEPRICATED
	 * This implementaiton requires that a class, in this case Service.class, to keep track of added Users
	 * by a List. This will simply iterate through the list and find the User. 
	 */
	private User findUser(String userName) { 
		for (User user : service.getUsers()) {
			if (user.getID().equals(userName)) {
				return user;
			}
		}
		return null;
	}
	
	/** Called to display all components of this frame */
	protected void display() {
		setTitle(user.getID() + " - User View");
		
		for (JComponent ele : elements) {
			userPanel.add(ele);
		}
		add(userPanel);
		setVisible(true);
	}
}
