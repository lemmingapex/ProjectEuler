package com.lemmingapex.projecteuler.magic5gonring;

/**
 * 01/22/2017
 * Magic5gonRing.java
 * Magic 5-gon ring
 *
 * @author Scott Wiedemann
 *
 */
public class Magic5gonRing {

	// lexicographical permutation algorithm
	boolean nextPermutation(int[] array) {
		// Find longest non-increasing suffix
		int i = array.length - 1;
		while (i > 0 && array[i - 1] >= array[i]) {
			i--;
		}
		// Now i is the head index of the suffix

		// Are we at the last permutation already?
		if (i <= 0) {
			return false;
		}

		// Let array[i - 1] be the pivot
		// Find rightmost element that exceeds the pivot
		int j = array.length - 1;
		while (array[j] <= array[i - 1]) {
			j--;
		}
		// Now the value array[j] will become the new pivot
		// Assertion: j >= i

		// Swap the pivot with j
		int temp = array[i - 1];
		array[i - 1] = array[j];
		array[j] = temp;

		// Reverse the suffix
		j = array.length - 1;
		while (i < j) {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}
		return true;
	}

	public String solve() {
		String solution = "";
		int[] p = new int[10];
		for(int i=1; i<=10; i++) {
			p[i-1] = i;
		}

		do {
			if(isValidMagicRing(p)) {
				String tempSolution = "";
				for(int i=0; i<10; i+=2) {
					tempSolution += p[i] + "" + p[i+1] + "" + p[(i+3)%p.length];
				}
				if(tempSolution.compareTo(solution) > 0) {
					solution = tempSolution;
				}
			}
		} while(nextPermutation(p));

		return solution;
	}

	boolean isValidMagicRing(int[] array) {
		for(int i=0; i<=8; i+=2) {
			// the number 10 should appear in the outside
			if(array[i+1] == 10) {
				return false;
			}

			// enfore array[0] being large
			if(i>0 && array[0] > array[i]) {
				return false;
			}

			// sums look good
			if(array[i] + array[i+1] != array[(i+2)%array.length] + array[(i+5)%array.length]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./magic5gonring.jar");
			System.exit(1);
		}

		System.out.println(new Magic5gonRing().solve());
	}
}
