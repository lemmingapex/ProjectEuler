package com.lemmingapex.projecteuler.countingsummations;

/**
 * 02/05/2017
 * CountingSummations.java
 * Counting summations
 *
 * @author Scott Wiedemann
 *
 */
public class CountingSummations {

	public int solve(int N) {
		int[][] partitionCount = new int[N][N];

		for(int i=0; i<N; i++) {
			partitionCount[0][i] = 1;
			partitionCount[i][0] = 0;
		}

		for(int i=1; i<N; i++) {
			for(int j=1; j<N; j++) {
				int sum = 1;
				for(int k=i-1, l=0; l<=j && k>=0; k--, l++) {
					sum += partitionCount[k][l];
				}
				partitionCount[i][j] = sum;
			}
		}

		// for(int i=0; i<N; i++) {
		// 	for(int j=0; j<N; j++) {
		// 		System.out.print(partitionCount[i][j] + " ");
		// 	}
		// 	System.out.println();
		// }

		int solution = 0;
		for(int j=N-1; j>=0; j--) {
			int i=(N-1)-j;
			solution += partitionCount[i][j];
		}

		return solution;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./countingsummations.jar N");
			System.exit(1);
		}
		final Integer N = Integer.parseInt(args[0]);

		System.out.println(new CountingSummations().solve(N));
	}
}
