# [Counting rectangles](http://projecteuler.net/problem=85)
Scott Wiedemann

02/23/2017

## Compile It
ant

## Run It
java -jar ./target/countingrectangles.jar C

C is 2000000 in this case.

## Thoughts

First, notice that a m x n grid will have the same number of rectangles as a n x m grid.  Without lose of generality, it is therefore only necessary to look at grids where m <= n.

Consider a 3 x 5 (m=3, n=5) grid:

```
+-+-+-+-+-+
| | | | | |
+-+-+-+-+-+
| | | | | |
+-+-+-+-+-+
| | | | | |
+-+-+-+-+-+
```

Start by forming rectangles in the first row; the smallest rectangles to largest rectangles.

```
   ***-+-+-+-+   +-***-+-+-+   +-+-***-+-+   +-+-+-***-+   +-+-+-+-***
   * * | | | |   | * * | | |   | | * * | |   | | | * * |   | | | | * *
   ***-+-+-+-+   +-***-+-+-+   +-+-***-+-+   +-+-+-***-+   +-+-+-+-***
5  | | | | | |   | | | | | |   | | | | | |   | | | | | |   | | | | | |
   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
   | | | | | |   | | | | | |   | | | | | |   | | | | | |   | | | | | |
   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
+
   *****-+-+-+   +-*****-+-+   +-+-*****-+   +-+-+-*****
   *   * | | |   | *   * | |   | | *   * |   | | | *   *
   *****-+-+-+   +-*****-+-+   +-+-*****-+   +-+-+-*****
4  | | | | | |   | | | | | |   | | | | | |   | | | | | |
   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
   | | | | | |   | | | | | |   | | | | | |   | | | | | |
   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
+
   *******-+-+   +-*******-+   +-+-*******
   *     * | |   | *     * |   | | *     *
   *******-+-+   +-*******-+   +-+-*******
3  | | | | | |   | | | | | |   | | | | | |
   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
   | | | | | |   | | | | | |   | | | | | |
   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
+
   *********-+   +-*********
   *       * |   | *       *
   *********-+   +-*********
2  | | | | | |   | | | | | |
   +-+-+-+-+-+   +-+-+-+-+-+
   | | | | | |   | | | | | |
   +-+-+-+-+-+   +-+-+-+-+-+
+
   ***********
   *         *
   ***********
1  | | | | | |
   +-+-+-+-+-+
   | | | | | |
   +-+-+-+-+-+
-
15
```

So there are 15 ways to form rectangles, or n(n+1)/2 ways.  You can repeat this process with the other two remaining rows.  For a total of 3*15 rectangles with height 1.

Now repeat this process with rectangles of height two instead of one:

```
   ***-+-+-+-+   +-***-+-+-+   +-+-***-+-+   +-+-+-***-+   +-+-+-+-***
   * * | | | |   | * * | | |   | | * * | |   | | | * * |   | | | | * *
   * *-+-+-+-+   +-* *-+-+-+   +-+-* *-+-+   +-+-+-* *-+   +-+-+-+-* *
5  * * | | | |   | * * | | |   | | * * | |   | | | * * |   | | | | * *
   ***-+-+-+-+   +-***-+-+-+   +-+-***-+-+   +-+-+-***-+   +-+-+-+-***
   | | | | | |   | | | | | |   | | | | | |   | | | | | |   | | | | | |
   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
+
   *****-+-+-+   +-*****-+-+   +-+-*****-+   +-+-+-*****
   *   * | | |   | *   * | |   | | *   * |   | | | *   *
   *   *-+-+-+   +-*   *-+-+   +-+-*   *-+   +-+-+-*   *
4  *   * | | |   | *   * | |   | | *   * |   | | | *   *
   *****-+-+-+   +-*****-+-+   +-+-*****-+   +-+-+-*****
   | | | | | |   | | | | | |   | | | | | |   | | | | | |
   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
+
   *******-+-+   +-*******-+   +-+-*******
   *     * | |   | *     * |   | | *     *
   *     *-+-+   +-*     *-+   +-+-*     *
3  *     * | |   | *     * |   | | *     *
   *******-+-+   +-*******-+   +-+-*******
   | | | | | |   | | | | | |   | | | | | |
   +-+-+-+-+-+   +-+-+-+-+-+   +-+-+-+-+-+
+
   *********-+   +-*********
   *       * |   | *       *
   *       *-+   +-*       *
2  ********* |   | *********
   +-+-+-+-+-+   +-+-+-+-+-+
   | | | | | |   | | | | | |
   +-+-+-+-+-+   +-+-+-+-+-+
+
   ***********
   *         *
   *         *
1  *         *
   ***********
   | | | | | |
   +-+-+-+-+-+
-

```

So again there are 15 ways to form rectangles, or n(n+1)/2 ways.  But you can now only repeat this process with the other one row.  For a total of 2*15 rectangles with height 2.

Repeating this process with rectangles of height three gives 1*15 rectangles.

The total number of rectangles is therefore:
```
R = 3*15 + 2*15 + 1*15
R = (3 + 2 + 1) * 15
R = m(m+1)/2 * n(n+1)/2
R = m*n*(m+1)(n+1)/4
```
So you could now search through m and n until you found values of R close to 2000000.  As to minimize the error, ϵ = R-C.

However, you can easily rearrange the equation; explicitly solving for m.
```
m = √(4*C/(n²+n) + 1/4) - 1/2
```
The negative root can be disregarded.  For general integer values of C, m will likely not take an integer value.  A close solution will take one of the integers bordering m however.  We can then redefine the optimization problem as the error in the decimal part of m:
ϵ = m%1 or ϵ = 1 - m%1.  This formulation runs much faster, because the naive search space need only consider n, instead of m and n.

Consider an upperbound on n:
```
R = m*n*(m+1)(n+1)/4
```
n is large when m is small.  m is bounded below by 1, so the largest n is:
```
R = n*(n+1)/2
n = √(2*C + 1/4) - 1/2
```
The negative root can be disregarded.

The search can sped up by starting at the n=upperbound and breaking when m>n. (Recall the redundancy stated above. m<=n.)

## Run-time
Unknown.
