package com.lemmingapex.projecteuler.distinctprimesfactors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import com.lemmingapex.basics.primes.PrimeGenerator;

/**
 * 11/08/2016
 * DistinctPrimesFactors.java
 * Distinct primes factors
 *
 * @author Scott Wiedemann
 *
 */
public class DistinctPrimesFactors {

	List<Long> primes;

	public DistinctPrimesFactors() {
		primes = PrimeGenerator.generatePrimesUpToN(1000000l);
	}

	private int getDistinctPrimeFactorLength(long l) {
		Set<Long> factors = new HashSet<Long>();
		for(int i = 0; ; i++) {
			long prime = this.primes.get(i);
			if(prime > l) {
				break;
			}
			while(l%prime == 0) {
				factors.add(prime);
				l /= prime;
			}
		}
		return factors.size();
	}

	public long solve(long n) {
		long currentLength = 0;
		long start = 2;
		while(true) {
			currentLength = 0;
			//System.out.println(start + " " + getDistinctPrimeFactorLength(start));
			while(n == getDistinctPrimeFactorLength(start)) {
				currentLength++;
				if(currentLength == n) {
					return (start - currentLength + 1);
				}
				start++;
			}
			start++;
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./distinctprimesfactors.jar N");
			System.exit(1);
		}
		long N = Long.parseLong(args[0]);

		System.out.println(new DistinctPrimesFactors().solve(N));
	}
}
