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

	// public long solve(int N) {
	// 	long perimeterSum = 0;
	//
	// 	for(long o=-1; o<=1; o+=2) {
	// 		for(long m=2; ; m++) {
	// 			double nSquared_double = (m*m - o)/3.0;
	// 			double n_double = Math.sqrt(nSquared_double);
	// 			if(2*(m*m + n_double*n_double)+(2*(m*m - n_double*n_double)) <= N) {
	// 				// check if n is an integer
	// 				//System.out.println("l = " + (m*m + n_double*n_double));
	// 				//System.out.println(m + " " + n_double);
	// 				if(n_double == Math.floor(n_double) && m*m > n_double*n_double) {
	// 					long n = (long) n_double;
	// 					// make sure m and n are co-prime
	// 					if(AlmostEquilateralTriangle.gcd(m, n) == 1) {
	// 						//System.out.println(m + " " + n);
	//
	// 						long l = m*m + n*n;
	// 						long k = 2*(m*m - n*n);
	// 						long p = 2*l+k;
	//
	// 						System.out.println(l + " " + l + " " + k);
	// 						perimeterSum += p;
	// 					}
	// 				}
	// 			} else {
	// 				break;
	// 			}
	// 		}
	// 	}
	// 	return perimeterSum;
	// }


	public long solve(int N) {
		long perimeterSum = 0;
		for(long s=1; ; s+=2) {
			if(s*s > (N + 1)/3) {
				break;
			}
			for(long t=s-2; t>0; t-=2) {
				if(AlmostEquilateralTriangle.gcd(s, t) == 1) {
					long a = s*t;
					long b = (s*s - t*t)/2;
					long c = (s*s + t*t)/2;
					for(long o=-1; o<=1; o+=2) {
						if(a*2 == c-o || b*2 == c-o) {
							long p = c*3-o;
							if(p <= N) {
								perimeterSum += p;
							}
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
