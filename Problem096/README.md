# [Su Doku](http://projecteuler.net/problem=96)
Scott Wiedemann

10/15/2016

## Compile It
ant

## Run It
./target/sudoku.jar ./input/p096_sudoku.txt

## Thoughts

A little background:
Sudoku is a puzzle played on a partially filled 9x9 grid. The task is to complete the assignment using numbers from 1 to 9 such that the entries in each row, each column and each major 3x3 block are pairwise different. [1]

Two ideas come to mind:
1) A backtracking approach.  The ideas here is start assigning values to empty squares in an enumerated sudoku grid.  While the puzzle is valid and not solved, continue assigning values.  During the process of assigning values, if there there is no legal assignment for a square (the puzzle is in some invalid state), then 'backtrack' to the previous square and continue the process.  Although a fairly brute force approach, this method guarantees that a solution will be found.

1) Constraint methods.  Each value, 1-9, in each row column and block must appear only once.  Possible values can be assigned to each box based on this idea.  If the number of possible values for a square is one, then that value must be the value for the square.  Similarly, if a possible value appears only once in it's row, it's column, or it's block, then that value must be the value for the square.  A puzzle can likely not be solved using these 2 methods alone, but these methods will reduce the search space significantly.  Backtracking, or other search methods can then be used to find a solution.

[1] H. Simonis. Sudoku as a constraint problem. In B. Hnich, P. Prosser, and B. Smith,
editors, Proceedings of the 4th International Workshop on Modelling and Reformulating
Constraint Satisfaction Problems, pages 13–27, September 2005

## Run-time
Unknown.
