package edu.cpp.cs.cs356.userinterface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * An auxiliary display class that is easy to call
 * for a popup window to display things. Usually
 * error messages and statistic values such as
 * total messages and such.
 *
 */
public class PopupLabel extends JFrame {

	private JPanel panel = new JPanel();
	private JLabel label;
	
	public PopupLabel(String value, String title) {
		super(title);
		setProperties();
		
		label = new JLabel(value);
		panel.add(label);
		add(panel);
	}
	
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
		
		Double d = value * 100;
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
