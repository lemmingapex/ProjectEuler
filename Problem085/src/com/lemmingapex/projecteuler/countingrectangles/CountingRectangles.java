package com.lemmingapex.projecteuler.countingrectangles;

/**
 * 02/23/2017
 * CountingRectangles.java
 * Counting rectangles
 *
 * @author Scott Wiedemann
 *
 */
public class CountingRectangles {

	public int solve(int C) {

		double smallestMError = Double.MAX_VALUE;
		int mWithSmallestError = -1;
		int nWithM = -1;

		int nLowerBound = 1;
		int nUpperBound = (int)(Math.sqrt((2.0*(double)C) + 0.25) - 0.5);

		for(int n=nLowerBound; n<=nUpperBound; n++) {
			double mDouble = Math.sqrt((((4.0*(double)C))/(n*n+n)) + 0.25) - 0.5;
			if(mDouble <= n) {
				int mInt = (int)mDouble;
				//System.out.println("m x n : " + mInt + " x " + n);
				double mError = mDouble - mInt;
				if(mError < smallestMError) {
					smallestMError = mError;
					mWithSmallestError = mInt;
					nWithM = n;
				}
			}
		}

		int area = mWithSmallestError*nWithM;
		//System.out.println("mWithSmallestError x nWithM = area " + mWithSmallestError + " x " + nWithM + " = " + area);
		//int closeC = mWithSmallestError*(1+mWithSmallestError)*nWithM*(1+nWithM)/4;
		//System.out.println("closeC " + closeC);

		return area;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./countingrectangles.jar N");
			System.exit(1);
		}
		final int C = Integer.parseInt(args[0]);

		System.out.println(new CountingRectangles().solve(C));
	}
}
