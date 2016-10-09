CC = g++
OPT = -O3
LIBS = -lm
WALL = -Wall

TARGET="largeprimefactor"

HEADERS = $(TARGET).h
OBJECTS = $(TARGET).o

all : $(TARGET)

$(TARGET) : $(OBJECTS)
	$(CC) $(WALL) $(OPT) -o $(TARGET) $(OBJECTS) $(LIBS)

$(OBJECTS) : *.cpp
	$(CC) $(OPT) -c *.cpp

clean: 
	rm *.o $(TARGET)
