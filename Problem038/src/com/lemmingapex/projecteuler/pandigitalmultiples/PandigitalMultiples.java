package com.lemmingapex.projecteuler.pandigitalmultiples;

import java.util.Arrays;

/**
 * 10/23/2016
 * PandigitalMultiples.java
 * Pandigital multiples
 *
 * @author Scott Wiedemann
 *
 */
public class PandigitalMultiples {

	private boolean is1to9Pandigital(String n) {
		if(n.length() != 9) {
			return false;
		}
		char[] nChars = n.toCharArray();
		Arrays.sort(nChars);
		for(int i=0; i<9; i++) {
			if((nChars[i] - '1') != i) {
				return false;
			}
		}
		return true;
	}

	public int solve() {
		int largestProduct = 0;

		// n > 1 from problem statement

		int iLowerBound = 3; // = (1*1)+(1*2)+(1*3)+...+(1*n), where the + operator is concatenation, and n is 2 in this case
		int iUpperBound = 10000; // = (10000*1)+(10000*2),  1000020000, the length of this number is greater than 9.

		for(Integer i=iLowerBound; i<=iUpperBound; i++) {
			String concatenatedProduct = i.toString();
			for(int n=2; concatenatedProduct.length() < 9; n++) {
				Integer product = i*n;
				String productString = product.toString();
				// can not contain a '0'
				if(productString.contains("0")) {
					break;
				}
				concatenatedProduct += productString;
				if(is1to9Pandigital(concatenatedProduct)) {
					Integer concatenatedProductInt = Integer.valueOf(concatenatedProduct);
					if(concatenatedProductInt > largestProduct) {
						largestProduct = concatenatedProductInt;
					}
				}
			}
		}

		return largestProduct;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./pandigitalmultiples.jar");
			System.exit(1);
		}

		System.out.println(new PandigitalMultiples().solve());
	}
}
