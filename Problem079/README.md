# [Passcode derivation](http://projecteuler.net/problem=79)
Scott Wiedemann

02/15/2015

## Compile It
ant


## Run It
java -jar ./target/passcodederivation.jar p079_keylog.txt


## Thoughts
Look for a common character that has no prefix.  Remove this character from the keys and add it to the password.

## Run-time
O(n*d) where n is the number of keys in the keylog and d is the mean number of digits in a key.
