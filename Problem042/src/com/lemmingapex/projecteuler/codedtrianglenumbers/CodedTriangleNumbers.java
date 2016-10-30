package com.lemmingapex.projecteuler.codedtrianglenumbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;
import java.util.Set;

/**
 * 10/30/2016
 * CodedTriangleNumbers.java
 * Coded triangle numbers
 *
 * @author Scott Wiedemann
 *
 */
public class CodedTriangleNumbers {

	private final int largestTriangularNumber;
	private Set<Integer> triangularNumbers = new TreeSet<Integer>();

	public CodedTriangleNumbers(int largestTriangularNumber) {
		this.largestTriangularNumber = largestTriangularNumber;
		for(int n = 1; ; n++) {
			int t = n*(n+1)/2;
			if(t <= largestTriangularNumber) {
				triangularNumbers.add(t);
			} else {
				break;
			}
		}
	}

	private int getWordValue(String word) {
		int wordValue = 0;

		int wordLength = word.length();
		char[] wordChars = word.toCharArray();
		for(int i=0; i < wordLength; i++) {
			wordValue += (wordChars[i] - 'A' + 1);
		}
		return wordValue;
	}

	public int solve(Set<String> words) {
		int count = 0;
		for(String word : words) {
			if(triangularNumbers.contains(getWordValue(word))) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./codedtrianglenumbers.jar p042_words.txt");
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

		int lengthOfLongestWord = 0;
		Set<String> words = new TreeSet<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String line = br.readLine();
			while (line != null && !line.trim().isEmpty()) {
				for(String n : line.split(",")) {
					words.add(n.substring(1, n.length()-1));
					if(n.length()-2 > lengthOfLongestWord) {
						lengthOfLongestWord = n.length()-2;
					}
				}
				line = br.readLine();
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
		// a word that is all Zs
		int largestTriangularNumber = ('Z'-'A' + 1)*lengthOfLongestWord;

		System.out.println(new CodedTriangleNumbers(largestTriangularNumber).solve(words));
	}
}
