//page 165
//1.3.29

import java.util.Iterator;

public class CircularQueue<Item extends Comparable<Item>> implements Iterable<Item>
{
	private CircularLinkedList<Item> list=new CircularLinkedList<Item>();
	
	public boolean isEmpty(){return list.size()==0;}
	
	public int size(){return list.size();}
	
	public void enqueue(Item item)
	{
		list.insertLast(item);
	}
	
	public Item dequeue()
	{
		return list.removeFirst();
	}
	
	public Iterator<Item> iterator()
	{
		return new CircularLinkedListIterator<Item>(list);
	}
	
	public static void main(String[] args)
	{
		CircularQueue<String> s=new CircularQueue<String>();

		while(!StdIn.isEmpty())
		{
			String item=StdIn.readString();
			if(!item.equals("-"))
				s.enqueue(item);
			else if(!s.isEmpty())
				StdOut.print(s.dequeue()+" ");
		}
		
		StdOut.println("("+s.size()+" left in queue)");
	}
}