package com.lemmingapex.projecteuler.countingfractions;

/**
 * 01/29/2017
 * CountingFractions.java
 * Counting fractions
 *
 * @author Scott Wiedemann
 *
 */
public class CountingFractions {

	public long solve(int upperBound) {
		long sum = 0;

		// initize phis[n] to n.
		int[] phis = new int[upperBound+1];
		for(int n=0; n<=upperBound; n++) {
			phis[n] = n;
		}

		// Then, we work our way up and modify each entry by the proper (p-1)/p in euler's product formula.
		for(int n=2; n<=upperBound; n++) {
			// the entry has not been modifyed (looked at), then it is a prime.  If an entry has been modified, then it is a prime.
			if(phis[n] == n) {
				for(int i=n; i<=upperBound; i+=n) {
					phis[i] = (phis[i]/n)*(n-1);
					// System.out.println("i phis[i] " + i + " " + phis[i]);
				}
			}
			// at this point, we won't look at phis[n] again, add it to the sum.
			sum += phis[n];
		}

		return sum;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./countingfractions.jar N");
			System.exit(1);
		}
		Integer N = Integer.parseInt(args[0]);

		System.out.println(new CountingFractions().solve(N));
	}
}
