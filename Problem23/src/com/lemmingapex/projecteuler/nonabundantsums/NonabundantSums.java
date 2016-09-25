package com.lemmingapex.projecteuler.nonabundantsums;

import java.util.TreeSet;
import java.util.Set;

/**
 * 09/25/2016
 * NonabundantSums.java
 * Non-abundantSums
 *
 * @author Scott Wiedemann
 *
 */
public class NonabundantSums {

	private long upperBound = 28123;

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

	private Set<Long> findAbundantNumbersUpToX(long x) {
		Set<Long> abundantNumbers = new TreeSet<Long>();
		for(long i = 1; i<x; i++) {
			long i_sumOfProperDivisors = sumOfProperDivisors(i);
			if(i_sumOfProperDivisors > i) {
				abundantNumbers.add(i);
			}
		}
		return abundantNumbers;
	}

	public long sumOfLongsThatCannotBeTheSumOfTwoAbundantNumbers() {
		long sumOfLongsThatCannotBeTheSumOfTwoAbundantNumbers = 0L;
		Set<Long> abundantNumbers = findAbundantNumbersUpToX(this.upperBound);
		for(long i = upperBound; i>0; i--) {
			boolean isISumOfTwoAbundantNumbers = false;
			for(long l : abundantNumbers) {
				if(i-l < 1) {
					break;
				}
				if(abundantNumbers.contains(i - l)) {
					isISumOfTwoAbundantNumbers = true;
					break;
				}
			}
			if(!isISumOfTwoAbundantNumbers) {
				sumOfLongsThatCannotBeTheSumOfTwoAbundantNumbers += i;
			}
		}
		return sumOfLongsThatCannotBeTheSumOfTwoAbundantNumbers;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./nonabundantsums.jar");
			System.exit(1);
		}

		System.out.println(new NonabundantSums().sumOfLongsThatCannotBeTheSumOfTwoAbundantNumbers());
	}
}
