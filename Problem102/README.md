# [Triangle containment](http://projecteuler.net/problem=102)
Scott Wiedemann

09/13/2017

## Compile It
make

## Run It
./trianglecontainment p102_triangles.txt

## Thoughts
To check if origin is inside triangle, look at vectors between the origin and the vertices of the triangle.  If the cross product of these vectors with each other all point in the same direction, then the origin is within the triangle. (This assumes that the winding number of a point within the triangle of the piecewise curve formed by the perimeter of the triangle (v1, v2, v3) is 1 and not -1.  If the winding number is -1 -- if the points are oriented clockwise from the point within the triangle -- then the second and third vertices should be switched to form triangle (v1,v3,v2) with winding number 1.)  In order to check if the cross products point in the same direction, you can take the dot product of the cross products.  The dot product should always be positive if the vectors point in the same direction.

## Run-time
O(N) where N is the number of triangles in p102_triangles.txt.
