# [Truncatable primes](http://projecteuler.net/problem=37)
Scott Wiedemann

10/22/2016

## Compile It
ant


## Run It
java -jar ./target/truncatableprimes.jar

## Thoughts
Solved two different ways:
1) Create a set of primes using Sieve of Eratosthenes to 1000000.  Loop over the primes greater than 7 and first check that it does not contain 0,4,6,8 and that is does not contain 2 or 5, expect in the major digit.  (If it did, at least one truncation would not be prime.)  Then check to see if the prime is a truncatable prime.

2) Notice that the last digit has to be a 3 or a 7.  Append a digit (1,2,3,4,5,7, or 9) to the beginning of 3 and 7 in a recursive fashion.  Check to see if these generated integers are truncatable primes.


## Run-time
Unknown.
