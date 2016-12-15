# [Pandigital products](http://projecteuler.net/problem=32)
Scott Wiedemann

10/10/2016

## Compile It
ant


## Run It
java -jar ./target/pandigitalproducts.jar

## Thoughts
Observations:  
1) The product should be larger than the multiplicand and the multiplier.  Multiplication with positive integers makes a number larger.  Therefore, the product should contain as many digits as the multiplicand and the product should contain as many digits as the multiplier.  

2) Also observe that the number of digits in the product can be at most the sum of the number of digits in the multiplicand and the multiplier. 999*999= 998001 for example.  

XXXXXX * Y = ZZ is not valid by observation 1  
XXXXX * Y = ZZZ is not valid by observation 1  
XXXX * Y = ZZZZ is valid  
XXX * Y = ZZZZZ is not valid by observation 2  
XXX * YY = ZZZZ is valid  
Z is four digits. Z is therefore bounded between the largest 4 digit number that can be made with the digits 1 to 9: 9876, and the smallest 4 digit number that can be made with the digits 1 to 9: 1234

Note that the last digit of the product can not be 5.  All multiples of 5 end in 0 or 5.  0 is not allowed and 5 would be a repeated digit.

Note that the last digit of the multiplier can not be 1.  If it were, then the last digit of the product would be the same as the multiplicand.  And by symmetry the last digit of the multiplicand can not be 1.  
XY1*ABC = IJC  

## Run-time
Unknown
