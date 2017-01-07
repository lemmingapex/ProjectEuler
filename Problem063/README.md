# [Powerful digit counts](http://projecteuler.net/problem=63)
Scott Wiedemann

01/06/2017

## Compile It
ant

## Run It
java -jar ./target/powerfuldigitcounts.jar

## Thoughts
Consider numbers of the form:

y = x^n

Although not explicitly stated, it is assumed that x ∈ {0,1,2,3,4,5,6,7,8,9}.

Therefore:

10^n-1 ≤ x^n < 10^n

You can use the above to find an upper limit on n given x:

10^n-1 ≤ x^n
(n-1) * log(10) ≤ n * log(x)
n-1 ≤ n * log(x)
n - n * log(x) ≤ 1
n * (1 - log(x)) ≤ 1
n ≤ 1/(1 - log(x))

and because n must be an integer:

n ≤ ⌊1/(1 - log(x))⌋

n must also be greater than 0:

0 < n

Now consider what 9^n looks like:

x^n = 9^1 = 9
x^n = 9^2 = 81
x^n = 9^3 = 729
x^n = 9^4 = 6561
x^n = 9^5 = 59049
x^n = 9^6 = 531441
x^n = 9^7 = 4782969
x^n = 9^8 = 43046721
x^n = 9^9 = 387420489
x^n = 9^10 = 3486784401
x^n = 9^11 = 31381059609
x^n = 9^12 = 282429536481
x^n = 9^13 = 2541865828329
x^n = 9^14 = 22876792454961
x^n = 9^15 = 205891132094649
x^n = 9^16 = 1853020188851841
x^n = 9^17 = 16677181699666569
x^n = 9^18 = 150094635296999121
x^n = 9^19 = 1350851717672992089
x^n = 9^20 = 12157665459056928801
x^n = 9^21 = 109418989131512359209

n = 21 is the upper bound, but also the number of values where length(x^n) == n.  Dwell on this fact for a bit.

## Run-time
Unknown.
