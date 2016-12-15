# [Lattice paths](http://projecteuler.net/problem=15)
Scott Wiedemann

07/14/2013

## Compile It
ant


## Run It
java -jar ./target/latticepaths.jar N

N is the size of the grid.

## Thoughts
Consider the moves in a possible route starting in the upper left and ending in the lower right.  A move is either _move right_ or _move down_.  For and N by N grid, a total of 2N moves are in a route, of which N are _move down_ and N are _move right_.  A possible route in a three by three grid might consist of the following moves: _move right_, _move down_, _move down_, _move right_, _move right_, _move down_.  As described, this route is of length six and contains three _move right_ and three _move down_.  If we denote _move right_ as R and _move down_ as D, the route could be written as such: RDDRRD.  Disregard each D in the sequence to obtain the following: *R__RR_*.  It is now easy to see that the number of routes can easily be described as a combination.  There are six spots, and three must be filled with Rs.  Once Rs are chosen, Ds fill the remaining spots.  6 choose 3 possibilities in this case.

BigInteger was used in the implementation to the handle large factorials.

## Run-time
O(1)
