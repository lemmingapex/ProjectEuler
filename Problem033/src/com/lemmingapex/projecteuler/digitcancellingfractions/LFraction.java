package com.lemmingapex.projecteuler.digitcancellingfractions;

public class LFraction {

	private long numerator;
	private long denominator;

	public LFraction(long numerator, long denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public LFraction(LFraction lFraction) {
		this.numerator = lFraction.numerator;
		this.denominator = lFraction.denominator;
	}

	public long getNumerator() {
		return this.numerator;
	}

	public long getDenominator() {
		return this.denominator;
	}

	static public long gcd(long a, long b) {
		long t = a;
		if(a < b) {
			a = b;
			b = t;
		}
		while(b != 0) {
			t = b;
			b = a % b;
			a = t;
		}
		return a;
	}

	public LFraction reduce() {
		long gcd = LFraction.gcd(this.getNumerator(), this.getDenominator());
		return new LFraction(this.getNumerator()/gcd, this.getDenominator()/gcd);
	}

	@Override
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		if(!(other instanceof LFraction)) {
			return false;
		}
		LFraction thisReduced = this.reduce();
		LFraction otherReduced = ((LFraction)other).reduce();
		if((thisReduced.getNumerator() == otherReduced.getNumerator()) && (thisReduced.getDenominator() == otherReduced.getDenominator())) {
			return true;
		}
		return false;
	}
}
