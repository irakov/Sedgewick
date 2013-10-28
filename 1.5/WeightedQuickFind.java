//1.5.11
//page 236

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

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
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		int size=input.nextInt();
		WeightedQuickFind wqf=new WeightedQuickFind(size);
		while(input.hasNext())
		{
			int p=input.nextInt();
			int q=input.nextInt();
			if(wqf.connected(p,q))
				continue;
			wqf.union(p,q);
			output.println(p+" "+q);
		}
		
		output.println("There are "+wqf.count()+" components");
	}
}