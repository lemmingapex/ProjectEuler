package com.lemmingapex.projecteuler.cubedigitpairs;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * 04/06/2017
 * CubeDigitPairs.java
 * Cube digit pairs
 *
 * @author Scott Wiedemann
 *
 */
public class CubeDigitPairs {

	private List<List<Integer>> generateCombinations(int n, int k) {
		List<List<Integer>> combinations = new ArrayList<>();

		List<Integer> combination = new ArrayList<>(k);
		for(int i=0; i<k; i++) {
			combination.add(0);
		}
		combinations(n, k, 0, combination, combinations);

		return combinations;
	}

	private void combinations(int n, int k, int p, List<Integer> combination, List<List<Integer>> combinations){
		combination = new ArrayList<>(combination);
		if (k == 0){
			combinations.add(combination);
			return;
		}
		int index = combination.size() - k;
		int bound = n-k;
		for (int i = p; i <= bound; i++){
			combination.set(index, i);
			combinations(n, k-1, i+1, combination, combinations);
		}
	}

	public int solve() {
		final int N = 10;

		int count = 0;

		List<List<Integer>> die1combinations = generateCombinations(N, 6);
		List<List<Integer>> die2combinations = new ArrayList<>(die1combinations.size());

		for(List<Integer> c : die1combinations) {

			if(c.contains(6) && !c.contains(9)) {
				c.add(9);
			}
			if(c.contains(9) && !c.contains(6)) {
				c.add(6);
			}

			// for(Integer i : c) {
			// 	System.out.print(i + " ");
			// }
			// System.out.println("");

			die2combinations.add(new ArrayList<>(c));
		}

		List<Integer> digits1 = new ArrayList<>();
		List<Integer> digits2 = new ArrayList<>();
		for(int i = 1; i<N; i++) {
			String s = new Integer(i*i).toString();
			if(s.length() < 2) {
				s = "0"+s;
			}
			digits1.add(Character.getNumericValue(s.charAt(0)));
			digits2.add(Character.getNumericValue(s.charAt(1)));
		}

		for(int i = 0; i<die1combinations.size()-1; i++) {
			List<Integer> d1c = die1combinations.get(i);
			for(int j = i+1; j<die2combinations.size(); j++) {
				List<Integer> d2c = die2combinations.get(j);

				boolean containsDigits = true;
				Iterator<Integer> d1i = digits1.iterator();
				Iterator<Integer> d2i = digits2.iterator();
				while (d1i.hasNext() && d2i.hasNext()) {
					Integer d1 = d1i.next();
					Integer d2 = d2i.next();

					if(!((d1c.contains(d1) && d2c.contains(d2)) || (d1c.contains(d2) && d2c.contains(d1)))) {
						containsDigits = false;
						break;
					}
				}
				if(containsDigits) {
					count++;
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./cubedigitpairs.jar");
			System.exit(1);
		}
		System.out.println(new CubeDigitPairs().solve());
	}
}
