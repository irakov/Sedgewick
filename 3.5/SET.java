//3.5.1 (507)

public class SET<Key extends Comparable<Key>>
{
	private RedBlackBST<Key,Integer> bst;
	
	public SET()
	{
		bst=new RedBlackBST<Key,Integer>();
	}
	
	public void add(Key key)
	{
		bst.put(key,1);
	}
	
	public void delete(Key key)
	{
		bst.delete(key);
	}
	
	public boolean contains(Key key)
	{
		return bst.contains(key);
	}
	
	public boolean isEmpty()
	{
		return bst.isEmpty();
	}
	
	public int size()
	{
		return bst.size();
	}
	
	public String toString()
	{
		for(Key key:bst.keys())
		{
			StdOut.print(key+" ");
		}
		StdOut.println();
	}
	
	public static void main(String[] args)
	{
		SET<String> set= new Set<String>();
		while(!StdIn.isEmpty())
			set.add(StdIn.readString());
			
		set.delete("S".intern());
		
		StdOu.println(set.toString());
	}
}
