# [Counting fractions in a range](http://projecteuler.net/problem=73)
Scott Wiedemann

01/31/2017

## Compile It
ant

## Run It
java -jar ./target/countingfractionsinarange.jar

## Thoughts
My approach here is similar to my approach in Problem 71.  For the each denominator, d, from 5 to 12000, I find the first fraction to the left of 1/2 with denominator d, and the first fraction to the right of 1/3 with denominator d.  You can then examine each fraction with with denominator d, between 1/2 and 1/3.  You can then count only the fractions, in the form n/d, that are already in there most reduced form (where gcd(n,d) == 1) and ignore all the others.  

There are much fancy ways to solve this problem using a Farey sequence and the properties of mediants.  This is far more complex.

## Run-time
Unknown.
