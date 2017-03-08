package com.lemmingapex.projecteuler.cuboidroute;

/**
 * 03/06/2017
 * CuboidRoute.java
 * Cuboid route
 *
 * @author Scott Wiedemann
 *
 */
public class CuboidRoute {

	public int solve(int M) {
		int count = 0;

		for(int i=2; ; i++) {
			for(int jk=1; jk<=2*i; jk++) {
				double h = Math.sqrt(i*i+jk*jk);
				if(h%1 == 0) {
					int c = jk/2;
					if(jk > i) {
						c = 1 + (i - (jk+1)/2);
					}
					count += c;
					if(count > M) {
						return i;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./cuboidroute.jar M");
			System.exit(1);
		}
		final int M = Integer.parseInt(args[0]);

		System.out.println(new CuboidRoute().solve(M));
	}
}
