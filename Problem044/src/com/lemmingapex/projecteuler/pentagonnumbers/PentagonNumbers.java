package com.lemmingapex.projecteuler.pentagonnumbers;

/**
 * 11/04/2016
 * PentagonNumbers.java
 * Pentagon numbers
 *
 * @author Scott Wiedemann
 *
 */
public class PentagonNumbers {

	public int solve() {

		for(int k=2; k<3000; k++) {
			for(int j=k-1; j>0; j--) {
				int Pk = k*(3*k-1)/2;
				int Pj = j*(3*j-1)/2;
				int Cl = 2*(Pj - Pk);
				int Cm = -2*(Pk + Pj);

				double l = (1 + Math.sqrt(1 - 12*Cl))/6;
				if(l%1 == 0) {
					double m = (1 + Math.sqrt(1 - 12*Cm))/6;
					if(m%1 == 0) {
						//System.out.println("k,j,Pk-Pj: " + k + "," + j + "," + (Pk-Pj));
						return (Pk-Pj);
					}
				}
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./pentagonnumbers.jar");
			System.exit(1);
		}

		System.out.println(new PentagonNumbers().solve());
	}
}
