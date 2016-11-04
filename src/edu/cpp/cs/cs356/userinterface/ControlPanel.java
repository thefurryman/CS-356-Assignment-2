package edu.cpp.cs.cs356.userinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.cpp.cs.cs356.assignment2.Service;
import edu.cpp.cs.cs356.observers.Group;
import edu.cpp.cs.cs356.observers.TreeElement;
import edu.cpp.cs.cs356.observers.User;

/**
 * 
 * The entry point to the program and is a singleton
 *
 */
public class ControlPanel extends JFrame  {

	private static ControlPanel INSTANCE;
	private Service service;
	
	private JPanel window = new JPanel();
	
	private JTextField userID;
	private JButton addUserBtn;

	private JTextField groupID;
	private JButton addGroupBtn;
	private JButton openUserBtn;
	
	private JButton showUserTotalBtn;
	private JButton showGroupTotalBtn;
	private JButton showMessagesTotalBtn;
	private JButton showPositivePercentageBtn;
	
	private UserTreeView userTree;
	
	private List<JComponent> components;
	
	private ControlPanel() {
		super("Admin Control Panel");
		
		components = new ArrayList<>();
		service = new Service();
		
		setSize(550,400);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setUserTree();
		
		setUserIDField();		
		setAddUserBtn();

		setGroupIDField();
		setAddGroupBtn();
		
		setOpenUserBtn();
		
		setUserTotalBtn();
		setGroupTotalBtn();
		setMessagesTotalBtn();
		setPositivePercBtn();
	}
	
	public static ControlPanel getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ControlPanel();
		}
		return INSTANCE;
	}
	
	private void setUserTree() {
		userTree = new UserTreeView();
		//JScrollPane pane = new JScrollPane(userTree.getPanel());
		//components.add(pane);
		components.add(userTree.getPanel());
	}
	
	protected UserTreeView getTree() {
		return userTree;
	}
		
	private void setAddUserBtn() {
		addUserBtn = new JButton("Add User");
		addUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((TreeElement) userTree.getLastSelected().getUserObject() instanceof Group) {
					User user = new User(userID.getText());
					userTree.addUser(userTree.getLastSelected(), user);
					service.setTotalUsers(service.getTotalUsers() + 1);
					service.addUser(user);
					userID.setText("");
				} else {
					new PopupLabel("Select a group/directory to add a user to");
				}
			}
		});
		components.add(addUserBtn);
	}
	
	private void setAddGroupBtn() {
		addGroupBtn = new JButton("Add Group");
		addGroupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((TreeElement) userTree.getLastSelected().getUserObject() instanceof Group) {
					TreeElement group = new Group("*" + groupID.getText());
					userTree.addGroup(userTree.getLastSelected(), group);
					service.setTotalGroups(service.getTotalGroups() + 1);
					groupID.setText("");
				} else {
					new PopupLabel("Select a group to add a group, not a user");
				}
			}
		});
		components.add(addGroupBtn);
	}
	
	private void setGroupIDField() {
		groupID = new JTextField("Enter Group ID", 10);
		components.add(groupID);
	}
	
	private void setUserIDField() {
		userID = new JTextField("Enter User ID", 10);
		components.add(userID);
	}
	
	/**
	 * this Button will open the User View for the selected user node.
	 * If a group is selected, will open an error popup window.
	 */
	private void setOpenUserBtn() {
		openUserBtn = new JButton("Open User View");
		openUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((TreeElement) userTree.getLastSelected().getUserObject() instanceof User) {
					User user = (User) userTree.getLastSelected().getUserObject();
					UserView uView = new UserView(service, userTree.getRoot(),  user);
					uView.display();
				} else {
					new PopupLabel("Group selected! Click on a user instead");
				}
			}
		});
		components.add(openUserBtn);
	}

	/**
	 * Same as below for setGroupTotalBtn(). If selected node is a User, 
	 * displays 1 in a small window.
	 */
	private void setUserTotalBtn() {
		showUserTotalBtn = new JButton("Show User Total");
		showUserTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = userTree.getLastSelected();
				if ((TreeElement) node.getUserObject() instanceof Group) {
					List<DefaultMutableTreeNode> list = Collections.list(node.depthFirstEnumeration());
					int numUsers = 0;
					
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getUserObject() instanceof User) {
							numUsers++;
						}
					}
					new PopupLabel(numUsers);
				} else if (node.getUserObject() instanceof User) {
					new PopupLabel(1);
				} else {
					new PopupLabel("error");
				}
			}
		});
		components.add(showUserTotalBtn);
	}
	
	/*
	 * If selected node is a group, will calculate total of all groups inside of the 
	 * group INCLUDING ITSELF. So a clean user tree from a clean program will display 
	 * group total = 1 if Root is selected. If a user is selected, returns 0.
	 */
	private void setGroupTotalBtn() {
		showGroupTotalBtn = new JButton("Show Group Total");
		showGroupTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = userTree.getLastSelected();
				if ((TreeElement) node.getUserObject() instanceof Group) {
					List<DefaultMutableTreeNode> list = Collections.list(node.depthFirstEnumeration());
					int numGroups = 0;
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getUserObject() instanceof Group) {
							numGroups++;
						}
					}
					new PopupLabel(numGroups);
				} else if (node.getUserObject() instanceof User) {
					new PopupLabel(0);
				} else {
					new PopupLabel("error");
				}
			}
		});
		components.add(showGroupTotalBtn);
	}
	
	/**
	 * Same as setPositivePercBtn() below, but just for seeing total messages in a group or
	 * from a user
	 */
	private void setMessagesTotalBtn() {
		showMessagesTotalBtn = new JButton("Show Messages Total");
		showMessagesTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = userTree.getLastSelected();
				if ((TreeElement) node.getUserObject() instanceof User) {
					User user = (User) node.getUserObject();
					new PopupLabel(user.getTotalMessages());
				} else if ((TreeElement) node.getUserObject() instanceof Group) {
					List<DefaultMutableTreeNode> list = Collections.list(node.depthFirstEnumeration());
					int totalMes = 0;
					User temp;
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getUserObject() instanceof User) {
							temp = (User) list.get(i).getUserObject();
							totalMes += temp.getTotalMessages();
						}
					} 
					new PopupLabel(totalMes);
				} else {
					new PopupLabel("error");
				}
			}
		});
		components.add(showMessagesTotalBtn);
	}
	
	/**
	 * Starting at the last selected node (user/group), will determine if it is a user or group
	 *  and adjust percentage accordingly. Performs DFS on a group node and will calculate positive
	 *  percentage on all users nodes and open a small window to display it. If selected node is a user,
	 *  then will open small window with just that user's positive percentage
	 */
	private void setPositivePercBtn() {
		showPositivePercentageBtn = new JButton("Show Positive Percentage");
		showPositivePercentageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = userTree.getLastSelected();
				if ((TreeElement) node.getUserObject() instanceof User) {
					User user = (User) node.getUserObject();
					if (user.getTotalMessages() != 0) {
						double posPerc = (double) user.getTotalPositiveMessages() / user.getTotalMessages();
						new PopupLabel(posPerc);
					} else {
						new PopupLabel(0.0);
					}
				} else if ((TreeElement) node.getUserObject() instanceof Group) {
					List<DefaultMutableTreeNode> list = Collections.list(node.depthFirstEnumeration());
					int totalMes = 0;
					int totalPosMes = 0;
					User temp;
					for (int i = 0; i < list.size(); i++) {
						if ((TreeElement) list.get(i).getUserObject() instanceof User) {
							temp = (User) list.get(i).getUserObject();
							totalMes += temp.getTotalMessages();
							totalPosMes += temp.getTotalPositiveMessages();
						}
					}
					if (totalPosMes != 0) {
						double posPerc = (double) totalPosMes / totalMes;
						new PopupLabel(posPerc);
					} else {
						new PopupLabel(0.0);
					}
				} else {
					new PopupLabel("error");
				}
			}
		});
		components.add(showPositivePercentageBtn);
	}
	
	/**
	 * from the list of all JComponents, will add them to the JPanel and 
	 * finall add the JPanel to the frame to display on screen.
	 */
	public void display() {
		for (JComponent comp : components) {
			window.add(comp);
		}
		add(window);
		setVisible(true);
	}
}
