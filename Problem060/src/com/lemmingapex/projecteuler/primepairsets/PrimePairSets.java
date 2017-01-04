package com.lemmingapex.projecteuler.primepairsets;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import com.lemmingapex.basics.primes.PrimeGenerator;

/**
 * 01/03/2017
 * PrimePairSets.java
 * Prime pair sets
 *
 * @author Scott Wiedemann
 *
 */
public class PrimePairSets {

	// Szudzik's Elegant Pairing Function
	// http://szudzik.com/ElegantPairing.pdf

	public int pair(int x, int y){
		return x >= y ? (x*x+x+y) : (y*y+x);
	}

	public int[] unpair(int z){
		int[] pair = new int[2];
		int sqrtz = (int)Math.sqrt(z);
		int l = z - sqrtz*sqrtz;
		if(l < sqrtz) {
			pair[0] = l;
			pair[1] = sqrtz;
		} else {
			pair[0] = sqrtz;
			pair[1] = l - sqrtz;
		}
		return pair;
	}

	private boolean isPrime(int p) {
		if(p == 1) {
			return false;
		}
		int upperBound = (int)Math.sqrt(p);
		for(int i=2; i<=upperBound; i++) {
			if(p%i == 0) {
				return false;
			}
		}
		return true;
	}

	private boolean isCompositePair(int x, int y) {
		int xy = Integer.parseInt(x+""+y);
		int yx = Integer.parseInt(y+""+x);
		if(!isPrime(xy) || !isPrime(yx)) {
			return true;
		}
		return false;
	}

	public int solve() {
		final long upperBound = 10000L;

		List<Long> primes = PrimeGenerator.generatePrimesUpToN(upperBound);

		Set<Integer> compositeFormingPairs = new HashSet<>();

		int primesLength = primes.size();
		for(int i=0; i<primesLength-1; i++) {
			for(int j=i+1; j<primesLength; j++) {
				int pi = (int)primes.get(i).longValue();
				int pj = (int)primes.get(j).longValue();
				if(isCompositePair(pi, pj)) {
					// remember that pi < pj
					compositeFormingPairs.add(pair(pi, pj));
				} else {
					//System.out.println(pi + " " + pj);
				}
			}
		}

		final int sumLowerBound = 1 + 3 + 7 + 9 + 11;

		for(int s=sumLowerBound; s<upperBound; s++) {
			for(int i=0; i<primesLength; i++) {
				int a = (int)primes.get(i).longValue();
				int b = s - a;
				if(primes.contains((long)b)) {
					if(!compositeFormingPairs.contains(pair(Math.min(a, b), Math.max(a, b)))) {
						System.out.println(a + " " + b);
					}
				}
			}
		}


		return 0;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./primepairsets.jar");
			System.exit(1);
		}

		System.out.println(new PrimePairSets().solve());
	}
}
