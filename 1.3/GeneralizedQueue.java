//1.3.38
//page 169

import java.util.Iterator;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class GeneralizedQueue<Item extends Comparable<Item>> implements Iterable<Item>
{
	private LinkedList<Item> list=new LinkedList<Item>();
	
	public boolean isEmpty(){return list.size()==0;}
	
	public void insert(Item item)
	{
		list.insertLast(item);
	}
	
	public Item delete(int k)
	{
		return list.remove(k);
	}
	
	public Iterator<Item> iterator()
	{
		return new LinkedListIterator<Item>(list);
	}
	
	public static void main(String[] args)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		GeneralizedQueue<String> queue=new GeneralizedQueue<String>();

		queue.insert("1");queue.insert("2");queue.insert("3");
		output.println(queue.delete(1));
		queue.insert("4");queue.insert("5");
		queue.insert("6");queue.insert("7");
		output.println(queue.delete(4));
		queue.insert("8");queue.insert("9");queue.insert("10");
		output.println(queue.delete(6));
		for(String st:queue)
			output.print(st+" ");
		output.println();
	}
}