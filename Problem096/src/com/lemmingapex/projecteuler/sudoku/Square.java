package com.lemmingapex.projecteuler.sudoku;

import java.util.Set;
import java.util.TreeSet;

public class Square {

	private Integer value = null;
	private Set<Integer> possibleValues = new TreeSet<Integer>();

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
		this.possibleValues = new TreeSet<Integer>();
	}

	public Set<Integer> getPossibleValues() {
		return possibleValues;
	}

	public void setPossibleValues(TreeSet<Integer> possibleValues) {
		this.possibleValues = possibleValues;
	}

	public Square(Integer value) {
		this.value = value;
	}
}
