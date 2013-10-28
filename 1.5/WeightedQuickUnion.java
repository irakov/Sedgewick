//algorithm 1.5
//page 228

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class WeightedQuickUnion
{
	private int[] id;
	private int[] size;
	private int count;
	
	public WeightedQuickUnion(int n)
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
		while(p!=id[p])
			p=id[p];
		return p;
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
		WeightedQuickUnion wqu=new WeightedQuickUnion(size);
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