package com.lemmingapex.projecteuler.romannumerals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 04/03/2017
 * RomanNumerals.java
 * Roman numerals
 *
 * @author Scott Wiedemann
 *
 */
public class RomanNumerals {

	private final static Map<String, Integer> romanNumeralDenominationToValue = new HashMap<>();
	static {
		romanNumeralDenominationToValue.put("I", 1);
		romanNumeralDenominationToValue.put("IV", 4);
		romanNumeralDenominationToValue.put("V", 5);
		romanNumeralDenominationToValue.put("IX", 9);
		romanNumeralDenominationToValue.put("X", 10);
		romanNumeralDenominationToValue.put("XL", 40);
		romanNumeralDenominationToValue.put("L", 50);
		romanNumeralDenominationToValue.put("XC", 900);
		romanNumeralDenominationToValue.put("C", 100);
		romanNumeralDenominationToValue.put("CD", 400);
		romanNumeralDenominationToValue.put("D", 500);
		romanNumeralDenominationToValue.put("CM", 900);
		romanNumeralDenominationToValue.put("M", 1000);
	}

	private final static TreeMap<Integer, String> valueToRomanNumeralDenomination = new TreeMap<>();
	static {
		valueToRomanNumeralDenomination.put(1, "I");
		valueToRomanNumeralDenomination.put(4, "IV");
		valueToRomanNumeralDenomination.put(5, "V");
		valueToRomanNumeralDenomination.put(9, "IX");
		valueToRomanNumeralDenomination.put(10, "X");
		valueToRomanNumeralDenomination.put(40, "XL");
		valueToRomanNumeralDenomination.put(50, "L");
		valueToRomanNumeralDenomination.put(90, "XC");
		valueToRomanNumeralDenomination.put(100, "C");
		valueToRomanNumeralDenomination.put(400, "CD");
		valueToRomanNumeralDenomination.put(500, "D");
		valueToRomanNumeralDenomination.put(900, "CM");
		valueToRomanNumeralDenomination.put(1000, "M");
	}

	public static int romanNumeralToInt(String romanNumeral) {
		int value = 0;
		int previousValue = romanNumeralDenominationToValue.get("M");
		for(int i=0; i<romanNumeral.length(); i++) {
			int v = romanNumeralDenominationToValue.get(romanNumeral.substring(i, i+1));
			if(v>previousValue) {
				value -= 2*previousValue;
			}
			value += v;
			previousValue = v;
		}

		return value;
	}

	public static String intToRomanNumeral(int value) {
		int d = valueToRomanNumeralDenomination.floorKey(value);
		if(value == d) {
			return valueToRomanNumeralDenomination.get(value);
		}
		return valueToRomanNumeralDenomination.get(d) + intToRomanNumeral(value - d);
	}

	public static int solve(List<String> romanNumeralsText) {
		int solution = 0;
		for(String romanNumeral : romanNumeralsText) {
			solution += romanNumeral.length() - intToRomanNumeral(romanNumeralToInt(romanNumeral)).length();
			//System.out.println(romanNumeral + " " + romanNumeralToInt(romanNumeral) + " " + intToRomanNumeral(romanNumeralToInt(romanNumeral)));
		}
		return solution;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./romannumerals.jar p089_roman.txt");
			System.exit(1);
		}

		String inputFileString = args[0];
		if (inputFileString == null || inputFileString.trim().isEmpty()) {
			System.err.println("Bad file name: " + String.valueOf(inputFileString));
			System.exit(1);
		}

		File inputFile = new File(inputFileString);
		if (!inputFile.exists()) {
			System.err.println(inputFile.getAbsolutePath() + " does not exist.");
			System.exit(1);
		}

		if (!inputFile.canRead()) {
			System.err.println("Can not read " + inputFile.getAbsolutePath() + ".");
			System.exit(1);
		}

		List<String> romanNumeralsText = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String romanNumeral = br.readLine();
			while(romanNumeral != null) {
				romanNumeralsText.add(romanNumeral);
				romanNumeral = br.readLine();
			}
		} catch (Exception e) {
			System.err.println("Problem reading file: " + inputFile.getAbsolutePath() + ".");
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// bummer
				}
			}
		}

		System.out.println(RomanNumerals.solve(romanNumeralsText));
	}
}
