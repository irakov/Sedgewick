//page 141
//algorithm 1.1

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Item> implements Iterable<Item>
{
	private Item[] stack=(Item[])new Object[1];
	private int size;
	
	private void resize(int newSize)
	{
		Item[] newStack=(Item[])new Object[newSize];
		for(int i=0;i<size;i++)
			newStack[i]=stack[i];
		stack=newStack;
	}
	
	public void push(Item item)
	{
		if(size==stack.length)
			resize(2*stack.length);
		stack[size]=item;
		size++;
	}
	
	public Item pop() throws EmptyStackException
	{
		if(size==0)
			throw new EmptyStackException();
		size--;
		Item item=stack[size];
		stack[size]=null;//avoid loitering!
		if(size<=stack.length/4)
			resize(stack.length/2);
		return item;
	}
	
	public boolean isEmpty() {return size==0;}
	
	public int size(){return size;}
	
	public Iterator<Item> iterator()
	{
		return new ArrayStackIterator();
	}
	
	private class ArrayStackIterator implements Iterator<Item>
	{
		private int position=size;
		
		public boolean hasNext() {return position>0;}
		
		public Item next()
		{
			if(!hasNext())
				throw new NoSuchElementException();
			position--;
			return stack[position];
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) throws EmptyStackException
	{
		ResizingArrayStack<String> stack=new ResizingArrayStack<String>();
		while(!StdIn.isEmpty())
		{
			String s=StdIn.readString();
			if(!s.equals("-"))
				stack.push(s);
			else
				if(!stack.isEmpty())
					StdOut.println(stack.pop());
		}
		StdOut.println("stack contains "+stack.size()+" items");
		for(String s:stack)
			StdOut.println(s);
	}
}