//page 142

import java.util.Iterator;
import java.util.NoSuchElementException;

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
	
	public Item getFirst()
	{
		if(first==null)
			return null;
		return first.item;
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
	
	public Item getLast()
	{
		if(last==null)
			return null;
		return last.item;
	}
	
	public int size()
	{
		return size;
	}
	
	public Iterator<Item> iterator()
	{
		return new LinkedListIterator();
	}
	
	private class Node
	{
		public Item item;
		public Node next;
	}
	
	private class LinkedListIterator implements Iterator<Item>
	{
		private Node currentNode=first;
		
		public boolean hasNext()
		{
			return currentNode!=null;
		}
		
		public Item next()
		{
			if(currentNode==null)
				throw new NoSuchElementException();
			Item returnValue=currentNode.item;
			currentNode=currentNode.next;
			return returnValue;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args)
	{
		LinkedList<Integer> list=new LinkedList<Integer>();
		list.insertFirst(1);
		StdOut.println("list size "+list.size());
		list.insertFirst(2);
		StdOut.println("list size "+list.size());
		list.insertFirst(3);
		for(Integer i:list)
		{
			StdOut.println("item "+i);
		}
		StdOut.println("list size "+list.size());
		StdOut.println("popped "+list.removeFirst());
		StdOut.println("list size "+list.size());
		StdOut.println("popped "+list.removeFirst());
		StdOut.println("list size "+list.size());
		StdOut.println("popped "+list.removeFirst());
		StdOut.println("list size "+list.size());
		list.insertLast(4);
		StdOut.println("list size "+list.size());
		list.insertLast(5);
		StdOut.println("list size "+list.size());
		list.insertLast(6);
		StdOut.println("list size "+list.size());
		list.insertFirst(7);
		for(Integer i:list)
		{
			StdOut.println("item "+i);
		}
		StdOut.println("list size "+list.size());
		StdOut.println("popped "+list.removeFirst());
		StdOut.println("list size "+list.size());
		StdOut.println("popped "+list.removeFirst());
		StdOut.println("list size "+list.size());
		StdOut.println("popped "+list.removeFirst());
		StdOut.println("list size "+list.size());
		StdOut.println("popped "+list.removeFirst());
		StdOut.println("list size "+list.size());
	}
}