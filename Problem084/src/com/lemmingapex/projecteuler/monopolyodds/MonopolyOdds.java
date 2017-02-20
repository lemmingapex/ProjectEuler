package com.lemmingapex.projecteuler.monopolyodds;

import java.util.HashMap;
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

	// public class Rule {
	// 	public double percentChanceEffect = 1.0;
	// 	public String destination = "GO";
	//
	// 	public Rule(double percentChanceEffect, String destination) {
	// 		this.percentChanceEffect = percentChanceEffect;
	// 		this.destination = destination;
	// 	}
	//
	// 	public String apply(Stirng currentSquare) {
	// 		double random = Math.random();
	// 		if(random <= percentChanceEffect) {
	// 			return destination;
	// 		}
	// 		return currentSquare;
	// 	}
	// }

	// Dice Sidedness
	private final int N;
	private final String[] board;
	//private final Map<String, List<Rule>> rules;

	public MonopolyOdds(int N) {
		this.N = N;
		this.board = new String[]{"GO", "A1", "CC1", "A2", "T1", "R1", "B1", "CH1", "B2", "B3", "JAIL", "C1", "U1", "C2", "C3", "R2", "D1", "CC2", "D2", "D3", "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3", "G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2"};

		// this.rules = new HashMap<>();
		// this.rules.put("G2J", new Rule(1.0, "JAIL"));
		// this.rules.put("CC", new Rule(1.0/16.0, "GO"));
		// this.rules.put("CC", new Rule(1.0/16.0, "G2J"));

	}

	private int roll() {
		return (int)Math.min(Math.random()*N + 1, N);
	}

	public int solve() {
		int[] odds = new int[this.board.length];

		final int numberOfRolls = 10000000;

		for(int i=0, currentSquare = 0; i<numberOfRolls; i++) {
			odds[currentSquare]++;
			int sumDice = this.roll() + this.roll();
			currentSquare += sumDice;
			currentSquare %= this.board.length;
		}

		int max = 1;
		for(int i=0; i<odds.length; i++) {
			if(odds[i] > max) {
				max = odds[i];
			}
		}

		DecimalFormat df = new DecimalFormat("#.0000");
		for(int i=0; i<odds.length; i++) {
			System.out.println(board[i] + " " + df.format((((double)odds[i])/max)/this.board.length));
		}

		return 0;
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
