//3.2.13+3.2.14+3.2.6 (417,418)

import java.util.List;
import java.util.ArrayList;

public class BSTNonRec<Key extends Comparable<Key>,Value>
{
	private Node root;
	
	private class Node implements Comparable<Node>
	{
		private Key key;
		private Value value;
		private Node left;
		private Node right;
	
		public Node(Key key,Value value)
		{
			this.key=key;
			this.value=value;
		}
		
		public int compareTo(Node other)
		{
			return this.key.compareTo(other.key);
		}
	}
	
	public void put(Key key,Value value)
	{
		if(value==null) delete(key);
		if(root==null) root=new Node(key,value);
		Node parent=null;
		Node currentNode=root;
		while(currentNode!=null)
		{
			parent=currentNode;
			int comp=key.compareTo(currentNode.key);
			if(comp<0) currentNode=currentNode.left;
			else if(comp>0) currentNode=currentNode.right;
			else 
			{
				currentNode.value=value;
				return;
			}
		}
		int comp=key.compareTo(parent.key);
		if(comp<0) parent.left=new Node(key,value);
		else parent.right=new Node(key,value);
	}
	
	public Value get(Key key)
	{
		Node node=root;
		while(node!=null)
		{
			int comp=key.compareTo(node.key);
			if(comp<0) node=node.left;
			else if(comp>0) node=node.right;
			else return node.value;
		}
		
		return null;
	}
	
	public void delete(Key key)
	{
		if(isEmpty()) return;
		if(size()==1) 
		{
			root=null;
			return;
		}
		
		Node parent=null;
		Node currentNode=root;
		boolean isLeftChild=true;
		
		while(currentNode!=null)
		{
			int comp=key.compareTo(currentNode.key);
			if(comp<0) 
			{
				currentNode=currentNode.left;
				isLeftChild=true;
			}
			else if(comp>0)
			{
				currentNode=currentNode.right;
				isLeftChild=true;
			}
			else break;
			parent=currentNode;
		}
		
		if(currentNode!=null)
		{
			if(currentNode.left==null)
			{
				if(isLeftChild) 
				{
					parent.left=currentNode.right;
					return;
				}
				else
				{
					parent.right=currentNode.right;
					return;
				}
			}
			if(currentNode.right==null)
			{
				if(isLeftChild)
				{
					parent.left=currentNode.left;
					return;
				}
				else
				{
					parent.right=currentNode.left;
					return;
				}
			}
		}
		
		Node newNode=null;
		if(isLeftChild)
		{
			parent.left=min(currentNode.right);
			parent.left.right=deleteMin(currentNode.right);
			parent.left.left=currentNode.left;
		}
		else
		{
			parent.right=min(currentNode.right);
			parent.right.right=deleteMin(currentNode.right);
			parent.right.left=currentNode.left;
		}
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
		
		int result=0;
		Queue<Node> queue=new Queue<Node>();
		queue.enqueue(node);
		while(queue.size()!=0)
		{
			result++;
			Node innerNode=queue.dequeue();
			if(innerNode.left!=null) queue.enqueue(innerNode.left);
			if(innerNode.right!=null) queue.enqueue(innerNode.right);
		}
		return result;
	}
	
	public Key min()
	{
		if(isEmpty()) return null;
		return min(root).key;
	}
	
	private Node min(Node node)
	{
		Node temp=node;
		while(temp.left!=null) temp=temp.left;
		return temp;
	}
	
	public Key max()
	{
		if(isEmpty()) return null;
		return max(root).key;
	}
	
	private Node max(Node node)
	{
		Node temp=node;
		while(temp.right!=null) temp=temp.right;
		return temp;
	}
	
	public Key floor(Key key)
	{
		Node node=floor(root,key);
		if(node==null) return null;
		return node.key;
	}
	
	private Node floor(Node node,Key key)
	{
		Node temp=node;
		Node floor=null;
		while(temp!=null)
		{
			int comp=key.compareTo(temp.key);
			if(comp==0) 
			{
				floor=temp; 
				break;
			}
			else if(comp<0) temp=temp.left;
			else 
			{
				floor=temp;
				temp=temp.right;
			}
		}
		
		return floor;
	}
	
	public Key ceiling(Key key)
	{
		Node node=ceiling(root,key);
		if(node!=null) return node.key;
		return null;
	}
	
	private Node ceiling(Node node,Key key)
	{
		Node temp=node;
		Node ceil=null;
		while(temp!=null)
		{
			int comp=key.compareTo(temp.key);
			if(comp==0)
			{
				ceil=temp;
				break;
			}
			else if (comp<0)
			{
				ceil=temp;
				temp=temp.left;
			}
			else temp=temp.right;
		}
		
		return ceil;
	}
	
	public int rank(Key key)
	{
		return rank(root,key);
	}
	
	private int rank(Node node,Key key)
	{
		if(node==null) return 0;
		
		int result=0;
		Queue<Node> queue=new Queue<Node>();
		queue.enqueue(node);
		while(queue.size()!=0)
		{
			result++;
			Node innerNode=queue.dequeue();
			result+=size(innerNode.left);
			if(innerNode.right!=null) queue.enqueue(innerNode.right);
		}
		
		return result;
	}
	
	public Key select(int k)
	{
		if(k<0||k>=size()) return null;
		Node node=select(root,k);
		return node.key;
	}
	
	private Node select(Node node,int k)
	{
		Node temp=node;
		
		while(temp!=null)
		{
			int leftSize=size(temp.left);
			if(leftSize<k)
			{
				k-=(leftSize+1);
				temp=temp.right;
			}
			else if(leftSize>k) temp=temp.left;
			else
			{
				temp=node;
				break;
			}
		}
		
		return temp;
	}
	
	public void deleteMin()
	{
		root=deleteMin(root);
	}
	
	private Node deleteMin(Node node)
	{
		Node temp=node;
		while(temp.left!=null) temp=temp.left;
		temp=temp.right;
		return node;
	}
	
	public void deleteMax()
	{
		root=deleteMax(root);
	}
	
	private Node deleteMax(Node node)
	{
		Node temp=node;
		while(temp.right!=null) temp=temp.right;
		temp=temp.left;
		return node;
	}
	
	public int size(Key lo,Key hi)
	{
		if(lo.compareTo(hi)>0) return 0;
		if(contains(hi)) return rank(hi)-rank(lo)+1;
		else return rank(hi)-rank(lo);
	}
	
	public Iterable<Key> keys(Key lo,Key hi)
	{
		List<Key> list=new ArrayList<Key>();
		keys(root,list,lo,hi);
		return list;
	}
	
	private void keys(Node node,List<Key> list,Key lo,Key hi)
	{
		if(node==null) return;
		
		Queue<Node> queue=new Queue<Node>();
		queue.enqueue(node);
		
		while(queue.size()>0)
		{
			Node tempNode=queue.dequeue();
			int compLo=lo.compareTo(tempNode.key);
			if(compLo<0) queue.enqueue(tempNode.left);
			int compHi=hi.compareTo(tempNode.key);
			if(compLo<=0&&compHi>=0) list.add(tempNode.key);
			if(compHi>0) queue.enqueue(tempNode.right);
		}
	}
	
	public Iterable<Key> keys()
	{
		return keys(min(),max());
	}
	
	public int height()
	{
		return height(root);
	}
	
	private int height(Node node)
	{
		if(node==null) return -1;
		int maxHeight=-1;
		
		Queue<NodeHeight> queue=new Queue<NodeHeight>();
		queue.enqueue(new NodeHeight(node,-1));
		while(queue.size()>0)
		{
			NodeHeight temp=queue.dequeue();
			int tempHeight=temp.getHeight()+1;
			Node innerNode=temp.getNode();
			if(maxHeight<tempHeight)
				maxHeight=tempHeight;
			if(innerNode.left!=null) queue.enqueue(new NodeHeight(innerNode.left,tempHeight));
			if(innerNode.right!=null) queue.enqueue(new NodeHeight(innerNode.right,tempHeight));
		}
		return maxHeight;
	}
	
	private class NodeHeight implements Comparable<NodeHeight>
	{
		private Node node;
		private int height;
		
		public NodeHeight(Node node,int height)
		{
			this.node=node;
			this.height=height;
		}
		
		public Node getNode(){return node;}
		public int getHeight(){return height;}
		
		public int compareTo(NodeHeight other)
		{
			return this.node.key.compareTo(other.node.key);
		}
	}
	
	public static void main(String[] args)
	{
		BSTNonRec<String,Integer> bst=new BSTNonRec<String,Integer>();
		for(int i=0;!StdIn.isEmpty();i++)
		{
			String s=StdIn.readString();
			bst.put(s,i);
		}
		
		for(String s:bst.keys())
			StdOut.println(s+" "+bst.get(s));
	}
}