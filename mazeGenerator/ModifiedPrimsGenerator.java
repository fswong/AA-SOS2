package mazeGenerator;

import maze.Maze;

public class ModifiedPrimsGenerator implements MazeGenerator {

	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub

		/*
		1. Pick a random starting cell and add it to set Z (initially Z is empty, after addition it contains
			just the starting cell). Put all neighbouring cells of starting cell into the frontier set F.
		2. Randomly select a cell c from the frontier set and remove it from F. Randomly select a cell b
			that is in Z and adjecent to the cell c. Carve a path between c and b.
		3. Add cell c to the set Z. Add the neighbours of cell c to the frontier set F.
		4. Repeat step 2 until Z includes every cell in the maze. At the end of the process, we would have
			generated a perfect maze.
		*/
		
	} // end of generateMaze()

} // end of class ModifiedPrimsGenerator
