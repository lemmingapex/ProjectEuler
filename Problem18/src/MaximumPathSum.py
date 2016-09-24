#!/usr/bin/python3
#
# 09/21/2016
# MaximumPathSum.py
# Maximum path sum I
# Maximum path sum II
#
# Scott Wiedemann
#

import sys

class MaximumPathSum:
	_triangleData = []

	def __init__(self, InputFile):
		for line in InputFile:
			self._triangleData.append([int(v) for v in line.split()])
		return

	def sumMaxPath(self):
		maxPathData = [row[:] for row in self._triangleData]

		i = len(maxPathData) - 2
		while i >= 0:
			#print(maxPathData[i])
			j = len(maxPathData[i]) - 1
			while j >= 0:
				leftChild = maxPathData[i+1][j]
				rightChild = maxPathData[i+1][j+1]
				maxPathData[i][j] += max(leftChild, rightChild)
				j-=1
			i-=1
		return maxPathData[0][0]

# main (DRIVER)
def main():
	if len(sys.argv) != 2:
		print("Incorrect number of arguments.", file=sys.stderr)
		print("Usage: " + sys.argv[0] + " Prog.asm\n", file=sys.stderr)
		return 1
	else:
		InputFileName = sys.argv[1]
		try:
			# read file
			InputFile = open(InputFileName, "r")
		except IOError:
			print("The file \"" + InputFileName + "\" does not exist.\n")
			return 2
		print(MaximumPathSum(InputFile).sumMaxPath())
	return 0

# call to main
if __name__ == "__main__":
	main()
