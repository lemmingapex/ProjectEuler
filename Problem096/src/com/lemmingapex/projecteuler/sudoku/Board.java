package com.lemmingapex.projecteuler.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Board {
	public static final int BLOCK_SIZE = 3;
	public static final int BOARD_SIZE = BLOCK_SIZE*BLOCK_SIZE;

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

	public Square getSquare(int row, int column) {
		return grid[row][column];
	}

	public Square setSquare(Square square, int row, int column) {
		grid[row][column] = square;
		return grid[row][column];
	}

	// does each row col and block contain unique values?
	public boolean isLegalPlacement() {
		boolean isLegalPlacement = true;
		List<Set<Integer>> rowsValues = new ArrayList<Set<Integer>>(BOARD_SIZE);
		List<Set<Integer>> colsValues = new ArrayList<Set<Integer>>(BOARD_SIZE);
		List<Set<Integer>> blocksValues = new ArrayList<Set<Integer>>(BOARD_SIZE);

		for(int i=0; i<BOARD_SIZE; i++) {
			rowsValues.add(new TreeSet<Integer>());
			colsValues.add(new TreeSet<Integer>());
			blocksValues.add(new TreeSet<Integer>());
		}

		for(int row=0; row<BOARD_SIZE; row++) {
			Set<Integer> rowValues = rowsValues.get(row);
			for(int col=0; col<BOARD_SIZE; col++) {
				Integer value = grid[row][col].getValue();
				if(value != null) {
					Set<Integer> colValues = colsValues.get(col);
					Set<Integer> blockValues = blocksValues.get(getBlock(row, col));
					if(!rowValues.contains(value)) {
						rowValues.add(value);
					} else {
						isLegalPlacement = false;
						break;
					}
					if(!colValues.contains(value)) {
						colValues.add(value);
					} else {
						isLegalPlacement = false;
						break;
					}
					if(!blockValues.contains(value)) {
						blockValues.add(value);
					} else {
						isLegalPlacement = false;
						break;
					}
				}
			}
			if(!isLegalPlacement) {
				break;
			}
		}
		return isLegalPlacement;
	}

	public boolean isSolved() {
		boolean isSolved = true;
		updatePossibleValues();
		if(!isLegalPlacement()) {
			isSolved = false;
		} else {
			for(int row=0; row<BOARD_SIZE; row++) {
				for(int col=0; col<BOARD_SIZE; col++) {
					Square square = grid[row][col];
					if(square.getPossibleValues().size() > 0 || square.getValue() == null) {
						isSolved = false;
						break;
					}
				}
			}
		}
		return isSolved;
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

	// 1) If the number of possible values for a square is one, then that value must be the value for the square.
	private boolean updateValuesFromPossibleValuesMethod1() {
		boolean madeChanges = false;

		for(int row=0; row<BOARD_SIZE; row++) {
			for(int col=0; col<BOARD_SIZE; col++) {
				Square square = grid[row][col];
				// if there is only one possible value, assign it
				if(square.getPossibleValues().size() == 1) {
					square.setValue(square.getPossibleValues().iterator().next());
					madeChanges = true;
				}
			}
		}
		updatePossibleValues();
		return madeChanges;
	}

	// 2) If a possible value appears only once in it's row, it's column, or it's block, then that value must be the value for the square.
	private boolean updateValuesFromPossibleValuesMethod2() {
		boolean madeChanges = false;

		List<Map<Integer, Integer>> rowsUniquePossibleValuesToColIndex = new ArrayList<Map<Integer, Integer>>(BOARD_SIZE);
		List<Map<Integer, Integer>> colsUniquePossibleValuesToRowIndex = new ArrayList<Map<Integer, Integer>>(BOARD_SIZE);
		List<Map<Integer, Tuple<Integer, Integer>>> blocksUniquePossibleValuesToBlockIndex = new ArrayList<Map<Integer, Tuple<Integer, Integer>>>(BOARD_SIZE);
		for(int i=0; i<BOARD_SIZE; i++) {
			rowsUniquePossibleValuesToColIndex.add(new HashMap<Integer, Integer>());
			colsUniquePossibleValuesToRowIndex.add(new HashMap<Integer, Integer>());
			blocksUniquePossibleValuesToBlockIndex.add(new HashMap<Integer, Tuple<Integer, Integer>>());
		}

		for(int row=0; row<BOARD_SIZE; row++) {
			for(int col=0; col<BOARD_SIZE; col++) {
				Square square = grid[row][col];
				for(Integer p : square.getPossibleValues()) {
					Integer colIndex = rowsUniquePossibleValuesToColIndex.get(row).get(p);
					if(colIndex == null) {
						rowsUniquePossibleValuesToColIndex.get(row).put(p, col);
					} else {
						// something already there? this doesn't map to anything
						rowsUniquePossibleValuesToColIndex.get(row).put(p, -1);
					}
					Integer rowIndex = colsUniquePossibleValuesToRowIndex.get(col).get(p);
					if(rowIndex == null) {
						colsUniquePossibleValuesToRowIndex.get(col).put(p, row);
					} else {
						// something already there? this doesn't map to anything
						colsUniquePossibleValuesToRowIndex.get(col).put(p, -1);
					}
					int block = getBlock(row, col);
					Tuple blockIndex = blocksUniquePossibleValuesToBlockIndex.get(block).get(p);
					if(blockIndex == null) {
						blocksUniquePossibleValuesToBlockIndex.get(block).put(p, new Tuple(row, col));
					} else {
						// something already there? this doesn't map to anything
						blocksUniquePossibleValuesToBlockIndex.get(block).put(p, new Tuple(-1, -1));
					}
				}
			}
		}

		for(int row=0; row<BOARD_SIZE; row++) {
			for(Integer uniquePossbileValue : rowsUniquePossibleValuesToColIndex.get(row).keySet()) {
				Integer colIndex = rowsUniquePossibleValuesToColIndex.get(row).get(uniquePossbileValue);
				if(colIndex != null && colIndex != -1) {
					grid[row][colIndex].setValue(uniquePossbileValue);
					madeChanges = true;
				}
			}
		}
		updatePossibleValues();

		for(int col=0; col<BOARD_SIZE; col++) {
			for(Integer uniquePossbileValue : colsUniquePossibleValuesToRowIndex.get(col).keySet()) {
				Integer rowIndex = colsUniquePossibleValuesToRowIndex.get(col).get(uniquePossbileValue);
				if(rowIndex != null && rowIndex != -1) {
					grid[rowIndex][col].setValue(uniquePossbileValue);
					madeChanges = true;
				}
			}
		}
		updatePossibleValues();

		for(int block=0; block<BOARD_SIZE; block++) {
			for(Integer uniquePossbileValue : blocksUniquePossibleValuesToBlockIndex.get(block).keySet()) {
				Tuple<Integer, Integer> blockIndex = blocksUniquePossibleValuesToBlockIndex.get(block).get(uniquePossbileValue);
				if(blockIndex != null && blockIndex.x != -1) {
					grid[blockIndex.x][blockIndex.y].setValue(uniquePossbileValue);
					madeChanges = true;
				}
			}
		}
		updatePossibleValues();

		return madeChanges;
	}

	public void solveUsingConstraints() {
		boolean madeChanges = false;
		do {
			// System.out.println(this);
			boolean madeChangesMethod1 = updateValuesFromPossibleValuesMethod1();
			boolean madeChangesMethod2 = updateValuesFromPossibleValuesMethod2();
			madeChanges = madeChangesMethod1 || madeChangesMethod2;
		} while(madeChanges);
	}

	private boolean solveUsingBacktrackingRecursive(Board b) {
		for(int row=0; row<BOARD_SIZE; row++) {
			for(int col=0; col<BOARD_SIZE; col++) {
				Square square = b.getSquare(row, col);
				if(square.getValue() != null) {
					continue;
				}
				for(Integer p : b.getPossbileSquareValues()) {
					square.setValue(p);
					b.updatePossibleValues();
					if(isLegalPlacement()) {
						if(solveUsingBacktrackingRecursive(b)) {
							return true;
						} else {
							square.setValue(null);
							b.updatePossibleValues();
						}
					} else {
						square.setValue(null);
						b.updatePossibleValues();
					}
				}
				return false;
			}
		}
		return true;
	}

	public void solveUsingBacktracking() {
		solveUsingBacktrackingRecursive(this);
	}

	// will slove a sudoku puzzle efficently using the combination of the methods above
	public boolean solve() {
		solveUsingConstraints();
		boolean solved = isSolved();
		System.out.println(this);
		if(!solved) {
			solveUsingBacktracking();
			solved = isSolved();
			System.out.println(this);
		}

		return solved;
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
