# [Cubic permutations](http://projecteuler.net/problem=62)
Scott Wiedemann

01/04/2017

## Compile It
ant

## Run It
java -jar ./target/cubicpermutations.jar

## Thoughts
Nothing fancy here.  Started forming cubes starting at 1:  1, 8, 27, 64...  For each cube I generated a key, the numeric sorting of the cube's digits. I used this key with a hash table to store and count keys.

## Run-time
Unknown.
