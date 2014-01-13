//page 568

import java.io.*;
import java.util.*;

public class Digraph
{
	private int vertices;
	private int edges;
	private Bag<Integer>[] adjency;
	
	public Digraph(int V)
	{
		buildGraph(V);
	}
	
	private void buildGraph(int V)
	{
		if(V<0) throw new IllegalArgumentException();
		vertices=v;
		edges=0;
		adjency=(Bag<Integer>[])new Bag[vertices];
		for(int i=0;i<vertices;i++) adjency[i]=new Bag<Integer>();
	}
	
	public Digraph(String fileName)
	{
		File file=new File(fileName);
		if(!file.exists()) throw new IllegalArgumentException();
		
		Scanner fileInput=null;
		try
		{
			fileInput=new Scanner(file);
		}
		catch(IOException ex)
		{
			System.err.println(ex);
			throw new IllegalArgumentException(ex);
		}
		
		int V=fileInput.nextInt();
		buildGraph(V);
		int E=fileInput.nextInt();
		if(E<0) throw new IllegalArgumentException();
		edges=E;
		for(int i=0;i<edges;i++)
		{
			int v=fileInput.nextInt();
			int w=fileInput.nextInt();
			addEdge(v,w);
		}
	}
	
	private void addEdge(Integer v,Integer w)
	{
		
	}
	
	public int V()
	{
		...
	}
	
	public int E()
	{
		...
	}
	
	public void addEdge(int v,int w)
	{
		...
	}
	
	public Iterable<Integer> adj(int v)
	{
		...
	}
	
	public Digraph reverse()
	{
	}
	
	public String toString()
	{
	}
	
	public static void main(String[] args)
	{
	}
}