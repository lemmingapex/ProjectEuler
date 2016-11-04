package com.lemmingapex.projecteuler.substringdivisibility;

import java.util.ArrayList;
import java.util.List;

/**
 * 11/03/2016
 * SubstringDivisibility.java
 * Sub-string divisibility
 *
 * @author Scott Wiedemann
 *
 */
public class SubstringDivisibility {

	private final int[] indexToDivisor = new int[]{2,3,5,7,11,13,17};

	public long solve() {
		List<Integer> digits = new ArrayList<Integer>();
		digits.add(0);
		digits.add(1);
		digits.add(2);
		digits.add(3);
		digits.add(4);
		digits.add(5);
		digits.add(6);
		digits.add(7);
		digits.add(8);
		digits.add(9);

		long sum = 0l;
		for(Long n : find0to9SubstringPandigital("", digits)) {
			System.out.println(n);
			sum += n;
		}

		return sum;
	}

	private boolean satisfiesDivisibility(String value) {
		int length = value.length();
		if(length < 4) {
			return true;
		}
		return ((Long.valueOf(value.substring(length-3, length))%this.indexToDivisor[length-4]) == 0);
	}

	private List<Long> find0to9SubstringPandigital(String value, List<Integer> digits) {
		List<Long> pandigitalNumbers = new ArrayList<Long>();
		if(satisfiesDivisibility(value)) {
			if(digits.size() == 1) {
				String newValue = value + digits.get(0);
				if(satisfiesDivisibility(newValue)) {
					pandigitalNumbers.add(Long.valueOf(newValue));
				}
			} else {
				//System.out.println(value);
				for(Integer digit : digits) {
					List<Integer> newDigits = new ArrayList<Integer>(digits);
					newDigits.remove(digit);
					String newValue = value + digit;
					pandigitalNumbers.addAll(find0to9SubstringPandigital(newValue, newDigits));
				}
			}
		}
		return pandigitalNumbers;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./substringdivisibility.jar");
			System.exit(1);
		}

		System.out.println("Sum: " + new SubstringDivisibility().solve());
	}
}
