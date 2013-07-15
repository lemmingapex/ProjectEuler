package sw.projecteuler.sumprimes;

import java.util.List;

import sw.basics.primes.PrimeGenerator;

/**
 * 07/14/2013
 * SPT.java
 * Special Pythagorean triplet
 * 
 * @author Scott Wiedemann
 * 
 */
public class SumPrimes {
	
	public static void main(String[] args) {
		if (args.length != 1) {
	        System.err.println("Incorrect number of arguments.");
	        System.err.println("Usage: ./sumprimes.jar N");
	        System.exit(1);
		}
		Long N = Long.parseLong(args[0]);
		List<Long> primes = PrimeGenerator.generatePrimesUpToN(N - 1);
		Long sum = 0l;
		for (int i = 0; i < primes.size(); i++) {
			sum +=primes.get(i);
		}
		System.out.println("Sum of all the primes below " + N + ": " + sum);
	}
}
