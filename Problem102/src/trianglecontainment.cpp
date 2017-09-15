// Scott Wiedemann
// 09/13/2013
// trianglecontainment.cpp
// Triangle Containment

#include <stdio.h>
#include <fstream>
#include <iostream>
#include <string>
#include <vector>
#include <vecmath.h>

using namespace std;

// global variables
vector <Vector2f*> Triangles;
int number_of_triangles = 1000;

bool containsOrigin(Vector2f a1, Vector2f a2, Vector2f a3) {
	// origin
	Vector2f o = Vector2f(0.0, 0.0);

	// check if origin is inside triangle
	// look at vectors between the origin and the vertices of the triangle.
	// If the cross product of these vectors with each other all point in the same direction, then the origin is within the triangle.

	Vector3f v1 = (a1-o).cross(a2-o);
	Vector3f v2 = (a2-o).cross(a3-o);
	Vector3f v3 = (a3-o).cross(a1-o);

	// if the dot product of all of the vectors is the same (should be postive or negative depending on triangle orientation)
	// then all the vectors point in the same direction, and the point is inside the triangle.
	// don't check equivalence becasue of numerical precision issues, use < or >
	if((v1.dot(v2)<0) || (v2.dot(v3)<0) || (v3.dot(v1)<0)) {
		return false;
	}
	return true;
}

bool read_input_file(string Filename) {
	ifstream ifs(Filename.c_str());
	if(ifs) {
		char comma;
		float temp_point[2];

		for(int i=0; i<number_of_triangles; i++) {
			ifs >> temp_point[0] >> comma >> temp_point[1] >> comma;
			Vector2f* a1 = new Vector2f(temp_point[0], temp_point[1]);

			ifs >> temp_point[0] >> comma >> temp_point[1] >> comma;
			Vector2f* a2 = new Vector2f(temp_point[0], temp_point[1]);

			ifs >> temp_point[0] >> comma >> temp_point[1];
			Vector2f* a3 = new Vector2f(temp_point[0], temp_point[1]);

			Triangles.push_back(a1);

			// winding number check
			if((*a2-*a1).cross(*a3-*a1).z() < 0) {
				Triangles.push_back(a3);
				Triangles.push_back(a2);
			} else {
				Triangles.push_back(a2);
				Triangles.push_back(a3);
			}
		}
		return true;
	}
	return false;
}

int main(int argc, const char *argv[]) {
	string inputFileName = "p102_triangles.txt";

	if (argc != 2) {
		cerr << "Usage: " << argv[0] << " <" << inputFileName << ">" << endl;
		return 1;
	}

	if(!read_input_file(inputFileName)) {
		cerr << "File " << inputFileName << " does not exist, or can not be read." << endl;
		return 2;
	}

	int count_with_origin = 0;

	for(int i=0; i<number_of_triangles*3; i+=3) {
		if(containsOrigin(*Triangles.at(i), *Triangles.at(i+1), *Triangles.at(i+2))) {
			count_with_origin++;
		}
	}

	printf("%i\n", count_with_origin);

	return 0;
}
