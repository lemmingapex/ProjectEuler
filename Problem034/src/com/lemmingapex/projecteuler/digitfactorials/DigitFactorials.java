package com.lemmingapex.projecteuler.digitfactorials;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 10/11/2016
 * DigitFactorials.java
 * Digit factorials
 *
 * @author Scott Wiedemann
 *
 */
public class DigitFactorials {

	/**
	*
	* This generates order unique permutations with the number of digits, not excedding the numberOfLoops input.
	* ex: 00,01,02,03,04,05,06,07,08,09,11,12,13,14,15,16,17,18,19,22,23,24,25,26,27,28,29,33...
	*
	*/
	private List<String> exclusiveLoops(int numberOfLoops) {
		List<Integer> currentValues = new ArrayList<Integer>();
		for(int i = 0; i<numberOfLoops; i++) {
			currentValues.add(0);
		}
		List<String> outputValues = new ArrayList<String>();
		exclusiveLoopsRecurse(0, currentValues, outputValues);
		return outputValues;
	}

	private void exclusiveLoopsRecurse(int index, List<Integer> currentValues, List<String> outputValues) {
		if(index == currentValues.size()) {
			return;
		} else {
			for(int v = currentValues.get(Math.max(index-1, 0)); v < 10; v++) {
				List<Integer> newValues = new ArrayList<Integer>(currentValues);
				newValues.set(index, v);
				exclusiveLoopsRecurse(index+1, newValues, outputValues);
			}
			if(index == currentValues.size()-1) {
				for(int v = currentValues.get(Math.max(index-1, 0)); v < 10; v++) {
					String value = "";
					for(int i = 0; i<currentValues.size(); i++){
						if(index == i) {
							value += v;
						} else {
							value += currentValues.get(i);
						}
					}
					outputValues.add(value);
				}
			}
		}
	}

	public long factorialSum(String s) {
		long factorialSum = 0;
		for (int i = s.length()-1; i >= 0; i--) {
			factorialSum += factorial(s.charAt(i) - '0');
		}
		return factorialSum;
	}


	public int factorial(int n) {
		int factorial = 1;
		for (int i = n; i > 1; i--) {
			factorial*=i;
		}
		return factorial;
	}

	public long solve() {
		Set<Long> sumValues = new HashSet<Long>();
		long upperBound = 100000L;

		List<String> exclusiveLoops = exclusiveLoops(String.valueOf(upperBound).length());
		for(int i=1; i<exclusiveLoops.size(); i++) {
			String s = exclusiveLoops.get(i);
			int zeroCount = s.replaceAll("[1-9]+","").length();
			//System.out.println(zeroCount + " " + s);
			for(int j=0; j<=zeroCount; j++) {
				String subS = s.substring(j, s.length());
				Long factorialSum = factorialSum(subS);
				if(upperBound > factorialSum)
				if(factorialSum.equals(factorialSum(factorialSum.toString()))) {
					sumValues.add(factorialSum);
				}
			}
		}

		long sum = 0;
		for(Long s : sumValues) {
			if(s > 2) {
				System.out.println(s);
				sum += s;
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./digitfactorials.jar");
			System.exit(1);
		}

		System.out.println(new DigitFactorials().solve());
	}
}
