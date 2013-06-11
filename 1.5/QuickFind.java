//algorithm 1.5
//page 222

public class QuickFind
{
	private int[] id;
	private int count;
	
	public QuickFind(int size)
	{
		count=size;
		id=new int[count];
		for(int i=0;i<count;i++)
			id[i]=i;
	}
	
	public int count()
	{
		return count;
	}
	
	public boolean connected(int p, int q)
	{
		return find(p)==find(q);
	}
	
	public int find(int p)
	{
		return id[p];
	}
	
	public void union(int p, int q)
	{
		int idP=find(p);
		int idQ=find(q);
		
		if(idP==idQ) return;
		for(int i=0;i<id.length;i++)
			if(id[i]==idP)
				id[i]=idQ;
		count--;
	}
	
	public static void main(String[] args)
	{
		int size=StdIn.readInt();
		QuickFind qf=new QuickFind(size);
		while(!StdIn.isEmpty())
		{
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(qf.connected(p,q))
				continue;
			qf.union(p,q);
			StdOut.println(p+" "+q);
		}
		
		StdOut.println("There are "+qf.count()+" components");
	}
}