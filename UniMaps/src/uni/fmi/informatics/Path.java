package uni.fmi.informatics;

import java.util.ArrayList;

import gui.Console;

public class Path {
	
	private Node to;

	private ArrayList<Node> path = new ArrayList<Node>();
	
	private double pathLength = 0;
	
	public Path() {
		
	}
	
	public Node getTo() {
		return to;
	}
	
	public void setTo(Node to) {
		this.to = to;
	}
	
	public double getPathLength () {
		return pathLength;
	}
	
	public void incrementPathLength (double pathLength) {
		this.pathLength += pathLength;
	}
	
	public ArrayList<Node> getPath() {
		return path;
	}
	
	public void addToPath(Node node) {
		path.add(node);
	}
	
	public void addToPath(int position, Node node) {
		path.add(position, node);
	}
	
	public static Path createPath(Node endNode) {
		
		Path path = new Path();
		
		Node temp = endNode;
		
		while(true) {
			if(temp.getPathVia()==null) {
				path.addToPath(0, temp);
				return path;
			}
			
			path.addToPath(0, temp);
			
			Node fromNode = temp.getPathVia();
			for(Link l : fromNode.getLinks()) {
				if(l.getToNode() == temp) {
					path.incrementPathLength(l.getLenght());
				}
			}
			
			temp = temp.getPathVia();
		}
		
	}

	public static void printPath(ArrayList<Path> allPaths) {
		if(allPaths.size() == 0) {
			System.out.println("--------");
			Console.println("--------");
			
			System.out.println("There is no path");
			Console.println("There is no path");
			
			System.out.println("--------");
			Console.println("--------");
			return;
		}
		
		Path totalPath = new Path();
		
		for(Path path : allPaths) {
			for(Node n : path.getPath()) {
				totalPath.addToPath(n);
				
				if(totalPath.getPath().size() > 2) {
					int indexOfLast = totalPath.getPath().size()-1;
					int indexOfPrevious = totalPath.getPath().size()-2;
					
					if(totalPath.getPath().get(indexOfLast) == totalPath.getPath().get(indexOfPrevious)) {
						totalPath.getPath().remove(indexOfLast);
					}
				}
				
			}
			totalPath.incrementPathLength(path.getPathLength());
		}
		
		System.out.print("--------\nPath: ");
		Console.print("--------\nPath: ");
		
		for(Node n : totalPath.getPath()) {
			System.out.print(n.getName() + " ");
			Console.print(n.getName() + " ");
		}
		
		System.out.println("\nPath length: " + totalPath.getPathLength() + "\n--------");
		Console.println("\nPath length: " + totalPath.getPathLength() + "\n--------");
	}
	
}
