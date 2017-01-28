package com.lemmingapex.projecteuler.orderedfractions;

import java.math.BigInteger;

/**
 * 01/27/2017
 * OrderedFractions.java
 * Ordered fractions
 *
 * @author Scott Wiedemann
 *
 */
public class OrderedFractions {

	public String solve() {

		final int upperBound = 1000000;
		int lowerBound = 0;

		final double threeOverSeven = 3.0/7.0;
		BigIntegerFraction threeOverSeven_BI = new BigIntegerFraction(BigInteger.valueOf(3), BigInteger.valueOf(7));
		BigIntegerFraction closestFractionLessThanThreeOverSeven = null;

		for(int i=upperBound; i>lowerBound; i--) {
			BigIntegerFraction fractionWithDenominatorI = new BigIntegerFraction(BigInteger.valueOf((int)Math.floor(threeOverSeven*i)), BigInteger.valueOf(i));
			if(fractionWithDenominatorI.getNumerator().equals(BigInteger.ZERO) || fractionWithDenominatorI.equals(threeOverSeven_BI)) {
				continue;
			}

			if(closestFractionLessThanThreeOverSeven != null) {
				//System.out.println(new BigIntegerFraction(BigInteger.valueOf(1), BigInteger.valueOf(i)) + " gap is larger than " + threeOverSeven_BI.subtract(closestFractionLessThanThreeOverSeven));
				BigIntegerFraction t = threeOverSeven_BI.subtract(closestFractionLessThanThreeOverSeven).multiply(new BigIntegerFraction(BigInteger.valueOf(7), BigInteger.valueOf(1))).invert();
				lowerBound = Math.max(t.getNumerator().divide(t.getDenominator()).intValue(), lowerBound);
			}

			if(closestFractionLessThanThreeOverSeven == null || fractionWithDenominatorI.subtract(closestFractionLessThanThreeOverSeven).getNumerator().compareTo(BigInteger.ZERO) == 1) {
				System.out.println(fractionWithDenominatorI.reduce() + " is larger than " + closestFractionLessThanThreeOverSeven);
				closestFractionLessThanThreeOverSeven = fractionWithDenominatorI;
			}
		}

		return closestFractionLessThanThreeOverSeven.reduce().getNumerator().toString();
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./orderedfractions.jar");
			System.exit(1);
		}

		System.out.println(new OrderedFractions().solve());
	}
}
