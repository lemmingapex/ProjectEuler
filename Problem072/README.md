# [Counting fractions](http://projecteuler.net/problem=72)
Scott Wiedemann

01/29/2017

## Compile It
ant

## Run It
java -jar ./target/countingfractions.jar

## Thoughts

<table>
  <tr>
    <th style="text-align:center">n</th>
    <th style="text-align:center">n is prime?</th>
		<th style="text-align:center">factors</th>
    <th style="text-align:center"># of new reduced proper fractions added aka r(n)</th>
    <th style="text-align:center"># of integers coprime to n and less than n aka Euler's totient function aka φ(n)</th>
  </tr>
  <tr></tr>
  <tr>
    <td>2</td>
    <td>true</td>
    <td>1,2</td>
    <td>2</td>
    <td>2</td>
  </tr>
  <tr>
    <td>3</td>
    <td>true</td>
    <td>1,3</td>
    <td>2</td>
    <td>2</td>
  </tr>
  <tr>
    <td>4</td>
    <td>false</td>
    <td>1,2,4</td>
    <td>2</td>
    <td>2</td>
  </tr>
	<tr>
    <td>5</td>
    <td>true</td>
    <td>1,5</td>
    <td>4</td>
    <td>4</td>
  </tr>
  <tr>
    <td>6</td>
    <td>false</td>
    <td>1,2,3,6</td>
    <td>2</td>
    <td>2</td>
  </tr>
  <tr>
    <td>7</td>
    <td>true</td>
    <td>1,7</td>
    <td>6</td>
    <td>6</td>
  </tr>
  <tr>
    <td>8</td>
    <td>false</td>
    <td>1,2,4,8</td>
    <td>4</td>
    <td>4</td>
  </tr>
	<tr>
    <td>9</td>
    <td>false</td>
    <td>1,3,9</td>
    <td>6</td>
    <td>6</td>
  </tr>
  <tr>
    <td>10</td>
    <td>false</td>
    <td>1,2,5</td>
    <td>4</td>
    <td>4</td>
  </tr>
  <tr>
    <td>11</td>
    <td>true</td>
    <td>1,11</td>
    <td>10</td>
    <td>10</td>
  </tr>
  <tr>
    <td>12</td>
    <td>false</td>
    <td>1,2,3,4,6,12</td>
    <td>4</td>
    <td>4</td>
  </tr>
	<tr>
    <td>13</td>
    <td>true</td>
    <td>1,13</td>
    <td>12</td>
    <td>12</td>
  </tr>
</table>

Consider a brute force bottom up approach. Count the number of unique reduced proper fractions seen thus far where where the denominator is 2.  Add the number of unique reduced proper fractions seen thus far where where the denominator is 3.  Where the denominator is 4. etc.

First notice that the number of unique reduced proper fractions when n is prime is n-1.  When n is composite, the number of unique reduced proper fractions is r(n) = (n-1) minus the number of unique reduced proper fractions for all of n's factors.  So for example r(12) = (12-1) - r(6) - r(4) - r(3) - r(2) = 4.  Notice that r(n) is Euler's totient function.  Euler's totient function counts the positive integers up to a given integer n that are relatively prime to n.  This is the same idea as the number of unique reduced proper fractions.

So we need to find Σ φ(n) from 2 to 1000000.  Although directly doable, this would involve calculating all the distinct prime factors from 2 to 1000000 (multiple times potentially) which could take a while.  Luckily, we can use Euler's product formula to make this process faster:

φ(n) = n*Π((p-1)/p) where p|n.

Since we are interested in all φ(n) from 2 to 1000000, the values of φ(n) can be computed incrementally, much like the code used in my implementation of sieve of Eratosthenes.

## Run-time
Unknown.
