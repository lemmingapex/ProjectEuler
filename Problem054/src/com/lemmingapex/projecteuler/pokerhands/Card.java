package com.lemmingapex.projecteuler.pokerhands;

import java.util.Comparator;

public class Card implements Comparable<Card> {

	final public int value;
	final public char suit;

	public Card(int value, char suit) {
		this.value = value;
		this.suit = suit;
	}

	@Override
	public int compareTo(Card other){
		if(this.value == other.value) {
			return ((Character)this.suit).compareTo(other.suit);
		} else {
			return ((Integer)this.value).compareTo(other.value);
		}
	}

	static Card valueOf(String valueSuit) {
		int cardValue;
		char charCardValue = valueSuit.charAt(0);
		if(charCardValue == 'A') {
			cardValue = 14;
		} else if(charCardValue == 'K') {
			cardValue = 13;
		} else if(charCardValue == 'Q') {
			cardValue = 12;
		} else if(charCardValue == 'J') {
			cardValue = 11;
		} else if(charCardValue == 'T') {
			cardValue = 10;
		} else {
			cardValue = charCardValue - '0';
		}
		char cardSuit = valueSuit.charAt(1);
		return new Card(cardValue, cardSuit);
	}

	@Override
	public String toString() {
		return ("" + this.value + this.suit);
	}
}
