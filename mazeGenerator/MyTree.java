public class MyTree<T> {
    private Node root;

	//element is the new location
    public Tree(T element) {
        root = new Node(null,element);
        root.element = element;
        root.children = new ArrayList<Node<T>>();
    }
	
	public getNode(T element){
		
	}
	
	//parent is where from, child is where to
	public class Node {
		public T element;
		public Node parent;
		public List<Node> children;
		
		public Node(Node prev,T element){
			this.prev=prev;
			this.element=element;
		}
		
		public void setLocation(String location){
			this.location = location;
		}
		
		public void setParent(Node parent){
			this.parent = parent;
		}
		
		public void addChild(Node newChild){
			this.children.add(newChild);
		}
	}
}