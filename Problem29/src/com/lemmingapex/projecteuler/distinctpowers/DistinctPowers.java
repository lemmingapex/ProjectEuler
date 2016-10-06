package com.lemmingapex.projecteuler.distinctpowers;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * 10/06/2016
 * DistinctPowers.java
 * Distinct powers
 *
 * @author Scott Wiedemann
 *
 */
public class DistinctPowers {
	public int solve(BigInteger N) {
		Set<BigInteger> powerExistance = new HashSet<BigInteger>();

		for(BigInteger a=BigInteger.valueOf(2); a.compareTo(N) <=0; a = a.add(BigInteger.ONE)) {
			for(Integer b=2; b <= N.intValue(); b++) {
				powerExistance.add(a.pow(b));
			}
		}

		return powerExistance.size();
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./distinctpowers.jar N");
			System.exit(1);
		}
		BigInteger N = new BigInteger(args[0]);

		System.out.println(new DistinctPowers().solve(N));
	}
}
