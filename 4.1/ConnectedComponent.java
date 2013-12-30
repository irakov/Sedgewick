//algorithm 4.3(page 544)

import java.io.*;

public class ConnectedComponent
{
	private boolean[] marked;
	private int[] id;
	private int count;

	public ConnectedComponent(Graph g)
	{
		marked=new boolean[g.V()];
		id=new int[g.V()];
		for(int i=0;i<g.V();i++)
			if(!marked[i])
			{
				dfs(g,i);
				count++;
			}
	}
	
	private void dfs(Graph g,int v)
	{
		marked[v]=true;
		id[v]=count;
		for(int i:g.adj(v))
			if(!marked[i])
				dfs(g,i);
	}
	
	public boolean isConnected(int v,int w)
	{
		return id[v]==id[w];
	}
	
	public int count()
	{
		return count;
	}
	
	public int id(int v)
	{
		return id[v];
	}
	
	public static void main(String[] args)
	{
		String fileName=args[0];
		Graph g=new Graph(fileName);
		ConnectedComponent cc=new ConnectedComponent(g);
		
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		output.println(cc.count()+" components");
		
		Bag<Integer>[] components=(Bag<Integer>[])new Bag[cc.count()];
		for(int i=0;i<cc.count();i++) components[i]=new Bag<Integer>();
		for(int i=0;i<g.V();i++) components[cc.id(i)].add(i);
		
		for(int i=0;i<cc.count();i++)
		{
			output.println(i+":");
			for(int j:components[i]) output.print(j+" ");
			output.println();
		}
	}
}