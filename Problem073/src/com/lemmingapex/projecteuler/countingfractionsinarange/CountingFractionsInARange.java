package com.lemmingapex.projecteuler.countingfractionsinarange;

/**
 * 01/31/2017
 * CountingFractionsInARange.java
 * Counting fractions in a range
 *
 * @author Scott Wiedemann
 *
 */
public class CountingFractionsInARange {

	public long solve() {
		long count = 0;

		final long dLowerBound = 5;
		final long dUpperBound = 12000;

		final double lowerBoundFraction_double = 1.0/3.0;
		final LFraction lowerBoundFraction = new LFraction(1L, 3L);
		final double upperBoundFraction_double = 1.0/2.0;
		final LFraction upperBoundFraction = new LFraction(1L, 2L);

		for(long d=dUpperBound; d>=dLowerBound; d--) {
			// find the fraction to the left of 1/2 with a denominator of d
			LFraction upperBoundFractionWithDenominatorD = new LFraction((long)Math.floor(upperBoundFraction_double*d), d);
			// find the fraction to the right of 1/3 with a denominator of d
			LFraction lowerBoundFractionWithDenominatorD = new LFraction((long)Math.ceil(lowerBoundFraction_double*d), d);

			if(upperBoundFraction.subtract(upperBoundFractionWithDenominatorD).getNumerator() == 0L) {
				upperBoundFractionWithDenominatorD = new LFraction(upperBoundFractionWithDenominatorD.getNumerator()-1L, upperBoundFractionWithDenominatorD.getDenominator());
			}

			if(lowerBoundFractionWithDenominatorD.subtract(lowerBoundFraction).getNumerator() == 0L) {
				lowerBoundFractionWithDenominatorD = new LFraction(lowerBoundFractionWithDenominatorD.getNumerator()+1L, lowerBoundFractionWithDenominatorD.getDenominator());
			}

			if(upperBoundFractionWithDenominatorD.subtract(lowerBoundFraction).getNumerator() > 0L && upperBoundFraction.subtract(lowerBoundFractionWithDenominatorD).getNumerator() > 0L) {
				long upperBoundFractionNumerator = upperBoundFractionWithDenominatorD.getNumerator();
				long lowerBoundFractionNumerator = lowerBoundFractionWithDenominatorD.getNumerator();

				for(long n = lowerBoundFractionNumerator; n <= upperBoundFractionNumerator; n++) {
					// count only the fractions that are already in the most redued form
					if(LFraction.gcd(n,d) == 1) {
						count++;
					}
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./countingfractionsinarange.jar");
			System.exit(1);
		}

		System.out.println(new CountingFractionsInARange().solve());
	}
}
