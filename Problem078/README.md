# [Coin partitions](http://projecteuler.net/problem=78)
Scott Wiedemann

02/08/2017

## Compile It
ant


## Run It
java -jar ./target/coinpartitions.jar N

N is 1000000.

## Thoughts
Similar problem to problem 76.  Had to look up a much more efficient method to generating the partitions.  See https://en.wikipedia.org/wiki/Partition_(number_theory)#Generating_function.  See the code for more details.

Used a mod to only keep track of the last log10(N) digits in order to avoid huge numbers.

## Run-time
Unknown.
