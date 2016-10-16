package com.lemmingapex.projecteuler.sudoku;

import java.io.File;
import java.util.Collection;

/**
 * 10/15/2016
 * SuDoku.java
 * Su Doku
 *
 * @author Scott Wiedemann
 *
 */
public class SuDoku {

	public long sumOfUpperLeftValues() {
		return 0L;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./sudoku.jar file.txt");
			System.exit(1);
		}

		String inputFileString = args[0];
		if (inputFileString == null || inputFileString.trim().isEmpty()) {
			System.err.println("Bad file name: " + String.valueOf(inputFileString));
			System.exit(2);
		}

		File inputFile = new File(inputFileString);
		if (!inputFile.exists()) {
			System.err.println(inputFile.getAbsolutePath() + " does not exist.");
			System.exit(3);
		}

		if (!inputFile.canRead()) {
			System.err.println("Can not read " + inputFile.getAbsolutePath() + ".");
			System.exit(4);
		}

		Collection<Board> boards = new BoardReader().readFile(inputFile);
		System.out.println(boards.iterator().next());

		System.out.println(new SuDoku().sumOfUpperLeftValues());
	}
}