//page 133
//with 1.3.1

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
		public EmptyStackException(){ super();}		
		public EmptyStackException(String message){ super(message);}
		public EmptyStackException(String message, Throwable cause){ super(message,cause);}
		public EmptyStackException(Throwable cause){ super(cause);}
	}
	
	public class FullStackException extends Exception
	{
		public FullStackException(){ super();}
		public FullStackException(String message){ super(message);}
		public FullStackException(String message, Throwable cause){ super(message,cause);}
		public FullStackException(Throwable cause){ super(cause);}
	}
	
	public static void main(String[] args) throws FullStackException, EmptyStackException
	{
		FixedCapacityStackOfStrings stack=new FixedCapacityStackOfStrings(100);
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
	}
}