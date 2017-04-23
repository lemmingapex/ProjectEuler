package com.lemmingapex.projecteuler.arrangedprobability;

/**
 * 04/22/2017
 * ArrangedProbability.java
 * Arranged probability
 *
 * @author Scott Wiedemann
 *
 */
public class ArrangedProbability {

	public void printFirstFewTerms() {
		long upperBound = 10000000L;
		for(long n=1; n<upperBound; n++) {
			double b = 0.5 + Math.sqrt(2.0*n*n-2.0*n+1.0)/2.0;
			if(b == Math.floor(b)) {
				System.out.println((int)b);
			}
		}
	}

	public long solve(long N) {
		//printFirstFewTerms();

		long bn_2 = 1L;
		long bn_1 = 3L;

		while(true) {
			long bn = 6*bn_1 - bn_2 - 2L;
			double n = 0.5 + Math.sqrt(8.0*bn*bn-8.0*bn+1.0)/2.0;
			if(n > N) {
				System.out.println(bn);
				break;
			} else {
				bn_2 = bn_1;
				bn_1 = bn;
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./arrangedprobability.jar N");
			System.exit(1);
		}
		final long N = Long.parseLong(args[0]);

		System.out.println(new ArrangedProbability().solve(N));
	}
}
