package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import uni.fmi.informatics.Map;

public class NodesPanel extends JPanel implements ActionListener{

	private JTextField nodeNameTextField = new JTextField(10);
	private JTextField nodeXCoordTextField = new JTextField(10);
	private JTextField nodeYCoordTextField = new JTextField(10);
	private JTextField nodeWeightTextField = new JTextField(10);
	
	private JLabel nodeNameLabel = new JLabel("Name");
	private JLabel nodeXCoordLabel = new JLabel("X coord");
	private JLabel nodeYCoordLabel = new JLabel("Y coord");
	private JLabel nodeWeightLabel = new JLabel("Weight");
	
	private JButton addNodeButton = new JButton("Add City");
	
	private JPanel namePanel = new JPanel(new GridLayout(0, 2));
	private JPanel xCoordPanel = new JPanel(new GridLayout(0, 2));
	private JPanel yCoordPanel = new JPanel(new GridLayout(0, 2));
	private JPanel weightPanel = new JPanel(new GridLayout(0, 2));
	
	public NodesPanel() {
		setLayout(new GridLayout(0, 1));
		
		namePanel.add(nodeNameLabel);
		namePanel.add(nodeNameTextField);
		
		xCoordPanel.add(nodeXCoordLabel);
		xCoordPanel.add(nodeXCoordTextField);
		
		yCoordPanel.add(nodeYCoordLabel);
		yCoordPanel.add(nodeYCoordTextField);
		
		weightPanel.add(nodeWeightLabel);
		weightPanel.add(nodeWeightTextField);
		
		add(namePanel);
		add(xCoordPanel);
		add(yCoordPanel);
		add(weightPanel);
		
		add(addNodeButton);
		
		addNodeButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(nodeNameTextField.getText().equals("") ||
			nodeXCoordTextField.getText().equals("") ||
			nodeYCoordTextField.getText().equals("") ||
			nodeWeightTextField.getText().equals("")
				) {
			
			JOptionPane.showMessageDialog(null, "Please fill out all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String name = nodeNameTextField.getText();
		int xCoord;
		int yCoord;
		double weight;
		
		try {
			xCoord = Integer.parseInt(nodeXCoordTextField.getText());
			yCoord = Integer.parseInt(nodeYCoordTextField.getText());
			weight = Double.parseDouble(nodeWeightTextField.getText());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Incorrect input values.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Map.addNode(name, weight, xCoord, yCoord);
		
		cleanFields();
	}
	
	private void cleanFields() {
		nodeNameTextField.setText("");
		nodeXCoordTextField.setText("");
		nodeYCoordTextField.setText("");
		nodeWeightTextField.setText("");
	}
	
}
