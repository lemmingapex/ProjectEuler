# [Square root digital expansion](http://projecteuler.net/problem=80)
Scott Wiedemann

02/11/2017

## Compile It
ant


## Run It
java -jar ./target/squarerootdigitalexpansion.jar N

N is 100, for the first 100 natural numbers.

## Thoughts
Implemented Newton's method for sqrt(S) using BigDecimal.

## Run-time
O(N*P) where N is the number of natural numbers and P is the precision.
