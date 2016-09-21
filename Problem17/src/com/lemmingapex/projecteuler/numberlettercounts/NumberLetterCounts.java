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
		while(i <= N && i < singleDigits.length) {
			nWrittenOut = singleDigits[i];
			i++;
		}

		// 10 to 19
		while(i <= N && i <= singleDigits.length + tenToNineteen.length - 1) {
			nWrittenOut = tenToNineteen[i - singleDigits.length];
			i++;
		}

		// 20 to 99
		while(i <= N && i < 100) {
			nWrittenOut = tens[(int)(i/10) - 2] + " " + singleDigits[i%10];
			i++;
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
			int i = 100;
			while(i <= N && i < 1000) {
				nWrittenOut = singleDigits[(int)(i/100)] + " " + places[(int)(i/1000)] + " " + and[(i%100 == 0) ? 0 : 1] + " " + ((i%100 == 0) ? "" : convertOneToNinitynine(i%100));
				i++;
			}
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
