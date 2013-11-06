//3.5.3 (page 507)

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWritter;
import java.io.OutputStreamWriter;

public class BinarySearchSET<Key extends Comparable<Key>>
{
	private int size;
	private Key[] keys;

	public BinarySearchSET(int capacity)
	{
		keys=(Key[])new Comparable[capacity];
	}

	public void put(Key key)
	{
		if(max()==null||key.compareTo(max)>0)
		{
			if(keys.length==size) resize(2*size);
			keys[size]=key;
			size++;
		}
		else
		{
			int rank=rank(key);
			if(rank<size&&keys[rank].compareTo(key)==0);
			else
			{
				if(keys.length==size) resize(2*size);
				for(int i=size;i>rank;i--)
					keys[i]=keys[i-1];
				keys[rank]=key;
				size++;
			}
		}
	}

	public Key get(Key key)
	{
		if(isEmpty()) return;

		int rank=rank(key);
		if(rank<size&&keys[rank].compareTo(key)==0) return key;
		return;
	}

	public void delete(Key key)
	{
		if(isEmpty()) return;

		int rank=rank(key);
		if(rank<size&&keys[rank].compareTo(key)==0)
		{
			for(int i=rank;i<size-1;i++)
				keys[i]=keys[i+1];
			keys[size-1]=null;
			size--;
			if(keys.length<=size/4) resize(size/2);
		}
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
			if(rank==0) return null;
			else return keys[rank-1];
		else return null;
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
			if(comp<0) right=middle-1;
			else left=middle+1;
		}
		return left;
	}

	public Key select(int k)
	{
		if(k<0||k>=size) return null;
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
		for(
	}
}
