package com.lemmingapex.projecteuler.sudoku;

public class Board {

	private static final int BOARD_SIZE = 9;
	private static final int BLOCK_SIZE = (int)Math.sqrt(BOARD_SIZE);

	// 0,0 is the upper-left
	// 0,8 is the upper-right
	// 8,0 is the lower-left
	// 8,8 is the lower-right
	private Square[][] grid = new Square[BOARD_SIZE][BOARD_SIZE];

	public Square getSquare(int row, int column) {
		return grid[row][column];
	}

	public Square setSquare(Square square, int row, int column) {
		grid[row][column] = square;
		return grid[row][column];
	}

	public boolean isRowLegal() {
		return true;
	}

	public boolean isLegalPlacement() {
		return false;
	}

	@Override
	public String toString() {
		String boardString = "";
		for(int i=0; i<BOARD_SIZE; i++) {
			if(i%BLOCK_SIZE == 0) {
				for(int k=0; k<BOARD_SIZE*2; k++) {
					if(k%(BLOCK_SIZE*2) == 0) {
						boardString += "+";
					} else {
						boardString += "-";
					}
				}
				boardString += "+\n";
			}
			for(int j=0; j<BOARD_SIZE; j++) {
				if(j%BLOCK_SIZE == 0) {
					boardString += "|";
				} else {
					boardString += " ";
				}
				Integer v = grid[i][j].getValue();
				boardString += (v==null?" ":String.valueOf(v));
			}
			boardString += "|\n";
		}
		for(int k=0; k<BOARD_SIZE*2; k++) {
			if(k%(BLOCK_SIZE*2) == 0) {
				boardString += "+";
			} else {
				boardString += "-";
			}
		}
		boardString += "+\n";
		return boardString;
	}
}
