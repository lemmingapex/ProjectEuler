package com.lemmingapex.projecteuler.powerfuldigitcounts;

/**
 * 01/06/2017
 * PowerfulDigitCounts.java
 * Powerful digit counts
 *
 * @author Scott Wiedemann
 *
 */
public class PowerfulDigitCounts {

	public int solve() {

		int count = 0;

		//int nLowerBound = 1;
		for(int x = 0; x <= 9; x++) {
			int nUpperBound = (int)Math.floor(1/(1 - Math.log10(x)));
			count += nUpperBound;
			// BigInteger x_BI = BigInteger.valueOf(x);
			// for(int n = nLowerBound; n <= nUpperBound; n++) {
			// 	BigInteger y = x_BI.pow(n);
			// 	if(y.toString().length() == n) {
			// 		System.out.println("x^n = " + x + "^" + n + " = " + y);
			// 		count++;
			// 	}
			// }
		}

		return count;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./powerfuldigitcounts.jar");
			System.exit(1);
		}

		System.out.println(new PowerfulDigitCounts().solve());
	}
}
