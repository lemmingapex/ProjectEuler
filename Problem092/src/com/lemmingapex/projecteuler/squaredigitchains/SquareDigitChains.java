package com.lemmingapex.projecteuler.squaredigitchains;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 04/07/2017
 * SquareDigitChains.java
 * Square digit chains
 *
 * @author Scott Wiedemann
 *
 */
public class SquareDigitChains {

	private int squareOfDigits(long n) {
		int squareOfDigits = 0;
		char[] s = String.valueOf(n).toCharArray();
		for(int i=0; i<s.length; i++) {
			int d = s[i] - 48;
			squareOfDigits += d*d;
		}
		return squareOfDigits;
	}

	public int solve(long N) {
		int numberOfSequencesThatArriveAt89 = 0;

		Map<Long, Boolean> sequenceCache = new HashMap<>();
		for(long i=1; i<N; i++) {
			long n = i;
			Boolean key = sequenceCache.get(n);
			sequenceCache.put(1L, false);
			sequenceCache.put(89L, true);
			Stack<Long> path = new Stack<>();
			while(key == null) {
				path.add(n);
				n = squareOfDigits(n);
				key = sequenceCache.get(n);
			}
			while(!path.isEmpty()) {
				sequenceCache.put(path.pop(), key);
			}
			if(key) {
				numberOfSequencesThatArriveAt89++;
			}
		}
		return numberOfSequencesThatArriveAt89;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./squaredigitchains.jar N");
			System.exit(1);
		}
		Long N = Long.parseLong(args[0]);
		System.out.println(new SquareDigitChains().solve(N));
	}
}
