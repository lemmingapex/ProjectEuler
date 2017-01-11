package com.lemmingapex.projecteuler.cyclicalfiguratenumbers;

import java.util.List;
import java.util.ArrayList;

/**
 * 01/10/2017
 * CyclicalFigurateNumbers.java
 * Cyclical figurate numbers
 *
 * @author Scott Wiedemann
 *
 */
public class CyclicalFigurateNumbers {

	public int generateFigurateNumber(int n, int type) {
		switch(type) {
			case 3:
				return n*(n+1)/2;
			case 4:
				return n*n;
			case 5:
				return n*(3*n-1)/2;
			case 6:
				return n*(2*n-1);
			case 7:
				return n*(5*n-3)/2;
			case 8:
				return n*(3*n-2);
			default:
				return -1;
		}
	}

	public void generateFigurateNumbers() {
		List<FigurateNumber> figurateNumbers = new ArrayList<FigurateNumber>();
		for(int n=18; n<150; n++) {
			for(int t=3; t<=8; t++) {
				int figNum = generateFigurateNumber(n, t);
				if(String.valueOf(figNum).length() == 4) {
					figurateNumbers.add(new FigurateNumber(figNum, t));
				}
			}
		}

		for(FigurateNumber f : figurateNumbers) {
			System.out.println(f);
		}
	}

	public int solve() {
		generateFigurateNumbers();
		return 0;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./cyclicalfiguratenumbers.jar");
			System.exit(1);
		}

		System.out.println(new CyclicalFigurateNumbers().solve());
	}
}
