//4.1.8(page 558)

import java.io.*;

public class UFSearch
{
	private WeightedQuickUnionWithPathCompression uf;
	private int source;
	
	public UFSearch(Graph g,int s)
	{
		uf=new WeightedQuickUnionWithPathCompression(g.V());
		for(int i=0;i<g.V();i++)
			for(int j:g.adj(i))
				uf.union(i,j);
		source=s;
	}
	
	public boolean marked(int v)
	{
		return uf.connected(source,v);
	}
	
	public int count()
	{
		return uf.count(source);
	}
	
	public static void main(String[] args)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		String fileName=args[0];
		Graph g=new Graph(fileName);
		int s=Integer.parseInt(args[1]);
		UFSearch ufs=new UFSearch(g,s);
		for(int v=0;v<g.V();v++)
			if(ufs.marked(v))
				output.print(v+" ");
				
		output.println();
		if(ufs.count()!=g.V()) output.println("not connected");
		else output.println("connected");
	}
}