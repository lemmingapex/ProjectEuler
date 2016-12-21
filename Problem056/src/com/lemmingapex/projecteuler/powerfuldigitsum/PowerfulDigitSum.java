package com.lemmingapex.projecteuler.powerfuldigitsum;

import java.math.BigInteger;

/**
 * 12/21/2016
 * PowerfulDigitSum.java
 * Powerful digit sum
 *
 * @author Scott Wiedemann
 *
 */
public class PowerfulDigitSum {

	public static int solve(int N) {
		int greatestDigitSum = 1;
		for(int a=N-1; a>0; a--) {
			BigInteger a_BI = BigInteger.valueOf(a);
			for(int b=N-1; b>0; b--) {
				String aToTheB = a_BI.pow(b).toString();
				if(aToTheB.length() < (Math.log10(greatestDigitSum) + 1)) {
					break;
				}
				int sum = 0;
				for(int i=0; i<aToTheB.length(); i++) {
					sum += Integer.parseInt("" + aToTheB.charAt(i));
				}
				if(sum > greatestDigitSum) {
					greatestDigitSum = sum;
				}
			}
		}
		return greatestDigitSum;
	}


	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./powerfuldigitsum.jar N");
			System.exit(1);
		}

		int N = Integer.parseInt(args[0]);
		System.out.println(PowerfulDigitSum.solve(N));
	}
}
