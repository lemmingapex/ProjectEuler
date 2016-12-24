package com.lemmingapex.projecteuler.squarerootconvergents;

import java.math.BigInteger;

/**
 * 12/23/2016
 * SquareRootConvergents.java
 * Square root convergents
 *
 * @author Scott Wiedemann
 *
 */
public class SquareRootConvergents {

	public static int solve(int N) {
		int numeratorDigitsExceedsDenominatorDigitsCount = 0;
		BigIntegerFraction one = new BigIntegerFraction(BigInteger.valueOf(1), BigInteger.valueOf(1));
		BigIntegerFraction two = new BigIntegerFraction(BigInteger.valueOf(2), BigInteger.valueOf(1));
		BigIntegerFraction x = new BigIntegerFraction(BigInteger.valueOf(1), BigInteger.valueOf(2));

		for(int i=0; i<N; i++) {
			BigIntegerFraction t = x.add(one);
			//System.out.println(t);
			// NOTE: you may also notice a pattern here, that I coul have taken advantage of.  But I never would have found the pattern, if I haven't used bigint in the first place, so...
			// System.out.println(t.getNumerator().toString().length() - t.getDenominator().toString().length());
			if(t.getNumerator().toString().length() > t.getDenominator().toString().length()) {
				numeratorDigitsExceedsDenominatorDigitsCount++;
			}
			x = two.add(x).invert();
		}

		return numeratorDigitsExceedsDenominatorDigitsCount;
	}


	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./squarerootconvergents.jar N");
			System.exit(1);
		}

		int N = Integer.parseInt(args[0]);
		System.out.println(SquareRootConvergents.solve(N));
	}
}
