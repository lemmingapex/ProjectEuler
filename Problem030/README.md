# [Digit fifth powers](http://projecteuler.net/problem=30)
Scott Wiedemann

10/08/2016

# Compile It
ant


# Run It
./target/digitfifthpowers.jar 5

# Thoughts

Let's first consider an upper bound.  If each digit can contribute at most 9^5 values, then how many digits can x numbers consume?  Somewhere around:

x*9^N = 10^x

When N is 5, x is about 5.5. So the largest number we need to check is around 10^6.  We can use that as an upper bound.  So 0 - 10^6.

Second, notice that the order of the sum in not important.

1634 = 1^4 + 6^4 + 3^4 + 4^4  
same as  
1634 = 1^4 + 3^4 + 4^4 + 6^4

So checking all the combinations of the digits 1,3,4 and 6 is repetitive.  Need not consider unique permutations.  Thurs we will only check numbers that contain digits of non-decreasing value:
00  
01  
02  
03  
04  
05  
06  
07  
08  
09  
11  
12  
13  
14  
15  
16  
17  
18  
19  
22  
23  
24  
etc.  

10 is already covered by 01.  20 is covered by 02.  21 is covered by 12. etc.

So I used a recursive structure to create this sequence up to 6 digits (the upper bound).

Finally, just iterate over the non-unique permutation sequence and generate a sum of the digits, S.  If a sorted version of s matches the iterator, then S is a sum of fifth powers.

# Run-time
Unknown
