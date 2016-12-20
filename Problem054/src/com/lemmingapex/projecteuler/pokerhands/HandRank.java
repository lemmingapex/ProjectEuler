package com.lemmingapex.projecteuler.pokerhands;

public class HandRank {

	public static final HandRank HighCard = new HandRank(1, "HighCard");
	public static final HandRank OnePair = new HandRank(2, "OnePair");
	public static final HandRank TwoPairs = new HandRank(3, "TwoPairs");
	public static final HandRank ThreeOfAKind = new HandRank(4, "ThreeOfAKind");
	public static final HandRank Straight = new HandRank(5, "Straight");
	public static final HandRank Flush = new HandRank(6, "Flush");
	public static final HandRank FullHouse = new HandRank(7, "FullHouse");
	public static final HandRank FourOfAKind = new HandRank(8, "FourOfAKind");
	public static final HandRank StraightFlush = new HandRank(9, "StraightFlush");

	public final int value;
	public final String name;
	public HandRank(int value, String name) {
		this.value = value;
		this.name = name;
	}
}
