package mazeSolver;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import maze.Cell;
import maze.Maze;

/**
 * Implements the BiDirectional recursive backtracking maze solving algorithm.
 */
public class BiDirectionalRecursiveBacktrackerSolver implements MazeSolver {
	private int count=0;
	boolean solved = false;
	Random rand = new Random();
	
	Stack<Cell> entry = new Stack();//DFS from entry
	Stack<Cell> exit = new Stack();//DFS from exit
	boolean[][] entryVisited; //this stores visited cells for the entry
	boolean[][] exitVisited; //this stores visited cells for the exit
	
	@Override
	public void solveMaze(Maze maze) {
		// TODO Auto-generated method stub
		/*
		 * Based on DFS do something similar to recursive backtracker generator
		 * Start from 2 ends, entrance and exit
		 * Perform recursive DFS
		 * If the 2 stacks meet, break and set that as the path
		 * */
		
		//coordinates for hex is c+(r-1)? no harm having more
		this.entryVisited = new boolean[maze.sizeR][maze.sizeC + (maze.sizeR + 1) / 2];
		this.exitVisited = new boolean[maze.sizeR][maze.sizeC + (maze.sizeR + 1) / 2];
		
		Cell cellEntry = maze.entrance;
		Cell cellExit = maze.exit;
		
		//set the required fields
		entry.push(cellEntry);
		exit.push(cellExit);
		entryVisited[cellEntry.r][cellEntry.c]=true;
		exitVisited[cellExit.r][cellExit.c]=true;
		maze.drawFtPrt(cellEntry);
		maze.drawFtPrt(cellExit);
		
		while(!solved){
			//from entry
			cellEntry = traverse(cellEntry,maze,entry,entryVisited);
			//from exit
			cellExit = traverse(cellExit,maze,exit,exitVisited);
			
			//check if the 2 lines have met by checking against the opposite array
			if(this.entryVisited[cellExit.r][cellExit.c]||this.exitVisited[cellEntry.r][cellEntry.c]){
				solved = true;
			}
		}
		
		
	} // end of solveMaze()
	
	//returns the next cell
	public Cell traverse(Cell from,Maze maze,Stack<Cell> stack,boolean[][] visited){
		Cell nextCell = new Cell();
		this.count++;
		
		//check for tunnels else Pick a random unvisited neighbouring cell
		if ((maze.type == 1) && (from.tunnelTo != null) && (!visited[from.tunnelTo.r][from.tunnelTo.c])) {
			//return tunnel cell
			nextCell = from.tunnelTo;
			stack.push(nextCell);
		}else{
			int[] toVisit = randomize();
			boolean forward =  false;
			//set the next cell to visit
			for (int i = 0; i < 6; i++) {
				Cell to = from.neigh[toVisit[i]];
				
				//If not out of bounds and is not visited traverse and break the wall else go back
				if ((to != null) && (!visited[to.r][to.c]) && (!from.wall[toVisit[i]].present)) {
					//return neighbour
					nextCell = to;
					stack.push(nextCell);
					forward = true;
				}
			}
			if (!stack.isEmpty()&&!forward) {
				stack.pop();
				nextCell = stack.peek();
			}
		}
		
		visited[nextCell.r][nextCell.c] = true;
		maze.drawFtPrt(nextCell);
		return nextCell;
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

	@Override
	public boolean isSolved() {
		return this.solved;
	} // end if isSolved()


	@Override
	public int cellsExplored() {
		return this.count;
	} // end of cellsExplored()

} // end of class BiDirectionalRecursiveBackTrackerSolver
