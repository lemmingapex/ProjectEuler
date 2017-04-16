# [Arithmetic expressions](http://projecteuler.net/problem=93)
Scott Wiedemann

04/14/2017

## Compile It
ant

## Run It
java -jar ./target/arithmeticexpressions.jar

## Thoughts
It should first be noted explicitly that 0 <= a < b < c < d <= 9.

This one was a bit tricky.  Basic idea here is to generate all permutations of digits from 1234 to 6789, and all combinations of operations ```(***, **-, **+, -/*, etc).```  You can then take each combinations of digit permutations and operations combinations and evaluate the expression.  You can not however simply evaluate every expression from left to right, and some expressions with parentheses can not be represented.  For example: (8-2) * (5+1).  To solve this dilemma, I simply used reverse polish notation and a some stack like data structures.  (8-2) * (5+1) would become 8 2 - 5 1 + * . There are only five valid RPN schemas where 4 digits and 3 operations can be used.  I just iterated over each one of these as well.

## Run-time
Unknown.
