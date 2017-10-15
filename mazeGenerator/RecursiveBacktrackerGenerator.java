package mazeGenerator;

import java.util.*;

import maze.Cell;
import maze.Maze;

public class RecursiveBacktrackerGenerator implements MazeGenerator {
	
	Random rand = new Random();
	
	//attributes
	int maxRows;
	int maxColumns;
	
	boolean[][] visited; //this stores visited cells
	
	@Override
	public void generateMaze(Maze maze) {
		this.maxRows = maze.sizeR;
		this.maxColumns = maze.sizeC;
		/*
		1. Randomly pick a starting cell.
		2. Pick a random unvisited neighbouring cell and move to that neighbour. In the process, carve a
			path (i.e, remove the wall) between the cells.
		3. Continue this process until we reach a cell that has no unvisited neighbours. In that case,
			backtrack one cell at a time, until we backtracked to a cell that has unvisited neighbours.
			Repeat step 2.
		4. When there are no more unvisited neighbours for all cells, then every cell would have been visited
			and we have generated a perfect maze.
		*/
		
		//coordinates for hex is c+(r-1)? no harm having more
		this.visited = new boolean[maze.sizeR][maze.sizeC + (maze.sizeR + 1) / 2];
		
		//1. Randomly pick a starting cell
		int startRow = rand.nextInt(maze.sizeR);
		int startCol = rand.nextInt(maze.sizeC);
		Cell start = new Cell();
		//for the coordinate system for the hex
		if (maze.type == 2) {
			startCol = rand.nextInt(maze.sizeC - (maze.sizeR + 1) / 2) + (startRow + 1) / 2;
		}
		start = maze.map[startRow][startCol];
		//begin recursion
		traverse(start,maze);
	} // end of generateMaze()
	
	public void traverse(Cell from,Maze maze){
		//we have visited this cell!
		//System.out.println(from.r + "//" + from.c);
		this.visited[from.r][from.c] = true;
		
		//check for tunnels
		if ((maze.type == 1) && (from.tunnelTo != null) && (!visited[from.tunnelTo.r][from.tunnelTo.c])) {
			traverse(from.tunnelTo,maze);
		}
		
		//2. Pick a random unvisited neighbouring cell
		int[] toVisit = randomize();
		
		for (int i = 0; i < 6; i++) {
			//set the next cell to visit
			Cell to = from.neigh[toVisit[i]];
			
			//If not out of bounds and is not visited traverse and break the wall
			if ((to != null) && (!visited[to.r][to.c])) {
				from.wall[toVisit[i]].present = false;
				traverse(to,maze);
			}else{/*do nothing*/}
		}
	} // end of traverse()
	
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
} // end of class RecursiveBacktrackerGenerator