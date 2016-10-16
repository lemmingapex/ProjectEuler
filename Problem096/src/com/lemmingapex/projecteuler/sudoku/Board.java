package com.lemmingapex.projecteuler.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Board {

	public static final int BOARD_SIZE = 9;
	public static final int BLOCK_SIZE = (int)Math.sqrt(BOARD_SIZE);

	// 0,0 is the upper-left
	// 0,8 is the upper-right
	// 8,0 is the lower-left
	// 8,8 is the lower-right
	private Square[][] grid = new Square[BOARD_SIZE][BOARD_SIZE];

	private Set<Integer> getPossbileSquareValues() {
		Set<Integer> possibleValues = new TreeSet<Integer>();
		for(int i=1; i<=BOARD_SIZE; i++) {
			possibleValues.add(i);
		}
		return possibleValues;
	}

	private int getBlock(int row, int col) {
		return (col/BLOCK_SIZE + (row/BLOCK_SIZE)*BLOCK_SIZE);
	}

	public void updatePossibleValues() {
		List<Set<Integer>> rowsPossbileValues = new ArrayList<Set<Integer>>(BOARD_SIZE);
		List<Set<Integer>> colsPossbileValues = new ArrayList<Set<Integer>>(BOARD_SIZE);
		List<Set<Integer>> blocksPossbileValues = new ArrayList<Set<Integer>>(BOARD_SIZE);
		for(int i=0; i<BOARD_SIZE; i++) {
			rowsPossbileValues.add(getPossbileSquareValues());
			colsPossbileValues.add(getPossbileSquareValues());
			blocksPossbileValues.add(getPossbileSquareValues());
		}

		for(int row=0; row<BOARD_SIZE; row++) {
			Set<Integer> rowPossbileValues = rowsPossbileValues.get(row);
			for(int col=0; col<BOARD_SIZE; col++) {
				Integer value = grid[row][col].getValue();
				if(value != null) {
					Set<Integer> colPossbileValues = colsPossbileValues.get(col);
					Set<Integer> blockPossbileValues = blocksPossbileValues.get(getBlock(row,col));
					rowPossbileValues.remove(value);
					colPossbileValues.remove(value);
					blockPossbileValues.remove(value);
				}
			}
		}

		for(int row=0; row<BOARD_SIZE; row++) {
			for(int col=0; col<BOARD_SIZE; col++) {
				Square square = grid[row][col];
				if(square.getValue() == null) {
					Set<Integer> rowPossbileValues = rowsPossbileValues.get(row);
					Set<Integer> colPossbileValues = colsPossbileValues.get(col);
					Set<Integer> blockPossbileValues = blocksPossbileValues.get(getBlock(row,col));
					TreeSet<Integer> intersection = new TreeSet<Integer>(rowPossbileValues);
					intersection.retainAll(colPossbileValues);
					intersection.retainAll(blockPossbileValues);
					grid[row][col].setPossibleValues(intersection);
				} else {
					grid[row][col].setPossibleValues(new TreeSet<Integer>());
				}
			}
		}
	}

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
