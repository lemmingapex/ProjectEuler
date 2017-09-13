# [Optimum polynomial](http://projecteuler.net/problem=101)
Scott Wiedemann

09/13/2017

## Compile It
./gradlew fatJar

## Run It
java -jar ./build/libs/Problem101.jar

## Thoughts
I used a newton polynomial to fit the first k terms and then found the value of the polynomial at k+1.  Thanks apache commons math.  See https://en.wikipedia.org/wiki/Newton_polynomial.

## Run-time
Unknown.
