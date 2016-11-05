package edu.cpp.cs.cs356.userinterface;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import edu.cpp.cs.cs356.observers.User;

/**
 * 
 * This is the news feed panel of the UserView where it
 * will display updates from users that the user is following.
 *
 */
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
	
	/** Called by the User class to force update a live UserView Window when
	 *  a new Tweet is posted. This is for automatic window refresh.
	 */
	public void update() {
		listModel.removeAllElements();
		listModel.addElement("------ News Feed -------");
		insertFeed();
	}
	
	protected void addTweet(String tweet) {
		listModel.addElement(tweet);
	}
	
	protected JPanel getPanel() {
		return panel;
	}
	
	/**
	 * Whenever the UserView is opened, this will populate the
	 * NewsFeed with updates from users that the specific user is following
	 * if available.
	 */
	protected void insertFeed() {
		for (int i = user.getNewsFeed().size() - 1; i >= 0; i--) {
			listModel.addElement(user.getNewsFeed().get(i));
		}
	}
}
