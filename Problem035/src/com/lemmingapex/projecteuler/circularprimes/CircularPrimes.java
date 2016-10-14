package com.lemmingapex.projecteuler.circularprimes;

import java.util.List;
import com.lemmingapex.basics.primes.PrimeGenerator;

/**
 * 10/13/2016
 * CircularPrimes.java
 * Circular primes
 *
 * @author Scott Wiedemann
 *
 */
public class CircularPrimes {

	public long solve(long N) {
		List<Long> primes = PrimeGenerator.generatePrimesUpToN(N);
		// because we will skip 2,3 and 5.  start count at 3
		Long count = 3L;
		// start with 3
		for (int i = 3; i < primes.size(); i++) {
			Long p = primes.get(i);
			boolean allRotationsOfPArePrime = true;
			String pString = p.toString();
			if(!pString.matches("\\d*[024568]\\d*")) {
				int numberOfRotations = pString.length() - 1;
				for(int j = 0; j < numberOfRotations; j++) {
					pString = pString.substring(numberOfRotations, numberOfRotations+1) + pString.substring(0, numberOfRotations);
					if(!primes.contains(Long.valueOf(pString))) {
						allRotationsOfPArePrime = false;
						break;
					}
				}
				if(allRotationsOfPArePrime) {
					//System.out.println(p);
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./circularprimes.jar N");
			System.exit(1);
		}
		Long P = Long.parseLong(args[0]);
		Long N = (long)Math.pow(10.0, P.doubleValue());

		System.out.println(new CircularPrimes().solve(N));
	}
}
