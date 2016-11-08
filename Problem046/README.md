# [Goldbach's other conjecture](http://projecteuler.net/problem=46)
Scott Wiedemann

11/07/2016

## Compile It
ant


## Run It
./target/goldbachsotherconjecture.jar

## Thoughts
o = p + 2x²

o is odd and 2x² is even, therefore p must be prime and odd: p >=3  
x must be a positive integer: x >=1  
An upper bound for x can be found when p is small:  
o = 3 + 2x²  
√((o - 3)/2) = x  

therefore:  

x <= ⌊√((o - 3)/2)⌋

## Run-time
Unknown.
