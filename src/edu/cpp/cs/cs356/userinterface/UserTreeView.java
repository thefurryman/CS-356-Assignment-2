package edu.cpp.cs.cs356.userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import edu.cpp.cs.cs356.assignment2.Service;
import edu.cpp.cs.cs356.observers.Group;
import edu.cpp.cs.cs356.observers.TreeElement;
import edu.cpp.cs.cs356.observers.User;

/**
 * 
 * @author tofum
 * This class controls how the JTree of users and groups on the Control Panel
 * functions. This uses the composite design pattern with an interface TreeElement
 * being implemented by a User node and Group node on the list.
 */
public class UserTreeView {
	
	private JPanel panel = new JPanel();
	private JTree userTree;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode lastSelected;
	
	public UserTreeView() {
		super();
		
		DefaultMutableTreeNode setRoot = new DefaultMutableTreeNode();
		setRoot.setUserObject(new Group("GRoot"));
		root = setRoot;
		//root = new DefaultMutableTreeNode("Root");
		lastSelected = root;
		userTree = new JTree(root);
		
		setSelectionListener();
		userTree.setShowsRootHandles(true);
		panel.add(userTree);
	}
	
	public DefaultMutableTreeNode getRoot() {
		return root;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	private void setTreeLeafIcon() {
		ImageIcon icon = new ImageIcon(UserTreeView.class.getResource("circle-512.jpg"));
		DefaultTreeCellRenderer rend = new DefaultTreeCellRenderer();
		rend.setLeafIcon(icon);
		userTree.setCellRenderer(rend);
	}
	
	/**
	 * This will set a selection listener and always return the last selected item on the tree list
	 */
	private void setSelectionListener() {
		userTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		userTree.addTreeSelectionListener(new TreeSelectionListener() {
			
			public void valueChanged(TreeSelectionEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
						userTree.getLastSelectedPathComponent();

				if (node == null) {
					lastSelected = root;
				} else {
					lastSelected = node;
				}
			}	
		});
	}
	
	public DefaultMutableTreeNode getLastSelected() {
		return lastSelected;
	}
	
	/**
	 * Sort of DEPRICATED, used in earlier versions for easier testing before
	 * using User/Group objects in the node.
	 * @param parent
	 * @param groupName
	 */
	public void addGroup(DefaultMutableTreeNode parent, String groupName) {
		if (!checkIfEmpty(groupName)) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode("*" + groupName);
			checkIfGroup(parent);
			parent.add(node);
			reloadTree();
		}
	}
	
	/**
	 * The "*" denotes it is a new group created with no children yet. Once
	 * a child has been added, the "*" will be removed automatically.
	 * @param parent
	 * @param groupName
	 */
	public void addGroup(DefaultMutableTreeNode parent, TreeElement group) {
		if (!checkIfEmpty(group.getID())) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(group);
			checkIfGroup(parent);
			parent.add(node);
			reloadTree();
		}
	}
	
	/**
	 * Same as addGroup
	 * @param parent
	 * @param user
	 */
	public void addUser(DefaultMutableTreeNode parent, User user) {
		if (!checkIfEmpty(user.getID())) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(user);
			checkIfGroup(parent);
			parent.add(node);
			reloadTree();
		}
	}
	
	/**
	 * Depricated as well, used for easy earlier testings.
	 * @param parent
	 * @param userName
	 */
	public void addUser1(DefaultMutableTreeNode parent, String userName) {
		if (!checkIfEmpty(userName)) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(userName);
			checkIfGroup(parent);
			parent.add(node);
			reloadTree();
		}
	}
	
	/**
	 * Reloads the tree model to refresh the nodes. Usually used after
	 * a User or Group has been added to the list.
	 */
	private void reloadTree() {
		DefaultTreeModel model = (DefaultTreeModel) userTree.getModel();
		model.reload();
	}

	public JTree getTree() {
		return userTree;
	}
	
	/**
	 * This is the function that fixes the group "*" issue
	 * @param parent
	 */
	private void checkIfGroup(DefaultMutableTreeNode parent) {
		if (parent.getUserObject().toString().contains("*")) {
			TreeElement group = (Group) parent.getUserObject();
			StringBuilder sb = new StringBuilder(group.getID());
			sb.deleteCharAt(0);
			parent.setUserObject(group);
		}
	}
	
	/**
	 * Checks if the input from the textbox was empty
	 * @param text
	 * @return
	 */
	private boolean checkIfEmpty(String text) {
		if (text.toCharArray()[0] == 0) {
			return true;
		}
		return false;
	}
}
