package com.lemmingapex.projecteuler.amicablenumbers;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;

/**
 * 09/24/2016
 * AmicableNumbers.java
 * Amicable numbers
 *
 * @author Scott Wiedemann
 *
 */
public class AmicableNumbers {

	private Map<Long,Long> divisorCache = new HashMap<Long,Long>();

	private Set<Long> findProperDivisors(long x) {
		Set<Long> divisors = new TreeSet<Long>();
		divisors.add(1L);
		long upperBound = (long)Math.sqrt(x);
		for(long i=2L; i<=upperBound; i++) {
			if(x%i == 0) {
				divisors.add(i);
				divisors.add((long)(x/i));
			}
		}
		return divisors;
	}

	private long sumOfProperDivisors(long x) {
		long sum = 0L;
		for(Long d : findProperDivisors(x)) {
			sum += d;
		}
		return sum;
	}

	public long solve(long N) {
		long sumOfAmicableNumbersUnderN = 0;
		divisorCache.clear();

		for(long i=2; i<N; i++) {
			long i_sumOfDivisors = sumOfProperDivisors(i);
			if(i_sumOfDivisors < N && i_sumOfDivisors > i) {
				divisorCache.put(i, i_sumOfDivisors);
			} else {
				if(divisorCache.get(i_sumOfDivisors) != null && i == divisorCache.get(i_sumOfDivisors)) {
					sumOfAmicableNumbersUnderN += i_sumOfDivisors + i;
				}
			}
		}

		return sumOfAmicableNumbersUnderN;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./amicablenumbers.jar N");
			System.exit(1);
		}
		Long N = Long.parseLong(args[0]);

		System.out.println(new AmicableNumbers().solve(N));
	}
}
