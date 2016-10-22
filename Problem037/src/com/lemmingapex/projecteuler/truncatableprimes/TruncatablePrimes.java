package com.lemmingapex.projecteuler.truncatableprimes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import com.lemmingapex.basics.primes.PrimeGenerator;

/**
 * 10/22/2016
 * TruncatablePrimes.java
 * Truncatable primes
 *
 * @author Scott Wiedemann
 *
 */
public class TruncatablePrimes {

	// slower way
	public long solve() {
		Long sum = 0L;
		List<Long> primes = PrimeGenerator.generatePrimesUpToN(1000000L);
		// because we will skip 2,3,5 and 7.  start at index 4
		for (int i = 4; i < primes.size(); i++) {
			Long p = primes.get(i);
			boolean allTruncationsArePrime = true;
			String pString = p.toString();
			if(pString.matches("\\d*[0468]\\d*") || pString.matches("\\d+[52]\\d*")) {
				allTruncationsArePrime = false;
			} else {
				//System.out.println("p " + p);
				int lastIndex = pString.length() - 1;
				for(int j = 1; j <= lastIndex; j++) {
					// left to right
					String pSubStringLtoR = pString.substring(j, lastIndex+1);
					// System.out.println("pSubStringLtoR " + pSubStringLtoR);
					if(!primes.contains(Long.valueOf(pSubStringLtoR))) {
						allTruncationsArePrime = false;
						break;
					}
					// right to left
					String pSubStringRtoL = pString.substring(0, lastIndex+1-j);
					// System.out.println("pSubStringRtoL " + pSubStringRtoL);
					if(!primes.contains(Long.valueOf(pSubStringRtoL))) {
						allTruncationsArePrime = false;
						break;
					}
				}
			}
			if(allTruncationsArePrime) {
				System.out.println(p);
				sum += p;
			}
		}
		return sum;
	}

	List<Integer> possibleDigits = new ArrayList<Integer>();
	public TruncatablePrimes() {
		possibleDigits.add(1);
		possibleDigits.add(2);
		possibleDigits.add(3);
		possibleDigits.add(5);
		possibleDigits.add(7);
		possibleDigits.add(9);
	}

	// faster way
	public long solve2() {
		Long sum = 0L;
		Set<Long> truncatablePrimes = new TreeSet<Long>();
		recurseTruncatablePrimes(truncatablePrimes, 3L);
		recurseTruncatablePrimes(truncatablePrimes, 7L);
		for(Long p : truncatablePrimes) {
			System.out.println(p);
			sum += p;
		}
		return sum;
	}

	private boolean isPrime(long p) {
		if(p == 1) {
			return false;
		} else if(p ==2) {
			return true;
		}
		long upperBound = (long)Math.sqrt(p);
		for(long i=2L; i<=upperBound; i++) {
			if(p%i == 0) {
				return false;
			}
		}
		return true;
	}

	private boolean isTruncatablePrime(Long p) {
		if(p < 10) {
			return false;
		}
		if(!isPrime(p)) {
			return false;
		}
		Boolean allTruncationsArePrime = true;
		String pString = p.toString();
		int lastIndex = pString.length() - 1;
		for(int j = 1; j <= lastIndex; j++) {
			// left to right
			String pSubStringLtoR = pString.substring(j, lastIndex+1);
			// System.out.println("pSubStringLtoR " + pSubStringLtoR);
			if(!isPrime(Long.valueOf(pSubStringLtoR))) {
				allTruncationsArePrime = false;
				break;
			}
			// right to left
			String pSubStringRtoL = pString.substring(0, lastIndex+1-j);
			// System.out.println("pSubStringRtoL " + pSubStringRtoL);
			if(!isPrime(Long.valueOf(pSubStringRtoL))) {
				allTruncationsArePrime = false;
				break;
			}
		}

		return allTruncationsArePrime;
	}

	private void recurseTruncatablePrimes(Set<Long> truncatablePrimes, Long p) {
		String pString = p.toString();
		if(isTruncatablePrime(p)) {
			truncatablePrimes.add(p);
		}
		if(pString.length() == 6) {
			return;
		} else if(!pString.matches("\\d*[52]\\d*")) {
			for(Integer possibleDigit : this.possibleDigits) {
				recurseTruncatablePrimes(truncatablePrimes, Long.valueOf(possibleDigit + pString));
			}
		}
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./truncatableprimes.jar");
			System.exit(1);
		}

		System.out.println("sum: " + new TruncatablePrimes().solve2());
	}
}
