# [Prime power triples](http://projecteuler.net/problem=87)
Scott Wiedemann

03/13/2017

## Compile It
ant

## Run It
java -jar ./target/primepowertriples.jar N

N is 50000000 in this case.

## Thoughts
n = x^2 + y^3 + z^4

Generate primes using Sieve of Eratosthenes.  x is bounded from above by √N.  Loop over z, y, x and count the unique number of sums.  See code for details.

## Run-time
O(N^(13/12))
