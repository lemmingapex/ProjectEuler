# [Monopoly odds](http://projecteuler.net/problem=84)
Scott Wiedemann

02/19/2017

## Compile It
ant

## Run It
java -jar ./target/monopolyodds.jar N

N is 4 in this case.

## Thoughts
I modeled the board and basic game mechanics utilizing the computer's PRNG.  Let a player take 10000 rolls, and keep track of which squares they end on, and then normalize the distribution.  I am not sure how a rigorous statistically analysis would be preformed.

## Run-time
Unknown.
