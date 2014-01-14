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
		vertices=V;
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
		for(int i=0;i<E;i++)
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
		if(v<0||v>=vertices) throw new IndexOutOfBoundsException();
		if(w<0||w>=vertices) throw new IndexOutOfBoundsException();
		edges++;
		adjency[v].add(w);
	}
	
	public Iterable<Integer> adj(int v)
	{
		if(v<0||v>=vertices) throw new IndexOutOfBoundsException();
		return adjency[v];
	}
	
	public Digraph reverse()
	{
		Digraph reverse=new Digraph(vertices);
		for(int i=0;i<vertices;i++)
			for(int j:adj(i))
				reverse.addEdge(j,i);
		return reverse;
	}
	
	public String toString()
	{
		StringBuilder sb=new StringBuilder();
		sb.append(vertices+" vertices "+edges+" edges"+System.getProperty("line.separator"));
		for(int i=0;i<vertices;i++)
		{
			sb.append(i+": ");
			for(int j:adj(i)) sb.append(j+" ");
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
	
	public static void main(String[] args)
	{
		Digraph g=new Digraph("test.txt");
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		output.println(g);
		output.println("<<<reverse>>>");
		output.println(g.reverse());
	}
}