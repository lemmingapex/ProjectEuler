package com.lemmingapex.projecteuler.onethousanddigitfibonaccinumber;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 09/29/2016
 * OneThousandDigitFibonacciNumber.java
 * 1000-digit Fibonacci number
 *
 * @author Scott Wiedemann
 *
 */
public class OneThousandDigitFibonacciNumber {

	// final static double sqrt5 = Math.sqrt(5.0);
	//
	// private long i_at_F(double F) {
	// 	return (long)(Math.log((F * sqrt5) + 0.5) / Math.log((1.0 + sqrt5) / 2.0));
	// }
	//
	// private double F_at_i(long i) {
	// 	return (Math.pow((1.0 + sqrt5), i) - Math.pow((1.0 - sqrt5), i))/(Math.pow(2.0, i) * sqrt5);
	// }
	//
	private long numDigits(BigInteger n) {
		return n.toString().length();
	}
	//
	// public double n_digit_fibonacci_number(long n) {
	// 	long i_higher = 1;
	// 	double F = F_at_i(i_higher);
	// 	// find an upper bound
	// 	while(numDigits(F) < n) {
	// 		i_higher = i_higher * 2;
	// 		F = F_at_i(i_higher);
	// 		System.out.println("F " + F);
	// 	}
	// 	System.out.println("i_higher " + i_higher);
	// 	// one step back should give a lower bound
	// 	long i_lower = i_higher/2;
	//
	// 	long i = (i_lower + i_higher)/2;
	// 	F = F_at_i(i);
	// 	while(numDigits(F) < n && F_at_i(i-1) >= n) {
	// 		i = i * 2;
	// 		F = F_at_i(i);
	// 		if(numDigits(F) < n) {
	// 			i_lower = i;
	// 		} else {
	// 			i_higher = i;
	// 		}
	// 		i = (i_lower + i_higher)/2;
	// 	}
	// 	return F;
	// }

	public long n_digit_fibonacci_number(long n) {
		BigInteger f0 = BigInteger.valueOf(0);
		BigInteger f = BigInteger.valueOf(1);
		long i = 1;
		while(numDigits(f) < n) {
			BigInteger t = f;
			f = f.add(f0);
			f0 = t;
			i++;
		}
		return i;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./onethousanddigitfibonaccinumber.jar N");
			System.exit(1);
		}
		Long N = Long.parseLong(args[0]);

		System.out.println(new OneThousandDigitFibonacciNumber().n_digit_fibonacci_number(N));
	}
}
