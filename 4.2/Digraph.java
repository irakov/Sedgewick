//page 568+569

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
	
	public int V()
	{
		return vertices;
	}
	
	public int E()
	{
		return edges;
	}
	
	public void addEdge(int v,int w)
	{
		...
	}
	
	public Iterable<Integer> adj(int v)
	{
		if(v<0||v>=vertices) throw new IndexOutOfBoundsException();
		if(w<0||w>=vertices) throw new IndexOutOfBoundsException();
		edges++;
		adjency[v].add(w);
	}
	
	public Digraph reverse()
	{
		Graph reverse=
	}
	
	public String toString()
	{
	}
	
	public static void main(String[] args)
	{
	}
}