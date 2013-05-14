//page 142
//with 1.3.19
//with 1.3.20
//with 1.3.21
//with 1.3.24
//with 1.3.25
//with 1.3.26

import java.util.Iterator;

public class LinkedList<Item extends Comparable<Item>> implements Iterable<Item>
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
	
	public Item remove(int k)
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
		size--;
		return result;
	}
	
	public boolean find(Item item)
	{
		if(first==null||last==null)
			return false;
		Node currentNode=first;
		while(currentNode!=null)
		{
			if(currentNode.item==item)
				return true;
			currentNode=currentNode.next;
		}
		return false;
	}
	
	public void removeAfter(Item item)
	{
		if(first==null||last==null)
			return;
		Node currentNode=first;
		while(currentNode!=null)
		{
			if(currentNode.item==item)
			{
				if(currentNode!=last)
				{
					if(currentNode.next==last)
						last=currentNode;
					currentNode.next=currentNode.next.next;
					size--;
				}
				return;
			}
			currentNode=currentNode.next;
		}
	}
	
	public void insertAfter(Item afterItem,Item item)
	{
		if(first==null||last==null)
			return;
		Node currentNode=first;
		while(currentNode!=null)
		{
			if(currentNode.item==afterItem)
			{
				Node newNode=new Node();
				newNode.item=item;
				if(currentNode==last)
				{
					currentNode.next=newNode;
					last=newNode;
				}
				else
				{
					Node afterNode=currentNode.next;
					currentNode.next=newNode;
					newNode.next=afterNode;
				}
				size++;
				return;
			}
			currentNode=currentNode.next;
		}
	}
	
	public void removeAll(Item item)
	{
		if(first==null||last==null)
			return;
		Node currentNode=first;
		Node previousNode=null;
		while(currentNode!=null)
		{
			if(currentNode.item==item)
			{
				if(currentNode==first)
				{
					first=currentNode.next;
					size--;
					continue;
				}
				if(currentNode==last)
				{
					previousNode.next=null;
					last=previousNode;
					size--;
					continue;
				}
				previousNode.next=currentNode.next;
				size--;
			}
			else
				previousNode=currentNode;
			currentNode=currentNode.next;
		}
	}
	
	public Item max()
	{
		if(first==null||last==null)
			return null;
		Node currentNode=first;
		Item maxItem=first.item;
		while(currentNode!=null)
		{
			if(currentNode.item.compareTo(maxItem)>0)
				maxItem=currentNode.item;
			currentNode=currentNode.next;
		}
		return maxItem;
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
		list.insertFirst(8);
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
		list.insertLast(8);
		list.insertLast(6);
		list.insertFirst(7);
		list.insertFirst(8);
		list.insertFirst(13);
		list.insertFirst(14);
		list.insertFirst(9);
		list.insertAfter(6,11);
		list.insertAfter(7,12);
		list.insertFirst(10);
		list.insertLast(8);
		StdOut.println("list size "+list.size());
		StdOut.println("finding 9 "+list.find(9));
		StdOut.println("finding 2 "+list.find(2));
		StdOut.println("max item is "+list.max());
		for(Integer i:list)
		{
			StdOut.println("item "+i);
		}
		StdOut.println("popped last "+list.removeLast());
		StdOut.println("popped last "+list.removeLast());
		StdOut.println("popped first "+list.removeFirst());
		StdOut.println("popped after 8 ");
		list.removeAfter(8);
		StdOut.println("popped first "+list.remove(0));
		StdOut.println("popped 2nd "+list.remove(1));
		StdOut.println("list size "+list.size());
		for(Integer i:list)
		{
			StdOut.println("item "+i);
		}
		list.removeAll(8);
		StdOut.println("list size "+list.size());
		for(Integer i:list)
		{
			StdOut.println("item "+i);
		}
	}
}