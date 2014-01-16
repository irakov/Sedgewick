//page 573

import java.io.*;

public class BreadthFirstDirectedPaths
{
	private int[] pathTo;
	private boolean[] marked;
	private int source;
	
	public BreadthFirstDirectedPaths(Digraph g,int s)
	{
		source=s;
		pathTo=new int[g.V()];
		marked=new boolean[g.V()];
		bfs(g,s);
	}
	
	private void bfs(Digraph g,int s)
	{
		Queue<Integer> queue=new Queue<Integer>();
		marked[s]=true;
		queue.enqueue(s);
		while(!queue.isEmpty())
		{	
			int i=queue.dequeue();
			for(int j:g.adj(i))
				if(!marked[j])
				{
					pathTo[j]=i;
					marked[j]=true;
					queue.enqueue(j);
				}
		}
	}
	
	public boolean hasPathTo(int v)
	{
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v)
	{
		if(!hasPathTo(v)) return null;
		Stack<Integer> stack=new Stack<Integer>();
		for(int i=v;i!=source;i=pathTo[i])
			stack.push(i);
		stack.push(source);
		return stack;
	}
	
	public static void main(String[] args)
	{
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		
		String fileName=args[0];
		int source=Integer.parseInt(args[1]);
		Digraph g=new Digraph(fileName);
		BreadthFirstDirectedPaths bfdp=new BreadthFirstDirectedPaths(g,source);
		
		for(int i=0;i<g.V();i++)
		{
			if(!bfdp.hasPathTo(i)) output.println(i+" is not connected to "+source);
			else
			{
				for(int j:bfdp.pathTo(i))
					output.print(j+" ");
				output.println();
			}
		}
	}
}