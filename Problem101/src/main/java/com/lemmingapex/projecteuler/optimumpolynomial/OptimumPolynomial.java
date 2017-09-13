package com.lemmingapex.projecteuler.optimumpolynomial;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm;

/**
 * 09/13/2017
 * OptimumPolynomial.java
 * Optimum polynomial
 *
 * @author Scott Wiedemann
 *
 */
public class OptimumPolynomial {

	private long U(int N) {
		long U = 1;
		long sign = -1;
		long UN = N;
		for(int i = 1; i <= 10; i++) {
			U += UN*sign;
			UN *= N;
			sign *= -1;
		}
		return U;
	}

	public long solve() {
		long fitsSum = 1L;
		List<Double> currentSequence = new ArrayList<>();
		List<Double> x = new ArrayList<>();
		currentSequence.add((double)U(1));
		x.add((double)1);
		for(int i = 2; i < 11; i++) {
			currentSequence.add((double)U(i));
			x.add((double)i);
			PolynomialFunctionNewtonForm polynomialFunctionNewtonForm = new DividedDifferenceInterpolator().interpolate(x.stream().mapToDouble(Double::doubleValue).toArray(), currentSequence.stream().mapToDouble(Double::doubleValue).toArray());

			fitsSum += polynomialFunctionNewtonForm.value(i+1);
			// for(int j = 0; j < x.size(); j++) {
			// 	System.out.print(x.get(j) + " ");
			// }
			// System.out.println("");
			// for(int j = 0; j < currentSequence.size(); j++) {
			// 	System.out.print(currentSequence.get(j) + " ");
			// }
			// System.out.print(polynomialFunctionNewtonForm.value(i+1) + "?");
			// System.out.println("");

		}
		return fitsSum;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./Problem101.jar");
			System.exit(1);
		}

		System.out.println(new OptimumPolynomial().solve());
	}
}
