[Largest product in a grid](http://projecteuler.net/problem=11)
====================
Scott Wiedemann

08/24/2013

Run It
------
ruby ./src/lpg.rb 4

Thoughts
--------
Used a brute force search.

Run-time
--------
(N*N-L)*L*4

Where:
N is the size of the grid.  (20 in this case)
L is the length of the product to be found. (4 in this case)
