package edu.cpp.cs.cs356.userinterface;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import edu.cpp.cs.cs356.observers.User;
import edu.cpp.cs.cs356.observers.Visitor;

public class NewsFeed {

	private JPanel panel;
	private JList list;
	private DefaultListModel<String> listModel;
	
	private User user;
	
	public NewsFeed(User user) {
		panel = new JPanel();
		listModel = new DefaultListModel<>();
		list = new JList(listModel);
		this.user = user;
		
		listModel.addElement("------ News Feed -------");
		insertFeed();
		
		list.setVisibleRowCount(5);
		list.setLayoutOrientation(0);
		list.ensureIndexIsVisible(0);
		panel.add(list);
	}
	
	public void addTweet(String tweet) {
		listModel.addElement(tweet);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	private void insertFeed() {
		for (int i = user.getNewsFeed().size() - 1; i >= 0; i--) {
			listModel.addElement(user.getNewsFeed().get(i));
		}
	}
}
