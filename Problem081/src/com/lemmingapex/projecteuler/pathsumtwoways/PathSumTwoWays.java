package com.lemmingapex.projecteuler.pathsumtwoways;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 02/11/2017
 * PathSumTwoWays.java
 * Path sum: two ways
 *
 * @author Scott Wiedemann
 *
 */
public class PathSumTwoWays {

	final int n;
	final int[][] matrix;

	public PathSumTwoWays(int[][] matrix) {
		this.matrix = matrix;
		this.n = matrix.length;
	}

	public int solve() {
		// stores minimum the cumulative sum as you move right and down in the matrix
		int[][] sumMatrix = new int[n][n];

		// initalize the sumMatrix
		sumMatrix[0][0] = matrix[0][0];
		for(int i=1; i < this.n; i++) {
			sumMatrix[0][i] = sumMatrix[0][i-1] + matrix[0][i];
			sumMatrix[i][0] = sumMatrix[i-1][0] + matrix[i][0];
		}

		for(int i=1; i < this.n; i++) {
			for(int j=1; j < this.n; j++) {
				sumMatrix[i][j] = matrix[i][j] + Math.min(sumMatrix[i][j-1], sumMatrix[i-1][j]);
			}
		}

		// for(int i=0; i < this.n; i++) {
		// 	for(int j=0; j < this.n; j++) {
		// 		System.out.print(sumMatrix[i][j] + " ");
		// 	}
		// 	System.out.println("");
		// }

		return sumMatrix[this.n-1][this.n-1];
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./pathsumtwoways.jar p081_matrix.txt");
			System.exit(1);
		}

		String inputFileString = args[0];
		if (inputFileString == null || inputFileString.trim().isEmpty()) {
			System.err.println("Bad file name: " + String.valueOf(inputFileString));
			System.exit(1);
		}

		File inputFile = new File(inputFileString);
		if (!inputFile.exists()) {
			System.err.println(inputFile.getAbsolutePath() + " does not exist.");
			System.exit(1);
		}

		if (!inputFile.canRead()) {
			System.err.println("Can not read " + inputFile.getAbsolutePath() + ".");
			System.exit(1);
		}

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String line = br.readLine();

			int n = line.split(",").length;
			int[][] matrix = new int[n][n];

			int i=0;
			while (line != null && !line.trim().isEmpty()) {
				String[] lineArray = line.split(",");
				for(int j=0; j < lineArray.length; j++) {
					matrix[i][j] = Integer.parseInt(lineArray[j]);
				}
				line = br.readLine();
				i++;
			}

			System.out.println(new PathSumTwoWays(matrix).solve());
		} catch (Exception e) {
			System.err.println("Problem reading file: " + inputFile.getAbsolutePath() + ".");
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// bummer
				}
			}
		}
	}
}
