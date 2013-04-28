// Scott Wiedemann
// 04/27/2013
// evenfib.cpp
// Even Fibonacci numbers

#include <stdio.h>
#include <math.h>

using namespace std;

const double sqrt5 = sqrt(5.0);


long i_at_F(double F) {
	return log((F * sqrt5) + 0.5) / log((1.0 + sqrt5) / 2.0);
}

double F_at_i(long i) {
	return (pow((1.0 + sqrt5), i) - pow((1.0 - sqrt5), i))/(pow(2.0, i) * sqrt5);
}

double sum_evens_to_i(long i) {
	i = i/3.0;
	return (F_at_i((3 * i) + 3) + F_at_i(3 * i) - 2.0)/4.0;
}

int main() {
	int upper_bound = 3999999;
	printf("%.0f\n", sum_evens_to_i(i_at_F(upper_bound)));

	return 0;
}
