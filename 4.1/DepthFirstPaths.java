//algorithm 4.1(536)

import java.io.*;
import java.util.*;

public class DepthFirstPaths
{
	private boolean[] marked;
	private int[] edgeTo;
	private int source;

	public DepthFirstPaths(Graph g,int s)
	{
		marked=new boolean[g.V()];
		edgeTo=new int[g.V()];
		source=s;
		dfs(g,s);
	}
	
	private void dfs(Graph g,int v)
	{
		marked[v]=true;
		for(int i:g.adj(v))
			if(!marked[i])
			{
				edgeTo[i]=v;
				dfs(g,i);
			}
	}
	
	public boolean hasPathTo(int v)
	{
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v)
	{
		if(!hasPathTo(v)) return null;
		Stack<Integer> path=new Stack<Integer>();
		for(int i=v;i!=source;i=edgeTo[i])
			path.push(i);
		path.push(source);
		return path;
	}
	
	public static void main(String[] args)
	{
		String fileName=args[0];
		int s=Integer.parseInt(args[1]);
		Graph g=new Graph(fileName);
		DepthFirstPaths dfp=new DepthFirstPaths(g,s);
		
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		
		for(int i=0;i<g.V();i++)
		{
			if(dfp.hasPathTo(i))
			{
				output.printf("from %d to %d: ",s,i);
				for(int j:dfp.pathTo(i))
					output.print(j+" ");
				output.println();
			}
		}
	}
}