package com.lemmingapex.projecteuler.latticepaths;

import java.math.BigInteger;

/**
 * 07/14/2013
 * LatticePaths.java
 * Lattice Paths
 * 
 * @author Scott Wiedemann
 * 
 */
public class LatticePaths {
	
	public static BigInteger factorial(long pN) {
		BigInteger fact = BigInteger.ONE;
		for (BigInteger i = BigInteger.valueOf(pN); i.compareTo(BigInteger.ZERO) > 0; i=i.subtract(BigInteger.ONE)) {
			fact = fact.multiply(i);
		}
		return fact;
	}
	
	public static void main(String[] args) {
		if (args.length != 1) {
	        System.err.println("Incorrect number of arguments.");
	        System.err.println("Usage: ./latticepaths.jar N");
	        System.exit(1);
		}
		Long N = Long.parseLong(args[0]);
		BigInteger numerator = LatticePaths.factorial(2*N);
		BigInteger partDenominator = LatticePaths.factorial(N);
		BigInteger routes = numerator.divide((partDenominator.multiply(partDenominator)));
		System.out.println("Number of routes: " + routes.longValue());
	}
}
