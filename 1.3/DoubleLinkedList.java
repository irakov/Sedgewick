//page 166
//1.3.31

import java.util.Iterator; 

public class DoubleLinkedList<Item extends Comparable<Item>> implements Iterable<Item>
{
	private DoubleLinkedNode first;
	private DoubleLinkedNode last;
	private int size;

	public void insertFirst(Item item)
	{
		DoubleLinkedNode oldFirst=first;
		first=new DoubleLinkedNode();
		first.item=item;
		first.next=oldFirst;
		if(oldFirst==null)
			last=first;
		else
			oldFirst.previous=first;
		size++;
	}
	
	public void insertLast(Item item)
	{
		DoubleLinkedNode oldLast=last;
		last=new DoubleLinkedNode();
		last.item=item;
		last.previous=oldLast;
		if(oldLast==null)
			last=first;
		else
			oldLast.next=last;
		size++;
	}
	
	public Item removeFirst()
	{
		if(first==null)
			return null;
		
		Item item=first.item;
		first=first.next;
		
		if(first==null)
			last=null;
		else
			first.previous=null;
		
		size--;
		
		return item;
	}
	
	public Item removeLast()
	{
		if(last==null)
			return null;
			
		Item item=last.item;
		last=last.previous;
		
		if(last==null)
			first=null;
		else
			last.next=null;
		
		size--;
		
		return item;
	}
	
	public void insertBefore(Item beforeItem,Item item)
	{
		if(first==null||last==null)
			return;
		DoubleLinkedNode currentNode=first;
		while(currentNode!=null)
		{
			if(currentNode.item==beforeItem)
			{
				DoubleLinkedNode newNode=new DoubleLinkedNode();
				newNode.item=item;
				DoubleLinkedNode beforeNode=currentNode.previous;
				newNode.previous=beforeNode;
				if(beforeNode!=null)
				{
					beforeNode.next=newNode;
					first=newNode;
				}
				newNode.next=currentNode;
				currentNode.previous=newNode;
				size++;
				return;
			}
			currentNode=currentNode.next;
		}
	}
	
	public void insertAfter(Item afterItem,Item item)
	{
		if(first==null||last==null)
			return;
		DoubleLinkedNode currentNode=first;
		while(currentNode!=null)
		{	
			if(currentNode.item==afterItem)
			{
				DoubleLinkedNode newNode=new DoubleLinkedNode();
				newNode.item=item;
				
				DoubleLinkedNode afterNode=currentNode.next;
				currentNode.next=newNode;
				newNode.previous=currentNode;
				newNode.next=afterNode;
				if(afterNode!=null)
					afterNode.previous=newNode;
				else
					last=newNode;
				
				size++;
				return;
			}
			currentNode=currentNode.next;
		}
	}
	
	public Item remove(int k)
	{
		if(first==null||last==null)
			return null;
	
		if(k==0)
			return removeFirst();
		if(k==size)
			return removeLast();
		if(k>size)
			return null;
		
		DoubleLinkedNode currentNode=first;
		for(int i=0;i<k-1;i++)
			currentNode=currentNode.next;
		Item result=currentNode.next.item;
		currentNode.next=currentNode.next.next;
		currentNode.next.previous=currentNode;
		
		size--;
		return result;
	}
	
	public DoubleLinkedNode getFirst()
	{
		return first;
	}
	
	public int size()
	{
		return size;
	}
	
	public Iterator<Item> iterator()
	{
		return new DoubleLinkedListIterator<Item>(this);
	}

	public class DoubleLinkedNode
	{
		public Item item;
		public DoubleLinkedNode previous;
		public DoubleLinkedNode next;
	}
	
	public static void main(String[] args)
	{
		DoubleLinkedList<Integer> list=new DoubleLinkedList<Integer>();
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
		for(Integer i:list)
		{
			StdOut.println("item "+i);
		}
		StdOut.println("popped last "+list.removeLast());
		StdOut.println("popped last "+list.removeLast());
		StdOut.println("popped first "+list.removeFirst());
		StdOut.println("popped first "+list.remove(0));
		StdOut.println("popped 2nd "+list.remove(1));
		StdOut.println("list size "+list.size());
		for(Integer i:list)
		{
			StdOut.println("item "+i);
		}
	}
}