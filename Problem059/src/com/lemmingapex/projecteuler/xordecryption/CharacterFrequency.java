package com.lemmingapex.projecteuler.xordecryption;

public class CharacterFrequency {

	public final int character;
	public long frequency;
	public final int bin;

	public CharacterFrequency(int character, long frequency, int bin) {
		this.character = character;
		this.frequency = frequency;
		this.bin = bin;
	}
}
