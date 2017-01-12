# [Cyclical figurate numbers](http://projecteuler.net/problem=61)
Scott Wiedemann

01/10/2017

## Compile It
ant

## Run It
java -jar ./target/cyclicalfiguratenumbers.jar

## Thoughts
I solved this using a graph like data structure.

First, I generated a list of the polygonal numbers of length four and degree 3 to 8.  By creating a map (prefix to numbers), I was I able to create a directed graph structure of the polygonal numbers of interest, where nodes of the graph are numbers, and edges are places where the suffix of one node matches the prefix of the other node.  You can then perform depth first searches on the graph observing the property that each figurate number type must be represented only once in the ordered set.  I choose to start the graph searches using a root that was a octagonal number.  This is because there are the fewest 4 digit octagonal numbers compared to the other number types.  See the comments in the code for more details.

## Run-time
Unknown, but quite good compared to a native approach.
