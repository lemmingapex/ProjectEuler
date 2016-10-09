package com.lemmingapex.projecteuler.quadraticprimes;

/**
 * 10/04/2016
 * QuadraticPrimes.java
 * Quadratic primes
 *
 * @author Scott Wiedemann
 *
 */
public class QuadraticPrimes {

	private boolean isPrime(int p) {
		int upperBound = (int)Math.sqrt(p);
		for(int i=2; i<=upperBound; i++) {
			if(p%i == 0) {
				return false;
			}
		}
		return true;
	}

	public long solve(int A, int B) {
		int maxN = 0;
		int maxA = 0;
		int maxB = 0;
		// p is prime and therefore odd when p > 2.  When n=1, p = 1 + a + b.  b is odd (see below).  p is odd, therefore a is odd as well.
		for(int a = (A%2==0)?(-1*(A-1)):(-1*A); a <= A; a+=2) {
			// when n=0, p=b, and therefore b must be prime and hence positive and odd
			for(int b = 1; b <= B; b+=2) {
				int n = 0;
				while(true) {
					int p = n*n + a*n + b;
					if(p <= 0 || !isPrime(p)) {
							break;
					}
					n++;
				}
				if(n > maxN) {
					maxN = n;
					maxA = a;
					maxB = b;
				}
			}
		}

		return maxA*maxB;
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./quadraticprimes.jar a b");
			System.exit(1);
		}
		Integer A = Integer.parseInt(args[0]);
		Integer B = Integer.parseInt(args[1]);

		System.out.println(new QuadraticPrimes().solve(A, B));
	}
}
