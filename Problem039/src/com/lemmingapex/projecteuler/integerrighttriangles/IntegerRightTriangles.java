package com.lemmingapex.projecteuler.integerrighttriangles;

/**
 * 10/23/2016
 * IntegerRightTriangles.java
 * Integer right triangles
 *
 * @author Scott Wiedemann
 *
 */
public class IntegerRightTriangles {
	public int solve(int n) {
		int largestCount = 0;
		int pLargestCount = 0;

		int pLowerBound = 3;
		int pUpperBound = n;

		double aCoefficientUpperBound = ((2 - Math.sqrt(2))/2);

		for(int p=pLowerBound; p<=pUpperBound; p++) {
			int aUpperBound = (int)(aCoefficientUpperBound*p);
			int count = 0;
			for(int a=1; a < aUpperBound; a++) {
				double b = (0.5*p*p - a*p)/(p - a);
				// is b an integer?
				if(b%1 == 0) {
					count++;
				}
			}
			if(count > largestCount) {
				largestCount = count;
				pLargestCount = p;
			}
		}

		return pLargestCount;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./integerrighttriangles.jar N");
			System.exit(1);
		}
		Integer N = Integer.parseInt(args[0]);

		System.out.println(new IntegerRightTriangles().solve(N));
	}
}
