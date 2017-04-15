package com.lemmingapex.projecteuler.arithmeticexpressions;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * 04/14/2017
 * ArithmeticExpressions.java
 * Arithmetic expressions
 *
 * @author Scott Wiedemann
 *
 */
public class ArithmeticExpressions {

	private void generateDigitsRecursive(List<Integer> digits, List<Integer> permutation, List<List<Integer>> digitPermutations) {
		if(digits.size() == 6) {
			digitPermutations.add(permutation);
		} else {
			for(int i=0; i<digits.size(); i++) {
				List<Integer> newDigits = new ArrayList<>(digits);
				List<Integer> newPermutation = new ArrayList<>(permutation);
				newDigits.remove(i);
				newPermutation.add(digits.get(i));
				generateDigitsRecursive(newDigits, newPermutation, digitPermutations);
			}
		}
	}

	private List<List<Integer>> generateDigits() {
		List<Integer> digits = new ArrayList<>();
		for(int i=0; i<10; i++) {
			digits.add(i);
		}

		List<Integer> digitPermutation = new ArrayList<>();
		List<List<Integer>> digitPermutations = new ArrayList<>();

		generateDigitsRecursive(digits, digitPermutation, digitPermutations);
		return digitPermutations;
	}

	private void generateOperationsRecursive(int count, List<String> operations, List<String> permutation, List<List<String>> operationPermutations) {
		if(count == 3) {
			operationPermutations.add(permutation);
		} else {
			for(int i=0; i<operations.size(); i++) {
				List<String> newPermutation = new ArrayList<>(permutation);
				newPermutation.add(operations.get(i));
				generateOperationsRecursive(count+1, operations, newPermutation, operationPermutations);
			}
		}
	}

	private List<List<String>> generateOperations() {
		List<String> operations = new ArrayList<>();
		operations.add("*");
		operations.add("+");
		operations.add("/");
		operations.add("-");

		List<String> operationPermutation = new ArrayList<>();
		List<List<String>> operationPermutations = new ArrayList<>();

		generateOperationsRecursive(0, operations, operationPermutation, operationPermutations);
		return operationPermutations;
	}

	public int solve() {

		List<List<Integer>> digitPermutations = generateDigits();
		List<List<String>> operationPermutations = generateOperations();

		for(List<String> op : operationPermutations) {
			for(String o : op) {
				System.out.print(o + "");
			}
			System.out.println("");
		}
		return 0;
		//
		// Map<TreeSet<Integer>, TreeSet<Integer>> combinationToDigitsMap = new HashMap<>();
		//
		// for(List<Integer> dp : digitPermutations) {
		// 	TreeSet<Integer> mapKey = new TreeSet<>();
		// 	mapKey.addAll(dp);
		// 	TreeSet<Integer> mapDigits = combinationToDigitsMap.get(mapKey);
		// 	if(mapDigits == null) {
		// 		mapDigits = new TreeSet<>();
		// 	}
		// 	for(List<String> op : operationPermutations) {
		// 		String expression = "" + dp.get(0);
		// 		double expressionValue = dp.get(0);
		// 		for(int i=0; i<op.size(); i++) {
		// 			String operation = op.get(i);
		// 			Integer digit = dp.get(i+1);
		// 			switch(operation) {
		// 				case "*":
		// 				expressionValue *= (double)digit;
		// 					break;
		// 				case "+":
		// 				expressionValue += (double)digit;
		// 					break;
		// 				case "/":
		// 				expressionValue /= (double)digit;
		// 					break;
		// 				case "-":
		// 				expressionValue -= (double)digit;
		// 					break;
		// 			}
		// 			expression += operation + digit;
		// 		}
		// 		// is the result an integer?
		// 		if(!Double.isInfinite(expressionValue) && (expressionValue == Math.floor(expressionValue)) && expressionValue >0) {
		// 			mapDigits.add((int)expressionValue);
		// 			System.out.println(expression + " = " + expressionValue);
		// 		}
		// 	}
		// 	combinationToDigitsMap.put(mapKey, mapDigits);
		// }
		//
		// int highestTarget = 0;
		// TreeSet<Integer> highestTargetCombination = null;
		// for (Map.Entry<TreeSet<Integer>, TreeSet<Integer>> combination : combinationToDigitsMap.entrySet()) {
		// 	int target = 0;
		// 	TreeSet<Integer> targetCombination = combination.getValue();
		// 	int i = 1;
		// 	for(Integer value : targetCombination) {
		// 		if(value == i) {
		// 			target++;
		// 		} else {
		// 			break;
		// 		}
		// 		i++;
		// 	}
		// 	if(target > highestTarget) {
		// 		highestTarget = target;
		// 		highestTargetCombination = combination.getKey();
		// 	}
		// }
		// System.out.println(highestTarget);
		//
		// return Integer.valueOf(highestTargetCombination.stream().map((d) -> { return d.toString(); }).collect(Collectors.joining("")));
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
