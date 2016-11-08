# [Self powers](http://projecteuler.net/problem=48)
Scott Wiedemann

11/08/2016

## Compile It
ant


## Run It
./target/selfpowers.jar N M

N terms in the sum. Find the last M digits.
N is 1000 and M is 10 in this case.

## Thoughts

S = 1¹ + 2² + 3³ + ... + 1000^1000

Only the last ten digits of the series are of interest:

long m = 10^10;

S%10 = (1¹ + 2² + 3³ + ... + 1000^1000)%10
     = (1¹%m + 2²%m + 3³%m + ... + 1000^1000%m)%m
     = (1%m + 2%m * 2%m + 3%m * 3%m * 3%m + ... + 1000%m * 1000%m * ... * 1000%m)%m

## Run-time
O(N²) where N is 1000 in this case.
