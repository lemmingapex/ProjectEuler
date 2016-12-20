package com.lemmingapex.projecteuler.pokerhands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 12/19/2016
 * PokerHands.java
 * Poker hands
 *
 * @author Scott Wiedemann
 *
 */
public class PokerHands {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./pokerhands.jar p054_poker.txt");
			System.exit(1);
		}

		String inputFileString = args[0];
		if (inputFileString == null || inputFileString.trim().isEmpty()) {
			System.err.println("Bad file name: " + String.valueOf(inputFileString));
			System.exit(1);
		}

		File inputFile = new File(inputFileString);
		if (!inputFile.exists()) {
			System.err.println(inputFile.getAbsolutePath() + " does not exist.");
			System.exit(1);
		}

		if (!inputFile.canRead()) {
			System.err.println("Can not read " + inputFile.getAbsolutePath() + ".");
			System.exit(1);
		}

		int handPlayerOneWins = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String line = br.readLine();
			while (line != null && !line.trim().isEmpty()) {
				String[] cards = line.split("\\s+");
				Hand player1Hand = new Hand();
				for(int i=0; i<5; i++) {
					String card = cards[i];
					player1Hand.cards.add(Card.valueOf(card));
				}
				HandRank player1HandRank = HandRank.getRank(player1Hand);
				//System.out.println("player 1 " + player1Hand + " " + player1HandRank.name);
				Hand player2Hand = new Hand();
				for(int i=5; i<10; i++) {
					String card = cards[i];
					player2Hand.cards.add(Card.valueOf(card));
				}
				HandRank player2HandRank = HandRank.getRank(player2Hand);
				//System.out.println("player 2 " + player2Hand + " " + player2HandRank.name);
				if(player1HandRank.value > player2HandRank.value) {
					//System.out.println("player 1 wins!");
					handPlayerOneWins++;
				} else if(player1HandRank.value < player2HandRank.value) {
					//System.out.println("player 2 wins!");
				} else {
					int player1HandValue = player1HandRank.getHandValue(player1Hand);
					int player2HandValue = player2HandRank.getHandValue(player2Hand);
					if(player1HandValue > player2HandValue) {
						//System.out.println("player 1 wins!");
						handPlayerOneWins++;
					} else if(player1HandValue < player2HandValue) {
						//System.out.println("player 2 wins!");
					} else {
						//System.out.println("tie!");
					}
				}
				//System.out.println("");

				line = br.readLine();
			}
		} catch (Exception e) {
			System.err.println("Problem reading file: " + inputFile.getAbsolutePath() + ".");
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// bummer
				}
			}
		}

		System.out.println(handPlayerOneWins);
	}
}
