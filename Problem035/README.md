[Circular primes](http://projecteuler.net/problem=35)
====================
Scott Wiedemann

10/13/2016

Compile It
----------
ant


Run It
------
./target/circularprimes.jar N

Finds circular primes up to 10^N. N is 6 in this case, so up to 10^6 = 1000000.


Thoughts
--------
Create a set of primes using Sieve of Eratosthenes to 10^N.  Loop over the primes greater than 5 and first check that it does not contain 0,2,4,5,6,8.  (If it did, a rotation would certainly not be prime.)  Then check each rotation and make sure it does not exist in the set of primes.  Runs under a second.

Run-time
--------
Unknown.
