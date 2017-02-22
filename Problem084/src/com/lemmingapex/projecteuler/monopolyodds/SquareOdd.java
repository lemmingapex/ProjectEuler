package com.lemmingapex.projecteuler.monopolyodds;

public class SquareOdd implements Comparable<SquareOdd> {
	public final Integer square;
	public final Double odds;
	public SquareOdd(int square, double odds) {
		this.square = square;
		this.odds = odds;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (!(other instanceof SquareOdd)){
			return false;
		}

		SquareOdd other_ = (SquareOdd) other;

		return other_.square.equals(this.square) && other_.odds.equals(this.odds);
	}

	@Override
	public int compareTo(SquareOdd other) {
		return other.odds.compareTo(this.odds);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((square == null) ? 0 : square.hashCode());
		result = prime * result + ((odds == null) ? 0 : odds.hashCode());
		return result;
	}
}
