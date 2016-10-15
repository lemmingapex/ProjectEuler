package com.lemmingapex.projecteuler.doublebasepalindromes;

/**
 * 10/14/2016
 * DoubleBasePalindromes.java
 * Double-base palindromes
 *
 * @author Scott Wiedemann
 *
 */
public class DoubleBasePalindromes {

	private String toBinary(long x) {
		String xInBase2 = "";
		do {
			xInBase2 = (x%2) + xInBase2;
			x /= 2;
		} while(x > 1L);
		xInBase2 = "1" + xInBase2;
		return xInBase2;
	}

	private boolean isPalindrome(String s) {
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


	private boolean isPalindromeInBothBases(long x) {
		return isPalindrome(String.valueOf(x)) && isPalindrome(toBinary(x));
	}

	public long solve(long N) {
		long sum = 0L;
		// check only odd numbers. (base 2 numbers must in with 1)
		for(long i=1L; i < N; i+=2) {
			if(isPalindromeInBothBases(i)) {
				//System.out.println(i);
				sum += i;
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./doublebasepalindromes.jar N");
			System.exit(1);
		}
		Long N = Long.parseLong(args[0]);

		System.out.println(new DoubleBasePalindromes().solve(N));
	}
}
