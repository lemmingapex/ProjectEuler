package com.lemmingapex.projecteuler.monopolyodds;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {
	final public String space;
	final public int numOfCards;
	final public List<Rule> rules = new ArrayList<>();

	RuleSet(final String space, final int numOfCards) {
		this.space = space;
		this.numOfCards = numOfCards;
	}

	public int apply(int currentSquare, List<String> board) {
		final int randomCardNumber = (int)Math.min(Math.random()*this.numOfCards + 1, this.numOfCards);
		final int numOfRules = rules.size();
		if(randomCardNumber <= numOfRules) {
			Rule rule = rules.get(randomCardNumber-1);
			return rule.apply(currentSquare, board);
		} else {
			// no op
			return currentSquare;
		}
	}
}
