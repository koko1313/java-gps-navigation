package uni.fmi.informatics;

import java.util.TreeMap;

import gui.Console;
import searches.ISearch;

public class Graph {
	
	private TreeMap<String, Node> graph = new TreeMap<String, Node>(String.CASE_INSENSITIVE_ORDER); 
	
	public TreeMap<String, Node> getMap(){
		return graph;
	}
	
	public void addNode(Node n) {
		graph.put(n.getName(), n);
	}
	
	public boolean searchPath(String from, String to, ISearch algorithm) {
		if(algorithm.search(from, to)) {
			System.out.println("Have a path");
			Console.println("Have a path");
			return true;
		} else {
			System.out.println("Have NOT a PaTH");
			Console.println("Have NOT a PaTH");
			return false;
		}
	}

	
	public void addRoute(Node from, Node to) {
		Link link = new Link(to);
		from.getLinks().add(link);
	}
	
	public void addRoute(Node from, Node to, double length) {
		Link link = new Link(to, length);
		from.getLinks().add(link);
	}
	
	public void addRoute(Node from, Node to, double length, LinkType type) {
		Link link = new Link(to, length, type);
		from.getLinks().add(link);
	}
	
		
	public void addTwoWayRoute(Node from, Node to) {
		addRoute(from, to);
		addRoute(to, from);
	}
	
	public void addTwoWayRoute(Node from, Node to, double length) {
		addRoute(from, to, length);
		addRoute(to, from, length);
	}
	
	public void addTwoWayRoute(Node from, Node to, double length, LinkType type) {
		addRoute(from, to, length, type);
		addRoute(to, from, length, type);
	}
	
	
	public void graphReset() {
		for(Node n : graph.values()) {
			n.resetNode();
		}
	}
	
	public void setDepth(Node n) {
		for(Link l : n.links) {
			if(l.getToNode().getDepth() == -1)
				l.getToNode().setDepth(n.getDepth() + 1);
		}
	}
	
}
