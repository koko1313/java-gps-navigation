package gui;

import javax.swing.*;

import java.awt.*;

public class MapsGUI extends JFrame{
		
	public MapsGUI() {
		super("UNI Maps");
		setSize(600, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setJMenuBar(new MenuBar());
		
		JPanel mainPanel = new JPanel(new GridLayout(0, 1));
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Add Cities", new NodesPanel());
		tabs.addTab("Add paths", new LinksPanel());
		tabs.addTab("Searches", new SearchesPanel());
		tabs.addTab("View Map", new DrawPanel());
		
		mainPanel.add(tabs);
		
		mainPanel.add(new Console());
		
		add(mainPanel);
		
		setVisible(true);
	}
	
}
