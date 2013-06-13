//1.5.12
//page 237

public class QuickUnionWithPathCompression
{
	private int[] id;
	private int count;
	
	public QuickUnionWithPathCompression(int size)
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
		id[idP]=idQ;
		count--;
	}
	
	public static void main(String[] args)
	{
		int size=StdIn.readInt();
		QuickUnionWithPathCompression qu=new QuickUnionWithPathCompression(size);
		while(!StdIn.isEmpty())
		{
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(qu.connected(p,q))
				continue;
			qu.union(p,q);
			StdOut.println(p+" "+q);
		}
		
		StdOut.println("There are "+qu.count()+" components");
	}
}