package com.lemmingapex.projecteuler.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BoardReader {

	public static List<Board> readFile(File boardsFile) {
		List<Board> boards = new ArrayList<Board>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(boardsFile));
			String line = br.readLine();
			while (line != null && !line.trim().isEmpty()) {
				Board board = new Board();
				int rowIndex=0;
				while(line != null && !line.trim().isEmpty() && rowIndex < 10) {
					// ignore the first line
					if(rowIndex > 0) {
						int colIndex=0;
						for(String n : line.split("")) {
							Integer s = Integer.valueOf(n);
							if(s == 0) {
								s = null;
							}
							board.setSquare(new Square(s), rowIndex - 1, colIndex);
							colIndex++;
						}
					}
					line = br.readLine();
					rowIndex++;
				}
				boards.add(board);
			}
		} catch (Exception e) {
			System.err.println("Problem reading file: " + boardsFile.getAbsolutePath() + ".");
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
		return boards;
	}
}
