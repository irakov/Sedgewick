import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedListIterator<Item extends Comparable<Item>> implements Iterator<Item>
{
	private CircularLinkedList<Item>.Node currentNode;
	private CircularLinkedList<Item>.Node lastNode;

	public CircularLinkedListIterator(CircularLinkedList<Item> list)
	{
		lastNode=list.getLast();
		currentNode=list.getFirst();
	}

	public boolean hasNext()
	{
		return currentNode!=null&&currentNode!=lastNode;
	}
	
	public Item next()
	{
		if(currentNode==null||currentNode==lastNode)
			throw new NoSuchElementException();
		Item returnValue=currentNode.item;
		currentNode=currentNode.next;
		return returnValue;
	}
	
	public void remove()
	{
		throw new UnsupportedOperationException();
	}
} 