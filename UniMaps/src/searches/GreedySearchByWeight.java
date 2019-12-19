package searches;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

import gui.Console;
import uni.fmi.informatics.Graph;
import uni.fmi.informatics.Link;
import uni.fmi.informatics.Node;

public class GreedySearchByWeight implements ISearch{
	
	private Graph myGraph = new Graph();
	private TreeMap<String, Node> myMap = new TreeMap<String, Node>(String.CASE_INSENSITIVE_ORDER);
	
	private SearchCriteria criteria;
	
	public GreedySearchByWeight(Graph g, SearchCriteria criteria) {
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
		fromNode.setDepth(0);
		ArrayList<Node> queue = new ArrayList<Node>();
		queue.add(fromNode);
		
		
		while(!queue.isEmpty()) {
			
			Node temp = queue.get(0);
			queue.remove(0);
			
			myGraph.setDepth(temp);
			
			System.out.println("Now on: " + temp.getName() + " |Depth: " + temp.getDepth());
			Console.println("Now on: " + temp.getName() + " |Depth: " + temp.getDepth());
					
			if(temp.getName().equalsIgnoreCase(to)) {
				return true;
			}
			
			temp.setTested(true);
			
			for(Link l : temp.links) {
				
				if(!criteria.checkSearchCriteria(l)) continue;
				
				if(!l.getToNode().isTested() && !queue.contains(l.getToNode())) {
					queue.add(l.getToNode());
					
					l.getToNode().setPathVia(temp);
				}
			}
			Comparator<Node>  nodes = (n1, n2) -> Double.compare(n1.getWeight(), n2.getWeight());
			queue.sort(nodes);
			temp.setExpanded(true);
			
		}//end while
		
		return false;
		
	}//end search

}
