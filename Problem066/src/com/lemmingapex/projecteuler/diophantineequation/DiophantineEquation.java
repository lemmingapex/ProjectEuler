package com.lemmingapex.projecteuler.diophantineequation;

import java.util.ArrayList;
import java.math.BigInteger;
import java.util.List;

/**
 * 01/17/2017
 * DiophantineEquation.java
 * Diophantine equation
 *
 * @author Scott Wiedemann
 *
 */
public class DiophantineEquation {

	private boolean isSquareFree(int d) {
		double sqrtD = Math.sqrt(d);
		return Math.floor(sqrtD) != sqrtD;
	}

	// public long solve(int D_UpperBound) {
	// 	long max_x = 0;
	// 	int D_LowerBound = 2;
	// 	for(int d=D_LowerBound; d<=D_UpperBound; d++) {
	// 		if(isSquareFree(d)) {
	// 			for(long x=Math.max(2, (int)Math.ceil(Math.sqrt(d))); ; x++) {
	// 				//System.out.println("x*x = " + (x*x));
	// 				double D = (x*x - 1.0)/(double)d;
	// 				if(Math.floor(D) == D) {
	// 					double y_double = Math.sqrt(D);
	// 					long y = (long)y_double;
	// 					if(Math.floor(y_double) == y_double) {
	// 						System.out.println(x + "² - " + d + "*" + y + "² = 1");
	// 						if(x > max_x) {
	// 							max_x = x;
	// 						}
	// 						break;
	// 					}
	// 				}
	// 			}
	// 		}
	// 	}
	//
	// 	return max_x;
	// }

	public int solve(int D_UpperBound) {
		BigInteger max_x = BigInteger.ZERO;
		int d_at_max_x = 0;
		int D_LowerBound = 2;
		for(int d=D_LowerBound; d<=D_UpperBound; d++) {
			if(isSquareFree(d)) {
				List<Integer> bis = new ArrayList<Integer>();
				int m = 0;
				int dd = 1;
				int a = 1;
				int b0 = (int)Math.sqrt(d);

				int b = b0;
				int i = 0;
				do {
					i++;
					m = dd*b - m;
					dd = (d - (m*m))/dd;
					if(dd == 0) {
						break;
					}
					b = (b0 + m)/dd;
					bis.add(b);
				} while(b != 2*b0);

				BigIntegerFraction convergent_n_minus_1 = new BigIntegerFraction(BigInteger.valueOf(1), BigInteger.valueOf(0));
				BigIntegerFraction convergent_n = new BigIntegerFraction(BigInteger.valueOf(b0), BigInteger.valueOf(1));
				i = 0;
				while(true) {
					Integer bi = bis.get(i);
					BigIntegerFraction convergent_n_plus_1 = new BigIntegerFraction(BigInteger.valueOf(bi).multiply(convergent_n.getNumerator()).add(convergent_n_minus_1.getNumerator()), BigInteger.valueOf(bi).multiply(convergent_n.getDenominator()).add(convergent_n_minus_1.getDenominator()));
					convergent_n_minus_1 = convergent_n;
					convergent_n = convergent_n_plus_1;

					BigInteger x_squared = convergent_n.getNumerator().multiply(convergent_n.getNumerator());
					BigInteger y_squared = convergent_n.getDenominator().multiply(convergent_n.getDenominator());

					if(x_squared.subtract(BigInteger.valueOf(d).multiply(y_squared)).equals(BigInteger.valueOf(1))) {
						System.out.println(convergent_n.getNumerator() + "² - " + d + "*" + convergent_n.getDenominator() + "² = 1");
						if(convergent_n.getNumerator().compareTo(max_x) == 1) {
							max_x = convergent_n.getNumerator();
							d_at_max_x = d;
						}

						break;
					}
					i = (i+1)%bis.size();
				}
			}
		}

		return d_at_max_x;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./diophantineequation.jar N");
			System.exit(1);
		}

		final int N = Integer.parseInt(args[0]);

		System.out.println(new DiophantineEquation().solve(N));
	}
}
