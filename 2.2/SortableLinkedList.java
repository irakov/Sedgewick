//2.2.17
//page 286

import java.util.Iterator;

public class SortableLinkedList<Item extends Comparable<Item>> implements Iterable<Item>
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
		if(first==null)
			return null;
	
		Item item=first.item;
		first=first.next;
		
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
		return new SortableLinkedListIterator<Item>(this);
	}
	
	public void sort()
	{
		boolean isSorted=false;
		Node preLeftStart=null;
		Node leftStart=first;
		Node right=last;
		Node leftEnd=first;
		Node rightEnd=leftEnd.next;
		
		while(!isSorted)
		{
			isSorted=true;
			preLeftStart=null;
			leftStart=first;
			
			while(leftStart!=right&&leftStart!=null)
			{
				leftEnd=leftStart;
				//ceva legat de right?!
				while(leftEnd!=null&&leftEnd.next!=null&&leftEnd.item.compareTo(leftEnd.next.item)<=0)
					leftEnd=leftEnd.next;
				StdOut.println("leftEnd "+leftEnd.item);
				rightEnd=leftEnd.next;
				while(rightEnd!=null&&rightEnd.next!=null&&rightEnd.item.compareTo(rightEnd.next.item)<=0)
					rightEnd=rightEnd.next;
				if(rightEnd!=null)
					StdOut.println("rightEnd "+rightEnd.item);
				if(rightEnd!=null)
				{
					StdOut.println("merge "+rightEnd.item);
					merge(preLeftStart,leftStart,leftEnd,rightEnd);
					isSorted=false;
					preLeftStart=rightEnd;
					leftStart=rightEnd.next;
					if(leftStart==null)
						StdOut.println("isnull");
					else
						StdOut.println("leftStart "+leftStart.item);
				}
				else
				{
					leftStart=null;
					preLeftStart=last;
				}
			}
		}
	}
	
	private void merge(Node preLeftStart,Node leftStart,Node leftEnd,Node rightEnd)
	{
		Node rightStart=leftEnd.next;
		while(leftStart!=null&&rightStart!=null&&rightStart.next!=leftStart)
		{
			StdOut.println("merge");
			for(Item str:this)
				StdOut.print(str);
			StdOut.println("");
			StdOut.println(leftStart.item+" "+rightStart.item);
			StdIn.readString();
			if(leftStart.item.compareTo(rightStart.item)<=0)
			{
				preLeftStart=leftStart;
				leftStart=leftStart.next;
			}
			else
			{
				Node nextRightStart=rightStart.next;
				if(preLeftStart==null)
				{
					first=rightStart;
					preLeftStart=first;
				}
				else
					preLeftStart.next=rightStart;
				rightStart.next=leftStart;
				leftEnd.next=nextRightStart;
				rightStart=nextRightStart;
			}
		}
	}
	
	public class Node
	{
		public Item item;
		public Node next;
	}

	public static void main(String[] args)
	{
		SortableLinkedList<String> list=new SortableLinkedList<String>();
		String[] a=In.readStrings();
		for(String s:a)
			list.insertLast(s);
		list.sort();
		for(String str:list)
			StdOut.print(str);
	}
}