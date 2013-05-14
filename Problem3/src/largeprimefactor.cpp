// Scott Wiedemann
// 05/13/2013
// largeprimefactor.cpp
// Largest prime factor

#include <stdio.h>
#include <stdlib.h> 
#include <math.h>

using namespace std;

int main(int argc, char **argv) {
	if (argc!=2) {
		printf("Usage: %s [long to factor]\n", argv[0]);
		return 1;
	}

	long toFactor = atol(argv[1]);
	long k = 2;
	while(k < toFactor) {
		if(toFactor % k == 0) {
			toFactor = toFactor / k--;
		}
		k++;
	}
	printf("Largest prime: %ld\n", toFactor);
	return 0;
}
