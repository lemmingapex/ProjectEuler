[Lexicographic permutations](http://projecteuler.net/problem=24)
====================
Scott Wiedemann

09/25/2016

Compile It
----------
ant


Run It
------
./target/lexicographicpermutations.jar

Thoughts
--------
Used the idea of percentage indexing.  The first 10th of the numbers start with 0.  Second 10th with 1, etc.  So if 1000000 is 1000000/10! ~ 0.27557319224, then the first digit should be a 2.  You can continue this idea to drill down into the digits.  The floating point math becomes a little nasty, but gives the correct answer in this case.  Should have used an arbitrary precision library.

Run-time
--------
Unknown.
