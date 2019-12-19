package uni.fmi.informatics;

import gui.Console;
import gui.LinksPanel;

public class Map {

	public static Graph g = new Graph();
	
	public static void addNode(String name, double weight, int xCoord, int yCoord) {
		Node node = new Node(name, weight, xCoord, yCoord);
		
		boolean contains = false;
		
		for(Node n : g.getMap().values()) {
			if(n.getName().equals(name)) {
				contains = true;
				break;
			}
		}
		
		if(!contains) {
			g.addNode(node);
			
			LinksPanel.addToCombos(name);
			
			Console.println("The node "+ node.getName() + ", xCoord: " + xCoord + ", yCoord: " + yCoord + ", weight: " + weight +" was added successfully.");
		} else {
			Console.println("The node "+ node.getName() +" already exist.");
		}
	}
	
	public static void addLink(String fromName, String toName, LinkType type, double length, boolean isTwoWay) {
		Node from = g.getMap().get(fromName);
		Node to = g.getMap().get(toName);
		
		boolean contains = false;
		
		Node n = g.getMap().get(fromName);
		
		for(Link l : n.getLinks()) {
			if(l.getToNode().getName().equals(toName)) {
				contains = true;
				break;
			}
		}
		
		if(!contains) {
			if(isTwoWay) {
				g.addTwoWayRoute(from, to, length, type);
			} else {
				g.addRoute(from, to, length, type);
			}
			Console.println("The link from "+ fromName + " to " + toName + ", length: " + length + ", type: " + type  + ", two way: "+ isTwoWay +" was added successfully.");
		} else {
			Console.println("The link from "+ fromName + " to " + toName + " already exist.");
		}
	}

	public static void resetMap() {
		g = new Graph();
		Console.clearConsole();
	}
}
