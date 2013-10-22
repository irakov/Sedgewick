//algorithm 3.5(465)
//3.4.9(480),3.3.418(482),3.4.19(482)
//with resizing

public class SeparateChainingHashST<Key extends Comparable<Key>,Value>
{
	private int keyCount;
	private int capacity;
	private int logCapacity;
	private SequentialSearchST<Key,Value>[] st;
	
	private static final int INITIAL_LOG_CAPACITY=3;
	private static final int INITIAL_CAPACITY=8;
	private static final int[] PRIMES={
		//3,4,5,6,7,8,9,10
		//8,16,32,64,128,256,512,1024... logCapacity-3
		7, 13, 19, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
		32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
		8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
		536870909, 1073741789, 2147483647};
	
	public SeparateChainingHashST()
	{
		this(INITIAL_CAPACITY,INITIAL_LOG_CAPACITY);
	}
	
	public SeparateChainingHashST(int capacity,int logCapacity)
	{
		this.capacity=capacity;
		this.logCapacity=logCapacity;
		st=(SequentialSearchST<Key,Value>[])new SequentialSearchST[capacity];
		for(int i=0;i<st.length;i++)
			st[i]=new SequentialSearchST();
	}
	
	private void resize(int newCapacity)
	{
		int newLogCapacity;
		if(newCapacity<capacity) newLogCapacity=logCapacity-1;
		else newLogCapacity=logCapacity+1;
		
		SeparateChainingHashST<Key,Value> temp=new SeparateChainingHashST<Key,Value>(newCapacity,newLogCapacity);
		for(int i=0;i<capacity;i++)
			for(Key key:st[i].keys())
				temp.put(key,st[i].get(key));
		keyCount=temp.keyCount;
		capacity=newCapacity;
		logCapacity=newLogCapacity;
		st=temp.st;
	}
	
	private int hash(Key key)
	{
		//to ensure all bits are taken into consideration
		return (key.hashCode()&0x7fffffff)%PRIMES[logCapacity-3];
	}
	
	public Value get(Key key)
	{
		return st[hash(key)].get(key);
	}
	
	public void put(Key key,Value value)
	{
		if(value==null) delete(key);
		
		if(keyCount>=10*capacity) resize(2*capacity);
		
		if(!contains(key)) keyCount++;
		st[hash(key)].put(key,value);
	}
	
	public void delete(Key key)
	{
		if(!contains(key)) return;
		st[hash(key)].delete(key);
		keyCount--;
		
		if(keyCount>INITIAL_CAPACITY&&logCapacity>INITIAL_LOG_CAPACITY&&keyCount<=2*capacity) resize(capacity/2);
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