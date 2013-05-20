package sw.projecteuler.smallestmultiple;

import java.util.ArrayList;
import java.util.List;

import sw.basics.primes.PrimeGenerator;

/**
 * 05/14/2013
 * SmallestMultiple.java
 * Smallest multiple
 * 
 * @author Scott Wiedemann
 * 
 */
public class SmallestMultiple {

	private static boolean allOnes(List<Long> pCollection) {
		for (int i = 0; i < pCollection.size(); i++) {
			if (pCollection.get(i) != 1l) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Finds the smallest multiple of the set of numbers from number from 1 to
	 * N.
	 * 
	 * @param N
	 * @return
	 */
	public static Long getSmallestMultiple(Integer N) {
		List<Long> numberList = new ArrayList<Long>();
		for (int i = 0; i < N; i++) {
			numberList.add(i + 1l);
		}

		List<Long> primes = PrimeGenerator.generatePrimesUpToN(N.longValue());

		List<Long> multiples = new ArrayList<Long>();
		int currentPrimeIndex = 0;
		while (!allOnes(numberList)) {
			boolean foundDivisor = false;
			for (int i = 0; i < numberList.size(); i++) {
				Long currentPrime = primes.get(currentPrimeIndex);
				if (numberList.get(i) != 1l && numberList.get(i) % currentPrime == 0) {
					foundDivisor = true;
					numberList.set(i, numberList.get(i) / currentPrime);
				}
			}
			if (foundDivisor) {
				multiples.add(primes.get(currentPrimeIndex));
			} else {
				currentPrimeIndex++;
			}
		}

		long lcm = 1;
		for (Long m : multiples) {
			lcm = lcm * m;
		}
		return lcm;
	}
	

	public static void main(String[] args) {
		System.out.println(SmallestMultiple.getSmallestMultiple(20));
	}
}