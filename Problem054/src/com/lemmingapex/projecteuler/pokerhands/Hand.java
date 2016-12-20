package com.lemmingapex.projecteuler.pokerhands;

import java.util.TreeSet;

public class Hand {
	final public TreeSet<Card> cards = new TreeSet<Card>();

	public int getHandValue() {
		int[] valueHistogram = new int[13];
		int i = 0;
		for(Card card : this.cards) {
			valueHistogram[card.value-2]++;
			i++;
		}

		int kp = 13*13*13*13*13;
		int v = (kp*getRank().value);
		kp /= 13;
		for(i=4; i>0; i--) {
			for(int j=12; j>=0; j--) {
				int valueCount = valueHistogram[j];
				if(valueCount == i) {
					for(int k=0; k<i; k++) {
						v += (j+1)*kp;
						kp /= 13;
					}
				}
			}
		}
		return v;
	}

	public HandRank getRank() {
		boolean straight = true;
		boolean flush = true;
		char suit = cards.first().suit;
		int value = cards.first().value;
		int[] valueHistogram = new int[13];
		int i = 0;
		for(Card card : cards) {
			if(card.suit != suit) {
				flush = false;
			}
			if(card.value != (value + i)) {
				straight = false;
			}
			valueHistogram[card.value-2]++;
			i++;
		}

		if(straight && flush) {
			return HandRank.StraightFlush;
		}

		boolean foundThree = false;
		int numberOfPairs = 0;
		for(int j=0; j<13; j++) {
			if(valueHistogram[j] == 4) {
				return HandRank.FourOfAKind;
			}
			if(valueHistogram[j] == 3) {
				foundThree = true;
			}
			if(valueHistogram[j] == 2) {
				numberOfPairs++;
			}
		}

		if(foundThree && numberOfPairs==1) {
			return HandRank.FullHouse;
		}

		if(flush) {
			return HandRank.Flush;
		}

		if(straight) {
			return HandRank.Straight;
		}

		if(foundThree) {
			return HandRank.ThreeOfAKind;
		}

		if(numberOfPairs==2) {
			return HandRank.TwoPairs;
		}

		if(numberOfPairs==1) {
			return HandRank.OnePair;
		}

		return HandRank.HighCard;
	}

	@Override
	public String toString() {
		String value = "";
		for(Card card : cards) {
			value += card.toString() + " ";
		}
		return value.trim();
	}
}
