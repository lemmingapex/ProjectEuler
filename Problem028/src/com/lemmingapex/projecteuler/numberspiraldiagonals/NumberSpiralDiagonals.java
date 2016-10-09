package com.lemmingapex.projecteuler.numberspiraldiagonals;

import java.math.BigInteger;

/**
 * 10/05/2016
 * NumberSpiralDiagonals.java
 * Number spiral diagonals
 *
 * @author Scott Wiedemann
 *
 */
public class NumberSpiralDiagonals {
	public BigInteger solve(BigInteger N) {
		BigInteger NSquared = N.multiply(N);
		BigInteger NCubed = NSquared.multiply(N);
		return ((BigInteger.valueOf(4).multiply(NCubed)).add(BigInteger.valueOf(3).multiply(NSquared)).add(BigInteger.valueOf(8).multiply(N)).subtract(BigInteger.valueOf(9))).divide(BigInteger.valueOf(6));
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./numberspiraldiagonals.jar N");
			System.exit(1);
		}
		BigInteger N = new BigInteger(args[0]);

		System.out.println(new NumberSpiralDiagonals().solve(N));
	}
}
