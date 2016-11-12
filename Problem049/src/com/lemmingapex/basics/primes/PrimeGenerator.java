package com.lemmingapex.basics.primes;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PrimeGenerator {

	/**
	 * Uses Sieve of Eratosthenes more or less
	 *
	 * @param pN
	 */
	public static List<Long> generatePrimesUpToN(Long pN) {
		// initial set will contain about 1/3 the of the natural numbers
		LinkedHashSet<Long> primes = new LinkedHashSet<Long>((int) (pN/3) + 1);

		if (pN == null || pN < 1) {
			return new ArrayList<Long>(primes);
		}

		if (pN >= 2) {
			primes.add(2l);
			if (pN >= 3) {
				primes.add(3l);

				// don't initialize list with multiples of 2 or 3. Will
				// reduce initial set set by 2/3, saving memory
				long i = 5;
				for (; i <= pN; i = i + 6) {
					primes.add(i);
					primes.add(i + 2);
				}
				// make sure that the last element added, (i-4), was within
				// bounds
				if (i > 5 && primes.contains(i - 4) && i - 4 > pN) {
					primes.remove(i - 4);
				}

			}
		}
		// index of odd numbers composites to save memory, 3,5,7,9
		boolean[] isComposite = new boolean[(int) ((pN / 2) + 1)];

		// start removing non-primes at 5, skip evens and index into the
		// composite array correctly
		long upperBound = (long)Math.sqrt(pN);
		for (long i = 3; i <= upperBound; i = i + 2l) {
			int compositeIndex = (int) ((i - 1) / 2);
			if (!isComposite[compositeIndex]) {
				long tempIndex = i * i;
				while (tempIndex <= pN) {
					if (primes.contains(tempIndex)) {
						primes.remove(tempIndex);
					}
					isComposite[(int) ((tempIndex - 1) / 2)] = true;
					tempIndex += i * 2;
				}
			}
		}
		return new ArrayList<Long>(primes);
	}

	/**
	 * Uses Sieve of Eratosthenes more or less
	 *
	 * @param pN
	 */
	public static List<Long> generateNPrimes(Long pN) {
		// find upper bound using Pn < n*(ln(n) + ln(ln(n)))
		// J. BARKLEY ROSSER AND LOWELL SCHOENFELD
		// see http://en.wikipedia.org/wiki/Prime_number_theorem#Approximations_for_the_nth_prime_number
		Long pNlt = Math.max(6l, pN);
		Double pNDouble = pNlt.doubleValue();
		Double lnpN = Math.log(pNDouble);
		Double primeAtNUpperBoundDouble = pNDouble * (lnpN + Math.log(lnpN));
		Long primeAtNUpperBoundLong = primeAtNUpperBoundDouble.longValue();
		return generatePrimesUpToN(primeAtNUpperBoundLong).subList(0, pN.intValue());
	}
}
