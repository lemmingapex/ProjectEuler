// Scott Wiedemann
// 05/14/2013
// largepalprod.cpp
// Largest palindrome product

#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <cstring>
#include <sstream>

using namespace std;

bool isPalindrome(long pNumber) {
	ostringstream oss;
	oss << pNumber;
	const char* tpal = oss.str().c_str();
	int length = strlen(tpal) - 1;
	int lengthOver2 = length/2;
	for(int i=0; i<=lengthOver2; i++) {
		if(tpal[i] != tpal[length-i]) {
			return false;
		}
	}
	return true;
}

int main(int argc, char **argv) {
	if (argc!=2) {
		printf("Usage: %s [number of digits]\n", argv[0]);
		return 1;
	}

	int digitCount = atoi(argv[1]);

	// Create the Lower and Upper bound multiples
	long int lbm = 1;
	for(int i=1; i<digitCount; i++) {
		lbm *= 10l;
	}
	long int ubm = (lbm*10l) - 1;

	// palindrome lowerbound
	long int lowerBound = lbm*lbm;
	long largestPal = 0;
	long largestOM = 0;
	long largestIM = 0;
	for(long int om=ubm; om>=lbm; om--) {
		for(long int im=om; im>=lbm; im--) {
			long tPal = om*im;
			if(tPal < lowerBound) {
				break;
			}
			if(isPalindrome(tPal)) {
				if(tPal > largestPal) {
					largestPal = tPal;
					largestOM = om;
					largestIM = im;
					if(tPal > lowerBound) {
						lowerBound = tPal;
					}
				}
			}
		}
	}
	printf("%ld = %ld * %ld\n", largestPal, largestOM, largestIM);
	return 0;
}
