# [Largest palindrome product](http://projecteuler.net/problem=4)
Scott Wiedemann

05/14/2013

## Compile It
cd ./src
make


## Run It
./largepalprod [number of digits]


## Thoughts
First, calculate the smallest and largest integers that can generate a possible product of length l.  (100 and 999 when l=3).  Use an exclusive decreasing double loop to find possible products.  Check if the product is a palindrome.  If so, is it the largest found so far?  During the looping process, constantly update a lower bound for the product.  Use this bound to drastically improve performance.

The palindrome almost always appears in the top 5% of the search range.  The quicker new lower bounds are found, the quicker the program will halt.  This suggests that a blocked type of loop that searches the top 5% or 10% of the range first will complete faster.  I did not add the blocked loop however.


## Run-time
Worst case O(n²) where n is the multiple range. 899, or 999-100 when l=3.  In practice, the program will run considerably faster.  Program can easily calculate product where l=7.
