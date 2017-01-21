# [Diophantine equation](http://projecteuler.net/problem=66)
Scott Wiedemann

01/17/2017

## Compile It
ant

## Run It
java -jar ./target/diophantineequation.jar 1000

## Thoughts

After I tried a stupid brute force solution that didn't work, I had to look this one up.  A good deal of the math here is beyond me.

https://en.wikipedia.org/wiki/Pell's_equation
https://en.wikipedia.org/wiki/Generalized_continued_fraction#Fundamental_recurrence_formulas

The solution involves the structure of the group of units of a general ring of algebraic integers for the ring Z[√D].

x² - Dy² = 1
x² - Dy² = 1
(x + y√D)(x - y√D) = 1

The algorithm looks at the numerator and denominator of the convergents of the continued fraction of √D.  The numerator and denominator of one of the convergents will give x and y respectively in the equation x² - Dy² = 1.  This has to do with the structure of the group which is beyond me.

## Run-time
Unknown.
