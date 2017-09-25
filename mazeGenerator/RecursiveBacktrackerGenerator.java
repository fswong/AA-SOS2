package mazeGenerator;

import java.util.*;
import maze.Maze;
import MyTree;

public class RecursiveBacktrackerGenerator implements MazeGenerator {

	//properties
	public Maze maze;
	public int maxRows; //based on maze size
	public int maxColumns;
	public int scR; //starting row
	public int srC; //starting cloumn
	public Node rootNode;
	public int currR; //current row
	public int currC; //current column
	public Node currNode;
	public HashMap visited = new HashMap();
	public HashMap neighboured = new HashMap();
	public HashMap routes = new HashMap();

	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub
		this.maze = maze;
		
		this.maxRows = maze.sizeR;
		this.maxColumns = maze.sizeC;
		/*
		1. Randomly pick a starting cell.
		sizeR
		sizeC
		2. Pick a random unvisited neighbouring cell and move to that neighbour. In the process, carve a
			path (i.e, remove the wall) between the cells.
		3. Continue this process until we reach a cell that has no unvisited neighbours. In that case,
			backtrack one cell at a time, until we backtracked to a cell that has unvisited neighbours.
			Repeat step 2.
		4. When there are no more unvisited neighbours for all cells, then every cell would have been visited
			and we have generated a perfect maze.
		*/
		
		//picking starting cell
		Random rand = new Random();
		this.scR = rand.nextInt(maxRows);
		this.srC = rand.nextInt(maxColumns);
		
		//begin generating
		/* 
		set the current cell
		get the cells neighbours
		add the cells to an array
		visit a random cell
		*/
		//maze.map[scR][srC];
		
		//begin
		this.currC = this.scR;
		this.currR = this.srC;
		
		//set the root node
		rootNode = new Node(null,createElement(this.currR, this.currC));
		currNode = rootNode;
		
		//find the neighbours
		getNeighbours();
		
		//move next
		
		
		
		
		
		

	} // end of generateMaze()
	
	//get the neighbours
	public void getNeighbours(){
		/*
		loop through the cell to find the neighbours
		check that it is not already in the neighbours and that it not already visited
		if pass the test add to the list
		*/
		for(int i;i<maze.NUM_DIR;i++){
			//if the location is not empty and is not already visited or neighboured
			if (maze.map[this.currR][this.currC].neigh[i] != null) {
				
				routes.put(createElement(int x, int y),node);
				Node node = new node();
			}
		}
		
	}
	
	public void traverse(){
		//if all children have been visited, move back
		for(){
			
		}
	}
	
	//visit the next cell
	public void moveNext(){
		routes.put(createElement(int x, int y),node);
	}
	
	public String createElement(int x, int y){
		String outputString = this.currC.toString()+","+this.currR.toString();
		return outputString;
	}
	
	public void setCurrCell(String element){
		String[] strSplitter = element.split(",");
		this.currC = strSplitter[0];
		this.currR = strSplitter[1];
	}
	
	public class Node {
		public T element;
		public Node parent;
		public List<Node> children;
		
		public Node(Node parent,T element){
			this.parent=parent;
			this.element=element;
		}
		
		public void addChild(Node newChild){
			this.children.add(newChild);
		}
	}

} // end of class RecursiveBacktrackerGenerator
