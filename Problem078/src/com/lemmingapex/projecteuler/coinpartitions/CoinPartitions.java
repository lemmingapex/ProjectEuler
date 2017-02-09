package com.lemmingapex.projecteuler.coinpartitions;

import java.util.ArrayList;
import java.util.List;

/**
 * 02/08/2017
 * CoinPartitions.java
 * Coin partitions
 *
 * @author Scott Wiedemann
 *
 */
public class CoinPartitions {

	public int solve(int N) {
		List<Long> p = new ArrayList<Long>();
		p.add(1L);
		int n = 0;

		while(p.get(n)%N != 0){
			n++;
			//System.out.print("p(" + n + ") = ");

			int m = 1;
			int pen = 1;
			long sum = 0;
			while(pen <= n) {
				long sign = (m-1)%2==0?1L:-1L;
				//System.out.print(((sign==1L)?"+":"-") + " p(" + n + "-" + pen + ") ");
				sum += sign*p.get(n-pen);
				// only care about the last log10(N) digits, so mod to keep the remainder
				sum %= N;
				m=(m<0)?((-1*m)+1):(-1*m);
				pen = (m*(3*m-1))/2;
			}
			//System.out.print("= " + sum + "\n");
			p.add(sum);
		}

		return n;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./coinpartitions.jar N");
			System.exit(1);
		}
		final Integer N = Integer.parseInt(args[0]);

		System.out.println(new CoinPartitions().solve(N));
	}
}
