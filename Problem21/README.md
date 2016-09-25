[Amicable numbers](http://projecteuler.net/problem=21)
====================
Scott Wiedemann

09/24/2016

Compile It
----------
ant


Run It
------
./target/amicablenumbers.jar N

N is 10000 in this case.

Thoughts
--------
Nothing wild here.  I used a map to store proper divisor sum keys to generator values.

Run-time
--------
O(H_n^(-1/2)) where H_n is the generalized harmonic number at n.
