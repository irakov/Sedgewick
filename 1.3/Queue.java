//page 151
//algorithm 1.3

import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Queue<Item extends Comparable<Item>> implements Iterable<Item>
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
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		Queue<String> s=new Queue<String>();

		while(input.hasNext())
		{
			String item=input.next();
			if(!item.equals("-"))
				s.enqueue(item);
			else if(!s.isEmpty())
				output.print(s.dequeue()+" ");
		}
		
		output.println("("+s.size()+" left in queue)");
	}
}