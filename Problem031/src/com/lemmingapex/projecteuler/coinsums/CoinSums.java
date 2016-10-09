package com.lemmingapex.projecteuler.coinsums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/**
 * 10/09/2016
 * CoinSums.java
 * Coin sums
 *
 * @author Scott Wiedemann
 *
 */
public class CoinSums {

	private List<Integer> coinValues = new ArrayList<Integer>();

	public CoinSums() {
		coinValues.add(1);
		coinValues.add(2);
		coinValues.add(5);
		coinValues.add(10);
		coinValues.add(20);
		coinValues.add(50);
		coinValues.add(100);
		coinValues.add(200);
	}

	public int solve(int N) {

		List<Set<List<Integer>>> sumNumberOfWays = new ArrayList<Set<List<Integer>>>(N + 1);

		for(int i = 0; i<N+1; i++) {
			sumNumberOfWays.add(new HashSet<List<Integer>>());
		}

		// init sums
		for(Integer coinValue : coinValues) {
			if(sumNumberOfWays.size() > coinValue) {
				List<Integer> coinValueQueue = new ArrayList<Integer>();
				coinValueQueue.add(coinValue);
				sumNumberOfWays.get(coinValue).add(coinValueQueue);
			}
		}

		for(Integer coinValue : coinValues) {
			for(Integer numberOfCoins = (N/coinValue) - 1; numberOfCoins>0; numberOfCoins--) {
				for(int j=1; j<sumNumberOfWays.size(); j++) {
					if(sumNumberOfWays.get(j).size() > 0 && j+coinValue < sumNumberOfWays.size()) {
						Set<List<Integer>> sumOfWaysAtJPlusCoinValue = new HashSet<List<Integer>>();
						for(List<Integer> pq : sumNumberOfWays.get(j)) {
							List<Integer> nPq = new ArrayList<Integer>();
							for(Integer k : pq) {
								nPq.add(k);
							}
							Collections.sort(nPq);
							sumOfWaysAtJPlusCoinValue.add(nPq);
						}
						for(List<Integer> pq : sumOfWaysAtJPlusCoinValue) {
							pq.add(coinValue);
							Collections.sort(pq);
						}
						sumNumberOfWays.get(j+coinValue).addAll(sumOfWaysAtJPlusCoinValue);
					}
				}
			}
		}

		// for(int k=0; k<sumNumberOfWays.size(); k++) {
		// 	System.out.println("ways to add to " + k);
		// 	Set<List<Integer>> s = sumNumberOfWays.get(k);
		// 	for(List<Integer> pq : s) {
		// 		for(Integer i : pq) {
		// 			System.out.print(i + " ");
		// 		}
		// 		System.out.println("\n");
		// 	}
		// }

		return sumNumberOfWays.get(N).size();
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./coinsums.jar N");
			System.exit(1);
		}
		Integer N = Integer.parseInt(args[0]);

		System.out.println(new CoinSums().solve(N));
	}
}
