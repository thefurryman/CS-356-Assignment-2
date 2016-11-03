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
import edu.cpp.cs.cs356.observers.User;

public class UserTreeView {
	
	private JPanel panel = new JPanel();
	private JTree userTree;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode lastSelected;
	
	public UserTreeView() {
		super();
		
		root = new DefaultMutableTreeNode("Root");
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
	
	public void addGroup(DefaultMutableTreeNode parent, String groupName) {
		if (!checkIfEmpty(groupName)) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode("*" + groupName);
			checkIfGroup(parent);
			parent.add(node);
			reloadTree();
		}
		
	}
	public void addUser(DefaultMutableTreeNode parent, User user) {
		if (!checkIfEmpty(user.getID())) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(user);
			checkIfGroup(parent);
			parent.add(node);
			reloadTree();
		}
	}
	
//	public void addUser(DefaultMutableTreeNode parent, String userName) {
//		if (!checkIfEmpty(userName)) {
//			DefaultMutableTreeNode node = new DefaultMutableTreeNode(userName);
//			checkIfGroup(parent);
//			parent.add(node);
//			reloadTree();
//		}
//	}
	
	private void reloadTree() {
		DefaultTreeModel model = (DefaultTreeModel) userTree.getModel();
		model.reload();
	}

	public JTree getTree() {
		return userTree;
	}
	
	private void checkIfGroup(DefaultMutableTreeNode parent) {
		if (parent.getUserObject().toString().contains("*")) {
			StringBuilder sb = new StringBuilder(parent.getUserObject().toString());
			sb.deleteCharAt(0);
			parent.setUserObject(sb.toString());
		}
	}
	
	private boolean checkIfEmpty(String text) {
		if (text.toCharArray()[0] == 0) {
			return true;
		}
		return false;
	}
}
