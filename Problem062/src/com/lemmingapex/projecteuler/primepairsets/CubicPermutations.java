package com.lemmingapex.projecteuler.cubicpermutations;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 01/04/2017
 * CubicPermutations.java
 * Cubic permutations
 *
 * @author Scott Wiedemann
 *
 */
public class CubicPermutations {

	private String getKey(long l) {
		char[] wordChars = String.valueOf(l).toCharArray();
		Arrays.sort(wordChars);
		return String.valueOf(wordChars);
	}

	public long solve(int N) {
		Map<String, Set<Long>> keyMap = new HashMap<>();

		for(long l = 1L; ; l++) {
			long lcubed = l*l*l;
			String key = getKey(lcubed);
			Set<Long> cubes = keyMap.get(key);
			if(cubes == null) {
				cubes = new LinkedHashSet<Long>();
			}
			cubes.add(lcubed);
			keyMap.put(key, cubes);

			if(cubes.size() == N) {
				return cubes.iterator().next();
			}
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./cubicpermutations.jar N");
			System.exit(1);
		}

		final Integer N = Integer.parseInt(args[0]);

		System.out.println(new CubicPermutations().solve(N));
	}
}
