package com.lemmingapex.projecteuler.permutedmultiples;

/**
 * 11/18/2016
 * PermutedMultiples.java
 * Permuted multiples
 *
 * @author Scott Wiedemann
 *
 */
public class PermutedMultiples {

	public int solve() {
		return 0;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./permutedmultiples.jar");
			System.exit(1);
		}

		System.out.println(new PermutedMultiples().solve());
	}
}
