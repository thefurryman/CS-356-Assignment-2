package edu.cpp.cs.cs356.userinterface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopupLabel extends JFrame {

	private JPanel panel = new JPanel();
	private JLabel label;
	
	public PopupLabel(String value) {
		super();
		setProperties();
		
		label = new JLabel(value);
		panel.add(label);
		add(panel);
	}
	
	public PopupLabel(int value) {
		super();
		setProperties();
		
		Integer n = value;
		label = new JLabel(n.toString());
		panel.add(label);
		add(panel);
	}
	
	public PopupLabel(double value) {
		super();
		setProperties();
		
		Double d = value;
		label = new JLabel(d.toString() + " %");
		panel.add(label);
		add(panel);
	}
	
	private void setProperties() {
		setSize(300,100);
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
