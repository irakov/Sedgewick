//1.3.35
//with 1.3.36
//page 168

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomQueue<Item> implements Iterable<Item>
{
	private Item[] queue=(Item[])new Object[1];
	private int leftIndex,rightIndex;
	
	private void resize(int newSize)
	{
		Item[] newQueue=(Item[])new Object[newSize];
		int j=0;
		for(int i=0;i<queue.length;i++)
		{
			if(queue[i]!=null)
			{
				newQueue[j]=queue[i];
				j++;
			}
		}
		
		queue=newQueue;
		leftIndex=0;
		rightIndex=j;
	}
	
	public boolean isEmpty(){return leftIndex==rightIndex;}
	
	public void enqueue(Item item)
	{
		queue[rightIndex]=item;
		rightIndex++;
		if(queue.length==rightIndex)
			resize(2*queue.length);
	}
	
	public Item dequeue()
	{
		if(isEmpty())
			return null;
		int position=leftIndex+StdRandom.uniform(rightIndex-leftIndex);
		Item result = queue[position];
		queue[position]=queue[leftIndex];
		queue[leftIndex]=null;
		leftIndex++;
		if(rightIndex-leftIndex<=queue.length/4)
			resize(queue.length/2);
		return result;
	}
	
	public Item sample()
	{
		if(isEmpty())
			return null;
		int position=leftIndex+StdRandom.uniform(rightIndex-leftIndex);
		Item result = queue[position];
		queue[position]=queue[leftIndex];
		queue[leftIndex]=result;
		return result;
	}
	
	public Iterator<Item> iterator()
	{
		return new RandomQueueIterator();
	}
	
	public class RandomQueueIterator implements Iterator<Item>
	{
		private Item[] items=queue;
		private int position=leftIndex;
		
		public RandomQueueIterator()
		{
			for(int i=leftIndex;i<rightIndex;i++)
			{
				int j=i+StdRandom.uniform(rightIndex-i);
				Item temp=items[j];
				items[j]=items[i];
				items[i]=temp;
			}
		}
		
		public boolean hasNext()
		{
			return position<rightIndex;
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
	
	public static void main(String[] args) throws EmptyQueueException
	{
		RandomQueue<String> s=new RandomQueue<String>();

		while(!StdIn.isEmpty())
		{
			String item=StdIn.readString();
			if(!item.equals("-"))
				s.enqueue(item);
			else if(!s.isEmpty())
				StdOut.print(s.dequeue()+" ");
		}
		
		StdOut.println("sample from queue:"+s.sample());
		for(String st:s)
			StdOut.print(st+" ");
	}
}