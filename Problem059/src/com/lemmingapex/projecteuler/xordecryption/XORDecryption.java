package com.lemmingapex.projecteuler.xordecryption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 01/02/2017
 * XORDecryption.java
 * XOR decryption
 *
 * @author Scott Wiedemann
 *
 */
public class XORDecryption {

	private Map<Character, CharacterFrequency> englishCharacterFrequency = new HashMap<>();
	private List<CharacterFrequency> sortedEnglishCharacterFrequencies = new ArrayList<>(englishCharacterFrequency.size());

	public XORDecryption(File englishTextFile) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(englishTextFile));
			String line = br.readLine();
			while (line != null) {
				for(int i=0; i<line.length(); i++) {
					char c = line.charAt(i);
					CharacterFrequency characterFrequency = englishCharacterFrequency.get(c);
					if(characterFrequency == null) {
						characterFrequency = new CharacterFrequency((int)c, 0, -1);
					}
					characterFrequency.frequency++;
					englishCharacterFrequency.put(c, characterFrequency);
				}
				line = br.readLine();
			}
		} catch (Exception e) {
			System.err.println("Problem reading file: " + englishTextFile.getAbsolutePath() + ".");
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

		sortedEnglishCharacterFrequencies.addAll(englishCharacterFrequency.values());
		Collections.sort(sortedEnglishCharacterFrequencies, (CharacterFrequency cf1, CharacterFrequency cf2) -> {
				return Long.compare(cf2.frequency, cf1.frequency);
			});

		// for(CharacterFrequency characterFrequency : sortedEnglishCharacterFrequencies) {
		// 	System.out.println((char)characterFrequency.character + " " + characterFrequency.frequency);
		// }
	}

	public long solve(File cipherFile, int keySize) {
		Map<Integer, CharacterFrequency> binedCharacterFrequency = new HashMap<>();
		List<Integer> cipherText = new ArrayList<>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(cipherFile));
			String line = br.readLine();
			int index = 0;
			while (line != null && !line.trim().isEmpty()) {
				for(String n : line.split(",")) {
					int bin = index%keySize;
					index++;
					int character = Integer.valueOf(n).intValue();
					cipherText.add(character);
					int key = 1000*(bin+1) + character;
					CharacterFrequency characterFrequency = binedCharacterFrequency.get(key);
					if(characterFrequency == null) {
						characterFrequency = new CharacterFrequency(character, 0, bin);
					}
					characterFrequency.frequency++;
					binedCharacterFrequency.put(key, characterFrequency);
				}
				line = br.readLine();
			}
		} catch (Exception e) {
			System.err.println("Problem reading file: " + cipherFile.getAbsolutePath() + ".");
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

		List<CharacterFrequency> sortedCharacterFrequencies = new ArrayList<>(binedCharacterFrequency.size());
		sortedCharacterFrequencies.addAll(binedCharacterFrequency.values());
		Collections.sort(sortedCharacterFrequencies, (CharacterFrequency cf1, CharacterFrequency cf2) -> {
				return Long.compare(cf2.frequency, cf1.frequency);
			});

		// for(CharacterFrequency characterFrequency : sortedCharacterFrequencies) {
		// 	System.out.println(characterFrequency.bin + " " + characterFrequency.character + " " + characterFrequency.frequency);
		// }

		String key = "";
		for(int i=0; i<keySize; i++) {
			CharacterFrequency characterFrequency = null;
			for(CharacterFrequency cf : sortedCharacterFrequencies) {
				if(cf.bin == i) {
					characterFrequency = cf;
					break;
				}
			}
			key += ("" + (char)(sortedEnglishCharacterFrequencies.get(0).character ^ characterFrequency.character));
		}
		System.out.println("key: " + key + "\n");

		String plainText = "";
		long sum = 0;
		long cipherTextLength = cipherText.size();
		for(int i=0; i<cipherTextLength; i++) {
			int cipherCharacter = cipherText.get(i);
			int keyCharacter = key.charAt(i%keySize);
			int plaintextCharacter = cipherCharacter^keyCharacter;
			sum += plaintextCharacter;
			plainText += "" + (char)plaintextCharacter;
		}

		System.out.println("plainText: " + plainText + "\n");

		return sum;
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./xordecryption.jar p059_cipher.txt MobyDick.txt key_size");
			System.exit(1);
		}

		String cipherFileString = args[0];
		if (cipherFileString == null || cipherFileString.trim().isEmpty()) {
			System.err.println("Bad file name: " + String.valueOf(cipherFileString));
			System.exit(2);
		}

		File cipherFile = new File(cipherFileString);
		if (!cipherFile.exists()) {
			System.err.println(cipherFile.getAbsolutePath() + " does not exist.");
			System.exit(2);
		}

		if (!cipherFile.canRead()) {
			System.err.println("Can not read " + cipherFile.getAbsolutePath() + ".");
			System.exit(2);
		}

		String englishTextFileString = args[1];
		if (englishTextFileString == null || englishTextFileString.trim().isEmpty()) {
			System.err.println("Bad file name: " + String.valueOf(englishTextFileString));
			System.exit(3);
		}

		File englishTextFile = new File(englishTextFileString);
		if (!englishTextFile.exists()) {
			System.err.println(englishTextFile.getAbsolutePath() + " does not exist.");
			System.exit(3);
		}

		if (!englishTextFile.canRead()) {
			System.err.println("Can not read " + englishTextFile.getAbsolutePath() + ".");
			System.exit(3);
		}

		final int keySize = Integer.parseInt(args[2]);

		System.out.println(new XORDecryption(englishTextFile).solve(cipherFile, keySize));
	}
}
