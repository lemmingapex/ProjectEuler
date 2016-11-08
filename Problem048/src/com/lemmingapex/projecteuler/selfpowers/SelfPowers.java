package com.lemmingapex.projecteuler.selfpowers;

/**
 * 11/08/2016
 * SelfPowers.java
 * Self powers
 *
 * @author Scott Wiedemann
 *
 */
public class SelfPowers {

	public long solve(long n, long mP) {
		long m = (long)Math.pow(10,mP);
		long sMod10 = 0;
		for(int i=1; i<=n; i++) {
			long termSum = 1;
			for(int j=0; j<i; j++) {
				termSum = (termSum*i)%m;
			}
			sMod10 = (sMod10 + termSum)%m;
		}
		return sMod10;
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./selfpowers.jar N M");
			System.exit(1);
		}
		long N = Long.parseLong(args[0]);
		long M = Long.parseLong(args[1]);

		System.out.println(new SelfPowers().solve(N, M));
	}
}
