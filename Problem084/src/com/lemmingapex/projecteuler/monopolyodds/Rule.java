package com.lemmingapex.projecteuler.monopolyodds;

import java.util.List;

public class Rule {
	public int destination = 0;

	public Rule(int destination) {
		this.destination = destination;
	}

	public int apply(int currentSquare, List<String> board) {
		return destination;
	}
}
