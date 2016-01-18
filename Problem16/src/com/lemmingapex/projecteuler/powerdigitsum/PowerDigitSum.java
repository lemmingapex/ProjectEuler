package com.lemmingapex.projecteuler.powerdigitsum;

import java.math.BigInteger;

/**
 * 01/18/2016
 * PowerDigitSum.java
 * Power Digit Sum
 *
 * @author Scott Wiedemann
 *
 */
public class PowerDigitSum {

	public static void main(String[] args) {
		if (args.length != 1) {
	        System.err.println("Incorrect number of arguments.");
	        System.err.println("Usage: ./powerdigitsum.jar N");
	        System.exit(1);
		}
		Integer N = Integer.parseInt(args[0]);
		String digits = BigInteger.valueOf(2).pow(N).toString();
		long sum = 0;
		for(int i = 0; i < digits.length(); i++) {
			sum += Character.getNumericValue(digits.charAt(i));
		}
		System.out.println(sum);
	}
}
