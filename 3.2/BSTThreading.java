//3.2.34+3.2.36 (420)

import java.util.ArrayList;
import java.util.List;

public class BSTThreading<Key extends Comparable<Key>,Value>
{
	private Node root;
	
	private class Node
	{
		private Key key;
		private Value value;
		private Node left,right,pred,succ;
		private int nodesCount;
		
		public Node(Key key,Value value,int nodesCount,Node pred,Node succ)
		{
			this.key=key;
			this.value=value;
			this.nodesCount=nodesCount;
			this.pred=pred;
			this.succ=succ;
		}
	}
	
	public Key next(Key key)
	{
		Node node=root;
		while(node!=null)
		{
			int comp=node.key.compareTo(key);
			if(comp==0)
				{
				if(node.succ==null) return null;
				else return node.succ.key;
				}
			else if(comp>0) node=node.left;
			else node=node.right;
		}
		return null;//or throw new NoSuchElementException()?
	}
	
	public Key prev(Key key)
	{
		Node node=root;
		while(node!=null)
		{
			int comp=node.key.compareTo(key);
			if(comp==0) 
				if(node.pred==null) return null;
				else return node.pred.key;
			else if(comp>0) node=node.left;
			else node=node.right;
		}
		return null;//or throw new NoSuchElementException()?
	}
	
	public void put(Key key,Value value)
	{
		if(value==null) delete(key);
		else root=put(root,key,value,null,null);
	}
	
	private Node put(Node node,Key key,Value value,Node pred,Node succ)
	{
		if(node==null) 
		{
			Node result=new Node(key,value,1,pred,succ);
			if(pred!=null) pred.succ=result;
			if(succ!=null) succ.pred=result;
			return result;
		}
		int comp=key.compareTo(node.key);
		if(comp<0) node.left=put(node.left,key,value,pred,node);
		else if(comp>0) node.right=put(node.right,key,value,node,succ);
		else node.value=value;
		node.nodesCount=size(node.left)+size(node.right)+1;
		return node;
	}
	
	public Value get(Key key)
	{
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
			Node pred=node.pred;
			Node succ=node.succ;
			Node temp=node;
			node=min(temp.right);
			node.right=deleteMin(temp.right,temp);
			node.left=temp.left;
			if(pred!=null) pred.succ=node;
			node.pred=pred;
			if(succ!=null) succ.pred=node;
			node.succ=succ;
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
		if(comp<0)
		{
			Node temp=ceiling(node.left,key);
			if(temp!=null) return temp;
			return node;
		}
		return ceiling(node.right,key);
	}
	
	public int rank(Key key)
	{
		return rank(root,key);
	}
	
	private int rank(Node node,Key key)
	{
		if(node==null) return 0;
		int comp=key.compareTo(node.key);
		if(comp<0) return rank(node.left,key);
		if(comp>0) return 1+size(node.left)+rank(node.right,key);
		return size(node.left);
	}
	
	public Key select(int k)
	{
		if(k<0||k>=size()) return null;
		Node node=select(root,k);
		return node.key;
	}
	
	private Node select(Node node,int k)
	{
		if(node==null) return null;
		int temp=size(node.left);
		if(temp<k) return select(node.right,k-temp-1);
		if(temp>k) return select(node.left,k);
		return node;
	}
	
	public void deleteMin()
	{
		root=deleteMin(root,null);
	}
	
	private Node deleteMin(Node node,Node parent)
	{
		if(node.left==null) 
		{
			Node result=node.right;
			if(parent!=null&&parent.succ!=null) parent.pred=null;
			return result;
		}
		node.left=deleteMin(node.left,node);
		node.nodesCount=size(node.left)+size(node.right)+1;
		return node;
	}
	
	public void deleteMax()
	{
		root=deleteMax(root,null);
	}
	
	private Node deleteMax(Node node,Node parent)
	{
		if(node.right==null)
		{
			Node result=node.left;
			if(parent!=null&&parent.pred!=null) parent.succ=null;
			return result;
		}
		node.right=deleteMax(node.right,node);
		node.nodesCount=size(node.left)+size(node.right)+1;
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
		int compLo=lo.compareTo(node.key);
		if(compLo<0) keys(node.left,list,lo,hi);
		int compHi=hi.compareTo(node.key);
		if(compLo<=0&&compHi>=0) list.add(node.key);
		if(compHi>0) keys(node.right,list,lo,hi);
		
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
		return 1+Math.max(height(node.left),height(node.right));
	}
	
	private boolean isBinaryTree(Node node)
	{
		if(node==null) return true;
		if(node.left!=null&&node.nodesCount<=node.left.nodesCount) return false;
		if(node.right!=null&&node.nodesCount<=node.right.nodesCount) return false;
		return isBinaryTree(node.left)&&isBinaryTree(node.right);
	}
	
	private boolean isOrdered(Node node,Key min,Key max)
	{
		if(node==null) return true;
		if(node.key.compareTo(min)<0||node.key.compareTo(max)>0) return false;
		if(node.left!=null&&node.left.key.compareTo(node.key)>=0) return false;
		if(node.right!=null&&node.right.key.compareTo(node.key)<=0) return false;
		return isOrdered(node.left,min,max)&&isOrdered(node.right,min,max);
	}
	
	private boolean hasNoDuplicates(Node node)
	{
		if(node==null) return true;
		if(node.left!=null&&node.left.key==node.key) return false;
		if(node.right!=null&&node.right.key==node.key) return false;
		return hasNoDuplicates(node.left)&&hasNoDuplicates(node.right);
	}
	
	public boolean isBST()
	{
		if(!isBinaryTree(root)) return false;
		if(!isOrdered(root,min(),max())) return false;
		if(!hasNoDuplicates(root)) return false;
		return true;
	}
	
	public static void main(String[] args)
	{
		BSTThreading<String,Integer> bst=new BSTThreading<String,Integer>();
		for(int i=0;!StdIn.isEmpty();i++)
		{
			String s=StdIn.readString();
			bst.put(s,i);
		}
		
		for(String s:bst.keys())
			StdOut.println(s+" "+bst.get(s));
			
		StdOut.println(bst.isBST());
	}
}