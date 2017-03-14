package com.lemmingapex.projecteuler.primepowertriples;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lemmingapex.basics.primes.PrimeGenerator;

/**
 * 03/13/2017
 * PrimePowerTriples.java
 * Prime power triples
 *
 * @author Scott Wiedemann
 *
 */
public class PrimePowerTriples {

	public int solve(int N) {
		// n = x^2 + y^3 + z^4
		int xUpperBound = (int)Math.floor(Math.pow(N, 1.0/2.0));

		List<Long> primes = PrimeGenerator.generatePrimesUpToN(Long.valueOf(xUpperBound));
		Set<Long> uniqueCount = new HashSet();

		for(Long z : primes) {
			long z4 = z*z*z*z;
			if(z4 >= N) {
				break;
			}
			for(Long y : primes) {
				long y3 = y*y*y;
				long z4plusy3 = z4 + y3;
				if(z4plusy3 >= N) {
					break;
				}
				for(Long x : primes) {
					long x2 = x*x;
					long n = x2 + z4plusy3;
					if(n >= N) {
						break;
					} else {
						//System.out.println("n = x^2 + y^3 + z^4 = " + x + "^2 + " + y + "^3 + " + z + "^4 = " + n);
						uniqueCount.add(n);
					}
				}
			}
		}

		return uniqueCount.size();
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./primepowertriples.jar N");
			System.exit(1);
		}
		final int N = Integer.parseInt(args[0]);

		System.out.println(new PrimePowerTriples().solve(N));
	}
}
