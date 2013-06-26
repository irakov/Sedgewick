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
		//de adaugat si previous
		boolean isSorted=false;
		Node left=first;
		Node right=last;
		Node leftPos=first;
		Node rightPos=leftPos.next;
		
		while(!isSorted)
		{
			isSorted=true;
			left=first;
			
			while(left!=right&&left!=null)
			{
				leftPos=left;
				while(leftPos!=null&&leftPos!=right&&leftPos.item.compareTo(leftPos.next.item)<0)
					leftPos=leftPos.next;
				rightPos=leftPos.next;
				while(rightPos!=null&&rightPos!=right&&rightPos.item.compareTo(rightPos.next.item)<0)
					rightPos=rightPos.next;
				if(rightPos!=null)
				{
					//merge(left,leftPos,rightPos);
					isSorted=false;
					left=rightPos.next;
				}
				else
					left=null;
			}
		}
	}
	
	// public void sort()
	// {
		// boolean isSorted=false;
		// Node preLeftStart=null;
		// Node leftStart=first;
		// Node leftEnd=first;
		// Node rightStart=null;
		// Node rightEnd=null;
		
		// while(!isSorted)
		// {
			// isSorted=true;
			// while(leftStart!=null)
			// {
				// leftEnd=leftStart;
				// while(leftEnd!=null&&leftEnd.next!=null&&leftEnd.item.compareTo(leftEnd.next.item)<0)
					// leftEnd=leftEnd.next;
				// rightStart=leftEnd.next;
				// rightEnd=rightStart;
				// while(rightEnd!=null&&rightEnd.next!=null&&rightEnd.item.compareTo(rightEnd.next.item)<0)
					// rightEnd=rightEnd.next;
				// if(rightEnd!=null)
				// {
					// isSorted=false;
					// merge(preLeftStart,leftStart,leftEnd,rightStart,rightEnd);
					// preLeftStart=rightEnd;
					// leftStart=rightEnd.next;
				// }
				// else
					// {
						// preLeftStart=leftStart;
						// leftStart=null;
					// }
			// }
		// }
	// }
		
	// private void merge(Node preLeftStart,Node leftStart,Node leftEnd,Node rightStart,Node rightEnd)
	// {
		// Node preLeftTemp=preLeftStart;
		// Node leftTemp=leftStart;
		// Node preRightTemp=leftEnd;
		// Node rightTemp=rightStart;
		
		// StdOut.println("merge: "+leftStart.item+" "+leftEnd.item+" "+rightStart.item+" "+rightEnd.item);
		// while(leftTemp!=rightTemp&&rightTemp!=rightEnd.next&&rightTemp!=null)
		// {
			
			// for(Item str:this)
				// StdOut.print(str);
			// StdOut.println("");
			// if(leftTemp.item.compareTo(rightTemp.item)<=0)
			// {
				// preLeftTemp=leftTemp;
				// leftTemp=leftTemp.next;
			// }
			// else
			// {
				// if(preLeftTemp!=null)
					// preLeftTemp.next=rightTemp;
				// else
				// {
					// first=rightTemp;
					// preLeftStart=rightTemp;
					// preLeftTemp=rightTemp;
					// leftStart=rightTemp;
				// }
				// Node temp=rightTemp.next;
				// rightTemp.next=leftTemp;
				// preRightTemp.next=temp;
				// rightTemp=temp;
			// }
		// }
	// }
	
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