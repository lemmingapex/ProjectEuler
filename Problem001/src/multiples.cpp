// Scott Wiedemann
// 04/27/2013
// multiples.cpp
// Multiples of 3 and 5

#include <stdio.h>

using namespace std;

int sum_of_i_to_n(int i, int k) {
	int n = k/i;
	return (i*n*(n+1))/2;
}

int main() {
	int x = 3;
	int y = 5;
	int n = 999;
	int sum = sum_of_i_to_n(x,n)+sum_of_i_to_n(y,n)-sum_of_i_to_n(x*y,n);

	printf("%i\n", sum);

	return 0;
}
