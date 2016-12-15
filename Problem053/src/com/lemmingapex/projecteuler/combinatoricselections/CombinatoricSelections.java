package com.lemmingapex.projecteuler.combinatoricselections;

/**
 * 12/15/2016
 * CombinatoricSelections.java
 * Combinatoric selections
 *
 * @author Scott Wiedemann
 *
 */
public class CombinatoricSelections {
	public int solve() {
		long bound = 1000000L;
		int count = 0;

		for(long n=1; n<=100; n++) {

			long rUpper = n/2;
			long rLower = 0L;
			int tCount = 0;
			for(long r=rUpper; r>=rLower; r--) {
				double nChooseR = 1;
				boolean brokeBound = false;
				// start computing n choose r term by term.  If at any point nChooseR is greater than 1000000, you can stop.  This is because any addtional terms will only increase it's size.
				for(long i=r; i>0L; i--) {
					nChooseR = nChooseR*((double)(n - (r-i))/(double)i);
					if(nChooseR > bound) {
						brokeBound = true;
						tCount++;
						break;
					}
				}
				if(!brokeBound) {
					break;
				}
			}
			if(rUpper%2 == 0 && tCount > 0) {
				count += ((tCount*2)-1);
			} else if(tCount > 0) {
				count += tCount*2;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./combinatoricselections.jar");
			System.exit(1);
		}

		System.out.println(new CombinatoricSelections().solve());
	}
}
