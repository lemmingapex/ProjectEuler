# [Monopoly odds](http://projecteuler.net/problem=84)
Scott Wiedemann

02/19/2017

## Compile It
ant

## Run It
java -jar ./target/monopolyodds.jar N

N is 4 in this case.

## Thoughts
I modeled the board and basic game mechanics utilizing the computer's PRNG.  Simulate 10000000 rolls of one player, and keep track of which squares they end on, and then normalize the distribution.  Tweaked the model a bit to get it accurate to 4 decimal places.  Interesting to see what happens at other values of N as well.  I am not sure how a very rigorous statistically analysis would be preformed.

## Run-time
Unknown.
