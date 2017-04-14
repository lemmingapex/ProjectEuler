package com.lemmingapex.projecteuler.arithmeticexpressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * 04/14/2017
 * ArithmeticExpressions.java
 * Arithmetic expressions
 *
 * @author Scott Wiedemann
 *
 */
public class ArithmeticExpressions {

	public String generateExpression() {

		String expression = "";
		List<Integer> digits = new ArrayList<>();
		for(int i=0; i<10; i++) {
			digits.add(i);
		}

		generateExpressionRecursive(digits, expression);

		return expression;
	}

	private void generateExpressionRecursive(List<Integer> digits, String expression) {
		if(digits.size() == 6) {
			System.out.println(expression);
		} else {
			for(int i=0; i<digits.size(); i++) {
				List<Integer> newDigits = new ArrayList<>(digits);
				newDigits.remove(i);
				generateExpressionRecursive(newDigits, expression + " " + digits.get(i));
			}
		}
	}

	public int solve() {
		generateExpression();
		return 0;
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./arithmeticexpressions.jar");
			System.exit(1);
		}
		System.out.println(new ArithmeticExpressions().solve());
	}
}
