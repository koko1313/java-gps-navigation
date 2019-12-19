package searches;

import uni.fmi.informatics.Link;
import uni.fmi.informatics.LinkType;

public class SearchCriteria {

	private boolean avoidNormalPaths;
	private boolean avoidHighways;
	private boolean avoidFirstClassPaths;
	private double maxPathLength;
	
	public SearchCriteria(boolean avoidNormalPaths, boolean avoidHighways, boolean avoidFistClassPaths, double maxPathLength) {
		this.avoidNormalPaths = avoidNormalPaths;
		this.avoidHighways = avoidHighways;
		this.avoidFirstClassPaths = avoidFistClassPaths;
		this.maxPathLength = maxPathLength;
	}
	
	public boolean checkSearchCriteria(Link l) {
		if(avoidNormalPaths && l.getType() == LinkType.NORMAL ||
			avoidHighways && l.getType() == LinkType.HIGHWAY ||
			avoidFirstClassPaths && l.getType() == LinkType.FIRST_CLASS ||
			maxPathLength!=Double.MAX_VALUE && l.getLenght() > maxPathLength) return false;
		
		return true;
	}
	
}
