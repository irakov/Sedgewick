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
		Node left=first;
		Node right=last;
		Node leftPos=first;
		Node preLeft=null;
		Node rightPos=leftPos.next;
		
		while(!isSorted)
		{
			isSorted=true;
			left=first;
			
			while(left!=null&&left.next!=null)
			{
				leftPos=left;
				while(leftPos.next!=null&&leftPos.item.compareTo(leftPos.next.item)<=0)
					leftPos=leftPos.next;
				rightPos=leftPos.next;
				while(rightPos!=null&&rightPos.next!=null&&rightPos.item.compareTo(rightPos.next.item)<=0)
					rightPos=rightPos.next;
				if(rightPos!=null)
				{
					merge(preLeft,left,leftPos,rightPos);
					isSorted=false;
					preLeft=rightPos;
					left=rightPos.next;
				}
				else
				{
					preLeft=null;
					left=null;
				}
			}
		}
	}
	
	private void merge(Node preLeftStart,Node leftStart,Node leftEnd,Node rightEnd)
	{
		Node rightStart=leftEnd.next;
		while(leftStart!=null&&rightStart!=null&&rightStart.next!=leftStart)
		{
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
				preLeftStart=rightStart;
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