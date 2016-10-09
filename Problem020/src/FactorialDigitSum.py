#!/usr/bin/python3
#
# 09/24/2016
# FactorialDigitSum.py
# Factorial Digit Sum
#
# Scott Wiedemann
#

import sys

class FactorialDigitSum:

	def sum(self, N):
		factorialSum = 0;

		factorial = 1;
		for i in range(2,N+1):
			factorial *= i

		factorialStr = str(factorial)
		for i in range(len(factorialStr)):
			factorialSum += int(factorialStr[i])
		return factorialSum

# main (DRIVER)
def main():
	if len(sys.argv) != 2:
		print("Incorrect number of arguments.", file=sys.stderr)
		print("Usage: " + sys.argv[0] + " N\n", file=sys.stderr)
		return 1
	else:
		N = int(sys.argv[1])
		print(FactorialDigitSum().sum(N))
	return 0

# call to main
if __name__ == "__main__":
	main()
