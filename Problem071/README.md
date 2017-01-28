# [Ordered fractions](http://projecteuler.net/problem=71)
Scott Wiedemann

01/27/2017

## Compile It
ant

## Run It
java -jar ./target/orderedfractions.jar

## Thoughts

Consider fractions with denominators 1000000.  There will be a lot of them: 1/1000000, 2/1000000, ... 999999/1000000.  We need not consider all of them, just the one closest and less than 3/7.  ⌊(3/7) * 1000000⌋/1000000.  So my idea here is to look at only the closest fractions less than 3/7 with different denominators.  You expect that the solution have a large denominator.  Numbers with large denominators are more closely spaced together than those with small denominators.  So as you are checking values, if the spacing between numbers exceeds that of the current difference, you can limit the search.

## Run-time
Unknown.
