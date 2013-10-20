//algorithm 3.5(465)
//3.4.19(482)
//with resizing

public class SeparateChainingHashST<Key extends Comparable<Key>,Value>
{
	private int keyCount;
	private int hashSize;
	private SequentialSearchST<Key,Value>[] st;
	
	public SeparateChainingHashST()
	{
		this(4);//some prime number, base for hashing
	}
	
	public SeparateChainingHashST(int hashSize)
	{
		this.hashSize=hashSize;
		st=(SequentialSearchST<Key,Value>[])new SequentialSearchST[hashSize];
		for(int i=0;i<st.length;i++)
			st[i]=new SequentialSearchST();
	}
	
	private void resize(int newSize)
	{
		SeparateChainingHashST<Key,Value> temp=new SeparateChainingHashST<Key,Value>(newSize);
		for(int i=0;i<hashSize;i++)
			for(Key key:st[i].keys())
				temp.put(key,st[i].get(key));
		keyCount=temp.keyCount;
		hashSize=newSize;
		st=temp.st;
	}
	
	private int hash(Key key)
	{
		return (key.hashCode()&0x7fffffff)%hashSize;
	}
	
	public Value get(Key key)
	{
		return st[hash(key)].get(key);
	}
	
	public void put(Key key,Value value)
	{
		if(value==null) delete(key);
		
		if(keyCount>=10*hashSize) resize(2*hashSize);
		
		if(!contains(key)) keyCount++;
		st[hash(key)].put(key,value);
	}
	
	public void delete(Key key)
	{
		if(!contains(key)) return;
		st[hash(key)].delete(key);
		keyCount--;
		
		if(keyCount<=2*hashSize) resize(hashSize/2);
	}
	
	public Iterable<Key> keys()
	{
		Queue<Key> queue=new Queue<Key>();
		for(int i=0;i<st.length;i++)
			for(Key key:st[i].keys())
				queue.enqueue(key);
		return queue;
	}
	
	public int size(){return keyCount;}
	
	public boolean isEmpty(){return size()==0;}
	
	public boolean contains(Key key){return get(key)!=null;}
	
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