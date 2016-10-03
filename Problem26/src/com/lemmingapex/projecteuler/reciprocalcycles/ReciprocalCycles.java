package com.lemmingapex.projecteuler.reciprocalcycles;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * 10/03/2016
 * ReciprocalCycles.java
 * Reciprocal cycles
 *
 * @author Scott Wiedemann
 *
 */
public class ReciprocalCycles {

	private int getLength(long N) {
		Map<Long, Integer> remaindersToPosition = new HashMap<Long, Integer>();
		List<Long> digits = new ArrayList<Long>();
		long numerator = 1L;
		long denominator = N;
		int position = 0;
		do {
			remaindersToPosition.put(numerator, position++);
			numerator *= 10L;
			digits.add(numerator/denominator);
			numerator %= denominator;
		} while(numerator != 0L && remaindersToPosition.get(numerator) == null);

		// for(Long d : digits) {
		// 	System.out.println(d);
		// }

		Integer fPosition = remaindersToPosition.get(numerator);
		if(fPosition == null) {
			fPosition = position;
		}
		int length = position - fPosition;
		return length;
	}


	public long solve(long N) {
		int maxLength = 0;
		long maxLengthI = N;
		for(long i = N; i>1; i--) {
			int iLength = getLength(i);
			if(iLength > maxLength) {
				maxLength = iLength;
				maxLengthI = i;
				// optimization to exit early.  A number X, can only have at most X-1 repeated digits.  see https://en.wikipedia.org/wiki/Repeating_decimal#Fractions_with_prime_denominators
				if(Long.valueOf(maxLength) >= i-1) {
					break;
				}
			}
		}

		return maxLengthI;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./reciprocalcycles.jar N");
			System.exit(1);
		}
		Long N = Long.parseLong(args[0]);

		System.out.println(new ReciprocalCycles().solve(N - 1));
	}
}
