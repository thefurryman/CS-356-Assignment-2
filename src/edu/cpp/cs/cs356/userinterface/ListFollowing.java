package edu.cpp.cs.cs356.userinterface;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;


import edu.cpp.cs.cs356.observers.User;
import edu.cpp.cs.cs356.observers.Visitor;

/**
 * 
 * @author tofum
 * This class is List on the UserView panel that displays the users
 * that a specific user is following.
 */
public class ListFollowing {

	private JPanel panel;
	private JList list;
	private DefaultListModel<String> listModel;
	
	private User user;
	
	public ListFollowing(User user) {
		panel = new JPanel();
		this.user = user;
		
		listModel = new DefaultListModel<>();
		listModel.addElement("------ Currently Following -------");
		insertUsers();
		
		list = new JList(listModel);
		
		list.setVisibleRowCount(5);
		list.setLayoutOrientation(0);
		list.ensureIndexIsVisible(0);
		panel.add(list);
	}
	
	public void addUser(User user) {
		listModel.addElement(user.getID());
	}
	
	public void addUser(String name) { 
		listModel.addElement(name);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	/**
	 * Whenever the UserView panel is opened, this will immediately
	 * get all the users that a user is following and display them.
	 */
	private void insertUsers() {
		for(Visitor us : user.getFollowing()) {
				listModel.addElement(us.getID());
		}
	}
}
