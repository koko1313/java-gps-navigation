package searches;

import java.util.ArrayList;

import gui.Console;
import uni.fmi.informatics.Graph;
import uni.fmi.informatics.Link;
import uni.fmi.informatics.Node;

public class LightOfSightSearch implements ISearch{
	// A*
	
	private Graph g;
	
	private int x;
	private int y;
	
	private SearchCriteria criteria;
	
	public LightOfSightSearch(Graph g, SearchCriteria criteria) {
		this.g = g;
		this.criteria = criteria;
	}
	
	@Override
	public boolean search(String from, String to) {
		
		g.graphReset();
		
		if(!g.getMap().containsKey(from) || !g.getMap().containsKey(to)) {
			return false;
		}
		
		Node start = g.getMap().get(from);
		Node end = g.getMap().get(to);
		
		x = end.getX();
		y = end.getY();
		
		ArrayList<Node> queue = new ArrayList<>();
		queue.add(start);
		
		while(!queue.isEmpty()) {
			Node temp = queue.get(0);
			queue.remove(0);
			
			System.out.println("Now on: " + temp.getName());
			Console.println("Now on: " + temp.getName());
			
			g.setDepth(temp);
			temp.setTested(true);
			
			if(temp.equals(end)) {
				return true;
			}
			
			for(Link l : temp.links) {
				
				if(!criteria.checkSearchCriteria(l)) continue;
				
				Node nodeToAdd = l.getToNode();
				
				if(!nodeToAdd.isTested() && !queue.contains(nodeToAdd)) {
					addToQueue(nodeToAdd, queue);
					l.getToNode().setPathVia(temp);
				}
			}
		}
		
		return false;
	}

	private void addToQueue(Node node, ArrayList<Node> queue) {
		double directLine = Math.sqrt( Math.pow(x - node.getX(), 2) + Math.pow(y - node.getY(), 2) ); // питагорова теорема
		
		node.setDistanceToEnd(directLine);
		
		for(int i=0; i < queue.size(); i++) {
			if(queue.get(i).getDistanceToEnd() > directLine) {
				queue.add(i, node);
				return;
			}
		}
		
		queue.add(node);
	}

}
