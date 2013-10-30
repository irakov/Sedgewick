//3.1.2+3.1.22
//page 389+391

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class ArrayST<Key,Value>
{
	private int size;
	private Key[] keys;
	private Value[] values;

	public ArrayST(int capacity)
	{
		keys=(Key[])new Object[capacity];
		values=(Value[])new Object[capacity];
	}

	public void put(Key key,Value value)
	{
		if(key==null) delete(key);
		for(int i=0;i<size;i++)
			if(keys[i].equals(key))
			{
				values[i]=value;
				return;
			}
		if(size==keys.length)
			resize(2*keys.length);
		keys[size]=key;
		values[size]=value;
		size++;
	}
	
	public Value get(Key key)
	{
		if(isEmpty()) return null;
		
		for(int i=0;i<size;i++)
			if(keys[i]==key)
				{
					Value result=values[i];
					moveToFront(i);
					return result;
				}
		return null;
	}
	
	public void delete(Key key)
	{
		if(isEmpty()) return;
		
		for(int i=0;i<size;i++)
		{
			if(keys[i]==key)
			{
				for(int j=size-1;j>i;j--)
				{
					keys[j-1]=keys[j];
					values[j-1]=values[j];
				}
				keys[size-1]=null;
				values[size-1]=null;
				size--;
				
				if(size<=keys.length/4) resize(keys.length/2);
				
				return;
			}
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
	
	public Iterable<Key> keys()
	{
		List<Key> result=new ArrayList<Key>();
		for(int i=0;i<size;i++)
			result.add(keys[i]);
		return result;		
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
	
	private void moveToFront(int position)
	{
		Key key=keys[position];
		Value value=values[position];
		for(int i=position;i>=1;i--)
		{
			keys[i]=keys[i-1];
			values[i]=values[i-1];
		}
		keys[0]=key;
		values[0]=value;
	}
	
	public static void main(String[] args)
	{	
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		ArrayST<String,Integer> st=new ArrayST<String,Integer>(5);
		for(int i=0;input.hasNext();i++)
		{
			String s=input.next();
			st.put(s,i);
		}
		for(String s:st.keys())
			output.println(s+" "+st.get(s));
		for(String s:st.keys())
			output.println(s+" "+st.get(s));
	}
}