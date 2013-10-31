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
}
