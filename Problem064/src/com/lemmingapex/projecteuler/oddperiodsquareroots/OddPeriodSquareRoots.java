package com.lemmingapex.projecteuler.oddperiodsquareroots;

/**
 * 01/16/2017
 * OddPeriodSquareRoots.java
 * Odd period square roots
 *
 * @author Scott Wiedemann
 *
 */
public class OddPeriodSquareRoots {

	public int solve(int N) {
		int count = 0;

		for(int s=2; s <= N; s++) {
			int m = 0;
			int d = 1;
			int a0 = (int)Math.sqrt(s);
			String sqrtText = "√" + s + " = [" + a0 + ";(";
			if(a0 != Math.sqrt(s)) {
				int a = a0;
				int i = 0;
				do {
					i++;
					m = d*a - m;
					d = (s - (m*m))/d;
					if(d == 0) {
						break;
					}
					a = (a0 + m)/d;
					sqrtText += a + ",";
				} while(a != 2*a0);
				sqrtText = sqrtText.substring(0, sqrtText.length()-1);
				sqrtText += ")], period=" + i;
				System.out.println(sqrtText);
				if(i%2 == 1) {
					count++;
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./oddperiodsquareroots.jar N");
			System.exit(1);
		}

		final int N = Integer.parseInt(args[0]);

		System.out.println(new OddPeriodSquareRoots().solve(N));
	}
}
