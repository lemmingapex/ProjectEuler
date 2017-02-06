package com.lemmingapex.projecteuler.singularintegerrighttriangles;

import java.util.Map;
import java.util.HashMap;

/**
 * 02/05/2017
 * SingularIntegerRightTriangles.java
 * Singular integer right triangles
 *
 * @author Scott Wiedemann
 *
 */
public class SingularIntegerRightTriangles {

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

	public int solve(int L) {

		Map<Long, Integer> triangleCount = new HashMap<Long, Integer>();

		// find a upper limit to check.
		// l = a + b + c
		// l = m*m-n*n + 2*m*n + m*m+n*n
		// l = 2m*m + 2m*n.  m is greatest when n=0. (0<n<m)
		// sqrt(l/2) = m
		final long upperBound = (long)Math.sqrt(((double)L)/2.0);

		// using Euclid's formula for a Pythagorean triple
		// m and n are odd. m>n. m and n are co-prime.
		// m is odd
		for(long m=1; m<upperBound; m++) {
			// n < m. and n is odd.
			for(long n=m-1; n>0; n--) {
				// m is odd and n is even or n is odd and m is even. m and n are co-prime.
				if((m+n)%2 == 1 && gcd(m, n) == 1) {
					// long a = (m*m-n*n);
					// long b = 2*m*n;
					// long c = (m*m+n*n);
					// long p = a+b+c;
					long p = 2*m*(m + n);
					long l = p;
					while(l <= L) {
						Integer c = triangleCount.get(l);
						if(c == null) {
							c = 0;
						}
						triangleCount.put(l, c+1);
						//System.out.println("(m, n) : (a, b, c) : l (" + m + ", " + n + ") : (" + a + ", " + b + ", " + c + ") : " + l);
						l += p;
					}
				}
			}
		}

		int count = 0;
		for(Long l : triangleCount.keySet()) {
			Integer c = triangleCount.get(l);
			if(c == 1) {
				count++;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./singularintegerrighttriangles.jar L");
			System.exit(1);
		}
		final Integer L = Integer.parseInt(args[0]);

		System.out.println(new SingularIntegerRightTriangles().solve(L));
	}
}
