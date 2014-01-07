//1.5.13(page 237)
//4.1.8(page 558)

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

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
	
	public int count(int v)
	{
		int result=0;
		int i=find(v);
		for(int j=0;j<id.length;j++)
			if(id[j]==i) result++;
		return result;
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
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		int size=input.nextInt();
		WeightedQuickUnionWithPathCompression wqu=new WeightedQuickUnionWithPathCompression(size);
		while(input.hasNext())
		{
			int p=input.nextInt();
			int q=input.nextInt();
			if(wqu.connected(p,q))
				continue;
			wqu.union(p,q);
			output.println(p+" "+q);
		}
		
		output.println("There are "+wqu.count()+" components");
	}
}