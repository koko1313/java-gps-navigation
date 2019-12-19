package searches;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

import gui.Console;
import uni.fmi.informatics.Graph;
import uni.fmi.informatics.Link;
import uni.fmi.informatics.Node;

public class DijkstraSearch implements ISearch{
	
	private Graph myGraph = new Graph();
	private TreeMap<String, Node> myMap = new TreeMap<String, Node>(String.CASE_INSENSITIVE_ORDER);
	
	private SearchCriteria criteria;
	
	public DijkstraSearch(Graph g, SearchCriteria criteria) {
		this.myGraph = g;
		myMap = myGraph.getMap();
		this.criteria = criteria;
	}
	
	@Override
	public boolean search(String from, String to) {
		
		myGraph.graphReset();
		
		if(!myMap.containsKey(from) || !myMap.containsKey(to)) {
			return false;
		}
		
		Node fromNode = myMap.get(from);
		ArrayList<Node> queue = new ArrayList<Node>();
		queue.add(fromNode);
		fromNode.setDistanceFromStart(0);
		
		while(!queue.isEmpty()) {
			
			Node temp = queue.get(0);
			queue.remove(0);
			System.out.println("Now on: " + temp.getName());
			Console.println("Now on: " + temp.getName());
			
			if(temp.getName().equalsIgnoreCase(to)) {
				return true;
			}
			
			temp.setTested(true);
			
			for(Link l : temp.links) {
				
				if(!criteria.checkSearchCriteria(l)) continue;
				
				if(!l.getToNode().isTested()) {
					if(!queue.contains(l.getToNode())) {
						queue.add(l.getToNode());
					}
					
					if(l.getToNode().getDistanceFromStart() > l.getLenght() + temp.getDistanceFromStart()) {
						l.getToNode().setDistanceFromStart(l.getLenght() + temp.getDistanceFromStart());
						l.getToNode().setPathVia(temp);
					}
				}
			}

			Comparator<Node>  nodes = (n1, n2) -> Double.compare(n1.getDistanceFromStart(), n2.getDistanceFromStart());
			queue.sort(nodes);
			
			printHistory(queue);
			
			temp.setExpanded(true);
			
		}//end while
		
		return false;
	}//end search()
	
	private void printHistory(ArrayList<Node> queue) {
		for(Node n : queue) {
			System.out.print(n.getName() + "(" + n.getDistanceFromStart() + ") ");
			Console.print(n.getName() + "(" + n.getDistanceFromStart() + ") ");
		}
		System.out.println();
		Console.println();
	}
	
}
