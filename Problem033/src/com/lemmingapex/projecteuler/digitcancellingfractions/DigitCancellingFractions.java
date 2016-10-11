package com.lemmingapex.projecteuler.digitcancellingfractions;

/**
 * 10/10/2016
 * DigitCancellingFractions.java
 * Digit cancelling fractions
 *
 * @author Scott Wiedemann
 *
 */
public class DigitCancellingFractions {

	public long solve(int N) {
		int upperBound = (int)Math.pow(10,N) - 1;
		int lowerBound = (int)Math.pow(10,N - 1);


		LFraction productOfFractions = new LFraction(1,1);
		for(int i = lowerBound; i<=upperBound; i++) {
			for(int j = i + 1; j<=upperBound; j++) {
				// is non-trival fraction
				if(!(i%10 == 0 && j%10 == 0)) {
					LFraction fraction = new LFraction(i, j);
					if(String.valueOf(i).substring(1, N).equals(String.valueOf(j).substring(0, N-1))) {
						LFraction incorrectFraction = new LFraction(Integer.parseInt(String.valueOf(i).substring(0, N-1)), Integer.parseInt(String.valueOf(j).substring(1, N)));
						if(incorrectFraction.equals(fraction)) {
							// System.out.println("i/j = " + fraction.getNumerator() + "/" + fraction.getDenominator());
							// System.out.println("i/j = " + incorrectFraction.getNumerator() + "/" + incorrectFraction.getDenominator());
							// System.out.println("\n");
							productOfFractions = new LFraction(productOfFractions.getNumerator()*fraction.getNumerator(),productOfFractions.getDenominator()*fraction.getDenominator());
						}
					}
				}
			}
		}

		return productOfFractions.reduce().getDenominator();
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./digitcancellingfractions.jar N");
			System.exit(1);
		}
		Integer N = Integer.parseInt(args[0]);

		System.out.println(new DigitCancellingFractions().solve(N));
	}
}
