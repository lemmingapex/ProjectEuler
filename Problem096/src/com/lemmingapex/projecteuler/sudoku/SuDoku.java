package com.lemmingapex.projecteuler.sudoku;

import java.io.File;
import java.util.List;

/**
 * 10/15/2016
 * SuDoku.java
 * Su Doku
 *
 * @author Scott Wiedemann
 *
 */
public class SuDoku {

	public long sumOfUpperLeftValues(List<Board> boards) {
		long sum = 0L;
		for(int i=0; i<boards.size(); i++) {
			Board b = boards.get(i);
			System.out.println("board " + i);
			System.out.println(b);
			b.solve();
			String upperLeft = "";
			for(int j=0; j<Board.BLOCK_SIZE; j++) {
				upperLeft += b.getSquare(0,j).getValue();
			}
			sum += Long.valueOf(upperLeft);
		}
		return sum;
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

		List<Board> boards = new BoardReader().readFile(inputFile);
		System.out.println("sum: " + new SuDoku().sumOfUpperLeftValues(boards));
	}
}
