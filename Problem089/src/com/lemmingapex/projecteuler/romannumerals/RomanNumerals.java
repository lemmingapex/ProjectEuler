package com.lemmingapex.projecteuler.romannumerals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 04/03/2017
 * RomanNumerals.java
 * Roman numerals
 *
 * @author Scott Wiedemann
 *
 */
public class RomanNumerals {

	static Map<Character, Integer> romanNumeralDenominationToValue = new HashMap<>();
	static {
		romanNumeralDenominationToValue.put('I', 1);
		romanNumeralDenominationToValue.put('V', 5);
		romanNumeralDenominationToValue.put('X', 10);
		romanNumeralDenominationToValue.put('L', 50);
		romanNumeralDenominationToValue.put('C', 100);
		romanNumeralDenominationToValue.put('D', 500);
		romanNumeralDenominationToValue.put('M', 1000);
	}

	static String romanNumeralDenominationOrder = "MDCLXVI";

	public static int romanNumeralToInt(String romanNumeral) {
		int value = 0;
		char[] romanNumeralChars = romanNumeral.toCharArray();
		int previousValue = romanNumeralDenominationToValue.get('M');
		for(int i=0; i<romanNumeralChars.length; i++) {
			int v = romanNumeralDenominationToValue.get(romanNumeralChars[i]);
			if(v>previousValue) {
				value -= 2*previousValue;
			}
			value += v;
			previousValue = v;
		}

		return value;
	}

	// all broken
	public static String intToRomanNumeral(int value) {
		String romanNumeral = "";
		for(int i=0; i<romanNumeralDenominationOrder.length(); i++) {
			char rn = romanNumeralDenominationOrder.charAt(i);
			int d = romanNumeralDenominationToValue.get(rn);
			while(value>0 && (value >= d || value + 1 == d)) {
				if(value + 1 == d) {
					romanNumeral += romanNumeralDenominationOrder.charAt(i + 1) + "" + rn;
				} else {
					romanNumeral += rn;
				}
				value -= d;
			}
		}
		return romanNumeral;
	}

	public static int solve(List<String> romanNumeralsText) {
		for(String romanNumeral : romanNumeralsText) {
			System.out.println(romanNumeral + " " + romanNumeralToInt(romanNumeral) + " " + intToRomanNumeral(romanNumeralToInt(romanNumeral)));
		}
		return 0;
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
