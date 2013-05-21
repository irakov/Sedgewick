import java.util.Iterator;

public class StackWithCircularLinkedList<Item extends Comparable<Item>> implements Iterable<Item>
{
	private CircularLinkedList<Item> list=new CircularLinkedList<Item>();
	
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
		return new CircularLinkedListIterator<Item>(list);
	}
	
	public static void main(String[] args)
	{
		StackWithCircularLinkedList<String> s=new StackWithCircularLinkedList<String>();

		while(!StdIn.isEmpty())
		{
			String item=StdIn.readString();
			if(!item.equals("-"))
				s.push(item);
			else if(!s.isEmpty())
				StdOut.print(s.pop()+" ");
		}
		
		StdOut.println("("+s.size()+" left on stack)");
		if(!s.isEmpty())
			StdOut.println("peeking top item: "+s.peek());
	}
}