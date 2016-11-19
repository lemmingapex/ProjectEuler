package com.lemmingapex.projecteuler.primedigitreplacements;

/**
 * 11/17/2016
 * PrimeDigitReplacements.java
 * Prime digit replacements
 *
 * @author Scott Wiedemann
 *
 */
public class PrimeDigitReplacements {

	private boolean isPrime(int p) {
		if(p == 1) {
			return false;
		} else if(p ==2) {
			return true;
		}
		int upperBound = (int)Math.sqrt(p);
		for(int i=2; i<=upperBound; i++) {
			if(p%i == 0) {
				return false;
			}
		}
		return true;
	}

	public int solve() {

		int[] lsds = new int[]{1,3,7,9};

		// n = jj + kkk + lsds[i]
		// n = j + k + j + kk + lsds[i]

		for(int i = 0; i<lsds.length; i++) {
			int lsd = lsds[i];

			// first j permutations
			for(int p = 0; p<4; p++) {
				for(int j = 0; j<10; j++) {

					// second j permutations
					for(int p1 = p + 1; p1<5; p1++) {
						for(int j1 = 0; j1<10; j1++) {

							// if the non-replacement digits are divisible by three, then the entire family will be divisible by three.
							if((j+j1)%3 == 0) {
								continue;
							}

							int familyLength = 0;
							int firstFamilyMember = -1;
							// k is the repeated digit
							for(int k = 0; k<10; k++) {

								// are all six digits the same? true ->  Not prime.
								if(lsd == k && k == j && j ==j1) {
									continue;
								}

								String n_string = "";
								for(int l = 0; l<5; l++) {
									if(p == l) {
										n_string += j;
									} else if(p1 == l) {
										n_string += j1;
									} else {
										n_string += k;
									}
								}
								//System.out.println(n_string);
								if(n_string.charAt(0) != '0') {
									n_string += lsd;
									int n = Integer.valueOf(n_string);
									if(isPrime(n)) {
										familyLength++;
										if(familyLength == 1) {
											firstFamilyMember = n;
										}
										//System.out.println(n);
									}
								}
							}
							if(familyLength >= 8) {
								//System.out.println("firstFamilyMember " +firstFamilyMember);
								return firstFamilyMember;
							}
						}
					}
				}
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./primedigitreplacements.jar");
			System.exit(1);
		}

		System.out.println(new PrimeDigitReplacements().solve());
	}
}
