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
	public long solve(BigInteger N) {
		return 0;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./quadraticprimes.jar N");
			System.exit(1);
		}
		BigInteger N = new BigInteger(args[0]);

		System.out.println(new NumberSpiralDiagonals().solve(N));
	}
}
