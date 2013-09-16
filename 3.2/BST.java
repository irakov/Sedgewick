//algorithm 3.3 (398+399+407+409+411+413)

public class BST<Key extends Comparable<Key>,Value>
{
	private Node root;
	
	private class Node
	{
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private int nodesCount;
	
		public Node(Key key,Value value,int nodesCount)
		{
			this.key=key;
			this.value=value;
			this.nodesCount=nodesCount;
		}
	}
	
	public void put(Key key,Value value)
	{
		if(value==null) delete(key);
		else root=put(rot,key,value);
	}
	
	private Node put(Node node,Key key,Value value)
	{
		if(node==null) return new Node(node,key,1);
		int comp=key.compareTo(node.key);
		if(comp<0) node.left=put(node.left,key,value);
		else if(comp>0) node.right=put(node.right,key,value);
		else node.value=value;
		node.nodesCount=size(node.left)+size(node.right)+1;
		return node;
	}
	
	public Value get(Key key)
	{
		if(isEmpty()) return null;
		return get(root, key);
	}
	
	private Value get(Node node,Key key)
	{
		if(node==null) return null;
		int comp=key.compareTo(node.key);
		if(comp<0) return get(node.left,key);
		else if(comp>0) return get(node.right,key);
		else return node.value;
	}
	
	public void delete(Key key)
	{
		if(isEmpty()) return null;
		root=delete(root,key);
	}
	
	private Node delete(Node node,Key key)
	{
		if(node==null) return null;
		int comp=key.compareTo(node.key);
		if(comp<0) node.left=delete(node.left,key);
		else if(comp>0) node.right=delete(node.right,key);
		else
		{
			if(node.left==null) return node.right;
			if(node.right==null) return node.left;
			Node temp=node;
			node=min(node.right);
			node.right=deleteMin(node.right);
			node.left=temp.left;
		}
		
		node.nodesCount=size(node.left)+size(node.right)+1;
		return node;
	}
	
	public boolean contains(Key key)
	{
		return get(key)!=null;
	}
	
	public boolean isEmpty()
	{
		return size()==0;
	}
	
	public int size()
	{
		return size(root);
	}
	
	private int size(Node node)
	{
		if(node==null)return 0;
		return node.nodesCount;
	}
	
	public Key min()
	{
		if(isEmpty()) return null;
		return min(root).key;
	}
	
	private Node min(Node node)
	{
		if(node.left==null) return node;
		return min(node.left);
	}
	
	public Key max()
	{
		if(isEmpty()) return null;
		return max(root).key;
	}
	
	private Node max(Node node)
	{
		if(node.right==null) return node;
		return max(node.right);
	}
	
	public Key floor(Key key)
	{
		Node node=floor(root,key);
		if(node==null) return null;
		return node.key;
	}
	
	private Node floor(Node node,Key key)
	{
		if(node==null) return null;
		int comp=key.compareTo(node.key);
		if(comp==0) return node;
		if(comp<0) return floor(node.left,key);
		Node temp=floor(node.right,key);
		if(temp!=null) return temp;
		return node;
	}
	
	public Key ceiling(Key key)
	{
		Node node=ceiling(root,key);
		if(node!=null) return node.key;
		return null;
	}
	
	private Node ceiling(Node node,Key key)
	{
		if(node==null) return null;
		int comp=key.compareTo(node.key);
		if(comp==0) return node;
		if(comp>0) return ceiling(node.right,key);
		Node temp=ceiling(node.left,key);
		if(temp!=null) return temp;
		return node;
	}
	
	public int rank(Key key)
	{
		return rank(root,key);
	}
	
	private int rank(Node node,Key key)
	{
		if(node==null) return 0;
		int comp=key.compareTo(node.key);
		if(comp<0) return rank(node,key);
		if(comp>0) return 1+size(node.left)+rank(node.right,key);
		return size(node.left);
	}
	
	public Key select(int k)
	{
		Node node=select(root,k);
		if(node==null) return null;
		return node.key;
	}
	
	private Key
	
	public void deleteMin()
	{
		root=deleteMin(root);
	}
	
	private Node deleteMin(Node node)
	{
		if(node.left==null) return node.right;
		node.left=deleteMin(node.left);
		node.nodesCount=size(node.left)+size(node.right)+1;
		return node;
	}
	
	public void deleteMax()
	{
		root=deleteMax(root);
	}
	
	private Node deleteMax(Node node)
	{
		if(node.right==null) return node.left;
		node.right=deleteMax(node.right);
		node.nodesCount=size(node.left)+size(node.right)+1;
		return node;
	}
	
	public int size(Key lo,Key hi)
	{
	}
	
	public Iterable<Key> keys(Key lo,Key hi)
	{
	}
	
	public Iterable<Key> keys()
	{
	}
}