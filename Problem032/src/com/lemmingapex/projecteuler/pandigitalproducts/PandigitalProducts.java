package com.lemmingapex.projecteuler.pandigitalproducts;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 10/10/2016
 * PandigitalProducts.java
 * Pandigital products
 *
 * @author Scott Wiedemann
 *
 */
public class PandigitalProducts {

	/**
	*
	* Makes sure nChars does not contain a digit of 0, and makes sure there are not repeated digit values in nChars
	*
	*/
	private boolean validForm(char[] nChars) {
		Arrays.sort(nChars);

		if(nChars[0] == '0') {
			return false;
		}

		char charPrev = nChars[0];
		for(int i=1; i<nChars.length; i++) {
			char charCurrent = nChars[i];
			if(charPrev == charCurrent) {
				return false;
			}
			charPrev = charCurrent;
		}
		return true;
	}

	private boolean validProduct(int n) {
		char[] nChars = String.valueOf(n).toCharArray();
		// the last digit of the product can not be 5.  All multiples of 5 end in 0 or 5.  0 is not allowed and 5 would be a repeated digit.
		if(nChars[nChars.length - 1] == '5') {
			return false;
		}

		return validForm(nChars);
	}

	private boolean validProductMultiplicandMultiplier(int i, int j, int k) {
		String jString = String.valueOf(j);
		String kString = String.valueOf(k);
		char[] nChars = (jString + kString + String.valueOf(i)).toCharArray();

		if(nChars.length != 9) {
			return false;
		}

		// the last digit of the multiplier can not be 1.  If it were, then the last digit of the product would be the same as the multiplicand.
		// by symmetry the last digit of the multiplicand can not be 1.
		// 1*ABC = XYC
		if(jString.charAt(jString.length() - 1) == '1' || kString.charAt(kString.length() - 1) == '1') {
			return false;
		}

		return validForm(nChars);
	}

	public long solve() {
		long sum = 0;

		Set<Integer> validProducts = new HashSet<Integer>();

		// observations:
		// 1) the product should be larger than the multiplicand and the multiplier.  multiplication with positive integers makes a number larger.  Therefore, the product should conatin as many digits as the multiplicand and the product should contain as many digits as the multiplier.
		// 2) you can as observe that the number of digits in the product can be at most the sum of the number of digits in the multiplicand and the multiplier. 999*999= 998001 for example.
		// XXXXXX * Y = ZZ is not valid by observation 1
		// XXXXX * Y = ZZZ is not valid by observation 1
		// XXXX * Y = ZZZZ is valid
		// XXX * Y = ZZZZZ is not valid by observation 2
		// XXX * YY = ZZZZ is valid
		// Z is therefore bounded between the largest 4 digit number that can be made with the digits 1 to 9: 9876, and the smallest 4 digit number that can be made with the digits 1 to 9: 1234

		int upperBound = 9876;
		int lowerBound = 1234;

		for(int i = upperBound; i >= lowerBound; i--) {
			if(validProduct(i)) {
				//System.out.println(i);
				for(int j = (int)Math.sqrt(i); j > 1; j--) {
					if(i%j == 0) {
						int k = i/j;
						// i = j*k
						if(validProductMultiplicandMultiplier(i, j, k) && !validProducts.contains(i)) {
							//System.out.println(i + "=" + j + "*" + k);
							validProducts.add(i);
							sum += i;
						}
					}
				}
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./pandigitalproducts.jar");
			System.exit(1);
		}

		System.out.println(new PandigitalProducts().solve());
	}
}
