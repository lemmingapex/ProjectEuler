package com.lemmingapex.projecteuler.convergentsofe;

import java.math.BigInteger;

/**
 * 01/16/2017
 * ConvergentsOfE.java
 * Convergents of e
 *
 * @author Scott Wiedemann
 *
 */
public class ConvergentsOfE {

	public int solve(int N) {
		N--;

		int sum = 0;

		BigIntegerFraction t = new BigIntegerFraction(BigInteger.ZERO, BigInteger.ONE);
		for(int i=N,k=((N+1)/3); i>0; i--) {
			int d = 1;
			if((i+1)%3 == 0) {
				d = 2*k;
				k--;
			}
			t = new BigIntegerFraction(BigInteger.valueOf(d), BigInteger.ONE).add(t).invert();
			//System.out.println(i + ": " + d + " : " + t);
		}

		t = t.add(new BigIntegerFraction(BigInteger.valueOf(2), BigInteger.ONE));
		System.out.println((N+1) + " term: " + t);

		char[] numerator = t.getNumerator().toString().toCharArray();
		for(int i=0; i<numerator.length; i++) {
			sum += ((int)numerator[i] - 48);
		}

		return sum;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./convergentsofe.jar N");
			System.exit(1);
		}

		final int N = Integer.parseInt(args[0]);

		System.out.println(new ConvergentsOfE().solve(N));
	}
}
