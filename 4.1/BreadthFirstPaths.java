import java.io.*;

public class BreadthFirstPaths
{
	private boolean[] marked;
	private int[] edgeTo;
	private int source;
	
	public BreadthFirstPaths(Graph g,int s)
	{
		marked=new boolean[g.V()];
		edgeTo=new int[g.V()];
		source=s;
		bfs(g,s);
	}
	
	private void bfs(Graph g,int v)
	{
		marked[v]=true;
		
		Queue<Integer> queue=new Queue<Integer>();
		queue.enqueue(v);
		
		while(!queue.isEmpty())
		{
			int i=queue.dequeue();
			for(int j:g.adj(i))
			{
				if(!marked[j])
				{
					marked[j]=true;
					edgeTo[j]=i;
					queue.enqueue(j);
				}
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
		BreadthFirstPaths bfp=new BreadthFirstPaths(g,s);
		
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		
		for(int i=0;i<g.V();i++)
		{
			if(bfp.hasPathTo(i))
			{
				output.printf("from %d to %d: ",s,i);
				for(int j:bfp.pathTo(i))
					output.print(j+" ");
				output.println();
			}
		}
	}
}