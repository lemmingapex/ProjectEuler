[Smallest multiple](http://projecteuler.net/problem=5)
====================
Scott Wiedemann

05/19/2013

Compile It
----------
ant


Run It
------
./target/smallestmultiple.jar N


Thoughts
--------
Reduce the problem to finding the least common multipule of a set: http://en.wikipedia.org/wiki/Least_common_multiple#A_method_using_a_table  Program generates a set from 1 to N.  Finds the lcm of the set.  Was unable to use random access during iteration, so run-time is not 'fantastic'.  Max N is 40.


Run-time
--------
Unknown
