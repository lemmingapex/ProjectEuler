# [Counting summations](http://projecteuler.net/problem=76)
Scott Wiedemann

02/05/2017

## Compile It
ant


## Run It
java -jar ./target/countingsummations.jar N

N is 100.

## Thoughts
Consider the number six broken down as follows:
=1+1+1+1+1+1
=2+1+1+1+1
=3+1+1+1
=4+1+1
=5+1

Then consider breaking down the remaining ones such that the leading number is always greater or equal to any new term:
=2+1+1+1+1
=2+2+1+1

=3+1+1+1
=3+2+1

=3+1+1+1
=3+3

=4+1+1
=4+2

One more time:
=2+2+1+1
=2+2+2

You are basically building a tree where the sums are in decreasing order of terms.  When you build a tree of size n+1, many of the nodes in a tree of size n will be reused.  So we can calculate a break down of a certain number of ones once in a table to avoid doing any duplicate work.

Idea here is to build a table with the number of ways to partition a certain sum.  Additional rows and columns in the table can be calculated from prior rows and columns.

| # of remaining 1s\leading number | 1   | 2   |
| -------------------------------- | ---:| ---:|
| 0                                | 1+0 | 2+0 |
| 1                                | 1+1 | 2+1  |
| 2                                | 1+1+1  | 2+1+1  |

initialize the first row to 1, then remaining rows can be calculated using prior entries.

Count for each sum can then build using prior rows and columns:

## Run-time
Unknown.
