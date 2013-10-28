//1.5.14
//page 237

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class WeightedQuickUnionByHeight
{
	private int[] id;
	private int[] height;
	private int count;
	
	public WeightedQuickUnionByHeight(int n)
	{
		count=n;
		id=new int[count];
		height=new int[n];
		for(int i=0;i<count;i++)
		{
			id[i]=i;
			height[i]=0;
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
		
		if(height[idP]<height[idQ])
			id[idP]=idQ;
		
		else 
			if(height[idQ]>height[idP])
				id[idQ]=idP;
			else
			{
				id[idQ]=idP;
				height[idP]++;
			}
		count--;
	}
	
	public static void main(String[] args)
	{
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
		
		int height=input.nextInt();
		WeightedQuickUnionByHeight wqu=new WeightedQuickUnionByHeight(height);
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