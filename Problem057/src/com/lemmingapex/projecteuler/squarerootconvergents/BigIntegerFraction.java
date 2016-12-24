package com.lemmingapex.projecteuler.squarerootconvergents;

import java.math.BigInteger;

public class BigIntegerFraction {

	private BigInteger numerator;
	private BigInteger denominator;

	public BigIntegerFraction(BigInteger numerator, BigInteger denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public BigIntegerFraction(BigIntegerFraction bigIntegerFraction) {
		this.numerator = bigIntegerFraction.numerator;
		this.denominator = bigIntegerFraction.denominator;
	}

	public BigInteger getNumerator() {
		return this.numerator;
	}

	public BigInteger getDenominator() {
		return this.denominator;
	}

	public BigIntegerFraction add(BigIntegerFraction bigIntegerFraction) {
		return new BigIntegerFraction(this.getNumerator().multiply(bigIntegerFraction.getDenominator()).add(bigIntegerFraction.getNumerator().multiply(this.getDenominator())), (this.getDenominator().multiply(bigIntegerFraction.getDenominator()))).reduce();
	}

	public BigIntegerFraction invert() {
		return new BigIntegerFraction(this.getDenominator(), this.getNumerator()).reduce();
	}

	public BigIntegerFraction reduce() {
		BigInteger gcd = this.getNumerator().gcd(this.getDenominator());
		return new BigIntegerFraction(this.getNumerator().divide(gcd), this.getDenominator().divide(gcd));
	}

	@Override
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		if(!(other instanceof BigIntegerFraction)) {
			return false;
		}
		BigIntegerFraction thisReduced = this.reduce();
		BigIntegerFraction otherReduced = ((BigIntegerFraction)other).reduce();
		if((thisReduced.getNumerator().equals(otherReduced.getNumerator())) && (thisReduced.getDenominator().equals(otherReduced.getDenominator()))) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return this.getNumerator().toString() + "/" + this.getDenominator().toString();
	}
}
