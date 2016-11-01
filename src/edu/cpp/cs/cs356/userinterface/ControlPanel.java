package edu.cpp.cs.cs356.userinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.cpp.cs.cs356.assignment2.Service;
import edu.cpp.cs.cs356.observers.User;

public class ControlPanel extends JFrame  {

	private static ControlPanel INSTANCE;
	private Service service;
	
	private JPanel window = new JPanel();
	
	private JTree treeView;
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
	
	private ControlPanel() {
		super("Admin Control Panel");
		
		service = new Service();
		
		setSize(500,400);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setUserTree(); //original
		//setTestTree(); //test
		
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
		userTree = new UserTreeView(service);
		window.add(userTree.getPanel());
	}
	
	private void setTestTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
		yes(root);
		yes(yes(root));
		DefaultMutableTreeNode sub1 = new DefaultMutableTreeNode("dog");
		root.add(sub1);
//		root.add(sub1);
//		sub1.add(new DefaultMutableTreeNode("retriever"));
		DefaultMutableTreeNode sub2 = new DefaultMutableTreeNode("cat");
		sub1.add(sub2);
		DefaultMutableTreeNode sub3 = new DefaultMutableTreeNode("fish");
		root.add(sub3);

//		sub1.add(sub2);
//		sub2.add(new DefaultMutableTreeNode("golden"));
		//root.add(sub1);
		//root.add(sub2);
		
		//System.out.println(sub1.getUserObject());
		
		treeView = new JTree(root); 
		window.add(treeView);
	}
	
	private DefaultMutableTreeNode yes(DefaultMutableTreeNode node) {
		DefaultMutableTreeNode dog = new DefaultMutableTreeNode("yes");
		node.add(dog);
		return dog;
	}
	private void setAddUserBtn() { //SET THIS
		addUserBtn = new JButton("Add User");
		addUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userTree.addUser(userTree.getLastSelected(), userID.getText()); //
				service.setTotalUsers(service.getTotalUsers() + 1);
			}
		});
		window.add(addUserBtn);
	}
	
	private void setAddGroupBtn() { //SET THIS
		addGroupBtn = new JButton("Add Group");
		addGroupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userTree.addGroup(userTree.getLastSelected(), groupID.getText());;
				//System.out.println(userTree.getLastSelected().getUserObject().toString());
				service.setTotalGroups(service.getTotalGroups() + 1);
			}
		});
		window.add(addGroupBtn);
	}
	
	private void setGroupIDField() {
		groupID = new JTextField("Enter Group ID", 10);
		window.add(groupID);
	}
	
	private void setUserIDField() {
		userID = new JTextField("Enter User ID", 10);
		window.add(userID);
	}
	
	private void setOpenUserBtn() {
		openUserBtn = new JButton("Open User View");
		openUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserView uView = new UserView(service, new User("TEMP"));
				uView.display();
			}
		});
		//get the node of the user and placei nparameter
		window.add(openUserBtn);
	}

	private void setUserTotalBtn() {
		showUserTotalBtn = new JButton("Show User Total");
		showUserTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PopupLabel(service.getTotalUsers());
			}
		});
		window.add(showUserTotalBtn);
	}
	
	private void setGroupTotalBtn() {
		showGroupTotalBtn = new JButton("Show Group Total");
		showGroupTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PopupLabel(service.getTotalGroups());
			}
		});
		window.add(showGroupTotalBtn);
	}
	
	private void setMessagesTotalBtn() {
		showMessagesTotalBtn = new JButton("Show Messages Total");
		showMessagesTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PopupLabel(service.getTotalMessages());
			}
		});
		window.add(showMessagesTotalBtn);
	}
	
	private void setPositivePercBtn() {
		showPositivePercentageBtn = new JButton("Show Positive Percentage");
		showPositivePercentageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double perc = 0;
				if (service.getTotalMessages() == 0) {
	
				} else {
					perc = ( service.getTotalPosMes() / service.getTotalMessages() );
				}
				new PopupLabel(perc);
			}
		});
		window.add(showPositivePercentageBtn);
	}

	public void display() {
//		window.setLayout(new GridLayout(2,2));
//		window.add(treeView, BorderLayout.PAGE_START);
//		window.add(userID, BorderLayout.; //FIX WINDOW LAYOUT!!!!!!
//		window.add(addUserBtn, BorderLayout.PAGE_START);

		add(window);
		setVisible(true);
	}
	
}
