package mazeGenerator;

import java.util.*;

import maze.Maze;
import maze.Cell;

public class GrowingTreeGenerator implements MazeGenerator {
	// Growing tree maze generator. As it is very general, here we implement as "usually pick the most recent cell, but occasionally pick a random cell"
	
	double threshold = 0.1;
	
	@Override
	public void generateMaze(Maze maze) {

	/*
	1. Pick a random starting cell and add it to set Z (initially Z is empty, after addition it contains
		just the starting cell).
	2. Using a particular strategy (see below) select a cell b from Z. If cell b has unvisited neighbouring
		cells, randomly select a neighbour, carve a path to it, and add the selected neighbour to set Z.
		If b has no unvisited neighbours, remove it from Z.
	3. Repeat step 2 until Z is empty.
	*/

	}

}
