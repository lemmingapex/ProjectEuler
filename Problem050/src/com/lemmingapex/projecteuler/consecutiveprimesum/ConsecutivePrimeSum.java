package com.lemmingapex.projecteuler.consecutiveprimesum;

import java.util.List;

import com.lemmingapex.basics.primes.PrimeGenerator;

/**
 * 11/12/2016
 * ConsecutivePrimeSum.java
 * Consecutive prime sum
 *
 * @author Scott Wiedemann
 *
 */
public class ConsecutivePrimeSum {

	private boolean isPrime(long p) {
		if(p == 1) {
			return false;
		} else if(p ==2) {
			return true;
		}
		int upperBound = (int)Math.sqrt(p);
		for(int i=2; i<=upperBound; i++) {
			if(p%i == 0) {
				return false;
			}
		}
		return true;
	}

	public Long solve() {

		int longestSequenceLength = 21;
		Long longestSequenceSum = 0L;

		List<Long> primes = PrimeGenerator.generatePrimesUpToN(1000000L/longestSequenceLength);

		for(int i = 0; i<primes.size(); i++) {
			Long sum = primes.get(i);
			if(sum > (1000000L/longestSequenceLength)) {
				break;
			}
			for(int j = i+1; j<primes.size(); j++) {
				sum += primes.get(j);
				if(sum > 1000000L) {
					break;
				} else if(isPrime(sum)) {
					int sequenceLength = (j-i)+1;
					if(sequenceLength > longestSequenceLength) {
						longestSequenceLength = sequenceLength;
						//System.out.println(longestSequenceLength);
						longestSequenceSum = sum;
					}
				}
			}
		}

		return longestSequenceSum;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./consecutiveprimesum.jar");
			System.exit(1);
		}

		System.out.println(new ConsecutivePrimeSum().solve());
	}
}
