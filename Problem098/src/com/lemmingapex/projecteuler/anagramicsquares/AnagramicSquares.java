package com.lemmingapex.projecteuler.anagramicsquares;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 12/20/2016
 * AnagramicSquares.java
 * Anagramic squares
 *
 * @author Scott Wiedemann
 *
 */
public class AnagramicSquares {

	private static List<Integer> squareNumbersOfLengthX(int x) {
		List<Integer> squareNumbersOfLengthX = new ArrayList<Integer>();

		int squared_upperBound = 1;
		for(int i=0; i<x; i++) {
			squared_upperBound *= 10;
		}
		squared_upperBound -= 1;
		int squared_lowerBound = (squared_upperBound+1)/10;

		int upperBound = (int)Math.sqrt(squared_upperBound);
		int lowerBound = (int)Math.ceil(Math.sqrt(squared_lowerBound));
		for(int i=lowerBound; i<=upperBound; i++) {
			squareNumbersOfLengthX.add(i*i);
		}
		return squareNumbersOfLengthX;
	}

	public static int solve(String[] wordDictionary) {
		int largestSquareNumberOfSquareAnagramWordPair = 0;

		Map<String, List<String>> anagramDictionary = new HashMap<String, List<String>>();
		for(String word : wordDictionary) {
			word = word.substring(1, word.length()-1);
			char[] wordChars = word.toCharArray();
			Arrays.sort(wordChars);
			String sortedWord = String.valueOf(wordChars);
			List<String> words = anagramDictionary.get(sortedWord);
			if(words == null) {
				anagramDictionary.put(sortedWord, new ArrayList<String>());
				words = anagramDictionary.get(sortedWord);
			}
			words.add(word);
		}

		for(String sortedWord : anagramDictionary.keySet()) {
			List<String> words = anagramDictionary.get(sortedWord);
			if(words.size() > 1) {
				for(int i=0; i<words.size()-1; i++) {
					String word1 = words.get(i);
					List<Integer> squareNumbersOfWordLength = squareNumbersOfLengthX(word1.length());
					for(int j=i+1; j<words.size(); j++) {
						String word2 = words.get(j);
						for(int word1SquareNumber : squareNumbersOfWordLength) {
							char[] word1SquareNumber_charArray = String.valueOf(word1SquareNumber).toCharArray();

							// map char to number, and number to char. Make sure this number is valid mapping (one to one)
							boolean mappingIsValid = true;
							Map<Character, Character> word1CharToNumberMapping = new HashMap<Character, Character>();
							Map<Character, Character> word1NumberToCharMapping = new HashMap<Character, Character>();
							for (int k = 0; k < word1.length(); k++) {
								char char_k = word1.charAt(k);
								char number_k = word1SquareNumber_charArray[k];
								if(word1CharToNumberMapping.get(char_k) == null) {
									word1CharToNumberMapping.put(char_k, number_k);
								} else if(word1CharToNumberMapping.get(char_k) == number_k) {
									// valid
								} else {
									// not valid mapping
									mappingIsValid = false;
									break;
								}

								if(word1NumberToCharMapping.get(number_k) == null) {
									word1NumberToCharMapping.put(number_k, char_k);
								} else if(word1NumberToCharMapping.get(number_k) == char_k) {
									// valid
								} else {
									// not valid mapping
									mappingIsValid = false;
									break;
								}
							}

							if(!mappingIsValid) {
								continue;
							}

							String word2Number_String = new String(word2);
							for (Character c : word1CharToNumberMapping.keySet()) {
								word2Number_String = word2Number_String.replace("" + c, "" + word1CharToNumberMapping.get(c));
							}

							Integer word2Number = Integer.parseInt(word2Number_String);

							int sqrtWord2Number = (int)Math.sqrt(word2Number);
							if(sqrtWord2Number*sqrtWord2Number == word2Number && String.valueOf(word2Number).length() == word1.length()) {
								// System.out.println("word1 " + word1);
								// System.out.println("word2 " + word2);
								// System.out.println("word1SquareNumber " + word1SquareNumber);
								// System.out.println("word2Number " + word2Number + "\n");
								int max = Math.max(word1SquareNumber, word2Number);
								if(max > largestSquareNumberOfSquareAnagramWordPair) {
									largestSquareNumberOfSquareAnagramWordPair = max;
								}
							}
						}
					}
				}
			}
		}
		return largestSquareNumberOfSquareAnagramWordPair;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./anagramicsquares.jar p098_words.txt");
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

		String[] wordDictionary = new String[]{};
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			wordDictionary = br.readLine().split(",");
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

		System.out.println(AnagramicSquares.solve(wordDictionary));
	}
}
