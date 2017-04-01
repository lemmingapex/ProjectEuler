package com.lemmingapex.projecteuler.productsumnumbers;

import java.util.ArrayList;
import java.util.List;

/**
 * 03/13/2017
 * ProductSumNumbers.java
 * Product-sum numbers
 *
 * @author Scott Wiedemann
 *
 */
public class ProductSumNumbers {


	List<Integer> properFactors(int n) {
		List<Integer> factors = new ArrayList<>();

		for (int i = 2; i <= n / i; i++) {
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
		if (n > 1) {
			factors.add(n);
		}

		return factors;
	}

	public int solve(int K) {
		for(int k=2; k<=K; k++) {
			// N >= k
			int nLowerBound = k;
			for(int n=nLowerBound; n<10000; n++) {
				System.out.println("n: " + n);
				List<Integer> properFactors = properFactors(n);
				for(Integer pF : properFactors) {
					System.out.println(pF);
				}
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./productsumnumbers.jar k");
			System.exit(1);
		}
		final int K = Integer.parseInt(args[0]);

		System.out.println(new ProductSumNumbers().solve(K));
	}
}
