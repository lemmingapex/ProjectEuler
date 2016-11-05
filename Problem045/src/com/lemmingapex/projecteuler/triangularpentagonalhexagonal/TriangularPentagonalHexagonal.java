package com.lemmingapex.projecteuler.triangularpentagonalhexagonal;

/**
 * 11/05/2016
 * TriangularPentagonalHexagonal.java
 * Triangular, pentagonal, and hexagonal
 *
 * @author Scott Wiedemann
 *
 */
public class TriangularPentagonalHexagonal {

	public long solve() {

		for(long t=286; t<Long.MAX_VALUE; t++) {
			long Cp = -((t*t)+t);
			double p = (1.0 + Math.sqrt(1L - 12L*Cp))/6.0;
			if(p%1 == 0) {
				long p_long = (long)p;
				long Ch = (p_long-(3*p_long*p_long));
				double h = (1.0 + Math.sqrt(1L - 4L*Ch))/4.0;
				if(h%1 == 0) {
					long T = (((t*t)+t)/2);
					System.out.println("t, T: " + t + ", " + T);
					return T;
				}
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./triangularpentagonalhexagonal.jar");
			System.exit(1);
		}

		System.out.println(new TriangularPentagonalHexagonal().solve());
	}
}
