package sw.projecteuler.longestcollatzsequence;

import java.util.HashMap;
import java.util.Map;

/**
 * 12/31/2013 LongestCollatzSequence.java Longest Collatz sequence
 * 
 * @author Scott Wiedemann
 * 
 */
public class LongestCollatzSequence {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./lcs.jar N");
			System.exit(1);
		}
		Long N = Long.parseLong(args[0]);

		int longestSequenceLength = 1;
		long longestSequenceStartingNumber = 1; 
		Map<Long, Integer> longestSequenceCache = new HashMap<Long, Integer>();
		longestSequenceCache.put(longestSequenceStartingNumber, longestSequenceLength);
		
		for(long i=1; i < N; i++) {
			long n = i;
			int currentLength = 0;
			Integer key = longestSequenceCache.get(n);
			while(key == null) {
				if(n%2 == 0) {
					n = n>>1;
				} else {
					n = 3*n + 1;
				}
				key = longestSequenceCache.get(n);
				currentLength++;
			}
			currentLength += key;
			longestSequenceCache.put(i, currentLength);
			if(currentLength > longestSequenceLength) {
				longestSequenceStartingNumber = i;
				longestSequenceLength = currentLength;
			}
		}
		System.out.println(longestSequenceStartingNumber + " is the starting value, less than " + N + ", in the longest Collatz sequence of length " + longestSequenceLength + ".");
	}
}
