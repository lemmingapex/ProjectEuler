package com.lemmingapex.projecteuler.largestexponential;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 04/21/2016
 * LargestExponential.java
 * Largest exponential
 *
 * @author Scott Wiedemann
 *
 */
public class LargestExponential {

	public static int solve(List<Integer> baseExpList) {
		double largestLog = -1.0;
		int lineNumber = -1;

		int upperBound = baseExpList.size()/2;
		for(int i=0; i<upperBound; i++) {
			int index = 2*i;
			int base = baseExpList.get(index);
			int exp = baseExpList.get(index+1);

			double log = exp * Math.log(base);
			if(log > largestLog) {
				largestLog = log;
				lineNumber = i+1;
			}
		}

		return lineNumber;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./largestexponential.jar p099_base_exp.txt");
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

		List<Integer> baseExpList = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String line = br.readLine();
			while(line != null) {
				String[] baseExp = line.split(",");
				baseExpList.add(Integer.parseInt(baseExp[0]));
				baseExpList.add(Integer.parseInt(baseExp[1]));
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

		System.out.println(LargestExponential.solve(baseExpList));
	}
}
