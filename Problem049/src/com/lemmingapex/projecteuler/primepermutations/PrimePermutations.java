package com.lemmingapex.projecteuler.primepermutations;

/**
 * 11/11/2016
 * PrimePermutations.java
 * Prime permutations
 *
 * @author Scott Wiedemann
 *
 */
public class PrimePermutations {

	public long solve() {
		return 0;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./primepermutations.jar");
			System.exit(1);
		}

		System.out.println(new PrimePermutations().solve());
	}
}
