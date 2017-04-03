package com.lemmingapex.projecteuler.productsumnumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 03/31/2017
 * ProductSumNumbers.java
 * Product-sum numbers
 *
 * @author Scott Wiedemann
 *
 */
public class ProductSumNumbers {

	private Map<Integer, Set<List<Integer>>> uniqueFactorizationCache = new HashMap<>();

	/**
	 *
	 * Important! This methods returns the list in increasing sorted order!
	 *
	 */
	private List<Integer> properFactors(int n) {
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

	private void populateUniqueFactorizations(List<Integer> factors, Set<List<Integer>> uniqueFactorizations) {
		int factorsSize = factors.size();
		uniqueFactorizations.add(factors);
		if(factorsSize != 1) {
			for(int i=0; i<factorsSize-1; i++) {
				Integer iFactor = factors.get(i);
				for(int j=i+1; j<factorsSize; j++) {
					// Important! We must be careful to populate the newFactors list in increasing sorted order!  It will allow us to compare lists in the set without further sorting
					List<Integer> newFactors = new ArrayList<>(factorsSize - 1);
					newFactors.addAll(factors);
					newFactors.remove(i);
					Integer jFactor = factors.get(j);
					newFactors.set(j-1, iFactor*jFactor);
					Collections.sort(newFactors);
					// don't look at set's you have already looked at!
					if(!uniqueFactorizations.contains(newFactors)) {
						populateUniqueFactorizations(newFactors, uniqueFactorizations);
					}
				}
			}
		}
		return;
	}

	private Set<List<Integer>> getUniqueFactorization(int n) {
		Set<List<Integer>> uniqueFactorizations = uniqueFactorizationCache.get(n);
		if(uniqueFactorizations == null) {
			List<Integer> properFactors = properFactors(n);
			uniqueFactorizations = new HashSet<>();
			populateUniqueFactorizations(properFactors, uniqueFactorizations);
			uniqueFactorizationCache.put(n, uniqueFactorizations);
		}
		return uniqueFactorizations;
	}

	public int solve(int K) {
		// reset cache
		this.uniqueFactorizationCache = new HashMap<>();

		Set<Integer> solution = new HashSet<>();
		for(int k=2; k<=K; k++) {
			// System.out.println("k: " + k);
			// N >= k
			int nLowerBound = k;
			boolean foundSolution = false;
			for(int n=nLowerBound; !foundSolution; n++) {
				Set<List<Integer>> uniqueFactorizations = getUniqueFactorization(n);
				for(List<Integer> factorization : uniqueFactorizations) {
					int factorizationSize = factorization.size();
					// otherwise there is no way this factorization can contain k terms
					if(factorizationSize <= k) {
						// add the number of ones to the factorization to make it the correct size
						int factorizationSum = k-factorizationSize;
						for(Integer f : factorization) {
							 factorizationSum += f;
						}
						if(factorizationSum == n) {
							// System.out.print(factorizationSum + " = ");
							// for(Integer f : factorization) {
							// 	System.out.print(f + " ");
							// }
							// for(int i=0; i<k-factorizationSize; i++) {
							// 	System.out.print("1 ");
							// }
							// System.out.println("");
							foundSolution = true;
							solution.add(n);
							break;
						}
					}
				}
			}
		}

		int solutionSum = 0;
		for(Integer i : solution) {
			solutionSum += i;
		}

		return solutionSum;
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
