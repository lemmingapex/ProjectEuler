# [Counting rectangles](http://projecteuler.net/problem=85)
Scott Wiedemann

02/23/2017

## Compile It
ant

## Run It
java -jar ./target/countingrectangles.jar N

N is 2000000 in this case.

## Thoughts

Consider a 3 x 5 (m x n) grid:

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
***-+-+-+-+
* * | | | |
***-+-+-+-+
| | | | | |
+-+-+-+-+-+
| | | | | |
+-+-+-+-+-+

+-***-+-+-+
| * * | | |
+-***-+-+-+
| | | | | |
+-+-+-+-+-+
| | | | | |
+-+-+-+-+-+

+-+-***-+-+
| | * * | |
+-+-***-+-+
| | | | | |
+-+-+-+-+-+
| | | | | |
+-+-+-+-+-+

+-+-+-***-+
| | | * * |
+-+-+-***-+
| | | | | |
+-+-+-+-+-+
| | | | | |
+-+-+-+-+-+

+-+-+-+-***
| | | | * *
+-+-+-+-***
| | | | | |
+-+-+-+-+-+
| | | | | |
+-+-+-+-+-+
```

## Run-time
Unknown.
