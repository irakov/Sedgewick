//algorithm 3.5(465)
//3.4.19(482)

public class SeparateChainingHashST<Key extends Comparable<Key>,Value>
{
	private int base;
	private int size;
	private SequentialSearchST<Key,Value>[] st;
	
	public SeparateChainingHashST()
	{
		this(997);//some prime number, base for hashing
	}
	
	public SeparateChainingHashST(int base)
	{
		this.base=base;
		st=(SequentialSearchST<Key,Value>[])new SequentialSearchST[base];
		for(int i=0;i<st.length;i++)
			st[i]=new SequentialSearchST();
	}
	
	private int hash(Key key)
	{
		return (key.hashCode()&0x7fffffff)%base;
	}
	
	public Value get(Key key)
	{
		return st[hash(key)].get(key);
	}
	
	public void put(Key key,Value value)
	{
		st[hash(key)].put(key,value);
	}
	
	public void delete(Key key)
	{
		st[hash(key)].delete(key);
	}
	
	public Iterable<Key> keys()
	{
		Queue<Key> queue=new Queue<Key>();
		for(int i=0;i<st.length;i++)
			for(Key key:st[i].keys())
				queue.enqueue(key);
		return queue;
	}
	
	public static void main(String[] args)
	{
		SeparateChainingHashST<String,Integer> hash=new SeparateChainingHashST<String,Integer>();
		for(int i=0;!StdIn.isEmpty();i++)
		{
			String s=StdIn.readString();
			hash.put(s,i);
		}
		
		for(String s:hash.keys())
			StdOut.println(s+" "+hash.get(s));
			
		StdOut.println("deleting S");
		hash.delete("S".intern());
		for(String s:hash.keys())
			StdOut.println(s+" "+hash.get(s));
	}
}