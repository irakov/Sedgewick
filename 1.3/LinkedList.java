//page 142
//with 1.3.19
//with 1.3.20

import java.util.Iterator;

public class LinkedList<Item> implements Iterable<Item>
{
	private Node first;
	private Node last;
	private int size;
	
	public void insertFirst(Item item)
	{
		Node oldFirst=first;
		first=new Node();
		first.item=item;
		first.next=oldFirst;
		if(oldFirst==null)
			last=first;
		size++;
	}
	
	public Item removeFirst()
	{
		Item item=null;
		if(first!=null)
		{
			item=first.item;
			first=first.next;
		}
		if(first==null)
			last=null;
		
		size--;
		
		return item;
	}
	
	public void insertLast(Item item)
	{
		Node oldLast=last;
		last=new Node();
		last.item=item;
		if(oldLast!=null)
			oldLast.next=last;
		else
			first=last;
		size++;
	}
	
	public Item removeLast()
	{
		if(last==null)
			return null;
		Item result=last.item;
		if(first==last)
			first=last=null;
		else
		{
			Node currentNode=first;
			while(currentNode.next!=last)
				currentNode=currentNode.next;
			currentNode.next=null;
			last=currentNode;
		}
		size--;
		
		return result;
	}
	
	public Item delete(int k)
	{
		if(first==null || last==null)
			return null;
	
		if(k==0)
			return removeFirst();
		if(k==size)
			return removeLast();
		if(k>size)
			return null;
		
		Node currentNode=first;
		for(int i=0;i<k-1;i++)
			currentNode=currentNode.next;
		Item result=currentNode.next.item;
		currentNode.next=currentNode.next.next;
		return result;
	}
	
	public Node getFirst()
	{
		return first;
	}
	
	public int size()
	{
		return size;
	}
	
	public Iterator<Item> iterator()
	{
		return new LinkedListIterator<Item>(this);
	}
	
	public class Node
	{
		public Item item;
		public Node next;
	}

	public static void main(String[] args)
	{
		LinkedList<Integer> list=new LinkedList<Integer>();
		list.insertFirst(1);
		list.insertFirst(2);
		list.insertFirst(3);
		StdOut.println("list size "+list.size());
		for(Integer i:list)
		{
			StdOut.println("item "+i);
		}
		StdOut.println("popped first "+list.removeFirst());
		StdOut.println("list size "+list.size());
		StdOut.println("popped first "+list.removeFirst());
		StdOut.println("list size "+list.size());
		StdOut.println("popped first "+list.removeFirst());
		StdOut.println("list size "+list.size());
		list.insertLast(4);
		list.insertLast(5);
		list.insertLast(6);
		list.insertFirst(7);
		list.insertFirst(8);
		list.insertFirst(9);
		list.insertFirst(10);
		StdOut.println("list size "+list.size());
		for(Integer i:list)
		{
			StdOut.println("item "+i);
		}
		StdOut.println("popped last "+list.removeLast());
		StdOut.println("popped last "+list.removeLast());
		StdOut.println("popped first "+list.removeFirst());
		StdOut.println("popped third "+list.delete(2));
		StdOut.println("popped first "+list.delete(0));
		StdOut.println("popped last "+list.delete(1));
		StdOut.println("list size "+list.size());
		for(Integer i:list)
		{
			StdOut.println("item "+i);
		}
	}
}