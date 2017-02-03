package com.lemmingapex.projecteuler.digitfactorialchains;

import java.util.HashMap;
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

	public long solve(long N) {
		final int searchSequenceLength = 60;

		long count = 0;
		Map<Long, Long> sequenceLengthCache = new HashMap<Long, Long>();

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

			Long cycleLength = 0L;
			if(n_seq_length == null) {
				Iterator<Long> chainIterator = chain.iterator();
				while(chainIterator.hasNext()) {
					Long e = chainIterator.next();
					if(e.equals(n)) {
						cycleLength = chain.size() - cycleLength;
						//System.out.println("cycleLength " + cycleLength);
						sequenceLengthCache.put(e, cycleLength);
						while(chainIterator.hasNext()) {
							e = chainIterator.next();
							sequenceLengthCache.put(e, cycleLength);
						}
						break;
					}
					cycleLength++;
				}
			}

			if(n_seq_length == null) {
				n_seq_length = chain.size() - cycleLength;
				currentLength = 0;
			}

			long l = currentLength + n_seq_length;
			sequenceLengthCache.put(i, l+1L);
			for(Long c : chain) {
				if(l == n_seq_length) {
					break;
				}
				//System.out.println("(c, l) (" + c + ", " + l + ")");
				sequenceLengthCache.put(c, l);
				l--;
			}
		}
		for(Long key : sequenceLengthCache.keySet()) {
			Long value = sequenceLengthCache.get(key);
			if(value == 60 && key <= N) {
				System.out.println("(key, value) (" + key + ", " + value + ")");
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./digitfactorialchains.jar N");
			System.exit(1);
		}
		Long N = Long.parseLong(args[0]);

		System.out.println(new DigitFactorialChains().solve(N));
	}
}
