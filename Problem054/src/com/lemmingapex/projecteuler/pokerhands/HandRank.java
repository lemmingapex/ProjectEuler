package com.lemmingapex.projecteuler.pokerhands;

import java.util.TreeSet;

public abstract class HandRank {

	public static final HandRank HighCard = new HandRank(0, "HighCard") {
		public int getHandValue(Hand hand) {
			int v = 0;
			int i = 1;
			for(Card card : hand.cards) {
				v += (i*card.value);
				i*=15;
			}
			return v;
		}
	};
	public static final HandRank OnePair = new HandRank(1, "OnePair") {
		public int getHandValue(Hand hand) {
			int[] valueHistogram = new int[13];
			int i = 0;
			for(Card card : hand.cards) {
				valueHistogram[card.value-2]++;
				i++;
			}

			int v = 0;
			int jp = 1;
			int kp = 15*15*15;
			for(int j=0; j<13; j++) {
				int valueHistogram_j = valueHistogram[j];
				int value = j+2;
				if(valueHistogram_j == 2) {
					for(int k=0; k<2; k++) {
						v += value*kp;
						kp*=15;
					}
				} else if(valueHistogram_j == 1) {
					v += value*jp;
					jp *= 15;
				}
			}
			return v;
		}
	};
	public static final HandRank TwoPairs = new HandRank(2, "TwoPairs") {
		public int getHandValue(Hand hand) {
			int[] valueHistogram = new int[13];
			int i = 0;
			for(Card card : hand.cards) {
				valueHistogram[card.value-2]++;
				i++;
			}

			int v = 0;
			int jp = 1;
			int kp = 15;
			for(int j=0; j<13; j++) {
				int valueHistogram_j = valueHistogram[j];
				int value = j+2;
				if(valueHistogram_j == 2) {
					for(int k=0; k<2; k++) {
						v += value*kp;
						kp*=15;
					}
				} else if(valueHistogram_j == 1) {
					v += value*jp;
					jp *= 15;
				}
			}
			return v;
		}
	};
	public static final HandRank ThreeOfAKind = new HandRank(3, "ThreeOfAKind") {
		public int getHandValue(Hand hand) {
			int[] valueHistogram = new int[13];
			int i = 0;
			for(Card card : hand.cards) {
				valueHistogram[card.value-2]++;
				i++;
			}

			int v = 0;
			int jp = 1;
			int kp = 15*15;
			for(int j=0; j<13; j++) {
				int valueHistogram_j = valueHistogram[j];
				int value = j+2;
				if(valueHistogram_j == 3) {
					for(int k=0; k<3; k++) {
						v += value*kp;
						kp*=15;
					}
				} else if(valueHistogram_j == 1) {
					v += value*jp;
					jp *= 15;
				}
			}
			return v;
		}
	};
	public static final HandRank Straight = new HandRank(4, "Straight") {
		public int getHandValue(Hand hand) {
			return hand.cards.last().value;
		}
	};
	public static final HandRank Flush = new HandRank(5, "Flush") {
		public int getHandValue(Hand hand) {
			int v = 0;
			int i = 1;
			for(Card card : hand.cards) {
				v += (i*card.value);
				i*=15;
			}
			return v;
		}
	};
	public static final HandRank FullHouse = new HandRank(6, "FullHouse") {
		public int getHandValue(Hand hand) {
			int[] valueHistogram = new int[13];
			int i = 0;
			for(Card card : hand.cards) {
				valueHistogram[card.value-2]++;
				i++;
			}

			int v = 0;
			int jp = 1;
			int kp = 15*15;
			for(int j=0; j<13; j++) {
				int valueHistogram_j = valueHistogram[j];
				int value = j+2;
				if(valueHistogram_j == 3) {
					for(int k=0; k<3; k++) {
						v += value*kp;
						kp*=15;
					}
				} else if(valueHistogram_j == 2) {
					for(int k=0; k<2; k++) {
						v += value*jp;
						jp *= 15;
					}
				}
			}
			return v;
		}
	};
	public static final HandRank FourOfAKind = new HandRank(7, "FourOfAKind") {
		public int getHandValue(Hand hand) {
			int[] valueHistogram = new int[13];
			int i = 0;
			for(Card card : hand.cards) {
				valueHistogram[card.value-2]++;
				i++;
			}

			int v = 0;
			for(int j=0; j<13; j++) {
				int valueHistogram_j = valueHistogram[j];
				int value = j+2;
				if(valueHistogram_j == 4) {
					for(int k=0, kp=15; k<4; k++) {
						v += value*kp;
						kp*=15;
					}
				} else if(valueHistogram_j == 1) {
					v += value;
				}
			}
			return v;
		}
	};
	public static final HandRank StraightFlush = new HandRank(8, "StraightFlush") {
		public int getHandValue(Hand hand) {
			return hand.cards.last().value;
		}
	};

	public final int value;
	public final String name;
	public HandRank(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public abstract int getHandValue(Hand hand);

	public static HandRank getRank(Hand hand) {
		final TreeSet<Card> cards = hand.cards;

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
}
