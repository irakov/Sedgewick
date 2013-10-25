//page 133
//with 1.3.1

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class FixedCapacityStackOfStrings
{
	private String[] stack;
	private int size;
	
	public FixedCapacityStackOfStrings(int capacity)
	{
		stack=new String[capacity];
	}
	
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public boolean isFull()
	{
		return size==stack.length;
	}
	
	public int size()
	{
		return size;
	}
	
	public void push(String s) throws FullStackException
	{
		if(isFull())
			throw new FullStackException();
		stack[size]=s;
		size++;
	}
	
	public String pop() throws EmptyStackException
	{
		if(isEmpty())
			throw new EmptyStackException();
		size--;
		return stack[size];
	}
	
	public class EmptyStackException extends Exception
	{
		private static final long serialVersionUID=42L;
	
		public EmptyStackException(){ super();}		
		public EmptyStackException(String message){ super(message);}
		public EmptyStackException(String message, Throwable cause){ super(message,cause);}
		public EmptyStackException(Throwable cause){ super(cause);}
	}
	
	public class FullStackException extends Exception
	{
		private static final long serialVersionUID=42L;
	
		public FullStackException(){ super();}
		public FullStackException(String message){ super(message);}
		public FullStackException(String message, Throwable cause){ super(message,cause);}
		public FullStackException(Throwable cause){ super(cause);}
	}
	
	public static void main(String[] args) throws FullStackException, EmptyStackException
	{
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		FixedCapacityStackOfStrings stack=new FixedCapacityStackOfStrings(100);
		while(input.hasNext())
		{
			String s=input.next();
			if(!s.equals("-"))
				stack.push(s);
			else
				if(!stack.isEmpty())
					output.println(stack.pop());
		}
		output.println("stack contains "+stack.size()+" items");
	}
}