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

	private boolean arrayContainsACompositeFormingPair(Set<Integer> compositeFormingPairs, int ... arr) {
		for(int i = 0; i<arr.length-1; i++) {
			for(int j = i+1; j<arr.length; j++) {
				int a = arr[i];
				int b = arr[j];
				if(compositeFormingPairs.contains(pair(Math.min(a, b), Math.max(a, b)))) {
					return true;
				}
			}
		}
		return false;
	}

	public int solve() {
		final long upperBound = 10000L;

		List<Long> primes = PrimeGenerator.generatePrimesUpToN(upperBound);

		// numbers 2 and 5 form composite pairs
		primes.remove(2L);
		primes.remove(5L);

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

		int minsum = Integer.MAX_VALUE;

		for(int ai=0; ai<primesLength-4; ai++) {
			int a = (int)primes.get(ai).longValue();
			for(int bi=ai+1; bi<primesLength-3; bi++) {
				int b = (int)primes.get(bi).longValue();
				if(!arrayContainsACompositeFormingPair(compositeFormingPairs, a, b)) {
					for(int ci=bi+1; ci<primesLength-2; ci++) {
						int c = (int)primes.get(ci).longValue();
						if(!arrayContainsACompositeFormingPair(compositeFormingPairs, a, b, c)) {
							for(int di=ci+1; di<primesLength-1; di++) {
								int d = (int)primes.get(di).longValue();
								if(!arrayContainsACompositeFormingPair(compositeFormingPairs, a, b, c, d)) {
									for(int ei=di+1; ei<primesLength; ei++) {
										int e = (int)primes.get(ei).longValue();
										if(!arrayContainsACompositeFormingPair(compositeFormingPairs, a, b, c, d, e)) {
											System.out.println(a + " " + b + " " + c + " " + d + " " + e);
											int tsum = a + b + c + d + e;
											if(tsum < minsum) {
												minsum = tsum;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if(minsum == Integer.MAX_VALUE) {
			System.err.println("sum not found.  Increase bound?");
		}

		return minsum;
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
