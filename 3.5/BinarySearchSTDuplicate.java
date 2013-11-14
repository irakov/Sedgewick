//3.5.9(page 507)

import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class BinarySearchSTDuplicate<Key extends Comparable<Key>,Value>
{
	private Key[] keys;
	private Value[] values;
	private int size;

	public BinarySearchSTDuplicate(int capacity)
	{
		keys=(Key[])new Comparable[capacity];
		values=(Value[])new Object[capacity];
	}

	public void put(Key key,Value value)
	{
		if(value==null) delete(key);
		else
		{
			if(max()==null||key.compareTo(max())>0)
			{
				if(keys.length==size) resize(2*size);
				keys[size]=key;
				values[size]=value;
				size++;
			}
			else
			{
				int rank=rank(key);
					
				if(keys.length==size) resize(2*size);
				for(int i=size;i>rank;i--)
				{
					keys[i]=keys[i-1];
					values[i]=values[i-1];
				}
				keys[rank]=key;
				values[rank]=value;
				size++;
			}
		}
	}
	
	public Value get(Key key)
	{
		if(isEmpty()) return null;
		
		int rank=rank(key);
		if(rank<size&&keys[rank].compareTo(key)==0)
			return values[rank];
		return null;
	}
	
	public void delete(Key key)
	{
		if(isEmpty()) return;
		
		boolean keyFound=false;
		
		do
		{
			int rank=rank(key);
			if(rank<size&&keys[rank].compareTo(key)==0)
			{
				keyFound=true;
				for(int i=rank;i<size-1;i++)
				{
					keys[i]=keys[i+1];
					values[i]=values[i+1];
				}
				keys[size-1]=null;
				values[size-1]=null;
				size--;
				if(keys.length<=size/4)
					resize(size/2);
			}
			else
				keyFound=false;
		}
		while(keyFound==true);
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
	
	public Key min()
	{
		if(isEmpty()) return null;
		return keys[0];
	}
	
	public Key max()
	{
		if(isEmpty()) return null;
		return keys[size-1];
	}
	
	public Key floor(Key key)
	{
		if(isEmpty()) return null;
		
		int rank=rank(key);
		if(rank<size&&keys[rank].compareTo(key)==0)
		{
			if(rank==0) return null;
			return keys[rank-1];
		}
		return null;
	}
	
	public Key ceiling(Key key)
	{
		if(isEmpty()) return null;
		
		int rank=rank(key);
		if(rank<size&&keys[rank].compareTo(key)==0)
			return keys[rank];
		return null;
	}
	
	public int rank(Key key)
	{
		int left=0;
		int right=size-1;
		while(left<=right)
		{
			int middle=(right-left)/2+left;
			int comp=keys[middle].compareTo(key);
			if(comp==0) return middle;
			if(comp>0) right=middle-1;
			else left=middle+1;
		}
		return left;
	}
	
	public Key select(int k)
	{
		if(k<0&&k>=size) return null;
		return keys[k];
	}
	
	public void deleteMin()
	{
		if(isEmpty()) throw new NoSuchElementException();
		delete(min());
	}
	
	public void deleteMax()
	{
		if(isEmpty()) throw new NoSuchElementException();
		delete(max());
	}
	
	public int size(Key lo,Key hi)
	{
		if(isEmpty()) return 0;
		if(lo.compareTo(hi)>0) return 0;
		if(contains(hi)) return rank(hi)-rank(lo)+1;
		return rank(hi)-rank(lo);
	}
	
	public Iterable<Key> keys(Key lo,Key hi)
	{
		List<Key> result=new ArrayList<Key>();
		if(lo.compareTo(hi)>0) return result;
		for(int i=rank(lo);i<rank(hi);i++)
			result.add(keys[i]);
		if(contains(hi)) result.add(hi);
		return result;
	}
	
	public Iterable<Key> keys()
	{
		return keys(min(),max());
	}
	
	private void resize(int capacity)
	{
		Key[] newKeys=(Key[])new Comparable[capacity];
		Value[] newValues=(Value[])new Object[capacity];
		
		for(int i=0;i<size;i++)
		{
			newKeys[i]=keys[i];
			newValues[i]=values[i];
		}
		keys=newKeys;
		values=newValues;
	}
	
	public static void main(String[] args)
	{
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		
		BinarySearchSTDuplicate<String, Integer> bs=new BinarySearchSTDuplicate<String, Integer>(5);
		for(int i=0;input.hasNext();i++)
		{
			String s=input.next();
			bs.put(s,i);
		}
		output.println(bs.size());
		for(String s:bs.keys())
			output.println(s+" "+bs.get(s));
			
		output.println("deleting E");
		bs.delete("E".intern());
		for(String s:bs.keys())
			output.println(s+" "+bs.get(s));
	}
}

