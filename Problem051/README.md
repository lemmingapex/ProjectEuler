# [Prime digit replacements](http://projecteuler.net/problem=51)
Scott Wiedemann

11/17/2016

## Compile It
ant


## Run It
./target/primedigitreplacements.jar

## Thoughts
I think this is the hardest problem thus far.  I had to to a bit of research to figure out how to reduce the search space effectively.  I made some assumptions as well.  I assumed that the number was 5 digits, when the program didn't find a solution, I assumed the number was 6 digits.  Without some type of similar confinement, the general problem appears to be intractable.

First, assume the number of digits for every member in the family is exactly 6.  Every number in the group must be prime and therefore must end with 1,3,7 or 9 (not with 0,2,4,5,6 or 8).  So the least significant digit of every member is 1,3,7 or 9.

Second, the number of digits that can be replaced can be limited based on the size of the family.  Let me explain.  A prime replacement family size of 8 or more is required.  This means that 8 of the 10 numbers in a non-prime replacement family must be prime.

Consider what a family might look like when you replace four digits.  Let the family below have the form XYYYYL, where X is arbitrarily between 1 and 9, Y is the digit we are replacing (0-9), and L is the least significant digit {1,3,7,9}.

X0000L  
X1111L  
X2222L  
X3333L  
X4444L  
X5555L  
X6666L  
X7777L  
X8888L  
X9999L  

The key insight here is to consider when a member is divisible by 3 (n%3==0).  Remember that X+L is a constant for the above family of 10 numbers.

For example, let:  
X = 4  
L = 2  
X + L = 6  

then:  

400002%3 == 0  
411112%3 == 1  
422222%3 == 2  
433332%3 == 0  
455552%3 == 2  
444442%3 == 1  
466662%3 == 0  
477772%3 == 1  
488882%3 == 2  
499992%3 == 0  

Notice that 4 of the 10 members are divisible by 3.  If instead: X + L = 7, 4 of the 10 members would still be divisible by 3. (This would simply cause a rotation of the result.)  This means that 4 of the 10 members are always divisible by 3 regardless of what X+L is.  Because 4 of the members are divisible by 3, 4 of the 10 numbers in the family are not prime.  The family could therefore be at most of size 6.  (Remember that the prime family of interest must be of size 8 or more.)

The above example was when there were 4 Y digits in the members. But 4 of the 10 members are always divisible by 3 when there are 1, 2, 4, or 5 Y digits in the members.  This does not occur when there are 3 Y digits.  If there are 3 Y digits, then either the entire family is divisible by 3 (when (X + L) % 3 == 0), or the entire family is not divisible by 3 (when (X + L) % 3 != 0).  (See [this](https://en.wikipedia.org/wiki/Divisibility_rule#Divisibility_by_3_or_9) or [this](http://math.stackexchange.com/questions/341202/how-to-prove-the-divisibility-rule-for-3/341213#341213) for more information on the 3 divisibility rule.)  We therefore only need to consider numbers where only 3 digits are replaced.

Finally, there are a few more details to keep in order when considering the permutations of 3 digits in 5 spots, which adds a few more loops to the code.

## Run-time
Unknown, but fairly fast despite 7 loops.
