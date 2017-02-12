package com.lemmingapex.projecteuler.pathsumfourways;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Element {

	final public Integer id;
	final public int x;
	final public int y;
	final public int value;

	// graph data structures
	final public List<Element> neighbors = new ArrayList<>();
	public boolean visited = false;
	public int distance = Integer.MAX_VALUE;
	public Element predecessor = null;
	//

	public Element(int value, int x, int y) {
		this.id = ElegantPairing.pair(x,y);
		this.x = x;
		this.y = y;
		this.value = value;
	}

	public Element getUnvisitedNeighbor() {
		Element neighbor = null;
		for(Element n : neighbors) {
			if(!n.visited) {
				neighbor = n;
				break;
			}
		}
		return neighbor;
	}

	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		Element element = (Element)obj;
		return this.id.equals(element.id);
	}

	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public String toString() {
		return this.value + "";
	}
}
