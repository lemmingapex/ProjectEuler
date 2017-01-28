package com.lemmingapex.projecteuler.totientmaximum;

import java.util.ArrayList;
import java.util.List;

import com.lemmingapex.basics.primes.PrimeGenerator;

/**
 * 01/24/2017
 * TotientMaximum.java
 * Totient maximum
 *
 * @author Scott Wiedemann
 *
 */
public class TotientMaximum {

	public long solve(Long N) {
		long primorial = 1;
		List<Long> primes = PrimeGenerator.generatePrimesUpToN(N);

		for(Long p : primes) {
			if(primorial*p < N) {
				primorial *= p;
			}
		}
		return primorial;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./totientmaximum.jar N");
			System.exit(1);
		}
		Long N = Long.parseLong(args[0]);

		System.out.println(new TotientMaximum().solve(N));
	}
}
