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

	public long solve(int N) {
		long perimeterSum = 0;

		length:
		for(int l=1; ; l++) {
			for(int k=l-1; k<=l+1; k+=2) {
				int p = 2*l+k;
				if(p <= N) {
					double A = Math.sqrt(4.0*l*l - k*k)*k*0.25;
					if(A == Math.floor(A)) {
						System.out.println(l + " " + l + " " + k + " " + A);
						perimeterSum += p;
					}
				} else {
					break length;
				}
			}
		}


		return perimeterSum;
	}

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
