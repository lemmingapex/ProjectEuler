# [Prime pair sets](http://projecteuler.net/problem=60)
Scott Wiedemann

01/03/2017

## Compile It
ant

## Run It
java -jar ./target/primepairsets.jar

## Thoughts
A prime of interest contains one or more digits.  Every concatenation is therefore guaranteed to have at least 2 digits.  The least significant digit for every prime of interest can not be 0, 2, 4, 5, 6, or 8; otherwise a concatenation would be composite (by 2 or 5).  Therefore the least significant digit for every prime of interest must be 1, 3, 7, or 9.  The first five primes of interest are therefore 1, 3, 7, 9, and 11.

I did not use this fact, because the search space was already reduced:
All primes are odd except for 2.  2 is not a prime of interest for the reasons above.  The sum of 5 odd numbers is an odd number.  Therefore the sum of an odd number of primes of interest must be odd.

## Run-time
Unknown.
