//algorithm 2.5
//page 289+291

public class Quicksort
{
	public static void sort(Comparable[] items)
	{
		StdRandom.shuffle(items);
		sort(items,0,items.length-1);
	}
	
	private static void sort(Comparable[] items,int left, int right)
	{
		if(left<right)
		{
			int i=partition(items,left,right);
			sort(items,left,i-1);
			sort(items,i+1,right);
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
			if(i>=j)
				break;
			Comparable temp=items[i];
			items[i]=items[j];
			items[j]=temp;
		}
		Comparable temp=items[left];
		items[left]=items[j];
		items[j]=temp;
		
		return j;
	}
	
	public static void main(String[] args)
	{
		String[] a=In.readStrings();
		sort(a);
		for(int i=0;i<a.length;i++)
			StdOut.print(a[i]+" ");
	}
}