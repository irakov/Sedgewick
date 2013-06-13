//1.5.13
//page 237

public class WeightedQuickUnionWithPathCompression
{
	private int[] id;
	private int[] size;
	private int count;
	
	public WeightedQuickUnionWithPathCompression(int n)
	{
		count=n;
		id=new int[count];
		size=new int[n];
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
		int root=p;
		while(root!=id[root])
			root=id[root];
		while(p!=root)
		{
			int temp=id[p];
			id[p]=root;
			p=temp;
		}
		return root;
	}
	
	public void union(int p, int q)
	{
		int idP=find(p);
		int idQ=find(q);
		
		if(idP==idQ) return;
		if(size[idP]<size[idQ])
		{
			id[idP]=idQ;
			size[idQ]+=size[idP];
		}
		else
		{
			id[idQ]=idP;
			size[idP]+=size[idQ];
		}
		count--;
	}
	
	public static void main(String[] args)
	{
		int size=StdIn.readInt();
		WeightedQuickUnionWithPathCompression wqu=new WeightedQuickUnionWithPathCompression(size);
		while(!StdIn.isEmpty())
		{
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(wqu.connected(p,q))
				continue;
			wqu.union(p,q);
			StdOut.println(p+" "+q);
		}
		
		StdOut.println("There are "+wqu.count()+" components");
	}
}