package searches;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

import gui.Console;
import uni.fmi.informatics.Graph;
import uni.fmi.informatics.Link;
import uni.fmi.informatics.Node;

public class GreedySearchByLinkLength implements ISearch{
	
	private Graph myGraph = new Graph();
	private TreeMap<String, Node> myMap = new TreeMap<String, Node>(String.CASE_INSENSITIVE_ORDER);

	private SearchCriteria criteria;
	
	public GreedySearchByLinkLength(Graph g, SearchCriteria criteria) {
		this.myGraph = g;
		myMap = myGraph.getMap();
		this.criteria = criteria;
	}
	
	int x, y;
	
	@Override
	public boolean search(String from, String to) {
		
		myGraph.graphReset();
		if(!myMap.containsKey(from) || !myMap.containsKey(to)) {
			return false;
		}
		
		Node end = myGraph.getMap().get(to);
		
		x = end.getX();
		y = end.getY();
		
		Node fromNode = myMap.get(from);
		ArrayList<Node> queue = new ArrayList<Node>();
		ArrayList<Node> currCueue = new ArrayList<Node>();
		queue.add(fromNode);
		
		while(!queue.isEmpty()) {
			
			Node temp = queue.get(0);
			queue.remove(0);
			System.out.println("Now on: " + temp.getName());
			Console.println("Now on: " + temp.getName());
			
			if(temp.getName().equalsIgnoreCase(to)) {
				return true;
			}
			
			temp.setTested(true);
			
			Comparator<Link>  link = (l1, l2) -> Double.compare(l1.getLenght(), l2.getLenght());
			temp.getLinks().sort(link);
			
			currCueue.clear();
			
			for(Link l : temp.links) {
				if(!l.getToNode().isTested()) {
					
					if(!criteria.checkSearchCriteria(l)) continue;
					
					currCueue.add(0, l.getToNode());
					
					l.getToNode().setPathVia(temp);
				}
			}
			
			for(Node n : currCueue) {
				if(queue.contains(n)) {
					queue.remove(n);
				}
				queue.add(0, n);
			}
			
			// проверяваме дали има възли на еднакви разстояния
			for(int i=0; i<temp.getLinks().size()-1; i++) {
				Link curLink = temp.getLinks().get(i);
				Link nextLink = temp.getLinks().get(i+1);
				
				// ако има
				if(curLink.getLenght() == nextLink.getLenght()) {
					setDistanceToEnd(curLink.getToNode());
					setDistanceToEnd(nextLink.getToNode());
					
					// ако единия е по-близо до крайната цел
					if(curLink.getToNode().getDistanceToEnd() > nextLink.getToNode().getDistanceToEnd() && !curLink.getToNode().isTested()) {
						queueSwap(curLink.getToNode(), nextLink.getToNode(), queue);
					}
				}
			}
			
			printHistory(queue);
			
			temp.setExpanded(true);
			
		}//end while
		
		return false;
	}//end search()
	
	private void printHistory(ArrayList<Node> queue) {
		for(Node n : queue) {
			System.out.print(n.getName() + " ");
			Console.print(n.getName() + " ");
		}
		System.out.println();
		Console.println();
	}
	
	private void setDistanceToEnd(Node node) {
		double directLine = Math.sqrt( Math.pow(x - node.getX(), 2) + Math.pow(y - node.getY(), 2) ); // питагорова теорема
		
		node.setDistanceToEnd(directLine);
	}
	
	// разменя два възела в опашката
	private void queueSwap(Node n1, Node n2, ArrayList<Node> queue) {
		int indexOfN1 = queue.indexOf(n1);
		int indexOfN2 = queue.indexOf(n2);
		
		queue.remove(indexOfN1);
		queue.add(indexOfN1, n2);
		
		queue.remove(indexOfN2);
		queue.add(indexOfN2, n1);
	}
	
}
