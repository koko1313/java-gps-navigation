package searches;

import java.util.ArrayList;
import java.util.TreeMap;

import gui.Console;
import uni.fmi.informatics.Graph;
import uni.fmi.informatics.Link;
import uni.fmi.informatics.Node;

public class BreadthSearch implements ISearch {
	
	private Graph myGraph = new Graph();
	private TreeMap<String, Node> myMap = new TreeMap<String, Node>(String.CASE_INSENSITIVE_ORDER);
	
	private SearchCriteria criteria;
	
	public BreadthSearch(Graph g, SearchCriteria criteria) {
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
		
		while(!queue.isEmpty()) {
			
			Node temp = queue.get(0);
			queue.remove(0);
			System.out.println("Now on: " + temp.getName());
			Console.println("Now on: " + temp.getName());
			
			if(temp.getName().equalsIgnoreCase(to.toLowerCase())) {
				return true;
			}
			
			temp.setTested(true);
			
			for(Link l : temp.links) {
				// ако не отговаря на критериите
				if(!criteria.checkSearchCriteria(l)) continue;
				
				if(!l.getToNode().isTested() && !queue.contains(l.getToNode())) {
					queue.add(0,l.getToNode());
					l.getToNode().setPathVia(temp);
				}
			}
			
			temp.setExpanded(true);
			
		}//end while
		
		return false;
	}//end search()

}
