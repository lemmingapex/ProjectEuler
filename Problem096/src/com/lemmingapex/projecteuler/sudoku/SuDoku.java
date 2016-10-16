package com.lemmingapex.projecteuler.sudoku;

/**
 * 10/15/2016
 * SuDoku.java
 * Su Doku
 *
 * @author Scott Wiedemann
 *
 */
public class SuDoku {

	public long solve(long N) {
		return 0L;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./sudoku.jar file.txt");
			System.exit(1);
		}
		Long N = 0L;

		System.out.println(new SuDoku().solve(N));
	}
}
