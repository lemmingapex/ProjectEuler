#!/usr/bin/python3
#
# 09/21/2016
# CountingSundays.py
# Counting Sundays
#
# Scott Wiedemann
#

import sys

class CountingSundays:
	_daysOfTheWeek = ["Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"]
	_months = ["January","February","March","April","May","June","July","August","September","October","November","December"]
	_daysPerMonth = [31,28,31,30,31,30,31,31,30,31,30,31]

	def isLeapYear(self, year):
		if(year%4 == 0):
			if (year%100 == 0):
				return (year%400 == 0)
			else:
				return True
		else:
			return False

	def count(self):
		numberOfSundays = 0

		# start in 1900
		currentYear = 1900
		# 1 Jan 1900 was a Monday
		currentDayOfTheWeekIndex = 0

		# figure out what day of the week 1 Jan 1901 is
		if(self.isLeapYear(currentYear)):
			currentDayOfTheWeekIndex = (currentDayOfTheWeekIndex + sum(self._daysPerMonth) + 1)%len(self._daysOfTheWeek)
		else:
			currentDayOfTheWeekIndex = (currentDayOfTheWeekIndex + sum(self._daysPerMonth))%len(self._daysOfTheWeek)

		# move to 1901
		currentYear += 1
		currentMonthIndex = 0
		while currentYear < 2001:
			while currentMonthIndex < 12:
				#print(self._months[currentMonthIndex],"1", currentYear,"is a " + self._daysOfTheWeek[currentDayOfTheWeekIndex] + "." + (" It's a leap year!" if self.isLeapYear(currentYear) else ""))
				if(currentDayOfTheWeekIndex == 6):
					numberOfSundays += 1
				if(self.isLeapYear(currentYear) and currentMonthIndex == 1):
					currentDayOfTheWeekIndex = (currentDayOfTheWeekIndex + self._daysPerMonth[currentMonthIndex] + 1)%len(self._daysOfTheWeek)
				else:
					currentDayOfTheWeekIndex = (currentDayOfTheWeekIndex + self._daysPerMonth[currentMonthIndex])%len(self._daysOfTheWeek)
				currentMonthIndex += 1
			currentMonthIndex = (currentMonthIndex)%12
			currentYear+=1

		return numberOfSundays

# main (DRIVER)
def main():
	if len(sys.argv) != 1:
		print("Incorrect number of arguments.", file=sys.stderr)
		print("Usage: " + sys.argv[0], file=sys.stderr)
		return 1
	else:
		print("numberOfSundays", CountingSundays().count())
	return 0

# call to main
if __name__ == "__main__":
	main()
