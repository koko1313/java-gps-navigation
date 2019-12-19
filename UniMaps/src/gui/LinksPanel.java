package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import uni.fmi.informatics.LinkType;
import uni.fmi.informatics.Map;

public class LinksPanel extends JPanel implements ActionListener{

	private JTextField linkLengthTextField = new JTextField(10);;
	
	public static JComboBox<String> linkStartNodeComboBox = new JComboBox<String>();
	public static JComboBox<String> linkEndNodeComboBox = new JComboBox<String>();
	
	private JComboBox<String> linkTypeComboBox;
	
	private JCheckBox isTwoWayCheckBox = new JCheckBox("Two way", true);
	
	private JButton addLinkButton = new JButton("Add Path");
	
	private JLabel linkStartNodeLabel = new JLabel("Start");
	private JLabel linkEndNodeLabel = new JLabel("End");
	private JLabel linkLengthLabel = new JLabel("Length");
	private JLabel linkTypeLabel = new JLabel("Type");
	
	private JPanel startNodePanel = new JPanel(new GridLayout(0, 2));
	private JPanel endNodePanel = new JPanel(new GridLayout(0, 2));
	private JPanel lengthPanel = new JPanel(new GridLayout(0, 2));
	private JPanel linkTypePanel = new JPanel(new GridLayout(0, 2));
	
	public LinksPanel() {
		setLayout(new GridLayout(0, 1));
		
		String[] linkTypes = {"Normal", "First_Class", "Highway"}; //get month names
		linkTypeComboBox = new JComboBox<String>(linkTypes);
		
		addLinkButton.addActionListener(this);
		
		startNodePanel.add(linkStartNodeLabel);
		startNodePanel.add(linkStartNodeComboBox);
		
		endNodePanel.add(linkEndNodeLabel);
		endNodePanel.add(linkEndNodeComboBox);
		
		lengthPanel.add(linkLengthLabel);
		lengthPanel.add(linkLengthTextField);
		
		linkTypePanel.add(linkTypeLabel);
		linkTypePanel.add(linkTypeComboBox);
		
		add(startNodePanel);
		add(endNodePanel);
		add(lengthPanel);
		add(linkTypePanel);
		
		add(isTwoWayCheckBox);
		
		add(addLinkButton);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(linkLengthTextField.getText().equals("") ||
			linkStartNodeComboBox.getSelectedItem().toString().equals("") ||
			linkEndNodeComboBox.getSelectedItem().toString().equals("") ||
			linkTypeComboBox.getSelectedItem().toString().equals("")) {
			
			JOptionPane.showMessageDialog(null, "Please fill out all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String fromName = linkStartNodeComboBox.getSelectedItem().toString();
		String toName = linkEndNodeComboBox.getSelectedItem().toString();
		LinkType type = LinkType.valueOf(linkTypeComboBox.getSelectedItem().toString().toUpperCase());
		double length;
		boolean isTwoWay = isTwoWayCheckBox.isSelected();
		
		try {
			length = Double.parseDouble(linkLengthTextField.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Incorrect input values.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Map.addLink(fromName, toName, type, length, isTwoWay);
		
		cleanFields();
	}
	
	private void cleanFields() {
		linkStartNodeComboBox.setSelectedIndex(0);
		linkEndNodeComboBox.setSelectedIndex(0);
		linkLengthTextField.setText("");
		linkTypeComboBox.setSelectedIndex(0);
		isTwoWayCheckBox.setSelected(false);
	}
	
	public static void addToCombos(String value) {
		linkStartNodeComboBox.addItem(value);
		linkEndNodeComboBox.addItem(value);
	}
	
}
