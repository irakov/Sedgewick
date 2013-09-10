//algorithm 3.1
//page 375

import java.util.ArrayList;
import java.util.List;

public class SequentialSearchST<Key,Value>
{
	private class Node
	{
		private Key key;
		private Value value;
		private Node next;
		
		public Node(Key key,Value value,Node next)
		{
			this.key=key;
			this.value=value;
			this.next=next;
		}
	}
	
	private Node first;
	
	private int size=0;

	public void put(Key key,Value value)
	{
		if(value==null)
		{
			delete(key);
			return;
		}
		for(Node node=first;node!=null;node=node.next)
		{
			if(node.key.equals(key))
			{
				node.value=value;
				return;
			}
		}
		
		first=new Node(key,value,first);
		size++;
	}
	
	public Value get(Key key)
	{
		for(Node node=first;node!=null;node=node.next)
		{
			if(node.key.equals(key))
				return node.value;
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
	
	public Iterable<Key> keys()
	{
		List<Key> keys=new ArrayList<Key>();
		for(Node node=first;node!=null;node=node.next)
			keys.add(node.key);
		return keys;
	}
	
	public static void main(String[] args)
	{
		SequentialSearchST<String,Integer> st=new SequentialSearchST<String,Integer>();
		for(int i=0;!StdIn.isEmpty();i++)
		{
			String key=StdIn.readString();
			st.put(key,i);
		}
		for(String s:st.keys())
			StdOut.println(s+" "+st.get(s));
	}
}