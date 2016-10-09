package com.lemmingapex.projecteuler.digitfifthpowers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * 10/08/2016
 * DigitFifthPowers.java
 * Digit fifth powers
 *
 * @author Scott Wiedemann
 *
 */
public class DigitFifthPowers {

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

	public BigInteger solve(int N) {
		BigInteger sum = BigInteger.ZERO;

		// this should really be the solution to x*9^N = 10^(x)...
		int numOfLoops = 6;

		for(String s : exclusiveLoops(numOfLoops)) {
			BigInteger value = BigInteger.ZERO;
			for (int i = 0; i < s.length(); i++){
				int v_i = s.charAt(i) - '0';
				value = value.add(BigInteger.valueOf(v_i).pow(N));
			}
			//System.out.println(value);
			char[] valueChars = value.toString().toCharArray();
			Arrays.sort(valueChars);
			String valueSorted = new String(valueChars);
			//System.out.println(valueSorted);
			if(valueSorted.replaceAll("0","").equals(s.replaceAll("0","")) && !valueSorted.replaceAll("0","").equals("1")) {
				//System.out.println(value);
				sum = sum.add(value);
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./digitfifthpowers.jar N");
			System.exit(1);
		}
		Integer N = Integer.parseInt(args[0]);

		System.out.println(new DigitFifthPowers().solve(N));
	}
}
