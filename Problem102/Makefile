INCFLAGS  = -I vecmath/include

CFLAGS = -O3 -funroll-loops -g -Wall -ansi
#CFLAGS = -O1 -g -Wall -ansi
#CFLAGS = -g -Wall -ansi
CC = g++
SRCS = $(wildcard src/*.cpp)
SRCS += $(wildcard vecmath/src/*.cpp)
OBJS = $(SRCS:.cpp=.o)
PROG = trianglecontainment

LINKFLAGS = -L.

all: $(SRCS) $(PROG)

$(PROG): $(OBJS)
	$(CC) $(CFLAGS) $(OBJS) -o $@ $(LINKFLAGS)

.cpp.o:
	$(CC) $(CFLAGS) $< -c -o $@ $(INCFLAGS)

depend:
	makedepend $(INCFLAGS) -Y $(SRCS)

clean:
	rm $(OBJS) $(PROG)
