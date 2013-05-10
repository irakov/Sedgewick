//page 151
//algorithm 1.3

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>
{
	private LinkedList<Item> list=new LinkedList<Item>();
	
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
		return new LinkedListIterator<Item>(list);
	}
	
	public static void main(String[] args)
	{
		Queue<String> s=new Queue<String>();

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