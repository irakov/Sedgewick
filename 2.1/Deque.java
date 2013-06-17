//1.3.33
import java.util.Iterator;

public class Deque<Item extends Comparable<Item>> implements Iterable<Item>
{
	private LinkedList<Item> list=new LinkedList<Item>();
	
	public boolean isEmpty(){return list.size()==0;}
	
	public int size(){return list.size();}
	
	public void pushLeft(Item item){list.insertFirst(item);}
	
	public void pushRight(Item item){list.insertLast(item);}
	
	public Item popLeft(){return list.removeFirst();}
	
	public Item popRight(){return list.removeLast();}
	
	public Iterator<Item> iterator()
	{
		return new LinkedListIterator<Item>(list);
	}
	
	public static void main(String[] args)
	{
		Deque<String> deque=new Deque<String>();
		while(!StdIn.isEmpty())
		{
			String s=StdIn.readString();
			if(s.substring(0,2).equals(">|"))
				deque.pushLeft(s.substring(2,s.length()));
			else if(s.substring(0,2).equals("<|"))
				StdOut.print(deque.popLeft()+" ");
			else if(s.substring(0,2).equals("|<"))
				deque.pushRight(s.substring(2,s.length()));
			else if(s.substring(0,2).equals("|>"))
				StdOut.print(deque.popRight()+" ");
		}
		
		StdOut.println("("+deque.size()+" items left in deque)");
		for(String s:deque)
			StdOut.print(s+" ");
	}
}