package com.lemmingapex.projecteuler.permutedmultiples;

import java.util.Arrays;

/**
 * 11/18/2016
 * PermutedMultiples.java
 * Permuted multiples
 *
 * @author Scott Wiedemann
 *
 */
public class PermutedMultiples {

	private boolean isPermutation(String i, String j) {
		char[] iChars = i.toCharArray();
		char[] jChars = j.toCharArray();
		if(jChars.length != iChars.length) {
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

	public long solve() {
		long sixX = 9;
		while(true) {
			long xUpperBound = sixX/6;
			long xLowerBound = (sixX+1)/10;
			for(long x=xLowerBound; x<xUpperBound; x++) {
				int count = 0;
				String xString = String.valueOf(x);
				for(int k = 6; k > 1; k--) {
					long kX = k*x;
					if(isPermutation(xString, String.valueOf(kX))) {
						count++;
					} else {
						break;
					}
				}
				if(count == 5) {
					return x;
				}
			}
			sixX = ((sixX+1)*10)-1;
		}
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./permutedmultiples.jar");
			System.exit(1);
		}

		System.out.println(new PermutedMultiples().solve());
	}
}
