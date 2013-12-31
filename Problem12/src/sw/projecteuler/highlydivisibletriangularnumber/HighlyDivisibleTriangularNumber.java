package sw.projecteuler.highlydivisibletriangularnumber;

import java.util.HashMap;
import java.util.Map;

/**
 * 12/31/2013 HighlyDivisibleTriangularNumber.java Highly divisible triangular
 * number
 * 
 * @author Scott Wiedemann
 * 
 */
public class HighlyDivisibleTriangularNumber {
	
	/**
	 * 
	 * 
	 * @param N
	 * @return
	 */
	public static Long getNumberOfDivisors(Long N) {
		Map<Long, Long> divisorOccurance = new HashMap<Long, Long>();
		long toFactor = N.longValue();
		long k = 2;
		while (k <= toFactor) {
			if (toFactor % k == 0) {
				Long divisorOccuranceFork = divisorOccurance.get(k);
				if (divisorOccuranceFork != null) {
					divisorOccurance.put(k, divisorOccuranceFork + 1l);
				} else {
					divisorOccurance.put(k, 1l);
				}
				toFactor = toFactor / k--;
			}
			k++;
		}

		long numberOfDivisors = 1l;
		//System.out.println(divisorOccurance);
		for (Long dO : divisorOccurance.values()) {
			numberOfDivisors *= (dO + 1l);
		}
		return numberOfDivisors;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./hdtn.jar N");
			System.exit(1);
		}
		Long N = Long.parseLong(args[0]);
		long i = 1;
		long t = i * (i + 1) / 2;
		long nd = getNumberOfDivisors(t);
		// nd1 and nd2 will always be coprime to one another
		long nd1 = 2;
		long nd2 = 2;
		while (nd < N) {
			if (i % 2 == 0) {
				nd1 = getNumberOfDivisors(i + 1);
				nd = nd1 * nd2;
			} else {
				nd2 = getNumberOfDivisors((i + 1) / 2);
				nd = nd1 * nd2;
			}
			t = i * (i + 1) / 2;
			i++;
		}
		System.out.println("The trianglar number " + t + " has " + nd + " divisors");
	}
}
