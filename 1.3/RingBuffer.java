//1.3.39
//page 169
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class RingBuffer<Item> implements Iterable<Item>
{
	private int maxSize,size,first,last;
	private Item[] buffer;
	
	public RingBuffer(int maxSize)
	{
		this.maxSize=maxSize;
		buffer=(Item[])new Object[maxSize];
	}
	
	public boolean isEmpty(){return size==0;}
	
	public boolean isFull(){return size==maxSize;}
	
	public void enqueue(Item item)
	{
		if(isFull())
			throw new RuntimeException("The buffer is full");
		buffer[last]=item;
		last++;
		if(last==maxSize)
			last=0;
		size++;
	}
	
	public Item dequeue()
	{
		if(isEmpty())
			throw new RuntimeException("The buffer is empty");
		Item item=buffer[first];
		first++;
		if(first==maxSize)
			first=0;
		size--;
		return item;
	}
	
	public Iterator<Item> iterator()
	{
		return new RingBufferIterator();
	}
	
	public class RingBufferIterator implements Iterator<Item>
	{
		private int current;
		private boolean iterated;
		public RingBufferIterator()
		{
			current=first;
		}
		
		public boolean hasNext()
		{
			if(iterated)
				return current!=last;
			return true;
		}
		
		public Item next()
		{
			if(!hasNext())
				throw new NoSuchElementException();
			Item result=buffer[current];
			current++;
			if(current==maxSize)
				current=0;
			iterated=true;
			return result;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args)
	{
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		RingBuffer<String> ring=new RingBuffer<String>(5);
		
		while(input.hasNext())
		{
			String item=input.next();
			if(!item.equals("-"))
				ring.enqueue(item);
			else if(!ring.isEmpty())
				output.print(ring.dequeue()+" ");
		}
		output.println();
		for(String st:ring)
			output.print(st+" ");
			
		output.println();
	}
}