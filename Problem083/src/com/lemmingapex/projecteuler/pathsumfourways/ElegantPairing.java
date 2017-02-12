package com.lemmingapex.projecteuler.pathsumfourways;

// Szudzik's Elegant Pairing Function
// http://szudzik.com/ElegantPairing.pdf
public class ElegantPairing {
	public static int pair(int x, int y){
		return x >= y ? (x*x+x+y) : (y*y+x);
	}

	public static int[] unpair(int z){
		int[] pair = new int[2];
		int sqrtz = (int)Math.sqrt(z);
		int l = z - sqrtz*sqrtz;
		if(l < sqrtz) {
			pair[0] = l;
			pair[1] = sqrtz;
		} else {
			pair[0] = sqrtz;
			pair[1] = l - sqrtz;
		}
		return pair;
	}
}
