//algorithm 1.5
//page 224

import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class QuickUnion
{
	private int[] id;
	private int count;
	
	public QuickUnion(int size)
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
		while(p!=id[p])
			p=id[p];
		return p;
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
		Scanner input=new Scanner(new BufferedInputStream(System.in));
		PrintWriter output=new PrintWriter(new OutputStreamWriter(System.out),true);
	
		int size=input.nextInt();
		QuickUnion qu=new QuickUnion(size);
		while(input.hasNext())
		{
			int p=input.nextInt();
			int q=input.nextInt();
			if(qu.connected(p,q))
				continue;
			qu.union(p,q);
			output.println(p+" "+q);
		}
		
		output.println("There are "+qu.count()+" components");
	}
}