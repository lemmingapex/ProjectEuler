package com.lemmingapex.projecteuler.coinsums;

import java.util.ArrayList;
import java.util.List;

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
		Integer numberOfWays = 0;
		return recurse(N, 0, numberOfWays);
	}

	public int recurse(Integer target, int k, Integer numberOfWays) {
		if(target == 0) {
			return ++numberOfWays;
		} else {
			for(int i = k; i<coinValues.size(); i++) {
				int v = target-coinValues.get(i);
				if(v >= 0) {
					numberOfWays = recurse(v, i, numberOfWays);
				} else {
					break;
				}
			}
		}
		return numberOfWays;
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
