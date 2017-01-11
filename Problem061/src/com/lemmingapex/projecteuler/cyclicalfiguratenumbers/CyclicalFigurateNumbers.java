package com.lemmingapex.projecteuler.cyclicalfiguratenumbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<FigurateNumber> generateFigurateNumbers() {
		List<FigurateNumber> figurateNumbers = new ArrayList<>();

		// the triangular numbers grow more slowly than the other figurate numbers.  Therefore the largest n that will generate a 4 digit number should be the solution to 9999 = n*(n-1)/2
		int n_upperBound = (int)(Math.sqrt(79993) - 1)/2;
		System.out.println(n_upperBound);

		// the octagonal numbers grow more quickly than the other figurate numbers.  Therefore the smallest n that will generate a 4 digit number should be the solution to 1000 = n*(3n-2)
		int n_lowerBound = (int)(Math.sqrt(3001) + 1)/3;
		System.out.println(n_lowerBound);

		for(int n=n_lowerBound; n<=n_upperBound; n++) {
			for(int t=3; t<=8; t++) {
				int figNum = generateFigurateNumber(n, t);
				int figNumLength = String.valueOf(figNum).length();
				if(String.valueOf(figNum).length() == 4) {
					figurateNumbers.add(new FigurateNumber(figNum, t));
				} else if(figNumLength > 4) {
					break;
				}
			}
		}

		return figurateNumbers;
	}

	public int solve() {
		List<FigurateNumber> figurateNumbers = generateFigurateNumbers();
		Map<String, List<FigurateNumber>> figurateNumberPrefixMap = new HashMap<>();

		List<FigurateNumber> octagonalNumbers = new ArrayList<>();

		for(FigurateNumber figurateNumber : figurateNumbers) {
			System.out.println(figurateNumber);

			if(figurateNumber.type != 8) {
				String prefix = figurateNumber.prefix;
				List<FigurateNumber> figurateNumbersWithPrefix = figurateNumberPrefixMap.get(prefix);
				if(figurateNumbersWithPrefix == null) {
					figurateNumbersWithPrefix = new ArrayList<FigurateNumber>();
				}
				figurateNumbersWithPrefix.add(figurateNumber);
				figurateNumberPrefixMap.put(prefix, figurateNumbersWithPrefix);
			} else {
				octagonalNumbers.add(figurateNumber);
			}
		}
		//
		// for(FigurateNumber octagonalNumber : octagonalNumbers) {
		// 	String friends = "";
		// 	List<FigurateNumber> figurateNumberFriends = figurateNumberPrefixMap.get(octagonalNumber.suffix);
		// 	if(figurateNumberFriends != null) {
		// 		for(FigurateNumber figurateNumber : figurateNumberFriends) {
		// 			friends += figurateNumber.toString() + " ";
		// 		}
		// 		System.out.println(octagonalNumber + " -> " + friends);
		// 	}
		// }

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
