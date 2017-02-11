package com.lemmingapex.projecteuler.squarerootdigitalexpansion;

import java.math.RoundingMode;
import java.math.BigDecimal;

/**
 * 02/11/2017
 * SquareRootDigitalExpansion.java
 * Square root digital expansion
 *
 * @author Scott Wiedemann
 *
 */
public class SquareRootDigitalExpansion {

	// Newton's method for root finding sqrt(S)
	private BigDecimal sqrt(BigDecimal S, final int precision) {
		final BigDecimal TWO = BigDecimal.valueOf(2);
		BigDecimal x0 = BigDecimal.valueOf(0);
		BigDecimal x1 = new BigDecimal(Math.sqrt(S.doubleValue()));
		while (!x0.equals(x1)) {
			x0 = x1;
			x1 = S.divide(x0, precision, RoundingMode.HALF_UP).add(x0).divide(TWO, precision, RoundingMode.HALF_UP);
		}
		return x1;
	}


	public long solve(final int N) {
		final int presision = 100;
		long solution = 0L;

		for(int i=2; i<=N; i++) {
			int sqrti_int = (int)Math.sqrt(i);
			if(sqrti_int*sqrti_int != i) {
				String sqrti_string = sqrt(BigDecimal.valueOf(i), presision+1).toString();
				String digits = sqrti_string.replaceAll("\\D","");
				for(int j=0; j<presision; j++) {
					int c = digits.charAt(j) - 48;
					solution += c;
				}
			}
		}

		return solution;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./squarerootdigitalexpansion.jar N");
			System.exit(1);
		}
		final Integer N = Integer.parseInt(args[0]);

		System.out.println(new SquareRootDigitalExpansion().solve(N));
	}
}
