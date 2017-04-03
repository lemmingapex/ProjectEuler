# [Product-sum numbers](http://projecteuler.net/problem=88)
Scott Wiedemann

03/31/2017

## Compile It
ant

## Run It
java -jar ./target/productsumnumbers.jar k

k is 12000 in this case.

## Thoughts
There was so much intimidating discussion centered around this problem.  All the forums saying how difficult 88 was.  I actually had very little trouble with this problem, and thought it was straight forward.

First thing I noticed was that N≥k.  The minimum value for a element in the product-sum is one.  If the sum contains only the minimum value (all ones), then N will equal k.  Given a k, start searching for a matching N at N=k.  Second thing to notice is that the product must be a factorization of N.  It is trivial to account for the ones in the product.  The key idea in this problem is to quickly generate all the possible factorizations of a number.  Because we are dealing with numbers less than 12000, there are straight forward ways to generate the prime factorization of N.  Once I had the prime factorization, I used a straight forward recursive method to generate all the unique factorization of N.  The same value of N will be examined when looking at different k.  In order to avoid repeated calculations, and to speed up the search, I used a simple cache to lookup the unique factorization of N.  Finally, check the sum of each factorization, accounting for the ones, and see if the sum equals N.  If so, add N to a set whose elements will sum to the solution.

## Run-time
Less than 2 seconds; faster than I expected.
