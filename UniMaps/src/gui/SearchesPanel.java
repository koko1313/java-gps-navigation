package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import searches.*;
import uni.fmi.informatics.Map;
import uni.fmi.informatics.Path;

public class SearchesPanel extends JPanel implements ActionListener{

	private JTextField fromTextField = new JTextField(10);
	private JTextField toTextField = new JTextField(10);
	private JTextField maxPathLengthTextField = new JTextField(10);
	private JTextField islandsTextField = new JTextField(10);
	
	private JButton greedySearchByWeightButton = new JButton("Greedy search by weight");
	private JButton greedySearchByLinkLengthButton = new JButton("Greedy search by link length");
	private JButton lightOfSightSearchButton = new JButton("Light of sight search");
	private JButton shortestWayWithAvoidsButton = new JButton("Find shortest way");
	private JButton breadthSearchButton = new JButton("Breadth search");
	
	private JLabel fromLabel = new JLabel("From");
	private JLabel toLabel = new JLabel("To");
	private JLabel maxPathLengthLabel = new JLabel("Max intermediate path length");
	private JLabel islandsLabel = new JLabel("Go through (use ',' for delimiter)");
	
	private JCheckBox avoidNormalCheckBox = new JCheckBox("Avoid normal paths");
	private JCheckBox avoidHighWayCheckBox = new JCheckBox("Avoid highways");
	private JCheckBox avoidFirstClassCheckBox = new JCheckBox("Avoid first class paths");
	
	private JPanel fromToPanel = new JPanel(new GridLayout(0, 1));
	private JPanel fromPanel = new JPanel(new GridLayout(0, 2));
	private JPanel toPanel = new JPanel(new GridLayout(0, 2));
	private JPanel criteriaPanel = new JPanel(new GridLayout(0, 1));
	private JPanel maxPathLengthPanel = new JPanel(new GridLayout(0, 2));
	private JPanel islandsPanel = new JPanel(new GridLayout(0, 2));
	private JPanel checkBoxesPanel = new JPanel(new GridLayout(0, 3));
	private JPanel buttonsPanel = new JPanel(new GridLayout(0, 2));
	
	public SearchesPanel() {
		setLayout(new GridLayout(0, 1));

		fromPanel.add(fromLabel);
		fromPanel.add(fromTextField);
		fromToPanel.add(fromPanel);
		add(fromToPanel);
		
		toPanel.add(toLabel);
		toPanel.add(toTextField);
		fromToPanel.add(toPanel);
		add(fromToPanel);
		
		criteriaPanel.setBorder(BorderFactory.createTitledBorder("Criteria"));
		
		maxPathLengthPanel.add(maxPathLengthLabel);
		maxPathLengthPanel.add(maxPathLengthTextField);
		criteriaPanel.add(maxPathLengthPanel);
		
		islandsPanel.add(islandsLabel);
		islandsPanel.add(islandsTextField);
		criteriaPanel.add(islandsPanel);
		
		checkBoxesPanel.add(avoidNormalCheckBox);
		checkBoxesPanel.add(avoidHighWayCheckBox);
		checkBoxesPanel.add(avoidFirstClassCheckBox);
		criteriaPanel.add(checkBoxesPanel);
		
		add(criteriaPanel);
		
		greedySearchByWeightButton.addActionListener(this);
		greedySearchByLinkLengthButton.addActionListener(this);
		lightOfSightSearchButton.addActionListener(this);
		shortestWayWithAvoidsButton.addActionListener(this);
		breadthSearchButton.addActionListener(this);
		
		buttonsPanel.add(greedySearchByWeightButton);
		buttonsPanel.add(greedySearchByLinkLengthButton);
		buttonsPanel.add(lightOfSightSearchButton);
		buttonsPanel.add(shortestWayWithAvoidsButton);
		buttonsPanel.add(breadthSearchButton);
		
		add(buttonsPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String from = fromTextField.getText();
		String to = toTextField.getText();
		
		boolean avoidNormalPaths = avoidNormalCheckBox.isSelected();
		boolean avoidHighways = avoidHighWayCheckBox.isSelected();
		boolean avoidFistClassPaths = avoidFirstClassCheckBox.isSelected();
		double maxPathLength = Double.MAX_VALUE;
		String islands = from + "," + to;
		
		if(!islandsTextField.getText().equals("")) {
			islands = from + "," + islandsTextField.getText().replaceAll("\\s+","") + "," + to;
		}
		
		if(!maxPathLengthTextField.getText().equals("")) {
			maxPathLength = Double.parseDouble(maxPathLengthTextField.getText());
		}
		
		String[] islandsArray = islands.split(",");
		
		ArrayList<Path> allPaths = new ArrayList<Path>(); // трябва ни, ако имаме повече от един път (през острови)

		ISearch algorythm = null;
		
		SearchCriteria criteria = new SearchCriteria(avoidNormalPaths, avoidHighways, avoidFistClassPaths, maxPathLength);
		
		if(e.getSource() == greedySearchByLinkLengthButton) {
			Console.println("Greedy search by link length from " + from + " to " + to);
			algorythm = new GreedySearchByLinkLength(Map.g, criteria);
		} else
			
		if(e.getSource() == shortestWayWithAvoidsButton) {
			Console.println("Shortest way from " + from + " to " + to);
			algorythm = new DijkstraSearch(Map.g, criteria);
		} else
			
		if(e.getSource() == greedySearchByWeightButton) {
			Console.println("Greedy search by weight from " + from + " to " + to);
			algorythm = new GreedySearchByWeight(Map.g, criteria);
		} else
			
		if(e.getSource() == lightOfSightSearchButton) {
			Console.println("Light of sight search from " + from + " to " + to);
			algorythm = new LightOfSightSearch(Map.g, criteria);
		} else
		
		if(e.getSource() == breadthSearchButton) {
			Console.println("Breadth search from " + from + " to " + to);
			algorythm = new BreadthSearch(Map.g, criteria);
		}
		
		allPaths = searchPath(from, to, islandsArray, algorythm);
		
		Path.printPath(allPaths);
	}
	
	private ArrayList<Path> searchPath(String from, String to, String[] islandsArray, ISearch algorythm) {
		ArrayList<Path> allPaths = new ArrayList<Path>();
		
		for(int i=0; i<islandsArray.length-1; i=i+1) {
			from = islandsArray[i];
			to = islandsArray[i+1];
			
			if(Map.g.searchPath(from, to, algorythm)) {
				Path path = Path.createPath( Map.g.getMap().get(to) );
				allPaths.add(path);
			}
		}
		
		return allPaths;
	}
	
}
