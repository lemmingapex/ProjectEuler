package com.lemmingapex.projecteuler.pokerhands;

import java.util.TreeSet;

public class Hand {
	final public TreeSet<Card> cards = new TreeSet<Card>();

	@Override
	public String toString() {
		String value = "";
		for(Card card : cards) {
			value += card.toString() + " ";
		}
		return value.trim();
	}
}
