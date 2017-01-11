package com.lemmingapex.projecteuler.cyclicalfiguratenumbers;

public class FigurateNumber {

	final public int number;
	final public int type;
	final public String prefix;
	final public String suffix;

	public FigurateNumber(int number, int type) {
		this.number = number;
		this.type = type;
		this.prefix = String.valueOf(number).substring(0,2);
		this.suffix = String.valueOf(number).substring(2,4);
	}

	@Override
	public String toString() {
		return this.type + " " + this.number;
	}
}
