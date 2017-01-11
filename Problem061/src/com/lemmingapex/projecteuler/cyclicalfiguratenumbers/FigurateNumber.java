package com.lemmingapex.projecteuler.cyclicalfiguratenumbers;

import java.util.ArrayList;
import java.util.List;

public class FigurateNumber {

	final public int number;
	final public int type;
	final public String prefix;
	final public String suffix;

	// graph data structures
	final public List<FigurateNumber> neighbors = new ArrayList<>();
	public boolean visited = false;
	//

	public FigurateNumber(int number, int type) {
		this.number = number;
		this.type = type;
		this.prefix = String.valueOf(number).substring(0,2);
		this.suffix = String.valueOf(number).substring(2,4);
	}

	public FigurateNumber getUnvisitedNeighbor() {
		FigurateNumber neighbor = null;
		for(FigurateNumber n : neighbors) {
			if(!n.visited) {
				neighbor = n;
				break;
			}
		}
		return neighbor;
	}

	@Override
	public String toString() {
		return this.type + " " + this.number;
	}
}
