package com.lemmingapex.projecteuler.almostequilateraltriangle;

/**
 * 04/16/2017
 * AlmostEquilateralTriangle.java
 * Almost equilateral triangles
 *
 * @author Scott Wiedemann
 *
 */
public class AlmostEquilateralTriangle {

	static public long gcd(long a, long b) {
		long t = a;
		if(a < b) {
			a = b;
			b = t;
		}
		while(b != 0) {
			t = b;
			b = a % b;
			a = t;
		}
		return a;
	}

	public long solve(int N) {
		long perimeterSum = 0;

		length:
		for(long m=2; ; m++) {
			long mSquared = m*m;
			for(long o=-1; o<=1; o+=2) {
				double nSquared_double = (mSquared - o)/3.0;
				int nSquared = (int)nSquared_double;
				double n_double = Math.sqrt(nSquared_double);
				// TODO: check all multipules of m and n up to N

				// check if n is an integer
				//System.out.println(mSquared + " " + nSquared);
				if(n_double == Math.floor(n_double) && mSquared > nSquared) {
					long n = (long) n_double;
					// make sure m and n are co-prime
					if(AlmostEquilateralTriangle.gcd(m, n) == 1) {
						long l = mSquared + nSquared;
						long k = 2*(mSquared - nSquared);
						long p = 2*l+k;
						if(p <= N) {
							System.out.println(l + " " + l + " " + k);
							perimeterSum += p;
						} else {
							break length;
						}
					}
				}
			}
		}

		return perimeterSum;
	}

	// public long solve(int N) {
	// 	long perimeterSum = 0;
	//
	// 	length:
	// 	for(int l=1; ; l++) {
	// 		for(int k=l-1; k<=l+1; k+=2) {
	// 			int p = 2*l+k;
	// 			if(p <= N) {
	// 				double A = Math.sqrt(4.0*l*l - k*k)*k*0.25;
	// 				if(A == Math.floor(A)) {
	// 					System.out.println(l + " " + l + " " + k + " " + A);
	// 					perimeterSum += p;
	// 				}
	// 			} else {
	// 				break length;
	// 			}
	// 		}
	// 	}
	//
	// 	return perimeterSum;
	// }

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./almostequilateraltriangle.jar N");
			System.exit(1);
		}
		final int N = Integer.parseInt(args[0]);

		System.out.println(new AlmostEquilateralTriangle().solve(N));
	}
}
