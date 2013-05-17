import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedListIterator<Item extends Comparable<Item>> implements Iterator<Item>
{
	private DoubleLinkedList<Item>.DoubleLinkedNode currentNode;
	
	public DoubleLinkedListIterator(DoubleLinkedList<Item> list)
	{
		currentNode=list.getFirst();
	}
	
	public boolean hasNext()
	{
		return currentNode!=null;
	}
	
	public Item next()
	{
		if(currentNode==null)
			throw new NoSuchElementException();
		Item result=currentNode.item;
		currentNode=currentNode.next;
		return result;
	}
	
	public void remove()
	{
		throw new UnsupportedOperationException();
	}
}