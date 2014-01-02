//page 547

import java.io.*;

public class TwoColor //same as bipartite
{
	private boolean[] marked;
	private boolean[] color;
	private boolean isBipartite=true;
	
	public TwoColor(Graph g)
	{
		marked=new boolean[g.V()];
		color=new boolean[g.V()];
		for(int i=0;i<g.V();i++)
			if(!marked[i]) dfs(g,i);
	}
	
	private void dfs(Graph g,int v)
	{
		marked[v]=true;
		for(int i:g.adj(v))
			if(!marked[i])
			{
				color[i]=!color[v];
				dfs(g,i);
			}
			else if(color[i]==color[v]) isBipartite=false;
	}
	
	public boolean isTwoColorable()
	{
		return isBipartite;
	}
	
	public static void main(String[] args)
	{
		String fileName=args[0];
		Graph g=new Graph(fileName);
		TwoColor tc=new TwoColor(g);
		
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		if(tc.isTwoColorable()) output.println("is bipartite");
		else output.println("is not bipartite");
	}
}