//1.3.34
//page 167

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.reflect.Array;

public class RandomBag<Item extends Comparable<Item>> implements Iterable<Item>
{
	private LinkedList<Item> list=new LinkedList<Item>();
	
	public boolean isEmpty(){return list.size()==0;}
	
	public int size(){return list.size();}
	
	public void add(Item item){list.insertFirst(item);}
	
	public Iterator<Item> iterator()
	{
		return new RandomBagIterator();
	}
	
	public class RandomBagIterator implements Iterator<Item>
	{
		private Item[] items=(Item[])Array.newInstance(Comparable.class,size());
		private int position=0;
		
		public RandomBagIterator()
		{
			int i=0;
			for(Item item:list)
			{
				items[i]=item;
				i++;
			}
			for(i=0;i<items.length;i++)
			{
				int j=i+StdRandom.uniform(items.length-i);
				Item temp=items[i];
				items[i]=items[j];
				items[j]=temp;
			}
		}
		
		public boolean hasNext()
		{
			return position<items.length;
		}
		
		public Item next()
		{
			if(!hasNext())
				throw new NoSuchElementException();
			Item result=items[position];
			position++;
			return result;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args)
	{
		RandomBag<String> b=new RandomBag<String>();

		while(!StdIn.isEmpty())
			b.add(StdIn.readString());
		
		StdOut.println("("+b.size()+" items in bag)");
		
		for(String s:b)
			StdOut.print(s+" ");
	}
}