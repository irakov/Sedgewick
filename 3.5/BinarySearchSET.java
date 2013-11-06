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
			if(rank>=size||keys[rank].compareTo(key)!=0)
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
	
	}
}
