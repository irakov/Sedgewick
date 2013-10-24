//3.5.1 (507)

public class HashSET<Key extends Comparable<Key>>
{
	private LinearProbingHashST<Key,Integer> hash;
	
	public HashSET()
	{
		hash=new LinearProbingHashST<Key,Integer>();
	}
	
	public void add(Key key)
	{
		hash.put(key,1);
	}
	
	public void delete(Key key)
	{
		hash.delete(key);
	}
	
	public boolean contains(Key key)
	{
		return hash.contains(key);
	}
	
	public boolean isEmpty()
	{
		return hash.isEmpty();
	}
	
	public int size()
	{
		return hash.size();
	}
	
	public String toString()
	{
		StringBuilder builder=new StringBuilder();
		for(Key key:hash.keys())
		{
			builder.append(key);
			builder.append(" ");
		}
		return builder.toString();
	}
	
	public static void main(String[] args)
	{
		HashSET<String> hash=new HashSET<String>();
		while(!StdIn.isEmpty())
			hash.add(StdIn.readString());
			
		hash.delete("S".intern());
		
		StdOut.println(hash.toString());
	}
}
