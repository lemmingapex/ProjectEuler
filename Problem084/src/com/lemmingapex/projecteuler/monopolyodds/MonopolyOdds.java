package com.lemmingapex.projecteuler.monopolyodds;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;

/**
 * 02/19/2017
 * MonopolyOdds.java
 * Monopoly odds
 *
 * @author Scott Wiedemann
 *
 */
public class MonopolyOdds {

	// Dice Sidedness
	private final int N;
	private final List<String> board;
	private final int boardLength;
	private final Map<String, RuleSet> ruleSets;

	public MonopolyOdds(int N) {
		this.N = N;
		this.board = new ArrayList<>(Arrays.asList("GO", "A1", "CC1", "A2", "T1", "R1", "B1", "CH1", "B2", "B3", "JAIL", "C1", "U1", "C2", "C3", "R2", "D1", "CC2", "D2", "D3", "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3", "G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2"));
		this.boardLength = this.board.size();
		this.ruleSets = new HashMap<>();

		RuleSet G2J = new RuleSet("G2J", 1);
		G2J.rules.add(new Rule(this.board.indexOf("JAIL")));

		this.ruleSets.put(G2J.space, G2J);

		RuleSet CC = new RuleSet("CC", 16);
		CC.rules.add(new Rule(this.board.indexOf("GO")));
		CC.rules.add(new Rule(this.board.indexOf("JAIL")));

		this.ruleSets.put(CC.space, CC);

		RuleSet CH = new RuleSet("CH", 16);
		CH.rules.add(new Rule(this.board.indexOf("GO")));
		CH.rules.add(new Rule(this.board.indexOf("JAIL")));
		CH.rules.add(new Rule(this.board.indexOf("C1")));
		CH.rules.add(new Rule(this.board.indexOf("E3")));
		CH.rules.add(new Rule(this.board.indexOf("H2")));
		CH.rules.add(new Rule(this.board.indexOf("R1")));
		CH.rules.add(new Rule(-1) {
			@Override
			public int apply(int currentSquare, List<String> board) {
				for(int i = currentSquare+1; ; i=(i+1)%board.size()) {
					if(board.get(i).startsWith("R")) {
						return i;
					}
				}
			}
		});
		CH.rules.add(new Rule(-1) {
			@Override
			public int apply(int currentSquare, List<String> board) {
				for(int i = currentSquare+1; ; i=(i+1)%board.size()) {
					if(board.get(i).startsWith("R")) {
						return i;
					}
				}
			}
		});
		CH.rules.add(new Rule(-1) {
			@Override
			public int apply(int currentSquare, List<String> board) {
				for(int i = currentSquare+1; ; i=(i+1)%board.size()) {
					if(board.get(i).startsWith("U")) {
						return i;
					}
				}
			}
		});
		CH.rules.add(new Rule(-1) {
			@Override
			public int apply(int currentSquare, List<String> board) {
				return (currentSquare-3)%board.size();
			}
		});

		this.ruleSets.put(CH.space, CH);
	}

	private int roll() {
		return (int)Math.min(Math.random()*N + 1, N);
	}

	private RuleSet getRuleSet(int currentSquare) {
		RuleSet ruleSet = null;
		String currentSpace = this.board.get(currentSquare);
		String prefix = currentSpace.substring(0, currentSpace.length()-1);
		String index = currentSpace;
		if(prefix.equals("CC") || prefix.equals("CH")) {
			index = prefix;
		}

		return this.ruleSets.get(index);
	}

	public String solve() {
		String solution = "";

		final int numberOfRolls = 10000000;
		int[] odds = new int[this.boardLength];

		int doublesCount = 0;
		for(int i=0, currentSquare = 0; i<numberOfRolls; i++) {
			odds[currentSquare]++;
			int dieOne = this.roll();
			int dieTwo = this.roll();
			if(dieOne == dieTwo) {
				doublesCount++;
			} else {
				doublesCount = 0;
			}
			if(doublesCount == 3) {
				doublesCount = 0;
				currentSquare = this.board.indexOf("JAIL");
			} else {
				int sumDice = dieOne + dieTwo;
				currentSquare += sumDice;
				currentSquare %= this.boardLength;
			}

			RuleSet ruleSet = this.getRuleSet(currentSquare);
			while(ruleSet != null) {
				int newSquare = ruleSet.apply(currentSquare, this.board);
				if(newSquare == currentSquare) {
					ruleSet = null;
				} else {
					currentSquare = newSquare;
					ruleSet = this.getRuleSet(currentSquare);
				}
			}
		}

		List<SquareOdd> squareOdds = new ArrayList<>();
		for(int i=0; i<odds.length; i++) {
			squareOdds.add(new SquareOdd(i, (((double)odds[i])/numberOfRolls)));
		}
		Collections.sort(squareOdds);

		DecimalFormat df = new DecimalFormat("0.00");
		int s = 0;
		for(SquareOdd squareOdd : squareOdds) {
			System.out.println(board.get(squareOdd.square) + " " + df.format(100*squareOdd.odds) + "%");
			if(s < 3) {
				solution += String.format("%02d", squareOdd.square);
			}
			s++;
		}

		return solution;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./monopolyodds.jar N");
			System.exit(1);
		}
		final int N = Integer.parseInt(args[0]);

		System.out.println(new MonopolyOdds(N).solve());
	}
}
