# [Arranged probability](http://projecteuler.net/problem=100)
Scott Wiedemann

04/22/2017

## Compile It
ant


## Run It
java -jar ./target/arrangedprobability.jar N

N is 1000000000000 in this case.

## Thoughts

Let n represent the total number of discs.  Let b represent the number of blue discs.  And let P(BB) be the probability of taking two blue discs.  

P(BB) = (b/n)×((b-1)/(n-1))

From the problem statement we are looking for b when n > 10^12 and P(BB) = ½.

½ = (b/n)×((b-1)/(n-1))  
0 = b² - b - (n²-n)/2  
b = ½ + √(2n²-2n+1)/2  

b is explicitly in terms of n.  By varying n it is possible to check if the obtained values of b are integers.  This method is not sufficient to solve the problem. (The search space that would be have to be covered when N > 10^12 is too vast, and the numbers become so large they can not reasonably fit in standard datatypes.)  This method does provide the first few integer values of b.  By examining the beginning of this sequence, it might be possible to obtain a recursive formula for b.

1, 3, 15, 85, 493, 2871, 16731, 97513, 568345, 3312555, ...  

As a start try to find a recursive formulation using only the previous two terms:

An(n) = X1×An-1 + X2×An-2 + X3×n + X4  

By using the first few terms in the sequence calculated above, a linear system can be obtained:

A0 = 1  
A1 = 3  

A2 = 15   = X1×3   + X2×1  + X3×2 + X4×1  
A3 = 85   = X1×15  + X2×3  + X3×3 + X4×1  
A4 = 493  = X1×85  + X2×15 + X3×4 + X4×1  
A5 = 2871 = X1×493 + X2×85 + X3×5 + X4×1  

Solving the systems gives:

X1 = 6  
X2 = -1  
X3 = 0  
X4 = -2  

which give a recursive form:

An = 6×An-1 - An-2 - 2  

The recursive formulation seems to hold when calculating the next few terms.  
A6 = 6×2871 - 493 - 2    = 16731  
A7 = 6×16731 - 2871 - 2  = 97513  
A8 = 6×97513 - 16731 - 2 = 568345  

It is now possible to iterate on An (which is b) and calculate a corresponding value of n (n = ½ + √(8b²-8b+1)/2).  If n is greater than 10^12, the search is done.

## Run-time
Unknown.
