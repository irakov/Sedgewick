//page 526
//with 4.1.3+4.1.4+4.1.5(558)

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
	
	public Graph(Graph g)
	{
		if(Cycle.detectSelfLoops(g)) throw new IllegalArgumentException();
		if(Cycle.detectParallelEdges(g)) throw new IllegalArgumentException();
		
		this.vertices=g.vertices;
		this.edges=g.edges;
		
		Stack<Integer> stack=null;
		for(int i=0;i<g.V();i++)
		{
			stack=new Stack<Integer>();
			for(int j:g.adj(i))
				stack.push(j);
			for(int k:stack)
				adjency[i].add(k);
		}
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
		
		if(Cycle.detectSelfLoops(this)) throw new IllegalArgumentException();
		if(Cycle.detectParallelEdges(this)) throw new IllegalArgumentException();
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
	
	public boolean hasEdge(int v,int w)
	{
		if(v<0||v>=vertices) throw new IndexOutOfBoundsException();
		if(w<0||w>=vertices) throw new IndexOutOfBoundsException();
		for(int i:this.adj(v))
			if(i==w) return true;
		return false;
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