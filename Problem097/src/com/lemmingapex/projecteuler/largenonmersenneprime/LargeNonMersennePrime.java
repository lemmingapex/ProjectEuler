package com.lemmingapex.projecteuler.largenonmersenneprime;

/**
 * 04/21/2017
 * LargeNonMersennePrime.java
 * Large non-Mersenne prime
 *
 * @author Scott Wiedemann
 *
 */
public class LargeNonMersennePrime {

	public static long solve() {
		long mod = 10000000000L;

		long n = 2L;
		for(int i=1; i<7830457; i++) {
			n = (n*2)%mod;
		}

		n = (n*28433)%mod;
		n++;
		return n;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./largenonmersenneprime.jar");
			System.exit(1);
		}

		System.out.println(LargeNonMersennePrime.solve());
	}
}
