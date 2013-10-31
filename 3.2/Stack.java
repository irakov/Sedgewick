//page 149
//algorithm 1.2
//with 1.3.7.

import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class Stack<Item extends Comparable<Item>> implements Iterable<Item>
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
	
	public Item peek()
	{
		if(!isEmpty())
			return list.getFirst().item;
		return null;
	}
	
	public Iterator<Item> iterator()
	{
		return new LinkedListIterator<Item>(list);
	}
	
	public static void main(String[] args)
	{
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		Stack<String> s=new Stack<String>();

		while(input.hasNext())
		{
			String item=input.next();
			if(!item.equals("-"))
				s.push(item);
			else if(!s.isEmpty())
				output.print(s.pop()+" ");
		}
		
		output.println("("+s.size()+" left on stack)");
		if(!s.isEmpty())
			output.println("peeking top item: "+s.peek());
	}
}