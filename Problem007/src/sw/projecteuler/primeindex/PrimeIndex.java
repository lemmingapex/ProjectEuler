package sw.projecteuler.primeindex;

import sw.basics.primes.PrimeGenerator;

/**
 * 05/29/2013
 * PrimeIndex.java
 * 10001st prime
 * 
 * @author Scott Wiedemann
 * 
 */
public class PrimeIndex {
	
	public static void main(String[] args) {
		if (args.length != 1) {
	        System.err.println("Incorrect number of arguments.");
	        System.err.println("Usage: ./primeindex.jar N");
	        System.exit(1);
		}
		
        Long N = Long.parseLong(args[0]);
		System.out.println(PrimeGenerator.generateNPrimes(N).get(N.intValue() - 1));
	}
}