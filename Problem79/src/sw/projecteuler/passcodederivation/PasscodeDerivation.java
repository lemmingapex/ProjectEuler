package sw.projecteuler.passcodederivation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 02/15/2015 PasscodeDerivation.java Passcode Derivation
 * 
 * @author Scott Wiedemann
 * 
 */
public class PasscodeDerivation {

	public String getPasscode(List<String> keylog) {
		String passcode = "";

		while (!keylog.isEmpty()) {
			Set<Character> possiblePrefixes = new HashSet<Character>();
			for (String key : keylog) {
				for (int i = 0; i < key.length(); i++) {
					Character c = key.charAt(i);
					possiblePrefixes.add(c);
				}
			}

			if (possiblePrefixes.isEmpty()) {
				break;
			}

			// find a character that is not prefixed by any other character
			for (String key : keylog) {
				for (int i = 1; i < key.length(); i++) {
					possiblePrefixes.remove(key.charAt(i));
				}
			}

			if (possiblePrefixes.size() == 0) {
				System.err.println("No vaild passcode in this file.");
			} else if (possiblePrefixes.size() > 1) {
				System.err.println("Multiple valid passcodes, choosing randomly.");
			}
			if (possiblePrefixes.size() == 1) {
				String prefix = "" + possiblePrefixes.iterator().next();

				for (int i = 0; i < keylog.size(); i++) {
					String key = keylog.get(i);
					if (key.startsWith(prefix)) {
						keylog.set(i, key.substring(1));
					}
				}

				passcode += prefix;
			}
		}

		return passcode;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./passcodederivation.jar p079_keylog.txt");
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

		List<String> keylog = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String line = br.readLine();
			while (line != null && !line.trim().isEmpty()) {
				keylog.add(line);
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

		String passcode = new PasscodeDerivation().getPasscode(keylog);

		System.out.println("Shortest possible secret passcode: " + passcode);
	}
}
