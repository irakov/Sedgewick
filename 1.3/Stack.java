//page 149
//algorithm 1.2

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>
{
	private LinkedList<Item> list=new LinkedList<Item>();
	
	public boolean isEmpty(){return list.size() == 0;}
	
	public int size(){return list.size();}
	
	public void push(Item item)
	{
		list.insertFirst(item);
	}
	
	public Item pop()
	{
		return list.removeFirst();
	}
	
	public Iterator<Item> iterator()
	{
		return new LinkedListIterator<Item>(list);
	}
	
	public static void main(String[] args)
	{
		Stack<String> s=new Stack<String>();

		while(!StdIn.isEmpty())
		{
			String item=StdIn.readString();
			if(!item.equals("-"))
				s.push(item);
			else if(!s.isEmpty())
				StdOut.print(s.pop()+" ");
		}
		
		StdOut.println("("+s.size()+" left on stack)");
	}
}