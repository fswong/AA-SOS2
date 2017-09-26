package mazeGenerator;

import java.util.*;
import maze.Maze;

public class RecursiveBacktrackerGenerator implements MazeGenerator {

	//properties
	public Maze maze;
	public int maxRows; //based on maze size
	public int maxColumns;
	public int scR; //starting row
	public int srC; //starting cloumn
	public Node rootNode;
	public Integer currR; //current row
	public Integer currC; //current column
	public Node currNode;
	public Node nextNode;
	public Node prevNode = null;
	public HashMap<String, Object> visited = new HashMap<String, Object>();
	public HashMap<String, Node> neighboured = new HashMap<String, Node>();
	public int counter=0; //to test if all are visited

	Random rand = new Random();
	
	//to not create so many objects
	public String key = "";
	public Node test = new Node(null,"");
	
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
		nextNode = new Node(rootNode,createElement(this.currR, this.currC));
		
		traverse();
		
		//move next
		
		
		
		
		
		

	} // end of generateMaze()
	
	//get the neighbours
	public void getNeighbours(){
		/*
		loop through the cell to find the neighbours
		check that it is not already in the neighbours and that it not already visited
		if pass the test add to the list
		*/
		
		for(int i=0;i<maze.NUM_DIR;i++){
			if (maze.map[this.currR][this.currC].neigh[i] != null) {
				//System.out.println("i "+i);
				
				//check if the cell is not doubled up
				key = createElement(maze.map[this.currR][this.currC].neigh[i].r,maze.map[this.currR][this.currC].neigh[i].c);
				test = neighboured.get(key);

				if(test != null){
					//dont add
					System.out.println("no add "+maze.map[this.currR][this.currC].neigh[i].r+","+maze.map[this.currR][this.currC].neigh[i].c);
				}else{
					//add to the neighboured list
					//check that the cell is not already neighboured
					System.out.println("add "+maze.map[this.currR][this.currC].neigh[i].r+","+maze.map[this.currR][this.currC].neigh[i].c);
					Node node = new Node(this.currNode,createElement((Integer)maze.map[this.currR][this.currC].neigh[i].r,(Integer)maze.map[this.currR][this.currC].neigh[i].c));
					this.currNode.children.add(node);
					
					neighboured.put(createElement(this.currR,this.currC),this.currNode);
				}
				
				
			}
		}
		
	}
	
	public void traverse(){
		this.counter++;
		//System.out.println("Counter " + this.counter);
		System.out.println("Visited: " + this.currNode.element);
		getNeighbours();
		
		if(this.currNode.children.size()>0){
			System.out.println("Moving forward");
			
			//pick a random child
			this.nextNode = this.currNode.children.get(rand.nextInt(this.currNode.children.size()));

			/*
			record the node
			move next
			delete from the to visit list
			*/
			this.prevNode = this.currNode;
			this.currNode = this.nextNode;
			this.prevNode.removeChild(this.currNode);
			
			setCurrCell(this.currNode.element);
			//log movement
			visited.put(createElement(this.currR,this.currC),this.currNode);
			traverse();
		}else{
			System.out.println("Moving backward");
			//no child then move back
			
			//check if it is the root
			if(this.currNode.parent == this.rootNode){
				//end of recursion
			}else{
				//backtrack
				this.currNode = this.currNode.parent;
				traverse();
			}
			
		}
	}
	
	public String createElement(Integer x, Integer y){
		String outputString = x+","+y;
		return outputString;
	}
	
	public void setCurrCell(String element){
		String[] strSplitter = element.split(",");
		this.currC = Integer.parseInt(strSplitter[0]);
		this.currR = Integer.parseInt(strSplitter[1]);
	}
	
	public class Node {
		public String element;
		public Node parent;
		public ArrayList<Node> children;
		
		public Node(Node parent,String element){
			this.parent=parent;
			this.element=element;
			this.children = new ArrayList<Node>();
		}
		
		public void addChild(Node newChild){
			this.children.add(newChild);
		}
		
		public void removeChild(Node currChild){
			Iterator<Node> childlist = children.iterator();
			
			while (childlist.hasNext()) {
				if (childlist.next() == currChild) {
					childlist.remove();
					break;
				}
			}
			
			childlist = null;
			/*for (Node thisChild : this.currNode.children) {
                if(thisChild == currChild){
					thisChild.remove();
					break;
				}
            }*/
		}
	}

} // end of class RecursiveBacktrackerGenerator
