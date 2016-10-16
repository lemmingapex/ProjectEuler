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

	public void solve(Board board) {
		boolean madeChanges = false;
		do {
			System.out.println(board);
			board.updatePossibleValues();
			// for(int i=0; i<Board.BOARD_SIZE; i++) {
			// 	for(int j=0; j<Board.BOARD_SIZE; j++) {
			// 		System.out.println(board.getSquare(i,j).getPossibleValues());
			// 	}
			// 	System.out.println("");
			// }
			madeChanges = board.updateValuesFromPossibleValues();
		} while(madeChanges);
	}

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

		SuDoku suDokuSolver = new SuDoku();
		List<Board> boards = new BoardReader().readFile(inputFile);
		//suDokuSolver.solve(boards.get(0);
		for(Board b : boards) {
			System.out.println("new board");
			suDokuSolver.solve(b);
		}


		//System.out.println(new SuDoku().sumOfUpperLeftValues());
	}
}
