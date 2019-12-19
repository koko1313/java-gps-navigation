package uni.fmi.informatics;

import java.util.ArrayList;

public class Node {
	private String name;
	private double weight;
	private int x;
	private int y;
	private boolean isExpanded;
	private boolean isTested;
	private int depth = -1;
	private double distanceToEnd;
	private double distanceFromStart = Double.MAX_VALUE;
	private Node pathVia;

	public ArrayList<Link> links = new ArrayList<>();
	
	public Node(String name) {
		this.name = name;
	}
	
	public Node(String name, double weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public Node(String name, int x, int y){
		this(name);
		this.x = x;
		this.y = y;
	}
	
	public Node(String name, double weight, int x, int y) {
		this(name, x, y);
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public double getDistanceToEnd() {
		return distanceToEnd;
	}
	
	public void setDistanceToEnd(double distanceToEnd) {
		this.distanceToEnd = distanceToEnd;
	}
	
	public double getDistanceFromStart() {
		return distanceFromStart;
	}
	
	public void setDistanceFromStart(double shortestDistanceFromStart) {
		this.distanceFromStart = shortestDistanceFromStart;
	}
	
	public Node getPathVia() {
		return pathVia;
	}
	
	public void setPathVia(Node n) {
		this.pathVia = n;
	}

	public ArrayList<Link> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public boolean isTested() {
		return isTested;
	}

	public void setTested(boolean isTested) {
		this.isTested = isTested;
	}
	
	public void resetNode() {
		isExpanded = false;
		isTested = false;
		depth = -1;
		distanceToEnd = 0;
		distanceFromStart = Double.MAX_VALUE;
		pathVia = null;
	}
	
}
