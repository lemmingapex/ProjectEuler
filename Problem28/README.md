[Number spiral diagonals](http://projecteuler.net/problem=28)
====================
Scott Wiedemann

10/05/2016

Compile It
----------
ant


Run It
------
./target/numberspiraldiagonals.jar 1001

Thoughts
--------

Here is a 9x9 grid:

73 74 75 76 77 78 79 80 81
72 43 44 45 46 47 48 49 50
71 42 21 22 23 24 25 26 51
70 41 20  7  8  9 10 27 52
69 40 19  6  1  2 11 28 53
68 39 18  5  4  3 12 29 54
67 38 17 16 15 14 13 30 55
66 37 36 35 34 33 32 31 56
65 64 63 62 61 60 59 58 57

Notice that a square grid formed by the spiral will have an odd number of values along one edge.  Then recognize that an mxm grid should have m^2 entries. (A 9x9 grid will have 9*9 = 81 entries.)

Consider the corner numbers for a certain grid size.  For example the corner numbers for a grid of size 7x7 are 31, 37, 43 and 49.  These corner numbers are evenly spaced as well (6 apart in this case).  The sum of these numbers is also the sum of the average of these numbers.

31 + 37 + 43 + 49 = 160
40 + 40 + 40 + 40 = 160
            4(40) = 160



Run-time
--------
O(1)
