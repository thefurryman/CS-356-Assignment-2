package edu.cpp.cs.cs356.userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import edu.cpp.cs.cs356.assignment2.Service;

public class UserTreeView {
	
	private JPanel panel = new JPanel();
	private JTree userTree;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode lastSelected;
	private Service service;
	
	public UserTreeView(Service service) {
		super();
		
		root = new DefaultMutableTreeNode("Root");
		userTree = new JTree(root);
		
		setSelectionListener();
		
		this.service = service;
		
		//userTree.setShowsRootHandles(true);
		panel.add(userTree);
	}
	public DefaultMutableTreeNode getRoot() {
		return root;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void setSelectionListener() {
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
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("*" + groupName);
		checkIfGroup(parent);
		parent.add(node);
		reloadTree();
	}
	
	public void addUser(DefaultMutableTreeNode parent, String userName) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(userName);
		checkIfGroup(parent);
		parent.add(node);
		reloadTree();
	}
	
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
}
