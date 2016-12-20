package com.lemmingapex.projecteuler.lychrelnumbers;

import java.math.BigInteger;
import java.util.HashSet;
import java.lang.StringBuilder;

/**
 * 12/20/2016
 * LychrelNumbers.java
 * Lychrel numbers
 *
 * @author Scott Wiedemann
 *
 */
public class LychrelNumbers {

	private static BigInteger reverse(BigInteger x) {
		return new BigInteger(new StringBuilder(x.toString()).reverse().toString());
	}

	private static boolean isPalindrome(String s) {
		boolean isPalindrome = true;

		int sLength = s.length();
		for(int i = 0; i < sLength/2; i++) {
			if(s.charAt(i) != s.charAt(sLength - 1 - i)) {
				isPalindrome = false;
				break;
			}
		}
		return isPalindrome;
	}

	public static int solve(int N) {
		HashSet<Integer> lychrelNumbers = new HashSet<Integer>();
		HashSet<Integer> nonLychrelNumbers = new HashSet<Integer>();

		final int upperBound = 50;

		for(int i=1; i<N; i++) {
			if(nonLychrelNumbers.contains(i) || lychrelNumbers.contains(i)) {
				continue;
			}
			boolean iIsPalindrome = false;
			BigInteger x = BigInteger.valueOf(i);
			for(int j=0; j<upperBound; j++) {
				x = x.add(reverse(x));
				if(isPalindrome(x.toString())) {
					iIsPalindrome = true;
					break;
				}
			}
			if(iIsPalindrome) {
				nonLychrelNumbers.add(i);
			} else {
				lychrelNumbers.add(i);
			}
		}
		return lychrelNumbers.size();
	}


	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./lychrelnumbers.jar N");
			System.exit(1);
		}

		int N = Integer.parseInt(args[0]);
		System.out.println(LychrelNumbers.solve(N));
	}
}
