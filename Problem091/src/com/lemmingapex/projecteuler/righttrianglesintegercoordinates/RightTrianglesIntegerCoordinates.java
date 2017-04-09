package com.lemmingapex.projecteuler.righttrianglesintegercoordinates;

/**
 * 04/09/2017
 * RightTrianglesIntegerCoordinates.java
 * Right triangles with integer coordinates
 *
 * @author Scott Wiedemann
 *
 */
public class RightTrianglesIntegerCoordinates {

	// Euclid's algorithm
	public int gcd(int a, int b) {
		int t = a;
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

	public int solve(int N) {
		int numberOfTriangles = 0;

		// number of trinagles where x or y is 0, plus the two mirror images of these triangles
		numberOfTriangles += 3*N*N;

		for(int x=1; x<=N; x++) {
			for(int y=1; y<=N; y++) {
				// find perpendicular slope components in lowest terms
				int gcd = Math.max(gcd(x, y), 1);
				int dy = x/gcd; // this is really negative
				int dx = y/gcd;

				// count the number of times triangles can be formed with a negative slope
				int c1 = Math.min((N-y)/dy, x/dx);
				// count the number of times triangles can be formed with a positive slope
				int c2 = Math.min(y/dy, (N-x)/dx);

				numberOfTriangles += c1 + c2;
			}
		}

		return numberOfTriangles;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./righttrianglesintegercoordinates.jar N");
			System.exit(1);
		}
		final int N = Integer.parseInt(args[0]);

		System.out.println(new RightTrianglesIntegerCoordinates().solve(N));
	}
}
