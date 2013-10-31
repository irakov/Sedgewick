//3.3.32(page 452)
//based on http://en.wikipedia.org/wiki/AVL_tree

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class AVLTree<Key extends Comparable<Key>,Value>
{
	private Node root;
	
	private class Node implements Comparable<Node>
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
		
		public int compareTo(Node other)
		{
			return this.key.compareTo(other.key);
		}
	}
		
	public void put(Key key,Value value)
	{
		if(value==null) delete(key);
		else root=put(root,key,value);
	}
	
	private Node put(Node node,Key key,Value value)
	{
		if(node==null) return new Node(key,value,1);
		
		int comp=key.compareTo(node.key);
		if(comp==0) node.value=value;
		else if(comp<0) node.left=put(node.left,key,value);
		else node.right=put(node.right,key,value);
	
		return balance(node);
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
			Node temp=min(node.right);
			node.key=temp.key;
			node.value=temp.value;
			node.right=deleteMin(node.right);
		}
		preOrderTraversal();
		return balance(node);
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
		if(isEmpty()) return;
		root=deleteMin(root);
	}
	
	private Node deleteMin(Node node)
	{
		if(node==null) return null;
		if(node.left==null) return node.right;
		node.left=deleteMin(node.left);
		return balance(node);
	}
	
	public void deleteMax()
	{
		if(isEmpty()) return;
		root=deleteMax(root);
	}
	
	private Node deleteMax(Node node)
	{
		if(node==null) return null;
		if(node.right==null) return node.left;
		node.right=deleteMax(node.right);
		return balance(node);
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
	
	private int balanceFactor(Node node)
	{
		if(node==null) return 0;
		
		int leftHeight=-1;
		int rightHeight=-1;
		if(node.left!=null) leftHeight=height(node.left);
		if(node.right!=null) rightHeight=height(node.right);
		return leftHeight-rightHeight;
	}
	
	private Node balance(Node node)
	{
		int balance=balanceFactor(node);
		if(balance>1)
		{
			if(balanceFactor(node.left)<0) node.left=rotateLeft(node.left);
			node=rotateRight(node);
		}
		else if(balance<-1)
		{
			if(balanceFactor(node.right)>0) node.right=rotateRight(node.right);
			node=rotateLeft(node);
		}
		
		node.nodesCount=size(node.left)+size(node.right)+1;
		return node;
	}
	
	private Node rotateLeft(Node node)
	{
		Node temp=node.right;
		node.right=temp.left;
		temp.left=node;
		return temp;
	}
	
	private Node rotateRight(Node node)
	{
		Node temp=node.left;
		node.left=temp.right;
		temp.right=node;
		return temp;
	}
	
	public void preOrderTraversal()
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		preOrder(root,output);
		output.println();
	}
	
	private void preOrder(Node node,PrintWriter output)
	{
		if(node==null) return;
		output.print(node.key+" ");
		preOrder(node.left,output);
		preOrder(node.right,output);
	}
	
	public static void main(String[] args)
	{
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		AVLTree<Integer,Integer> tree=new AVLTree<Integer,Integer>();
		for(int i=0;input.hasNext();i++)
		{
			Integer s=input.nextInt();
			tree.put(s,i);
		}
		
		output.println("preorder after inserting keys");		
		tree.preOrderTraversal();
		tree.delete(76);
		output.println("preorder after deleting key 76");
		tree.preOrderTraversal();
	}
}