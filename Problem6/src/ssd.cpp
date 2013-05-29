// Scott Wiedemann
// 05/27/2013
// ssd.cpp
// Sum square difference

#include <stdio.h>
#include <stdlib.h>

using namespace std;

int main(int argc, char **argv) {
	if (argc!=2) {
		printf("Usage: %s [N]\n", argv[0]);
		return 1;
	}

	long n = atol(argv[1]);
	long sum = n*(3l*n*n*n+2l*n*n-3l*n-2l)/12l;

	printf("%ld\n", sum);
	return 0;
}
