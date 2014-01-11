//4.1.36(page 562)

public class Brigde
{
	Queue<Pair> bridges;
	private int[] order;
	private int[] lowest;
	private int counter;

	public Bridge(Graph g)
	{
		bridges=new Queue<Pair>();
		order=new int[g.V()];
		lowest=new int[g.V()];
		
		for(int i=0;i<g.V();i++)
		{	
			order[i]=-1;
			lowest[i]=-1;
		}
		
		for(int i=0;i<g.V();i++)
			if(order[i]==-1)
				dfs(g,i,i);
	}
	
	private void dfs(Graph g,int u,int v)
	{
		order[v]=counter++;
		lowest[v]=order[v];
		
		for (int w:g.adj(v))
		{
			if(order[w]==-1)
			{
				dfs(g,v,w);
				lowest[v]=Math.Min(lowest[v],lowest[w]);
				if(lowest[w]=order[w])
					bridges.enqueue(new Pair(v,w));
			}
			else
			if(w!=u)
				lowest[v]=Math.Min(lowest[v],order[w]);
		}
	}
	
	public Iterable<Pair> bridges()
	{
	
	}
	
	public class Pair
	{
		private int first;
		private int second;
		
		public Pair(int first,int second)
		{
			this.first=first;
			this.second=second;
		}
		
		public int getFirst(){return first;}
		public int getSecond(){return second;}
	}
	
	public static void main(String[] args)
	{
	}
}