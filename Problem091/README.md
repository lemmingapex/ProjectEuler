# [Right triangles with integer coordinates](http://projecteuler.net/problem=91)
Scott Wiedemann

04/09/2017

## Compile It
ant


## Run It
java -jar ./target/righttrianglesintegercoordinates.jar N

N is 50 in this case. N=x2=y2

## Thoughts
There are x2*y2 triangles where the right angle is at the origin.  Then there are two mirror images of each of these cases, for a total of 3*x2*y2 triangles.  These are the uninteresting cases.

In the fourteen triangles formed where 0 ≤ x1, y1, x2, y2 ≤ 2, there are only two interesting cases, where the right angle is not on the boundary.  The vertex of the triangle with the right angle, v, will be somewhere within the grid.  One edge of the triangle, Eo, is formed between v and the origin.  The other edge of v, Ep, must therefore be perpendicular to Eo.  Using a simple matrix rotation, the slope of Ep can be calculated from the slope of Eo.  The slope of Ep is -Vy/Vx.  The vertex that forms the third edge of the triangle, v2, is formed with the origin and the points where Ep intersects the vertices of the grid lines.  Stepping by the reduced fraction Vx, -Vy from V will give all of these intersections with one ray of Ep.  Stepping by -Vx, Vy will give the other ray of Ep.  The number of steps can be calculated based on the range of the grid.  See the code for more details.

## Run-time
O(N²*log(N))
