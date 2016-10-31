package edu.cpp.cs.cs356.userinterface;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserView extends JFrame {

	private List<JComponent> elements;
	
	private JPanel userPanel;
	private JTextField userID;
	private JButton followUserBtn;
	private JList currentFollowing;
	private JTextArea messageArea;
	private JButton postTweetBtn;
	private JList newsFeed;
	
	public UserView() {
		super("User View");
		elements = new ArrayList<>();
		
		setSize(500,400);
		setResizable(true);
		
		userPanel = new JPanel();
		userID = new JTextField("User ID", 10);
		followUserBtn = new JButton("Follow User");
		currentFollowing = new JList();
		messageArea = new JTextArea("Enter a message", 4, 10);
		postTweetBtn = new JButton("Post Tweet");
		newsFeed = new JList();
		
		elements.add(userID);
		elements.add(followUserBtn);
		elements.add(currentFollowing);
		elements.add(messageArea);
		elements.add(postTweetBtn);
		elements.add(newsFeed);
	}
	
	public void display() {
		for (JComponent ele : elements) {
			userPanel.add(ele);
		}
		add(userPanel);
		setVisible(true);
	}
}
