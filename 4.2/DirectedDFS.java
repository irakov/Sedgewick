//page 570 (algorithm 4.4)

public class DirectedDFS
{	
	private boolean[] marked;

	public DirectedDFS(Digraph g,int s)
	{
		marked=new boolean[g.V()];
		dfs(G,s);
	}
	
	public DirectedDFS(Digraph g,Iterable<Integer> sources)
	{
		marked=new boolean[g.V()];
		for(int s:sources)
			if(!marked[s])
				dfs(G,s);
	}
	
	public boolean marked(int v)
	{
	}
	
	public static void main(String[] args)
	{
	}
}