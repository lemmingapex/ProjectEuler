# [Reciprocal cycles](http://projecteuler.net/problem=26)
Scott Wiedemann

10/03/2016

## Compile It
ant


## Run It
./target/reciprocalcycles.jar 1000

## Thoughts
Multiply the fraction by 10, note the quotient, the remainder, and the position where the remainder occurs in the decimal.  Let the new fraction be the remainder and continue this process until there is no remainder, or the same remainder has been encountered before.

## Run-time
Unknown.
