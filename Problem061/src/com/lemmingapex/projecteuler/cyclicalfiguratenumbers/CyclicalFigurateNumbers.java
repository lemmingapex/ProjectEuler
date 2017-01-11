package com.lemmingapex.projecteuler.cyclicalfiguratenumbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
		//System.out.println(n_upperBound);

		// the octagonal numbers grow more quickly than the other figurate numbers.  Therefore the smallest n that will generate a 4 digit number should be the solution to 1000 = n*(3n-2)
		int n_lowerBound = (int)(Math.sqrt(3001) + 1)/3;
		//System.out.println(n_lowerBound);

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

	// helper method to generate a set of types 3 to 7
	private Set<Integer> makeTypeSet() {
		Set<Integer> types = new HashSet<>();
		for(int i=3; i<=7; i++) {
			types.add(i);
		}
		return types;
	}

	public int solve() {
		List<FigurateNumber> figurateNumbers = generateFigurateNumbers();

		// generated and populate graph data structures that will aid in the search
		Map<String, List<FigurateNumber>> figurateNumberPrefixMap = new HashMap<>();
		List<FigurateNumber> octagonalNumbers = new ArrayList<>();
		for(FigurateNumber figurateNumber : figurateNumbers) {
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

		// populate neighbors for each node
		for(FigurateNumber figurateNumber : figurateNumbers) {
			String suffix = figurateNumber.suffix;
			List<FigurateNumber> figurateNumbersWherePrefixMatchesSuffix = figurateNumberPrefixMap.get(suffix);
			if(figurateNumbersWherePrefixMatchesSuffix != null) {
				for(FigurateNumber match : figurateNumbersWherePrefixMatchesSuffix) {
					if(figurateNumber.type != match.type) {
						figurateNumber.neighbors.add(match);
					}
				}
			}
		}

		// sum is the the solution. the thing to return.
		int sum = 0;

		// perfrom DFS for each octagonal number in the graph until a solution is found
		for(FigurateNumber root : octagonalNumbers) {
			// DFS
			Stack<FigurateNumber> stack = new Stack<>();
			Set<Integer> typeSet = makeTypeSet();
			boolean foundSolution = false;
			stack.push(root);
			root.visited = true;
			while(!stack.isEmpty()) {
				FigurateNumber node = stack.peek();
				FigurateNumber neighbor = node.getUnvisitedNeighbor();
				if(neighbor != null && typeSet.contains(neighbor.type)) {
					neighbor.visited = true;
					stack.push(neighbor);
					typeSet.remove(neighbor.type);
				} else {
					typeSet.add(stack.pop().type);
				}
				// are all types represented?  And does the prefix of the first match the suffix of the last?
				if(typeSet.isEmpty() && root.prefix.equals(stack.peek().suffix)) {
					foundSolution = true;
					break;
				}
			}

			if(foundSolution) {
				for(FigurateNumber f : stack) {
					System.out.println(f);
					sum += f.number;
				}
				break;
			}

			// Clear visited property for next search
			for(FigurateNumber figurateNumber : figurateNumbers) {
				figurateNumber.visited = false;
			}
		}

		return sum;
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
