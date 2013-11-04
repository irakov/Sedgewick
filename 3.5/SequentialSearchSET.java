//3.5.2 (page 507)

public class SequentialSearchSET<Key>
{
	private class Node
	{
		private Key key;
		private Node next;
		
		public Node(Key key, Node node)
		{
			this.key=key;
			this.node=node;
		}
	}
	
	private Node first;
	
	private int size=0;
	
	public void put(Key key)
	{
		first=new Node(key,first);
		size++;
	}
	
	public Key get(Key key)
	{
		for(Node node=first;node!=null;node=node.next)
		{
			if(node.key.equals(key)) return key;
		}
		
		return null;
	}

	public void delete(Key key)
	{
		first=delete(key,first);
	}

	private Node delete(Key key,Node node)
	{
		if(node==null) return null;

		if(node.key.equals(key))
		{
			size--;
			return node.next;
		}
		node.next=delete(key,node.next);
		return node;
	}

	public boolean contains(Key key)
	{
		return get(key)!=null;
	}

	public boolean isEmpty()
	{
		return size==0;
	}

	public int size()
	{
		return size;
	}
	
}
