//page 526

import java.io.*;
import java.util.*;

public class Graph
{
	private int vertices;
	private int edges;
	private Bag<Integer>[] adjency;
	
	public Graph(int V)
	{
		buildGraph(V);
	}
	
	public Graph(String fileName)
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
		for(int i=0;i<E;i++)
		{
			int v=fileInput.nextInt();
			int w=fileInput.nextInt();
			addEdge(v,w);
		}
	}
	
	private void buildGraph(int V)
	{
		if(V<0) throw new IllegalArgumentException();
		vertices=V;
		edges=0;
		adjency=(Bag<Integer>[])new Bag[V];
		for(int i=0;i<V;i++)
			adjency[i]=new Bag<Integer>();
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
		if(v<0||v>=vertices) throw new IndexOutOfBoundsException();
		if(w<0||w>=vertices) throw new IndexOutOfBoundsException();
		edges++;
		adjency[v].add(w);
		adjency[w].add(v);
	}
	
	public Iterable<Integer> adj(int v)
	{
		if(v<0||v>=vertices) throw new IndexOutOfBoundsException();
		return adjency[v];
	}
	
	public String toString()
	{
		StringBuilder sb=new StringBuilder();
		sb.append(vertices+" vertices "+edges+" edges "+System.getProperty("line.separator"));
		for(int i=0;i<vertices;i++)
		{
			sb.append(i+":");
			for(int j:adjency[i])
				sb.append(j+" ");
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
	
	public static void main(String[] args)
	{
		Graph g=new Graph("test.txt");
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		output.println(g);
	}
}