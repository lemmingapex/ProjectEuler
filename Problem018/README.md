# [Maximum path sum I](http://projecteuler.net/problem=18)
Scott Wiedemann

09/21/2016

## Run It
./src/MaximumPathSum.py ./input/p018_triangle.txt

## Thoughts
Consider an entry, E, in the second to last row.  When maximizing E's path, the maximum of E's two children, E_max_child = max(E_left_child, E_right_child), in the last row will be in the path.  Add E_max_child to E, and repeat this process up the tree to the root.

## Run-time
H*(H-1) where H is the height of the tree.
