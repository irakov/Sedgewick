//2.3.20
//page 306
import java.util.Stack;

public class NonRecursiveQuicksort
{
	public static void sort(Comparable[] items)
	{
		Comparable[] result=new Comparable[items.length];
		Stack stack=new Stack();
		StdRandom.shuffle(items);
		stack.push(new Params(0,items.length-1));
		sort(stack,items);
	}
	
	private static void sort(Stack stack,Comparable[] items)
	{
		while(stack.size()!=0)
		{
			Params params=(Params)stack.pop();
			if(params.Right>params.Left)
			{
				int i=partition(items,params.Left,params.Right);
				if(i-params.Left>params.Right-i)
				{
					stack.push(new Params(params.Left,i-1));
					stack.push(new Params(i+1,params.Right));
				}
				else
				{
					stack.push(new Params(i+1,params.Right));
					stack.push(new Params(params.Left,i-1));
				}
			}
		}
	}
	
	private static int partition(Comparable[] items,int left,int right)
	{
		int i=left;
		int j=right+1;
		Comparable pivot=items[left];
		while(true)
		{
			while(items[++i].compareTo(pivot)<0)
				if(i==right)
					break;
			while(items[--j].compareTo(pivot)>0)
				if(j==left)
					break;
			
			if(i>=j) break;
			
			Comparable temp=items[i];
			items[i]=items[j];
			items[j]=temp;
		}
		
		Comparable temp=items[left];
		items[left]=items[j];
		items[j]=temp;
		
		return j;
	}
	
	private static class Params
	{
		public final int Left;
		public final int Right;
	
		public Params(int left,int right)
		{
			Left=left;
			Right=right;
		}
	}
	
	public static void main(String[] args)
	{
		String[] a=In.readStrings();
		sort(a);
		for(int i=0;i<a.length;i++)
			StdOut.print(a[i]+" ");
	}
}