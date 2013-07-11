//2.3.22
//page 306

public class BentleyMcIlroy
{
	public static void sort(Comparable[] items)
	{
		StdRandom.shuffle(items);
		sort(items,0,items.length-1);
	}
	
	private static void sort(Comparable[] items,int left,int right)
	{
		if(left>=right)
			return;
		Pair<Integer,Integer> pivot=partition(items,left,right);
		sort(items,left,pivot.x);
		sort(items,pivot.y,right);
	}
	
	private static class Pair<X,Y>
	{
		public final X x;
		public final Y y;
		public Pair(X x,Y y)
		{
			this.x=x;
			this.y=y;
		}
	}
	
	private static Pair<Integer,Integer> partition(Comparable[] items,int left,int right)
	{
		Comparable pivot=items[left];
		int i=left;
		int j=right+1;
		int p=left;
		int q=right+1;
		
		while(true)
		{
			while(items[++i].compareTo(pivot)<=0)
			{
				if(items[i].compareTo(pivot)==0)
				{
					p++;
					Comparable temp=items[p];
					items[p]=items[i];
					items[i]=temp;
				}
				if(i==right)
					break;
			}
			
			while(items[--j].compareTo(pivot)>=0)
			{
				if(items[j].compareTo(pivot)==0)
				{
					q--;
					Comparable temp=items[q];
					items[q]=items[j];
					items[j]=temp;
				}
				if(j==left)
					break;
			}
		
			if(i>=j)
				break;
				
			Comparable temp=items[i];
			items[i]=items[j];
			items[j]=temp;
		}
		
		//structure: [left p][p+1 j][j+1 q-1][q right]
		//				 =     <        >         =
		if(p>q)
			p=q;
		Comparable[] temps=new Comparable[p-left+1];
		for(int k=left;k<=p;k++)
			temps[k-left]=items[k];
		for(int k=p+1;k<=j;k++)
			items[k-p-1+left]=items[k];
		for(int k=0;k<=p-left;k++)
			items[k+j-p+left]=temps[k];
			
		temps=new Comparable[right-q+1];
		for(int k=q;k<=right;k++)
			temps[k-q]=items[k];
		for(int k=q-1;k>=j+1;k--)
			items[right-q+1+k]=items[k];
		for(int k=0;k<=right-q;k++)
			items[j+1+k]=temps[k];
		
		return new Pair(j-p-1+left,right-q+j+2);
	}
	
	public static void main(String[] args)
	{
		String[] a=In.readStrings();
		sort(a);
		for(int i=0;i<a.length;i++)
			StdOut.print(a[i]+" ");
	}
}