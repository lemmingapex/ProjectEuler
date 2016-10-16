# [Even Fibonacci numbers](http://projecteuler.net/problem=2)
Scott Wiedemann

04/28/2013

## Compile It
cd ./src  
make


## Run It
./evenfib


## Thoughts

![table](lib/table.png)

Notice that every third number in the Fibonacci sequence is even.

The fibonacci number at index n can be found using the closed form:

![closed form](lib/closed_form.png)

The index of a fibonacci number (reverse lookup) can be found using:

![index](lib/index.png)

where

![golden ratio](lib/golden_ratio.png)

The closed form for the general summation of the fibonacci sequence is given by:

![summation formula](lib/summation_formula.png)

where lucas number, L, is

![Lucas number](lib/Lucas_number.png)

and generator, g, is

![generator](lib/generator.png)

Consider the summation where:

a = 3

b = 0

x = 1

n = _F_to_i(3999999)/3_

By using the above equations, a closed form can easily be written describing the sum of every third number--every even number--in the sequence up to n.

## Run-time
O(1)
