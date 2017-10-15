package mazeGenerator;

import java.util.ArrayList;
import java.util.Random;

import maze.Cell;
import maze.Maze;

public class ModifiedPrimsGenerator implements MazeGenerator {

	Random rand = new Random();
	
	//attributes
	ArrayList<Cell> Z = new ArrayList<Cell>();
	ArrayList<Cell> F = new ArrayList<Cell>();
	
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
		
		//1. Randomly pick a starting cell and add it to set Z
		int startRow = rand.nextInt(maze.sizeR);
		int startCol = rand.nextInt(maze.sizeC);
		//for the coordinate system for the hex
		if (maze.type == 2) {
			startCol = rand.nextInt(maze.sizeC - (maze.sizeR + 1) / 2) + (startRow + 1) / 2;
		}
		Cell start = new Cell();
		start = maze.map[startRow][startCol];
		Z.add(start);
		
		//do while because you need to start by adding into F
		do{
			Cell from = new Cell();
			int random = 0;
			//if it is the first time, F will be empty
			if(F.size()==0){
				from = start;
			}else{
				random = rand.nextInt(F.size());
				from = F.get(random);
			}	
			
			//add the neighbours
			for (int i = 0; i < 6; i++) {
				//set the next cell to visit
				Cell toF = from.neigh[i];
	
				//If not out of bounds add to F dont break the wall yet
				if ((toF != null) && !Z.contains(toF) && !F.contains(toF)) {
					F.add(toF);
				}else{/*do nothing*/}
			}
			
			//F, check against Z, if exists in Z, break the wall and stop
			int[] toVisit = randomize();
			if(from != start){
				for (int i = 0; i < 6; i++) {
					//set the next cell to visit
					Cell toZ = from.neigh[toVisit[i]];
		
					//Break the wall between F and Z
					if ((toZ != null) && Z.contains(toZ)) {
						from.wall[toVisit[i]].present = false;
						break; //only add one at a time, it will check again on the way back
					}else{/*do nothing*/}
				}
				Z.add(from);
				F.remove(random);
			}
		}while(F.size()>0);
	} // end of generateMaze()
	
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
} // end of class ModifiedPrimsGenerator
