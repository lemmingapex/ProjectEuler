# [Cuboid route](http://projecteuler.net/problem=86)
Scott Wiedemann

03/06/2017

## Compile It
ant

## Run It
java -jar ./target/cuboidroute.jar M

M is 1000000 in this case.

## Thoughts
Let the dimensions of the cube be i,j,k.  In order to incorporate the restriction that rotations are ignored, add the constraint: i≥j≥k.  The idea here is that you really only need 2 dimensions to describe the problem.  The path of the spider lives on the plane of the cube that has been unfolded.  After drawing paths on unfolded cubes, you notice that the triangle whose hypotenuse forms the shortest path has legs, l1 l2, where l1 = max(i,j,k) and l2 = i+j+k-l1.  Because of the constraint: i≥j≥k, l1 = i and l2 = j+k.  The largest l2 can be is 2i (the case where j=k and i=k).  Simply loop over l1 and l2 and use a trivial method to determine if the hypotenuse is an integer.  If it is, then increment the count appropriately.  If l2 ≤ l1, then there are ⌊l2/2⌋ ways to divide l2 such that i≥j≥k. If l2 > l1 then there are l1 - ⌈l2/2⌉ + 1 ways to divide l2 such that i≥j≥k.

## Run-time
Unknown.
