package com.lemmingapex.projecteuler.pandigitalprime;

import java.util.Arrays;

/**
 * 10/25/2016
 * PandigitalPrime.java
 * Pandigital Prime
 *
 * @author Scott Wiedemann
 *
 */
public class PandigitalPrime {

	private boolean is1toNPandigital(String n) {
		char[] nChars = n.toCharArray();
		Arrays.sort(nChars);
		for(int i=0; i<n.length(); i++) {
			if((nChars[i] - '1') != i) {
				return false;
			}
		}
		return true;
	}

	private boolean isPrime(int p) {
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

	public int solve() {

		// 7654321b is upperbound because:
		// 987654321 is always dividable by 3
		// 87654321 is always dividable by 3

		for(int i=7654321; i > 0; i-=2) {
			if(is1toNPandigital(String.valueOf(i)) && isPrime(i)) {
				return i;
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./pandigitalprime.jar");
			System.exit(1);
		}

		System.out.println(new PandigitalPrime().solve());
	}
}
