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

public class ControlPanel extends JFrame  {

	private static ControlPanel INSTANCE;
	private List<JComponent> elements;
	
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
	
	private ControlPanel() {
		super("Admin Control Panel");
		
		setSize(500,400);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		elements = new ArrayList<>();
		
		treeView = new JTree(); ///////////////
		
		userID = new JTextField("Enter User ID", 10);
		
		addUserBtn = new JButton("Add User");
		
		groupID = new JTextField("Enter Group ID", 10);
		addGroupBtn = new JButton("Add Group");
		
		openUserBtn = new JButton("Open User View");
		openUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserView uView = new UserView();
				uView.display();
			}
		});
		
		showUserTotalBtn = new JButton("Show User Total");
		showGroupTotalBtn = new JButton("Show Group Total");
		showMessagesTotalBtn = new JButton("Show Messages Total");
		showPositivePercentageBtn = new JButton("Show Positive Percentage");
		
		elements.add(treeView);
		elements.add(userID);
		elements.add(addUserBtn);
		elements.add(groupID);
		elements.add(addGroupBtn);
		elements.add(openUserBtn);
		elements.add(showUserTotalBtn);
		elements.add(showGroupTotalBtn);
		elements.add(showMessagesTotalBtn);
		elements.add(showPositivePercentageBtn);
	}
	
	public static ControlPanel getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ControlPanel();
		}
		return INSTANCE;
	}
	
	public void display() {
//		window.setLayout(new GridLayout(2,2));
//		window.add(treeView, BorderLayout.PAGE_START);
//		window.add(userID, BorderLayout.; //FIX WINDOW LAYOUT!!!!!!
//		window.add(addUserBtn, BorderLayout.PAGE_START);
		for (JComponent element: elements) {
			window.add(element);
		}
		//window.add(addGroupBtn, GridLayout);
		add(window);
		setVisible(true);
	}
}
