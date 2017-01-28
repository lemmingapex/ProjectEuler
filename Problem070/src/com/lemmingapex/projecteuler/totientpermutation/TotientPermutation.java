package com.lemmingapex.projecteuler.totientpermutation;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

/**
 * 01/25/2017
 * TotientPermutation.java
 * Totient permutation
 *
 * @author Scott Wiedemann
 *
 */
public class TotientPermutation {

	private boolean isPermutation(Integer i, Integer j) {
		char[] iChars = i.toString().toCharArray();
		char[] jChars = j.toString().toCharArray();
		if(iChars.length != jChars.length) {
			return false;
		}
		Arrays.sort(iChars);
		Arrays.sort(jChars);
		for(int k=0; k<iChars.length; k++) {
			if(iChars[k] != jChars[k]) {
				return false;
			}
		}
		return true;
	}

	public Set<Integer> distinctPrimeFactors(int r) {
		int n = r;
		Set<Integer> factors = new HashSet<Integer>();
		for (int i = 2; i <= n / i; i++) {
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
		if (n > 1) {
			factors.add(n);
		}
		return factors;
	}

	public int solve(Long N) {
		double minimum_ratio = Double.MAX_VALUE;
		int n_with_minimum_ratio = -1;
		for(int n=2; n<N; n++) {
			int phi = n;
			for(Integer p : distinctPrimeFactors(n)) {
				phi /= p;
				phi *= (p-1);
			}
			if(isPermutation(n, phi)) {
				double t_ratio = ((double)n)/phi;
				//System.out.println("n=" + n + " phi=" + phi + " n/phi=" + t_ratio);
				if(t_ratio < minimum_ratio) {
					minimum_ratio = t_ratio;
					n_with_minimum_ratio = n;
				}
			}
		}
		return n_with_minimum_ratio;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./totientpermutation.jar N");
			System.exit(1);
		}
		Long N = Long.parseLong(args[0]);

		System.out.println(new TotientPermutation().solve(N));
	}
}
