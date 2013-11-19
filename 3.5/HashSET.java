//3.5.1 (507)

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

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
	
	public Iterable<Key> keys()
	{
		return hash.keys();
	}
	
	public static void main(String[] args)
	{
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		HashSET<String> hash=new HashSET<String>();
		while(input.hasNext())
			hash.add(input.next());
			
		hash.delete("S".intern());
		
		output.println(hash.toString());
	}
}
