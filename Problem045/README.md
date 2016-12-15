# [Triangular, pentagonal, and hexagonal](http://projecteuler.net/problem=45)
Scott Wiedemann

11/05/2016

## Compile It
ant


## Run It
java -jar ./target/triangularpentagonalhexagonal.jar

## Thoughts
Nothing fancy here.

loop over t and check if p and h are whole numbers:
p = (1 + √(1+12(t² + t)))/6
h = (1 + √(1-4(p - 3p²)))/4

## Run-time
O(n)
