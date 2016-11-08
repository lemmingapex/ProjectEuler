package com.lemmingapex.projecteuler.goldbachsotherconjecture;

/**
 * 11/07/2016
 * GoldbachsOtherConjecture.java
 * Goldbach's other conjecture
 *
 * @author Scott Wiedemann
 *
 */
public class GoldbachsOtherConjecture {

	private boolean isPrime(int p) {
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

	public int solve() {
		int o = 5;
		int xLowerBound = 1;
		while(true) {
			int xUpperBound = ((int)Math.sqrt((o-3)/2));
			boolean foundPrime = false;
			for(int x=xLowerBound; x<=xUpperBound; x++) {
				int p = o - 2*x*x;
				if(isPrime(p)) {
					//System.out.println("o = p + 2*x^2,  " + o + " = " + p + " + 2*" + x + "^2");
					foundPrime = true;
					break;
				}
			}
			if(!foundPrime) {
				return o;
			}
			do {
				o +=2;
			} while(isPrime(o));
		}
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./goldbachsotherconjecture.jar");
			System.exit(1);
		}

		System.out.println(new GoldbachsOtherConjecture().solve());
	}
}
