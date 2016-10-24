# [Integer right triangles](http://projecteuler.net/problem=39)
Scott Wiedemann

10/23/2016

## Compile It
ant


## Run It
./target/integerrighttriangles.jar N

N is 1000 in this case

## Thoughts
A few things to notice:  
1) a + b + c = p    (as stated by the problem)  
2) a² + b² = c²     (right triangle pythagorean theorem as stated by the problem)  
3) a < b < c        (without loss of generality)  
4) a + b > c        (by the triangle inequality)  

From [1] and [2]:  
a + b + √(a² + b²) = p  
...  
b = (½p² - ap)/(p - a)  

Notice that when p > a > ½p, b is negative, therefore a ≤ ½p.  From [3], a is also less than b.  So an upperbound on a in terms of p occurs when the triangle is isosceles, or when b = a:  
a ≤ (½p² - ap)/(p - a)  
...  
a ≤ ((2 - √2)/2)p ≈ 0.29289p  

and  

((2-√2)/2)p ≤ ½p  

Ok, so an outer loop p, from 3 to 1000, and an inner loop a, from 1 to ⌊((2-√2)/2)p⌋.

## Run-time
O(((2-√2)/2)N²) = O(N²)
