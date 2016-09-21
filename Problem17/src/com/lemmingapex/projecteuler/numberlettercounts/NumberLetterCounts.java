package com.lemmingapex.projecteuler.numberlettercounts;

/**
 * 09/20/2016
 * NumberLetterCounts.java
 * Number Letter Counts
 *
 * @author Scott Wiedemann
 *
 */
public class NumberLetterCounts {

	private String[] singleDigits = {"","one","two","three","four","five","six","seven","eight","nine"};
	private String[] tenToNineteen = {"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};

	private String[] tens = {"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};

	private String[] places = {"hundred","thousand","million","billion","trillion","quadrillion"};
	private String[] and = {"", "and"};

	private String convertOneToNinitynine(int N) {
		if(N > 99) {
			throw new IndexOutOfBoundsException(N + " should be 99 or less.");
		}
		if(N < 1) {
			throw new IndexOutOfBoundsException(N + " should be 1 or greater.");
		}

		String nWrittenOut = "";
		int i = 1;
		// 0 to 9
		if(N <= 9) {
			nWrittenOut = singleDigits[N];
		} else if(N <= 19) {
			nWrittenOut = tenToNineteen[N - singleDigits.length];
		} else {
			nWrittenOut = tens[(int)(N/10) - 2] + " " + singleDigits[N%10];
		}

		return nWrittenOut.trim();
	}

	private String convertOneToNineHundredAndNinitynine(int N) {
		if(N > 999) {
			throw new IndexOutOfBoundsException(N + " should be 999 or less.");
		}
		if(N < 1) {
			throw new IndexOutOfBoundsException(N + " should be 1 or greater.");
		}

		String nWrittenOut = "";
		if(N < 100) { // 1 to 99
			nWrittenOut = convertOneToNinitynine(N);
		} else { // 100 to 999
			nWrittenOut = singleDigits[(int)(N/100)] + " " + places[(int)(N/1000)] + " " + and[(N%100 == 0) ? 0 : 1] + " " + ((N%100 == 0) ? "" : convertOneToNinitynine(N%100));
		}
		return nWrittenOut.trim();
	}

	private int countOneToAQuadrillion(int N) {
		if(N > 10e15) {
			throw new IndexOutOfBoundsException(N + " should be 10e15 or less.");
		}
		if(N < 1) {
			throw new IndexOutOfBoundsException(N + " should be 1 or greater.");
		}

		int totalNumberOfLetters = 0;

		int i = 1;
		while(i <= N) {
			String prefix = "";
			int j = (int)(Math.log10((double)i)/3.0);
			int jI = i;
			while(j > 0) {
				int j1000 = (int)Math.pow(1000, j);
				prefix += convertOneToNineHundredAndNinitynine(jI/j1000) + " " + places[j] + " ";
				j--;
				jI -= (jI/j1000)*j1000;
			}

			String value = (prefix.trim() + " " + ((jI%1000 == 0) ? "" : convertOneToNineHundredAndNinitynine(jI))).trim();
			//System.out.println(value);
			totalNumberOfLetters += (value.replaceAll(" ", "")).length();
			i++;
		}
		return totalNumberOfLetters;
	}

	public int count(int N) {
		int totalNumberOfLetters = countOneToAQuadrillion(N);
		return totalNumberOfLetters;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./numberlettercounts.jar N");
			System.exit(1);
		}
		Integer N = Integer.parseInt(args[0]);

		System.out.println(new NumberLetterCounts().count(N));
	}
}
