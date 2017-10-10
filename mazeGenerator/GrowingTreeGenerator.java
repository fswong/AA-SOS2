package mazeGenerator;

import java.util.*;

import maze.Maze;
import maze.Cell;

public class GrowingTreeGenerator implements MazeGenerator {
	// Growing tree maze generator. As it is very general, here we implement as "usually pick the most recent cell, but occasionally pick a random cell"
	
	double threshold = 0.1;
	Random rand = new Random();
	
	//attributes
	Stack<Cell> Z = new Stack();
	boolean[][] visited; //this stores visited cells
	
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
		
		//coordinates for hex is c+(r-1)? no harm having more
		visited = new boolean[maze.sizeR][maze.sizeC + maze.sizeR];
		
		//1. Randomly pick a starting cell and add it to set Z
		int startRow = rand.nextInt(maze.sizeR);
		int startCol = rand.nextInt(maze.sizeC);
		Cell start = new Cell();
		start = maze.map[startRow][startCol];

		//begin
		this.Z.push(start);
		
		//this method always moves forward or backwards
		while(!this.Z.isEmpty()){
			Cell from = this.Z.peek();
			this.visited[from.r][from.c] = true;
			
			boolean foward = false;
			int[] toVisit = randomize();
			for (int i = 0; i < 6; i++) {
				//set the next cell to visit
				Cell to = from.neigh[toVisit[i]];
				
				//If cell b has unvisited neighbouring cells, randomly select a neighbour, carve a path to it
				if ((to != null) && (!visited[to.r][to.c])) {
					from.wall[toVisit[i]].present = false;
					this.Z.push(to);
					foward = true;
					break; //only add one at a time, it will check again on the way back
				}else{/*do nothing*/}
				
			}
			//If b has no unvisited neighbours, remove it from Z so we can go backwards
			if(!foward && !this.Z.isEmpty()){
				this.Z.pop();
			}
		}
	}// end of generateMaze()

	//store the directions so that all directions are visited 
	public int[] randomize(){
		int[] randomized = new int[6];
		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		for(int i=0;i<6;i++){
			toAdd.add(i);
		}
		for(int i=0;i<6;i++){
			int remove = rand.nextInt(toAdd.size());
			randomized[i] = toAdd.get(remove);
			toAdd.remove(remove);
		}
		return randomized;
	}// end of randomize()
}// end of class GrowingTreeGenerator
