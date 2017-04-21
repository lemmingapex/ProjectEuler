package com.lemmingapex.projecteuler.amicablechains;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;

/**
 * 04/20/2017
 * AmicableChains.java
 * Amicable chains
 *
 * @author Scott Wiedemann
 *
 */
public class AmicableChains {

	private final int N;
	private final Map<Integer, Integer> properDivisorsSums;

	public AmicableChains(int N) {
		this.N = N;
		this.properDivisorsSums = new HashMap<>();

		for(int i=2; i<N; i++) {
			Set<Integer> factors = factors(i);
			properDivisorsSums.put(i, factors.stream().mapToInt((ii) -> { return ii.intValue(); }).sum());
		}
	}

	private Set<Integer> factors(int n) {
		Set<Integer> factors = new TreeSet<>();

		factors.add(1);
		int bound = (int)Math.sqrt(n);
		for (int i=2; i<=bound; i++) {
			if(n%i == 0) {
				factors.add(i);
				factors.add(n/i);
			}
		}

		return factors;
	}

	// returns length until repetition
	private Integer floydTortoiseAndHare(int i) {
		Integer tortoise = properDivisorsSums.get(i);
		Integer hare = properDivisorsSums.get(properDivisorsSums.get(i));
		while (hare != null && tortoise != hare) {
			tortoise = properDivisorsSums.get(tortoise);
			hare = properDivisorsSums.get(properDivisorsSums.get(hare));
		}

		// no loops
		if(hare == null) {
			return -1;
		}

		// see if loop is the one we are looking for, if not, return -1
		if(hare != i) {
			return -1;
		}

		int mu = 0;
		tortoise = i;
		while (tortoise != hare) {
			tortoise = properDivisorsSums.get(tortoise);
			hare = properDivisorsSums.get(hare);
			mu += 1;
		}

		int lam = 1;
		hare = properDivisorsSums.get(tortoise);
		while (tortoise != hare) {
			hare = properDivisorsSums.get(hare);
			lam += 1;
		}

		return mu + lam;
	}

	public int solve() {
		int longestChainLength = 0;
		int longestChainStartingElement = -1;

		for(Integer i : properDivisorsSums.keySet()) {
			int length = floydTortoiseAndHare(i);
			if(length > longestChainLength) {
				longestChainLength = length;
				longestChainStartingElement = i;
			}
		}

		int longestChainSmallestElement = longestChainStartingElement;
		System.out.println("longest chain length " + (longestChainLength-1));
		System.out.print(longestChainStartingElement + " ");
		int e = properDivisorsSums.get(longestChainStartingElement);
		while(e != longestChainStartingElement) {
			System.out.print(e + " ");
			if(e < longestChainSmallestElement) {
				longestChainSmallestElement = e;
			}
			e = properDivisorsSums.get(e);
		};
		System.out.println("");

		return longestChainSmallestElement;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./amicablechains.jar N");
			System.exit(1);
		}
		final int N = Integer.parseInt(args[0]);

		System.out.println(new AmicableChains(N).solve());
	}
}
