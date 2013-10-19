//algorithm 3.6(470)
//3.4.19(482)

public class LinearProbingHashST<Key extends Comparable<Key>,Value>
{
	private int keyCount;
	private int tableSize;
	private Key[] keys;
	private Value[] values;
	
	public LinearProbingHashST()
	{
		this(16);
	}
	
	public LinearProbingHashST(int tableSize)
	{
		this.tableSize=tableSize;
		keys=(Key[])new Comparable[tableSize];
		values=(Value[])new Object[tableSize];
	}
	
	private int hash(Key key)
	{
		return (key.hashCode()&0x7fffffff)%tableSize;
	}
	
	private void resize(int newTableSize)
	{
		LinearProbingHashST<Key,Value> temp=new LinearProbingHashST<Key,Value>(newTableSize);
		for(int i=0;i<keys.length;i++)
			if(keys[i]!=null) temp.put(keys[i],values[i]);
		keys=temp.keys;
		values=temp.values;
		tableSize=newTableSize;
	}
	
	public void put(Key key,Value value)
	{
		if(value==null) delete(key);
		if(keyCount>=tableSize/2) resize(tableSize*2);
		
		int i=0;
		for(i=hash(key);keys[i]!=null;i=(i+1)%tableSize)
			if(keys[i].equals(key))
			{
				values[i]=value;
				return;
			}
		keys[i]=key;
		values[i]=value;
		keyCount++;
	}
	
	public Value get(Key key)
	{
		for(int i=hash(key);keys[i]!=null;i=(i+1)%tableSize)
			if(keys[i].equals(key)) return values[i];
		return null;
	}
	
	public void delete(Key key)
	{
		if(!contains(key)) return;
		
		int i=hash(key);
		while(!keys[i].equals(key)) i=(i+1)%tableSize;
		keys[i]=null;
		values[i]=null;
		keyCount --;
		
		i=(i+1)%tableSize;
		while(keys[i]!=null)
		{
			Key tempKey=keys[i];
			Value tempValue=values[i];
			keys[i]=null;
			values[i]=null;
			keyCount--;
		
			put(tempKey,tempValue);
			i=(i+1)%tableSize;
		}
		if(keyCount<=tableSize/8) resize(tableSize/2);
	}
	
	public int size() {return keyCount; }
	
	public boolean isEmpty() {return size()==0;}

	public boolean contains(Key key){return get(key)!=null;}
	
	public Iterable<Key> keys()
	{
		Queue<Key> queue=new Queue<Key>();
		for(int i=0;i<keys.length;i++)
			if(keys[i]!=null) queue.enqueue(keys[i]);
		
		return queue;
	}
	
	public static void main(String[] args)
	{
		LinearProbingHashST<String,Integer> hash=new LinearProbingHashST<String,Integer>();
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