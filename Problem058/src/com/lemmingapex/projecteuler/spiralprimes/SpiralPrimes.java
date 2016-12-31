package com.lemmingapex.projecteuler.spiralprimes;

/**
 * 12/31/2016
 * SpiralPrimes.java
 * Spiral primes
 *
 * @author Scott Wiedemann
 *
 */
public class SpiralPrimes {

	private static boolean isPrime(int p) {
		if(p == 1) {
			return false;
		} else if(p ==2) {
			return true;
		}
		int upperBound = (int)Math.sqrt(p);
		for(int i=2; i<=upperBound; i++) {
			if(p%i == 0) {
				return false;
			}
		}
		return true;
	}

	public static int solve(double P) {
		int primeCount = 0;
		int diagonalCount = 1;
		for(int gridSize = 3; ; gridSize+=2) {
			int diagonal = gridSize*gridSize;
			for(int i = 0; i<3; i++) {
				diagonal = diagonal - (gridSize - 1);
				if(isPrime(diagonal)) {
					primeCount++;
				}
			}
			diagonalCount+=4;
			//System.out.println(primeCount + "/" + diagonalCount);
			double percentPrime = (double)primeCount/(double)diagonalCount;
			if(percentPrime < P) {
				return gridSize;
			}
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./spiralprimes.jar P");
			System.exit(1);
		}

		final double P = Double.parseDouble(args[0]);
		System.out.println(SpiralPrimes.solve(P));
	}
}
