//page 135

public class FixedCapacityStack<Item>
{
	private Item[] stack;
	private int size;
	
	public FixedCapacityStack(int capacity)
	{
		stack=(Item[])new Object[capacity];
	}
	
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public int size()
	{
		return size;
	}
	
	public void push(Item item) throws FullStackException
	{
		if(size>=stack.length)
			throw new FullStackException();
		stack[size]=item;
		size++;
	}
	
	public Item pop() throws EmptyStackException
	{
		if(size==0)
			throw new EmptyStackException();
		size--;
		return stack[size];
	}
	
	public static void main(String[] args) throws FullStackException, EmptyStackException
	{
		FixedCapacityStack<String> stack=new FixedCapacityStack<String>(100);
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