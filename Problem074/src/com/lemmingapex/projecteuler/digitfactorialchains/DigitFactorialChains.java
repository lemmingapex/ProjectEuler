package com.lemmingapex.projecteuler.digitfactorialchains;

import java.util.TreeMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

/**
 * 02/02/2017
 * DigitFactorialChains.java
 * Digit factorial chains
 *
 * @author Scott Wiedemann
 *
 */
public class DigitFactorialChains {

	private long factorial(int n) {
		if(n <= 0) {
			return 1;
		} else {
			return n*factorial(n-1);
		}
	}

	public long factorialOfDigits(Long d) {
		long factorialOfDigits = 0;
		String dString = d.toString();
		for(int i = 0, n = dString.length() ; i < n ; i++) {
			int c = dString.charAt(i) - 48;
			factorialOfDigits += factorial(c);
		}
		return factorialOfDigits;
	}

	public long solve(long N, int M) {
		final int searchSequenceLength = 60;

		long count = 0;
		Map<Long, Long> sequenceLengthCache = new TreeMap<Long, Long>();

		for(long i=3; i <= N; i++) {
			long n = i;
			//System.out.println("n: " + n);
			int currentLength = 0;
			Set<Long> chain = new LinkedHashSet<Long>();
			Long n_seq_length = null;
			do {
				chain.add(n);
				n_seq_length = sequenceLengthCache.get(n);
				if(n_seq_length != null) {
					break;
				}
				n = factorialOfDigits(n);
				//System.out.println(n);
				currentLength++;
			} while(!chain.contains(n));

			// we have detected a new cycle that we haven't seen before.  Fill the cache with the new cycle entries.
			if(n_seq_length == null) {
				currentLength = 0;
				Iterator<Long> chainIterator = chain.iterator();
				while(chainIterator.hasNext()) {
					Long e = chainIterator.next();
					if(e.equals(n)) {
						n_seq_length = (long)(chain.size() - currentLength);
						sequenceLengthCache.put(e, n_seq_length);
						while(chainIterator.hasNext()) {
							e = chainIterator.next();
							sequenceLengthCache.put(e, n_seq_length);
						}
						break;
					}
					currentLength++;
				}
			}
			// add entries to the cache that are in the chain. (If it's in the chain, then it is not in the cache.)
			Iterator<Long> chainIterator = chain.iterator();
			for(long j=currentLength + n_seq_length; j>n_seq_length; j--) {
				Long c = chainIterator.next();
				sequenceLengthCache.put(c, j);
			}
		}
		// count the cache entires that meet the criteria
		for(Long key : sequenceLengthCache.keySet()) {
			Long value = sequenceLengthCache.get(key);
			if(value == M && key <= N) {
				//System.out.println("(key, value) (" + key + ", " + value + ")");
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./digitfactorialchains.jar N M");
			System.exit(1);
		}
		Long N = Long.parseLong(args[0]);
		Integer M = Integer.parseInt(args[1]);

		System.out.println(new DigitFactorialChains().solve(N, M));
	}
}
