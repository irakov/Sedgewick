//1.5.11
//page 236

public class WeightedQuickFind
{
	private int[] id;
	private int[] size;
	private int count;
	
	public WeightedQuickFind(int n)
	{
		count=n;
		id=new int[count];
		size=new int[count];
		for(int i=0;i<count;i++)
		{
			id[i]=i;
			size[i]=1;
		}
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
		if(size[idP]<size[idQ])
		{
			for(int i=0;i<id.length;i++)
				if(id[i]==idP)
					id[i]=idQ;
			size[idQ]+=size[idP];
		}
		else
		{
			for(int i=0;i<id.length;i++)
				if(id[i]==idQ)
					id[i]=idP;
			size[idP]+=size[idQ];
		}
		count--;
	}
	
	public static void main(String[] args)
	{
		int size=StdIn.readInt();
		WeightedQuickFind wqf=new WeightedQuickFind(size);
		while(!StdIn.isEmpty())
		{
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(wqf.connected(p,q))
				continue;
			wqf.union(p,q);
			StdOut.println(p+" "+q);
		}
		
		StdOut.println("There are "+wqf.count()+" components");
	}
}