package com.lemmingapex.projecteuler.namesscores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;
import java.util.Set;

/**
 * 09/25/2016
 * NamesScores.java
 * Names scores
 *
 * @author Scott Wiedemann
 *
 */
public class NamesScores {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./namesscores.jar p022_names.txt");
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

		Set<String> names = new TreeSet<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String line = br.readLine();
			while (line != null && !line.trim().isEmpty()) {
				for(String n : line.split(",")) {
					names.add(n.substring(1, n.length()-1));
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

		long namesScore = 0L;
		int i = 1;
		for (String name : names) {
			int nameScore = 0;
			for (int j = 0; j < name.length(); j++) {
				nameScore += (name.charAt(j) - 'A') + 1;
			}
			namesScore += nameScore*i;
			i++;
		}

		System.out.println(namesScore);
	}
}
