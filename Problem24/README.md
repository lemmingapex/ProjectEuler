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
The first 10th of the lexicographic permutations start with 0.  Second 10th with 1, etc.  So where does 1000000 fall in the series?  Well there are 10! options for the most significant digit; so the 1000000 position is 1000000/10! (~0.27557319224) percent into the lexicographic sequence.  The first digit should therefore be a 2.  You can continue this idea to calculate the remaining digits.  You can not use floating point arithmetic or data types, as the precision and error would be unreliable and unmanageable.  Arbitrary-precision integers are therefore used to store numerators and denominators of the fractions.  Basic arithmetic and modulo arithmetic are then used to evaluate all the percent indexing.

Run-time
--------
O(10)
