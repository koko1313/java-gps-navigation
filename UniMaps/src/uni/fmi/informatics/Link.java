package uni.fmi.informatics;

public class Link {
	private double lenght;
	private Node toNode;
	private LinkType type;
	
	public Link(Node to) {
		this.toNode = to;
	}
	
	public Link(Node to, double lenght) {
		this(to);
		this.lenght = lenght;
	}
	
	public Link(Node to, double lenght, LinkType type) {
		this(to, lenght);
		this.type = type;
	}

	public double getLenght() {
		return lenght;
	}

	public void setLenght(double lenght) {
		this.lenght = lenght;
	}

	public Node getToNode() {
		return toNode;
	}

	public void setToNode(Node to) {
		this.toNode = to;
	}

	public LinkType getType() {
		return type;
	}

	public void setType(LinkType type) {
		this.type = type;
	}
}
