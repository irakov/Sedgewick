//page 547
//with 4.1.29 (page 561)

import java.io.*;

public class Cycle
{
	private boolean hasCycle;
	private boolean[] marked;
	
	public Cycle(Graph g)
	{
		detectSelfLoops(g);
		if(hasCycle) return;
		
		detectParallelEdges(g);
		if(hasCycle) return;
		
		marked=new boolean[g.V()];
		for(int i=0;i<g.V();i++)
			if(!marked[i])
				dfs(g,i,i);
	}
	
	private void detectSelfLoops(Graph g)
	{
		for(int i=0;i<g.V();i++)
			for(int j:g.adj(i))
				if(i==j)
				{
					hasCycle=true;
					return;
				}
	}
	
	private void detectParallelEdges(Graph g)
	{
		marked=new boolean[g.V()];
	
		for(int i=0;i<g.V();i++)
		{
			for(int j:g.adj(i))
			{
				if(marked[j])
				{
					hasCycle=true;
					return;
				}
				marked[j]=true;
			}
			for(int j:g.adj(i)) marked[j]=false;
		}
	}

	private void dfs(Graph g,int v,int u)
	{
		marked[v]=true;
		for(int i:g.adj(v))
			if(!marked[i]) dfs(g,i,v);
			else 
				if(i!=u)
				{
					hasCycle=true;
					return;
				}
	}

	public boolean hasCycle()
	{
		return hasCycle;
	}
	
	public static void main(String[] args)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		String fileName=args[0];
		Graph g=new Graph(fileName);
		
		Cycle c=new Cycle(g);
		if(c.hasCycle()) output.println("has cycle");
		else output.println("doesn't have cycle");
	}
}