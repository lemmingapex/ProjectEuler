package com.lemmingapex.projecteuler.champernownesconstant;

/**
 * 10/25/2016
 * ChampernownesConstant.java
 * Champernowne's Constant
 *
 * @author Scott Wiedemann
 *
 */
public class ChampernownesConstant {
	public long solve() {
		long dProduct = 1;
		int index = 1;
		int indexTarget = 10;

		for(Integer i = 1; index <= 1000000; i++) {
			//System.out.println(i);
			String iString = String.valueOf(i);
			int iLength = iString.length();

			if(index  >= indexTarget) {
				int indexValue = iString.toCharArray()[index - indexTarget] - '0';
				dProduct *= indexValue;
				indexTarget *= 10;
			}
			index += iLength;
		}

		return dProduct;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./champernownesconstant.jar");
			System.exit(1);
		}

		System.out.println(new ChampernownesConstant().solve());
	}
}
