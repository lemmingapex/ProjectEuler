package com.lemmingapex.projecteuler.pathsumthreeways;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 02/12/2017
 * PathSumThreeWays.java
 * Path sum: three ways
 *
 * @author Scott Wiedemann
 *
 */
public class PathSumThreeWays {

	final int n;
	final int[][] matrix;
	final Element[][] graph;

	public PathSumThreeWays(int[][] matrix) {
		this.matrix = matrix;
		this.n = matrix.length;
		this.graph = new Element[this.n][this.n];

		for(int i=0; i < this.n; i++) {
			for(int j=0; j < this.n; j++) {
				graph[i][j] = new Element(this.matrix[i][j], i, j);
			}
		}

		for(int i=0; i < this.n; i++) {
			for(int j=0; j < this.n; j++) {
				Element e = graph[i][j];
				for(int di=-1; di<=1; di+=2) {
					int ni = i + di;
					int nj = j;
					if(ni >= 0 && nj >= 0 && ni < this.n && nj < this.n) {
						e.neighbors.add(graph[ni][nj]);
					}
				}
				int ni = i;
				int nj = j + 1;
				if(ni >= 0 && nj >= 0 && ni < this.n && nj < this.n) {
					e.neighbors.add(graph[ni][nj]);
				}
			}
		}
	}

	private Element getUnvisitedMinDistanceElement() {
		Element minDistanceElement = null;
		int minDistance = Integer.MAX_VALUE;
		for(int i=0; i < this.n; i++) {
			for(int j=0; j < this.n; j++) {
				Element e = graph[i][j];
				if(!e.visited && e.distance < minDistance) {
					minDistanceElement = e;
					minDistance = e.distance;
				}
			}
		}
		return minDistanceElement;
	}

	public int solve() {
		int minDistanceRoute = Integer.MAX_VALUE;

		for(int r=0; r<this.n; r++) {
			Element root = graph[r][0];
			root.distance = root.value;

			// Dijkstra's
			Element minDistanceElement = getUnvisitedMinDistanceElement();
			while(minDistanceElement != null) {
				for(Element neighbor : minDistanceElement.neighbors) {
					final int distance =  minDistanceElement.distance + neighbor.value;
					if(distance < neighbor.distance) {
						neighbor.distance = distance;
						neighbor.predecessor = minDistanceElement;
					}
				}

				minDistanceElement.visited = true;
				minDistanceElement = getUnvisitedMinDistanceElement();
			}

			for(int i=0; i<this.n; i++) {
				int d = graph[i][this.n-1].distance;
				if(d < minDistanceRoute) {
					minDistanceRoute = d;
				}
			}

			// reset graph
			for(int i=0; i < this.n; i++) {
				for(int j=0; j < this.n; j++) {
					Element e = graph[i][j];
					e.visited = false;
					e.distance = Integer.MAX_VALUE;
					e.predecessor = null;
				}
			}
		}

		return minDistanceRoute;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Incorrect number of arguments.");
			System.err.println("Usage: ./pathsumthreeways.jar p082_matrix.txt");
			System.exit(1);
		}

		String inputFileString = args[0];
		if (inputFileString == null || inputFileString.trim().isEmpty()) {
			System.err.println("Bad file name: " + String.valueOf(inputFileString));
			System.exit(1);
		}

		File inputFile = new File(inputFileString);
		if (!inputFile.exists()) {
			System.err.println(inputFile.getAbsolutePath() + " does not exist.");
			System.exit(1);
		}

		if (!inputFile.canRead()) {
			System.err.println("Can not read " + inputFile.getAbsolutePath() + ".");
			System.exit(1);
		}

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String line = br.readLine();

			int n = line.split(",").length;
			int[][] matrix = new int[n][n];

			int i=0;
			while (line != null && !line.trim().isEmpty()) {
				String[] lineArray = line.split(",");
				for(int j=0; j < lineArray.length; j++) {
					matrix[i][j] = Integer.parseInt(lineArray[j]);
				}
				line = br.readLine();
				i++;
			}

			System.out.println(new PathSumThreeWays(matrix).solve());
		} catch (Exception e) {
			System.err.println("Problem reading file: " + inputFile.getAbsolutePath() + ".");
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// bummer
				}
			}
		}
	}
}
