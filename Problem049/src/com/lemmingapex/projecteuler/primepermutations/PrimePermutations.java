package com.lemmingapex.projecteuler.primepermutations;

import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import com.lemmingapex.basics.primes.PrimeGenerator;

/**
 * 11/11/2016
 * PrimePermutations.java
 * Prime permutations
 *
 * @author Scott Wiedemann
 *
 */
public class PrimePermutations {

	private boolean isPermutation(Long i, Long j) {
		char[] iChars = i.toString().toCharArray();
		char[] jChars = j.toString().toCharArray();
		Arrays.sort(iChars);
		Arrays.sort(jChars);
		for(int k=0; k<iChars.length; k++) {
			if(iChars[k] != jChars[k]) {
				return false;
			}
		}
		return true;
	}

	public String solve() {

		List<Long> primes = PrimeGenerator.generatePrimesUpToN(10000l);
		Iterator<Long> primeIterator = primes.iterator();
		while (primeIterator.hasNext()) {
			Long prime = primeIterator.next();
			if(prime < 1000) {
				primeIterator.remove();
			} else {
				break;
			}
		}

		for(int i = 0; i<primes.size(); i++) {
			Long p_i = primes.get(i);
			if(p_i == 1487) {
				continue;
			}
			for(int j = i+1; j<primes.size(); j++) {
				Long p_j = primes.get(j);
				if(isPermutation(p_i, p_j)) {
					Long p_k = p_j + (p_j - p_i);
					if(primes.contains(p_k) && isPermutation(p_j, p_k)) {
						return p_i + "" + p_j + "" + p_k;
					}
				}
			}
		}

		return "";
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./primepermutations.jar");
			System.exit(1);
		}

		System.out.println(new PrimePermutations().solve());
	}
}
