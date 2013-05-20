//page 167
//1.3.32
import java.util.Iterator;

public class Steque<Item extends Comparable<Item>> 
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
	
	public void enqueue(Item item)
	{
		list.insertLast(item);
	}
	
	public Iterator<Item> iterator()
	{
		return new LinkedListIterator<Item>(list);
	}
	
	public static void main(String[] args)
	{
		Steque<String> s=new Steque<String>();

		while(!StdIn.isEmpty())
		{
			String item=StdIn.readString();
			if(item.substring(0,1).equals("+"))
				s.enqueue(item.substring(1,item.length()));
			else if(!item.equals("-"))
				s.push(item);
			else if(!s.isEmpty())
				StdOut.print(s.pop()+" ");
		}
		
		StdOut.println("("+s.size()+" left on steque)");
	}
}