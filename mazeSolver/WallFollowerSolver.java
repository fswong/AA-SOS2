package mazeSolver;

import java.util.HashSet;

import maze.Cell;
import maze.Maze;

/**
 * Implements WallFollowerSolver
 * Wall Directions -> East-0, North East-1, North-2, North West-3, West-4, South-5
		
		 Algorithm
		 * 1) If isSolved -> return
         * 2) If current cell is exit -> mark isSolved as true
         * 3) Else find next broken wall (smallest) from current broken wall direction
         * 4) If broken wall neighbor not visited -> Go to Step 1 and pass broken Wall Neighbor(Cell) and broken Wall direction calculated for neighbor
                   
 */

public class WallFollowerSolver implements MazeSolver {
	
	Maze maze;
	Boolean isSolved;
	HashSet<Cell> visitedNeighborSet=new HashSet<Cell>();
	
	@Override
	public void solveMaze(Maze maze) {
		this.maze=maze;
		isSolved=false;
		int startDirection=0;
		for(int i=0;i<maze.entrance.wall.length;i++){
			if(maze.entrance.wall[i]!=null){
				if(maze.entrance.neigh[i]!=null){
					startDirection=i;
				}
			}
		}
		traverse(maze.entrance,(startDirection>=3)?startDirection-3:startDirection+3);
	} // end of solveMaze()
    
    
	private void traverse(Cell currCell, int brokenWallDirection) {
		// 1) Boundary Condition for recursion
		if(isSolved)
			return;
		// Add Cell to Visited Neighbor Set 		
		visitedNeighborSet.add(currCell);
		// Draw Path
		maze.drawFtPrt(currCell);
		// 2) If current cell is exit -> mark isSolved as true
		if(currCell==maze.exit){
			isSolved=true;
		}// 3) Else find next broken wall (smallest index) from current broken wall direction
		else {
			Boolean flag=false;
			//Always look for broken wall in ascending order direction of Walls
			for(int i=brokenWallDirection+1;i<currCell.wall.length;i++){
				findBrokenWall(currCell, i);
			}
			//If did not find broken wall then check remaining Walls
			if(!flag){
				for(int i=0;i<brokenWallDirection;i++){
					findBrokenWall(currCell, i);
				}
			}
		}

	}
	private void findBrokenWall(Cell currCell,int i){
		//If wall is not null
		if(currCell.wall[i]!=null){
			//If wall present is false
			if(currCell.wall[i].present==false){
				//If this neighbor has not already been visited
				if(!visitedNeighborSet.contains(currCell.neigh[i])){
					/***Traverse Neighbor and pass opposite direction Wall by subtracting or adding 3***/
					//Check for Special Case :-> Tunnels [Direction reset, will start from 0]
					if(currCell.neigh[i].tunnelTo!=null){
						// Add Cell to Visited Neighbor Set 		
						visitedNeighborSet.add(currCell.neigh[i]);
						// Draw Path
						maze.drawFtPrt(currCell.neigh[i]);
						traverse(currCell.neigh[i].tunnelTo,-1);
						if(!isSolved)
							traverse(currCell.neigh[i],-1);;
					}
					else traverse(currCell.neigh[i],(i>=3)?i-3:i+3);
				}
			}
		}
	}

	@Override
	public boolean isSolved() {
		return isSolved;
	} // end if isSolved()
    
	@Override
	public int cellsExplored() {
		return visitedNeighborSet.size();
	} // end of cellsExplored()

} // end of class WallFollowerSolver
