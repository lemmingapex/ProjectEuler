package com.lemmingapex.projecteuler.lexicographicpermutations;

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
		// adjust the index by one
		N--;
		String solution = "";

		List<Long> digitsAvailable = new ArrayList<Long>();
		for(Long l = 0L; l <= 9L; l++) {
			digitsAvailable.add(l);
		}

		double percent = ((double)N)/factorial(digitsAvailable.size());
		// System.out.println("percent " + percent);
		while(digitsAvailable.size() > 1) {
			// System.out.println("size " + digitsAvailable.size());
			int index = (int)(percent*digitsAvailable.size());
			// System.out.println("fpindex " + percent*digitsAvailable.size());
			// System.out.println("index " + index);
			// for(Long l : digitsAvailable) {
			// 	System.out.print(l + " ");
			// }
			// System.out.println("");
			long digit = digitsAvailable.get(index);
			// System.out.println("digit " + digit + "\n");
			solution += digit;
			percent = (percent*digitsAvailable.size()) - index;
			// System.out.println("percent " + percent);
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

		System.out.println("Should be around: " + new LexicographicPermutations().solve(N));
	}
}
