package com.lemmingapex.projecteuler.lexicographicpermutations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 09/25/2016
 * LexicographicPermutations.java
 * lexicographic permutations
 *
 * @author Scott Wiedemann
 *
 */
public class LexicographicPermutations {

	private long factorial(long n) {
		if(n <= 0) {
			return 1;
		} else {
			return n*factorial(n-1);
		}
	}

	public String solve(long N) {
		String solution = "";

		List<Long> digitsAvailable = new ArrayList<Long>();
		for(Long l = 0L; l <= 9L; l++) {
			digitsAvailable.add(l);
		}

		BigInteger percentNumerator = BigInteger.valueOf(N);
		BigInteger percentDenominator = BigInteger.valueOf(factorial(digitsAvailable.size()));

		// reduce the fraction
		BigInteger gcd = percentNumerator.gcd(percentDenominator);
		percentNumerator = percentNumerator.divide(gcd);
		percentDenominator = percentDenominator.divide(gcd);

		while(digitsAvailable.size() > 1) {
			//// find the index
			BigInteger index = BigInteger.valueOf(digitsAvailable.size() - 1);
			if(!percentDenominator.equals(BigInteger.ONE)) {
				BigInteger percentNumeratorIndex = percentNumerator.multiply(BigInteger.valueOf(digitsAvailable.size()));

				// reduce the fraction
				gcd = percentNumeratorIndex.gcd(percentDenominator);
				percentNumeratorIndex = percentNumeratorIndex.divide(gcd);
				BigInteger percentDenominatorIndex = percentDenominator.divide(gcd);

				index = percentNumeratorIndex.divide(percentDenominatorIndex);
				if(percentNumeratorIndex.remainder(percentDenominatorIndex).equals(BigInteger.ZERO)) {
					index = index.subtract(BigInteger.ONE);
				}
			}
			// System.out.println("index " + index);
			// for(Long l : digitsAvailable) {
			// 	System.out.print(l + " ");
			// }
			// System.out.println("");
			long digit = digitsAvailable.get(index.intValue());
			// System.out.println("digit " + digit + "\n");
			solution += digit;

			//// update precentage
			percentNumerator = percentNumerator.multiply(BigInteger.valueOf(digitsAvailable.size()));

			// reduce the fraction
			gcd = percentNumerator.gcd(percentDenominator);
			percentNumerator = percentNumerator.divide(gcd);
			percentDenominator = percentDenominator.divide(gcd);

			if(percentNumerator.remainder(percentDenominator).equals(BigInteger.ZERO)) {
				percentNumerator = percentNumerator.divide(percentDenominator);
				percentNumerator = percentNumerator.subtract(BigInteger.ONE);
				percentDenominator = BigInteger.valueOf(1L);
			} else {
				percentNumerator = percentNumerator.remainder(percentDenominator);
			}

			// reduce the fraction
			gcd = percentNumerator.gcd(percentDenominator);
			percentNumerator = percentNumerator.divide(gcd);
			percentDenominator = percentDenominator.divide(gcd);

			// System.out.println("percentNumerator " + percentNumerator);
			// System.out.println("percentDenominator " + percentDenominator);
			digitsAvailable.remove(digit);
		}
		solution += digitsAvailable.get(0);

		return solution;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./lexicographicpermutations.jar N");
			System.exit(1);
		}
		Long N = Long.parseLong(args[0]);

		System.out.println(new LexicographicPermutations().solve(N));
	}
}
