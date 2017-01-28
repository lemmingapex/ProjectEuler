# [Totient maximum](http://projecteuler.net/problem=69)
Scott Wiedemann

01/24/2017

## Compile It
ant

## Run It
java -jar ./target/totientmaximum.jar

## Thoughts
https://en.wikipedia.org/wiki/Euler's_totient_function#Euler.27s_product_formula

Gives Euler's product formula for the totient function.  Maximizing n/φ(n) therefore means maximizing:

n/φ(n) = Π(p/(p-1))

To maximize this function is to find the number below the limit of 1000000 that has the most small primes (large ratios of p/(p-1)).

## Run-time
Unknown.
