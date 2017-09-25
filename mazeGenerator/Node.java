public class Node<T> {
	public T data;
	public String location;
	public Node<T> parent;
	public List<Node<T>> children;
	
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