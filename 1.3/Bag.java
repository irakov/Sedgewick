//page 155
//algorithm 1.4

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item>
{
	private LinkedList<Item> list=new LinkedList<Item>();
	
	public boolean isEmpty(){return list.size()==0;}
	
	public int size(){return list.size();}
	
	public void add(Item item)
	{
		list.insertFirst(item);
	}
	
	public Iterator<Item> iterator()
	{
		return new LinkedListIterator<Item>(list);
	}
	
	public static void main(String[] args)
	{
		Bag<String> b=new Bag<String>();

		while(!StdIn.isEmpty())
			b.add(StdIn.readString());
		
		StdOut.println("("+b.size()+" items in bag)");
		
		for(String s:b)
			StdOut.print(s+" ");
	}
}