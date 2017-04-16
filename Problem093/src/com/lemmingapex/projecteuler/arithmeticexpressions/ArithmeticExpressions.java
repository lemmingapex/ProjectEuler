package com.lemmingapex.projecteuler.arithmeticexpressions;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
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

		List<List<String>> reversePolishNotations = new ArrayList<List<String>>();
		reversePolishNotations.add(Arrays.asList(new String[]{"D", "D", "O", "D", "O", "D", "O"}));
		reversePolishNotations.add(Arrays.asList(new String[]{"D", "D", "D", "O", "D", "O", "O"}));
		reversePolishNotations.add(Arrays.asList(new String[]{"D", "D", "D", "O", "O", "D", "O"}));
		reversePolishNotations.add(Arrays.asList(new String[]{"D", "D", "D", "D", "O", "O", "O"}));
		reversePolishNotations.add(Arrays.asList(new String[]{"D", "D", "O", "D", "D", "O", "O"}));

		Map<TreeSet<Integer>, TreeSet<Integer>> combinationToDigitsMap = new HashMap<>();

		for(List<Integer> dp : digitPermutations) {
			TreeSet<Integer> mapKey = new TreeSet<>();
			mapKey.addAll(dp);
			TreeSet<Integer> mapDigits = combinationToDigitsMap.get(mapKey);
			if(mapDigits == null) {
				mapDigits = new TreeSet<>();
			}
			for(List<String> op : operationPermutations) {
				for(List<String> reversePolishNotation : reversePolishNotations) {
					LinkedList<Object> expressionQueue = new LinkedList<Object>();
					int d = 0;
					int o = 0;
					for(String m : reversePolishNotation) {
						switch(m) {
							case "D":
								expressionQueue.add(dp.get(d++));
								break;
							case "O":
								expressionQueue.add(op.get(o++));
								break;
						}
					}

					Stack<Double> expressionValueStack = new Stack<>();
					for(Object e : expressionQueue) {
						if(e instanceof String) {
							String operation = (String)e;
							double A = expressionValueStack.pop();
							double B = expressionValueStack.pop();
							switch(operation) {
								case "*":
									expressionValueStack.push(A*B);
									break;
								case "+":
									expressionValueStack.push(A+B);
									break;
								case "/":
									expressionValueStack.push(A/B);
									break;
								case "-":
									expressionValueStack.push(A-B);
									break;
							}
						} else if(e instanceof Integer) {
							double digit = (double)((Integer)e);
							expressionValueStack.push(digit);
						}
					}

					double expressionValue = expressionValueStack.pop();
					// is the result an integer?
					if(!Double.isInfinite(expressionValue) && (expressionValue == Math.floor(expressionValue)) && expressionValue>0) {
						mapDigits.add((int)expressionValue);
						// System.out.println(expression + " = " + expressionValue);
					}
				}
			}
			combinationToDigitsMap.put(mapKey, mapDigits);
		}

		int highestTarget = 0;
		TreeSet<Integer> highestTargetCombination = null;
		for (Map.Entry<TreeSet<Integer>, TreeSet<Integer>> combination : combinationToDigitsMap.entrySet()) {
			int target = 0;
			TreeSet<Integer> targetCombination = combination.getValue();
			int i = 1;
			for(Integer value : targetCombination) {
				if(value == i) {
					target++;
				} else {
					break;
				}
				i++;
			}
			if(target > highestTarget) {
				highestTarget = target;
				highestTargetCombination = combination.getKey();
			}
		}
		// System.out.println(highestTarget);

		return Integer.valueOf(highestTargetCombination.stream().map((d) -> { return d.toString(); }).collect(Collectors.joining("")));
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
