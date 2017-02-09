package com.lemmingapex.projecteuler.primesummations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.lemmingapex.basics.primes.PrimeGenerator;

/**
 * 02/09/2017
 * PrimeSummations.java
 * Prime summations
 *
 * @author Scott Wiedemann
 *
 */
public class PrimeSummations {

	public int solve(Integer N) {
		int solution = 0;

		Long N_l = N.longValue();
		List<Long> primes = PrimeGenerator.generatePrimesUpToN(N_l);

		long[] partitions = new long[N+1];
		// 0 can be partitioned 1 way
		partitions[0] = 1L;

		for(Long p : primes) {
			for(long j=p; j<=N; j++) {
				partitions[(int)j] += partitions[(int)(j-p)];
			}
		}

		for(int i=0; i<N+1; i++) {
			if(partitions[i] >= N) {
				solution = i;
				break;
			}
		}

		return solution;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./primesummations.jar N");
			System.exit(1);
		}
		final Integer N = Integer.parseInt(args[0]);

		System.out.println(new PrimeSummations().solve(N));
	}
}
