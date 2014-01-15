//page 570 (algorithm 4.4)

import java.io.*;

public class DirectedDFS
{	
	private boolean[] marked;

	public DirectedDFS(Digraph g,int s)
	{
		marked=new boolean[g.V()];
		dfs(g,s);
	}
	
	public DirectedDFS(Digraph g,Iterable<Integer> sources)
	{
		marked=new boolean[g.V()];
		for(int s:sources)
			if(!marked[s])
				dfs(g,s);
	}
	
	private void dfs(Digraph g,int s)
	{
		marked[s]=true;
		for(int i:g.adj(s))
			if(!marked[i])
				dfs(g,i);
	}
	
	public boolean marked(int v)
	{
		return marked[v];
	}
	
	public static void main(String[] args)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		String fileName=args[0];
		int source=Integer.parseInt(args[1]);
		Digraph g=new Digraph(fileName);
		DirectedDFS dfs=new DirectedDFS(g,source);
		
		for(int i=0;i<g.V();i++)
			output.println("is "+i+" reachable from "+source+":"+dfs.marked(i));
	}
}